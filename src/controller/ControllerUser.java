/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoAuthority;
import dao.DaoUsers;
import java.io.InputStream;
import java.sql.SQLException;
import model.Authority;
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
public class ControllerUser {

    private final DaoUsers daoUsers;
    private final DaoAuthority daoAuthority;
    private Users u;
    private List<Users> listUsers, listUsersBusca;
    private String[] colunas;
    private String[][] linhas, linhasBusca;

    public ControllerUser() {
        daoUsers = new DaoUsers();
        daoAuthority = new DaoAuthority();
        u = new Users();
    }

    public void editar(ArrayList<String> arrayList) {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Users user = new Users();
        user.setUserPkId(Integer.valueOf(arrayList.get(0)));
        user.setUserName(arrayList.get(1));
        user.setUserPassword(arrayList.get(2));
        user.setUserStatus(arrayList.get(3));
        user.setUserFkAuthorityPkId((Authority) session.get(Authority.class, daoAuthority.consultaPorPkId(arrayList.get(4))));
        daoUsers.editar(user);
    }

    public void saveUsers(ArrayList<String> arrayList) {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Users u = new Users();
        u.setUserName(arrayList.get(0));
        u.setUserPassword(arrayList.get(1));
        u.setUserStatus(arrayList.get(2));
        DaoAuthority daoAuthority = new DaoAuthority();
        u.setUserFkAuthorityPkId((Authority) session.get(Authority.class, daoAuthority.consultaPorPkId(arrayList.get(3))));
        DaoUsers du = new DaoUsers();
        du.inserir(u);
    }

    public Boolean verificaUsuario(String user_name) {
        Boolean recebeUsuaio = daoUsers.verificaUsuario(user_name);
        return recebeUsuaio;
    }

    public String[] getFkAuthorityPkId() {
        String[] auth = new String[daoAuthority.listar().size()];
        for (int i = 0; i < daoAuthority.listar().size(); i++) {
            auth[i] = daoAuthority.listar().get(i).getAuthName();
        }
        return auth;
    }

    public Users logar(String username) {
        u = daoUsers.logar(username);
        return u;
    }

    public List<Users> listar() {
        listUsers = daoUsers.listar();
        return listUsers;
    }

    public void setListarBusca(String nome) {
        listUsersBusca = daoUsers.buscarUsers(nome);
    }

    public String[] colunas() {
        colunas = new String[5];
        colunas[0] = "ID";
        colunas[1] = "USUÁRIO";
        colunas[2] = "SENHAs";
        colunas[3] = "STATUS";
        colunas[4] = "PERMISSÃO";
        return colunas;
    }

    public String[][] linhas() {
        linhas = new String[listar().size()][5];
        for (int i = 0; i < listar().size(); i++) {
            linhas[i][0] = String.valueOf(listar().get(i).getUserPkId());
            linhas[i][1] = listar().get(i).getUserName();
            linhas[i][2] = listar().get(i).getUserPassword();
            linhas[i][3] = listar().get(i).getUserStatus();
            linhas[i][4] = listar().get(i).getUserFkAuthorityPkId().toString();
        }
        return linhas;
    }

    public String[][] linhasBusca() {
        linhasBusca = new String[listUsersBusca.size()][5];
        for (int i = 0; i < listUsersBusca.size(); i++) {
            linhasBusca[i][0] = String.valueOf(listUsersBusca.get(i).getUserPkId());
            linhasBusca[i][1] = listUsersBusca.get(i).getUserName();
            linhasBusca[i][2] = listUsersBusca.get(i).getUserPassword();
            linhasBusca[i][3] = listUsersBusca.get(i).getUserStatus();
            linhasBusca[i][4] = listUsersBusca.get(i).getUserFkAuthorityPkId().toString();
        }
        return linhasBusca;
    }

    public void imprimirUsers(String titulo, String relatorio, Map parametros) throws JRException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void imprimirUser(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
