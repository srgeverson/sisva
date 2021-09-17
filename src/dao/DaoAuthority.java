/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Authority;
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
public class DaoAuthority {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão authority...", e);
        }
    }

    public void inserir(Authority a) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            sessao.save(a);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao salvar authority...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public List<Authority> listar() {
        List<Authority> listaAuthority = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.Authority");
            listaAuthority = consulta.list();
            transacao.commit();
            return listaAuthority;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar authority...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaAuthority;
    }

    public boolean remover(Integer id) {
        boolean b = false;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            Authority a = (Authority) sessao.get(Authority.class, id);
            if (a != null) {
                this.sessao.delete(a);
                b = true;
            }
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao remover authority...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return b;
    }

    public boolean atualizar(Authority newAuthority) {
        boolean b = false;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            this.sessao.update(newAuthority);
            b = true;
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao atualizar authority", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return b;
    }

    public Authority consultaPorNome(String username) {
        Query resultado;
        Authority a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Authority where user_name = :parametro");
            resultado.setString("parametro", username);
            a = (Authority) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar por nome authority...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public Integer consultaPorPkId(String authority) {
        Query resultado;
        Authority a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Authority where auth_name = :parametro");
            resultado.setString("parametro", authority);
            a = (Authority) resultado.uniqueResult();
            this.transacao.commit();
            return a.getAuthPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar por id...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a.getAuthPkId();
    }

    public Boolean verificaAuthority(String authname) {
        Query resultado;
        Authority a = null;
        Boolean verificado = false;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Authority where auth_name = :parametro");
            resultado.setString("parametro", authname);
            a = (Authority) resultado.uniqueResult();
            this.transacao.commit();
            if (a == null) {
                return verificado = true;
            } else {
                return verificado;
            }
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro verificar authority...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return verificado;
    }

}
