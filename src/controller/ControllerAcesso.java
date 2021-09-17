/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoAcesso;
import dao.DaoUsers;
import java.io.InputStream;
import java.sql.SQLException;
import model.Acesso;
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
public class ControllerAcesso {

    private final DaoAcesso daoAcesso;
    private List<Acesso> listAcesso, listAcessoBuscar;
    private String[] camposAcesso;
    private String[][] dadosAcesso, dadosAcessoBuscar;

    public ControllerAcesso() {
        daoAcesso = new DaoAcesso();
    }

    public void salvar(ArrayList<String> arrayList) {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Acesso a = new Acesso();
        DaoUsers du = new DaoUsers();
        DaoAcesso da = new DaoAcesso();
        a.setAcesData(arrayList.get(0));
        a.setAcesHora(arrayList.get(1));
        a.setAcesFkUsersPkId((Users) session.get(Users.class, du.retornaPkId(arrayList.get(2))));
        da.inserir(a);
    }

    public List<Acesso> acessos() {
        listAcesso = daoAcesso.listar();
        return listAcesso;
    }

    public void setListarBusca(String nome) {
        listAcessoBuscar = daoAcesso.listar(nome);
    }

    public String[] colunas() {
        camposAcesso = new String[4];
        camposAcesso[0] = "ID";
        camposAcesso[1] = "DATA DO ACESSO";
        camposAcesso[2] = "HORA DO ACESSO";
        camposAcesso[3] = "USUÁRIO VALIDADO";
        return camposAcesso;
    }

    public String[][] linhas() {
        dadosAcesso = new String[acessos().size()][4];
        for (int i = 0; i < acessos().size(); i++) {
            dadosAcesso[i][0] = String.valueOf(acessos().get(i).getAcesPkId());
            dadosAcesso[i][1] = acessos().get(i).getAcesData();
            dadosAcesso[i][2] = acessos().get(i).getAcesHora();
            dadosAcesso[i][3] = acessos().get(i).getAcesFkUsersPkId().toString();
        }
        return dadosAcesso;
    }

    public String[][] linhasBusca() {
        dadosAcessoBuscar = new String[listAcessoBuscar.size()][4];
        for (int i = 0; i < listAcessoBuscar.size(); i++) {
            dadosAcessoBuscar[i][0] = String.valueOf(listAcessoBuscar.get(i).getAcesPkId());
            dadosAcessoBuscar[i][1] = listAcessoBuscar.get(i).getAcesData();
            dadosAcessoBuscar[i][2] = listAcessoBuscar.get(i).getAcesHora();
            dadosAcessoBuscar[i][3] = listAcessoBuscar.get(i).getAcesFkUsersPkId().toString();
        }
        return dadosAcessoBuscar;
    }

    public void imprimirAcesso(String titulo, String relatorio, Map parametros) throws JRException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void imprimirAcessos(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
