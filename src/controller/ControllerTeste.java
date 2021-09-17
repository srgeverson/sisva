/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoTeste;
import dao.DaoUsers;
import java.io.InputStream;
import java.sql.SQLException;
import model.Users;
import setup.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Teste;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerTeste {

    private final DaoTeste daoTeste;
    private List<Teste> listTeste, listTesteBuscar;
    private String[] camposTeste;
    private String[][] dadosTeste, dadosTesteBuscar;

    public ControllerTeste() {
        daoTeste = new DaoTeste();
    }

    public void salvar(ArrayList<String> arrayList) {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Teste a = new Teste();
        DaoUsers du = new DaoUsers();
        DaoTeste da = new DaoTeste();
        a.setTestData(arrayList.get(0));
        a.setTestHora(arrayList.get(1));
        a.setTestFkUsersPkId((Users) session.get(Users.class, du.retornaPkId(arrayList.get(2))));
        da.inserir(a);
    }

    public List<Teste> testes() {
        listTeste = daoTeste.listar();
        return listTeste;
    }

    public void setListarBusca(String nome) {
        listTesteBuscar = daoTeste.listar(nome);
    }

    public String[] colunas() {
        camposTeste = new String[4];
        camposTeste[0] = "ID";
        camposTeste[1] = "DATA DO TESTE";
        camposTeste[2] = "HORA DO TESTE";
        camposTeste[3] = "USUÁRIO VALIDADO";
        return camposTeste;
    }

    public String[][] linhas() {
        dadosTeste = new String[testes().size()][4];
        for (int i = 0; i < testes().size(); i++) {
            dadosTeste[i][0] = String.valueOf(testes().get(i).getTestPkId());
            dadosTeste[i][1] = testes().get(i).getTestData();
            dadosTeste[i][2] = testes().get(i).getTestHora();
            dadosTeste[i][3] = testes().get(i).getTestFkUsersPkId().toString();
        }
        return dadosTeste;
    }

    public String[][] linhasBusca() {
        dadosTesteBuscar = new String[listTesteBuscar.size()][4];
        for (int i = 0; i < listTesteBuscar.size(); i++) {
            dadosTesteBuscar[i][0] = String.valueOf(listTesteBuscar.get(i).getTestPkId());
            dadosTesteBuscar[i][1] = listTesteBuscar.get(i).getTestData();
            dadosTesteBuscar[i][2] = listTesteBuscar.get(i).getTestHora();
            dadosTesteBuscar[i][3] = listTesteBuscar.get(i).getTestFkUsersPkId().toString();
        }
        return dadosTesteBuscar;
    }

    public void imprimirTeste(String titulo, String relatorio, Map parametros) throws JRException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void imprimirTestes(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void deletar(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
