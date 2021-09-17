/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.LogAjuda;
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
public class DaoLogAjuda {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão Log Ajuda...", e);
        }
    }

    public List<LogAjuda> listar() {
        List<LogAjuda> listaLogAjuda = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.LogAjuda");
            listaLogAjuda = consulta.list();
            transacao.commit();
            return listaLogAjuda;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar Log Ajuda...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaLogAjuda;
    }

    public LogAjuda buscar(Integer id) {
        Query resultado;
        LogAjuda a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAjuda where id = :parametro");
            resultado.setInteger("parametro", id);
            a = (LogAjuda) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Ajuda...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public LogAjuda consultaPorNome(String username) {
        Query resultado;
        LogAjuda a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAjuda where user_name = :parametro");
            resultado.setString("parametro", username);
            a = (LogAjuda) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar Log Ajuda por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public Integer retornaPkId(String authority) {
        Query resultado;
        LogAjuda a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAjuda where auth_name = :parametro");
            resultado.setString("parametro", authority);
            a = (LogAjuda) resultado.uniqueResult();
            this.transacao.commit();
            return a.getLajudPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Ajuda por id...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a.getLajudPkId();
    }
}
