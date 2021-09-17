/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.LogAuthority;
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
public class DaoLogAuthority {

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

    public Integer buscaPorUserName(String userName) {
        Query resultado;
        LogAuthority u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAuthority where user_name = :parametro");
            resultado.setString("parametro", userName);
            u = (LogAuthority) resultado.uniqueResult();
            this.transacao.commit();
            return u.getLauthPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Ajuda por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u.getLauthPkId();
    }

    public List<LogAuthority> listar() {
        List<LogAuthority> listaLogAuthority = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.LogAuthority");
            listaLogAuthority = consulta.list();
            transacao.commit();
            return listaLogAuthority;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar Log Authority...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaLogAuthority;
    }

    public LogAuthority buscaPorPkId(Integer id) {
        Query resultado;
        LogAuthority u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAuthority where id = :parametro");
            resultado.setInteger("parametro", id);
            u = (LogAuthority) resultado.uniqueResult();
            this.transacao.commit();
            return u;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Authority por id...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u;
    }

    public LogAuthority buscaPorNome(String nome) {
        Query resultado;
        LogAuthority u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.LogAuthority where user_name = :parametro");
            resultado.setString("parametro", nome);
            u = (LogAuthority) resultado.uniqueResult();
            this.transacao.commit();
            return u;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar Log Authority por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u;
    }
}
