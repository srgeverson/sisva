/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Ajuda;
import setup.HibernateUtil;
import java.util.List;
import model.Users;
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
public class DaoAjuda {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão ajuda", e);
        }
    }

    public void inserir(Ajuda a) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            sessao.save(a);
            this.transacao.commit();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao salvar ajuda...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public List<Ajuda> listar() {
        List<Ajuda> listaAjuda = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.Ajuda");
            listaAjuda = consulta.list();
            transacao.commit();
            return listaAjuda;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar ajuda...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaAjuda;
    }

    public Ajuda buscar(Integer id) {
        Query resultado;
        Ajuda a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Ajuda where id = :parametro");
            resultado.setInteger("parametro", id);
            a = (Ajuda) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar ajuda...", e);
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
            Ajuda a = (Ajuda) sessao.get(Ajuda.class, id);
            if (a != null) {
                this.sessao.delete(a);
            }
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao remover ajuda...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public void atualizar(Ajuda newAjuda) throws Exception {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            this.sessao.update(newAjuda);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao atualizar ajuda...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public Ajuda consultaPorNome(String username) {
        Query resultado;
        Ajuda a = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Ajuda where user_name = :parametro");
            resultado.setString("parametro", username);
            a = (Ajuda) resultado.uniqueResult();
            this.transacao.commit();
            return a;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar ajuda por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return a;
    }

    public List<Ajuda> buscarAjudas(String descricao) {
        this.sessao = HibernateUtil.getFabrica().openSession();
        this.transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Ajuda.class);
        criteria.add(Restrictions.like("ajudDescricao", descricao + "%"));
        List<Ajuda> list = criteria.list();
        this.transacao.commit();
        return list;
    }
}
