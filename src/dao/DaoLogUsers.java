/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.LogUsers;
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
public class DaoLogUsers {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar Log User...", e);
        }
    }

    public Integer buscaPorNome(String nome) {
        Query resultado;
        LogUsers u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogUsers where user_name = :parametro");
            resultado.setString("parametro", nome);
            u = (LogUsers) resultado.uniqueResult();
            this.transacao.commit();
            return u.getLuserPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log User por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u.getLuserPkId();
    }

    public List<LogUsers> listar() {
        List<LogUsers> listaLogUsers = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.LogUsers");
            listaLogUsers = consulta.list();
            transacao.commit();
            return listaLogUsers;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar Log User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaLogUsers;
    }

    public LogUsers buscarPorPkId(Integer pkId) {
        Query resultado;
        LogUsers u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from LogUsers where id = :parametro");
            resultado.setInteger("parametro", pkId);
            u = (LogUsers) resultado.uniqueResult();
            this.transacao.commit();
            return u;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log User por id...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u;
    }

    public LogUsers retornaObjeto(String username) {
        Query resultado;
        LogUsers u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogUsers where user_name = :parametro");
            resultado.setString("parametro", username);
            u = (LogUsers) resultado.uniqueResult();
            this.transacao.commit();
            return u;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u;
    }
}
