/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.LogTeste;
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
public class DaoLogTeste {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão Log Teste...", e);
        }
    }

    public List<LogTeste> listar() {
        List<LogTeste> listaLogTeste = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.LogTeste");
            listaLogTeste = consulta.list();
            transacao.commit();
            return listaLogTeste;
        } catch (HibernateException e) {
        } finally {
            this.fecharSessao();
        }
        return listaLogTeste;
    }

    public LogTeste buscar(Integer id) {
        Query resultado;
        LogTeste lt = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogTeste where id = :parametro");
            resultado.setInteger("parametro", id);
            lt = (LogTeste) resultado.uniqueResult();
            this.transacao.commit();
            return lt;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar log Testes...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return lt;
    }

    public LogTeste consultaPorNome(String username) {
        Query resultado;
        LogTeste lt = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogTeste where user_name = :parametro");
            resultado.setString("parametro", username);
            lt = (LogTeste) resultado.uniqueResult();
            this.transacao.commit();
            return lt;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar as Log Teste por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return lt;
    }

    public Integer retornaPkId(String authority) {
        Query resultado;
        LogTeste lt = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogTeste where auth_name = :parametro");
            resultado.setString("parametro", authority);
            lt = (LogTeste) resultado.uniqueResult();
            this.transacao.commit();
            return lt.getLtestPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Teste por id...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return lt.getLtestPkId();
    }
}
