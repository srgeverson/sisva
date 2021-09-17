/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoAjuda;
import model.Ajuda;
import dao.DaoUsers;
import java.io.InputStream;
import java.sql.SQLException;
import model.Users;
import setup.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerAjuda {

    private final DaoAjuda daoAjuda;
    private List<Ajuda> listAjuda, listAjudaBuscar;
    private String[] camposAjuda;
    private String[][] dadosAjuda, dadosAjudaBuscar;

    public ControllerAjuda() {
        daoAjuda = new DaoAjuda();
    }

    public void apagar(String id) throws Exception {
        daoAjuda.remover(Integer.valueOf(id));
    }

    public void salvar(ArrayList<String> arrayList) throws Exception {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Ajuda a = new Ajuda();
        DaoUsers du = new DaoUsers();
        DaoAjuda da = new DaoAjuda();
        a.setAjudDescricao(arrayList.get(0));
        a.setAjudStatus(Boolean.valueOf(arrayList.get(1)));
        a.setAjudFkUsersPkId((Users) session.get(Users.class, du.retornaPkId(arrayList.get(2))));
        da.inserir(a);
    }

    public List<Ajuda> ajudas() {
        listAjuda = daoAjuda.listar();
        return listAjuda;
    }

    public String[] colunas() {
        camposAjuda = new String[4];
        camposAjuda[0] = "ID";
        camposAjuda[1] = "DESCRIÇÃO";
        camposAjuda[2] = "STATUS";
        camposAjuda[3] = "USUÁRIO SOLICITANTE";
        return camposAjuda;
    }

    public String[][] linhas() {
        dadosAjuda = new String[ajudas().size()][4];
        for (int i = 0; i < ajudas().size(); i++) {
            dadosAjuda[i][0] = String.valueOf(ajudas().get(i).getAjudPkId());
            dadosAjuda[i][1] = ajudas().get(i).getAjudDescricao();
            if (String.valueOf(ajudas().get(i).getAjudStatus()).equals("true")) {
                dadosAjuda[i][2] = "visualizado";
            } else {
                dadosAjuda[i][2] = "não visualizado";
            }
            dadosAjuda[i][3] = ajudas().get(i).getAjudFkUsersPkId().toString();
        }
        return dadosAjuda;
    }

    public void atualizar(ArrayList<String> arrayList) throws Exception {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Ajuda a = new Ajuda();
        DaoUsers du = new DaoUsers();
        DaoAjuda da = new DaoAjuda();
        a.setAjudPkId(Integer.valueOf(arrayList.get(0)));
        a.setAjudDescricao(arrayList.get(1));
        a.setAjudStatus(Boolean.valueOf(arrayList.get(2)));
        a.setAjudFkUsersPkId((Users) session.get(Users.class, du.buscaPorUserName(arrayList.get(3))));
        da.atualizar(a);
    }

    public void setListarBusca(String descricao) {
        listAjudaBuscar = daoAjuda.buscarAjudas(descricao);
    }

    public Object[][] linhasBusca() {
        dadosAjudaBuscar = new String[listAjudaBuscar.size()][4];
        for (int i = 0; i < listAjudaBuscar.size(); i++) {
            dadosAjudaBuscar[i][0] = String.valueOf(listAjudaBuscar.get(i).getAjudPkId());
            dadosAjudaBuscar[i][1] = listAjudaBuscar.get(i).getAjudDescricao();
            if (String.valueOf(listAjudaBuscar.get(i).getAjudStatus()).equals("true")) {
                dadosAjudaBuscar[i][2] = "visualizado";
            } else {
                dadosAjudaBuscar[i][2] = "não visualizado";
            }
            dadosAjudaBuscar[i][3] = listAjudaBuscar.get(i).getAjudFkUsersPkId().toString();
        }
        return dadosAjudaBuscar;
    }
    
    public void imprimirAjuda(String titulo, String relatorio, Map parametros) throws JRException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void imprimirAjudas(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
