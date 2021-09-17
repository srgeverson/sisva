/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Acesso;
import setup.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import view.ViewErro;

/**
 *
 * @author administrador
 */
public class DaoAcesso {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão", e);
        }
    }

    public void inserir(Acesso a) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            sessao.save(a);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao salvar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public List<Acesso> listar() {
        List<Acesso> listaAcesso = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.Acesso");
            listaAcesso = consulta.list();
            transacao.commit();
            return listaAcesso;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar acessos", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaAcesso;
    }

    public Acesso buscar(Integer id) {
        Query resultado;
        Acesso a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Acesso where id = :parametro");
            resultado.setInteger("parametro", id);
            a = (Acesso) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public void remover(Integer id) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            Acesso a = (Acesso) sessao.get(Acesso.class, id);
            if (a != null) {
                this.sessao.delete(a);
            }
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao remover dado", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public void atualizar(Acesso newAcesso) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            this.sessao.update(newAcesso);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao atualizar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public Acesso consultaPorNome(String username) {
        Query resultado;
        Acesso a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Acesso where user_name = :parametro");
            resultado.setString("parametro", username);
            a = (Acesso) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public List<Acesso> listar(String hora) {
        this.sessao = HibernateUtil.getFabrica().openSession();
        this.transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Acesso.class);
        criteria.add(Restrictions.like("acesData", hora + "%"));
        List<Acesso> list = criteria.list();
        this.transacao.commit();
        return list;
    }
}
