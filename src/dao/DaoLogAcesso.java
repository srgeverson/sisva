/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.LogAcesso;
import setup.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.ViewErro;

/**
 *
 * @author administrador
 */
public class DaoLogAcesso {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão Log Acesso...", e);
        }
    }

    public List<LogAcesso> listar() {
        List<LogAcesso> listaLogAcesso = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.LogAcesso");
            listaLogAcesso = consulta.list();
            transacao.commit();
            return listaLogAcesso;
        } catch (HibernateException e) {
        } finally {
            this.fecharSessao();
        }
        return listaLogAcesso;
    }

    public LogAcesso buscar(Integer id) {
        Query resultado;
        LogAcesso a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAcesso where id = :parametro");
            resultado.setInteger("parametro", id);
            a = (LogAcesso) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar log Acessos...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public LogAcesso consultaPorNome(String username) {
        Query resultado;
        LogAcesso a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAcesso where user_name = :parametro");
            resultado.setString("parametro", username);
            a = (LogAcesso) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar as Log Acesso por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public Integer retornaPkId(String authority) {
        Query resultado;
        LogAcesso a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAcesso where auth_name = :parametro");
            resultado.setString("parametro", authority);
            a = (LogAcesso) resultado.uniqueResult();
            this.transacao.commit();
            return a.getLacesPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Acesso por id...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a.getLacesPkId();
    }
}
