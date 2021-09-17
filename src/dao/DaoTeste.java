/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Teste;
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
public class DaoTeste {

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

    public void inserir(Teste t) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            sessao.save(t);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao salvar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public List<Teste> listar() {
        List<Teste> listaTeste = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.Teste");
            listaTeste = consulta.list();
            transacao.commit();
            return listaTeste;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar acessos", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaTeste;
    }

    public Teste buscar(Integer id) {
        Query resultado;
        Teste t = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Teste where id = :parametro");
            resultado.setInteger("parametro", id);
            t = (Teste) resultado.uniqueResult();
            this.transacao.commit();
            return t;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return t;
    }

    public void remover(Integer id) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            Teste t = (Teste) sessao.get(Teste.class, id);
            if (t != null) {
                this.sessao.delete(t);
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

    public void atualizar(Teste newTeste) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            this.sessao.update(newTeste);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao atualizar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public Teste consultaPorNome(String username) {
        Query resultado;
        Teste t = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Teste where user_name = :parametro");
            resultado.setString("parametro", username);
            t = (Teste) resultado.uniqueResult();
            this.transacao.commit();
            return t;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar dados", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return t;
    }

    public List<Teste> listar(String hora) {
        this.sessao = HibernateUtil.getFabrica().openSession();
        this.transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Teste.class);
        criteria.add(Restrictions.like("acesData", hora + "%"));
        List<Teste> list = criteria.list();
        this.transacao.commit();
        return list;
    }
}
