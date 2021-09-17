/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.LogErro;
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
public class DaoLogErro {

    private Session sessao;
    private Transaction transacao;

    private void fecharSessao() {
        try {
            sessao.close();
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao fechar sessão Log Erro...", e);
        }
    }

    public List<LogErro> listar() {
        List<LogErro> listaLogErro = null;
        Query consulta;
        try {
            this.sessao = HibernateUtil.getFabrica().openSession();
            this.transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from model.LogErro");
            listaLogErro = consulta.list();
            transacao.commit();
            return listaLogErro;
        } catch (HibernateException e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao listar Log Erro...", e);
            this.transacao.rollback();
        } finally {
            this.fecharSessao();
        }
        return listaLogErro;
    }
}
