/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Users;
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
public class DaoUsers {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão User...", e);
        }
    }

    public Users buscarPorId(Integer id) {
        Query resultado;
        Users u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Users where id = :parametro");
            resultado.setInteger("parametro", id);
            u = (Users) resultado.uniqueResult();
            this.transacao.commit();
            return u;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u;
    }

    public Integer buscaPorUserName(String userName) {
        Query resultado;
        Users u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Users where user_name = :parametro");
            resultado.setString("parametro", userName);
            u = (Users) resultado.uniqueResult();
            this.transacao.commit();
            return u.getUserPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro consultar  usuário por nome...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u.getUserPkId();
    }

    public List<Users> buscarUsers(String nome) {
        this.sessao = HibernateUtil.getFabrica().openSession();
        this.transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Users.class);
        criteria.add(Restrictions.like("userName", nome + "%"));
        List<Users> list = criteria.list();
        this.transacao.commit();
        return list;
    }

    public void inserir(Users u) throws HibernateException {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            sessao.save(u);
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao salvar User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public List<Users> listar() {
        List<Users> listaUsers = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.Users");
            listaUsers = consulta.list();
            transacao.commit();
            return listaUsers;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaUsers;
    }

    public void remover(Integer id) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            Users u = (Users) sessao.get(Users.class, id);
            if (u != null) {
                this.sessao.delete(u);
            }
            this.transacao.commit();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao remover User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public void editar(Users newUsers) {
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            this.sessao.update(newUsers);
            this.transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao editar User..." + e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
    }

    public Users logar(String username) {
        Query resultado;
        Users u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Users where user_name = :parametro");
            resultado.setString("parametro", username);
            u = (Users) resultado.uniqueResult();
            this.transacao.commit();
            return u;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro carregar User...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u;
    }

    public Boolean verificaUsuario(String username) {
        Query resultado;
        Users u = null;
        Boolean verificado = false;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Users where user_name = :parametro");
            resultado.setString("parametro", username);
            u = (Users) resultado.uniqueResult();
            this.transacao.commit();
            if (u == null) {
                return verificado = true;
            } else {
                return verificado;
            }
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro carregar usuário", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return verificado;
    }

    public Integer retornaPkId(String user_name) {
        Query resultado;
        Users u = null;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            resultado = sessao.createQuery("from model.Users where user_name = :parametro");
            resultado.setString("parametro", user_name);
            u = (Users) resultado.uniqueResult();
            this.transacao.commit();
            return u.getUserPkId();
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao buscar codigo do usúario", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return u.getUserPkId();
    }
}
