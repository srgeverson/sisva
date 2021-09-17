/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Erro;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import setup.HibernateUtil;
import view.ViewErro;

/**
 *
 * @author geverson
 */
public class DaoErro {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão erro", e);
        }
    }

    public void inserir(Erro e) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            sessao.save(e);
            this.transacao.commit();
        } catch (Exception ex) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao salvar erro...", ex);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public List<Erro> listar() {
        List<Erro> listaErro = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.Erro");
            listaErro = consulta.list();
            transacao.commit();
            return listaErro;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar erro...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaErro;
    }

    public Erro buscar(Integer id) {
        Query resultado;
        Erro e = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Erro where id = :parametro");
            resultado.setInteger("parametro", id);
            e = (Erro) resultado.uniqueResult();
            this.transacao.commit();
            return e;
        } catch (HibernateException ex) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar erro...", ex);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return e;
    }

    public void remover(Integer id) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            Erro e = (Erro) sessao.get(Erro.class, id);
            if (e != null) {
                this.sessao.delete(e);
            }
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao remover erro...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public void atualizar(Erro newErro) throws Exception {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            this.sessao.update(newErro);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao atualizar erro...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public Erro consultaPorDescricao(String descricao) {
        Query resultado;
        Erro e = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Erro where erro_descricao = :parametro");
            resultado.setString("parametro", descricao);
            e = (Erro) resultado.uniqueResult();
            this.transacao.commit();
            return e;
        } catch (HibernateException ex) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao consultar Erro por descrição...", ex);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return e;
    }
}
