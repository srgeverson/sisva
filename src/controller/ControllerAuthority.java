/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoAuthority;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Authority;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Users;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerAuthority {

    private final DaoAuthority daoAuthority;
    private String[] camposAuthority;
    private String[][] dadosAuthority;

    public ControllerAuthority() {
        daoAuthority = new DaoAuthority();
    }

    public List<Authority> authoritys() {
        List<Authority> listaAuthority = daoAuthority.listar();
        return listaAuthority;
    }

    public boolean deletar(Integer id) {
        return daoAuthority.remover(id);
    }

    public String[] colunas() {
        camposAuthority = new String[3];
        camposAuthority[0] = "ID";
        camposAuthority[1] = "AUTHORITY";
        camposAuthority[2] = "DESCRIÇÃO";
        return camposAuthority;
    }

    public String[][] linhas() {
        dadosAuthority = new String[authoritys().size()][3];
        for (int i = 0; i < authoritys().size(); i++) {
            dadosAuthority[i][0] = String.valueOf(authoritys().get(i).getAuthPkId());
            dadosAuthority[i][1] = authoritys().get(i).getAuthName();
            dadosAuthority[i][2] = authoritys().get(i).getAuthDescricao();
        }
        return dadosAuthority;
    }

    public void salvar(ArrayList<String> arrayList) {
        Authority a = new Authority();
        a.setAuthName(arrayList.get(0));
        a.setAuthDescricao(arrayList.get(1));
        daoAuthority.inserir(a);
    }

    public Boolean verificaAuthority(String auth_name) {
        Boolean recebeUsuaio = daoAuthority.verificaAuthority(auth_name);
        return recebeUsuaio;
    }

    public boolean editar(ArrayList<String> arrayList) {
        Authority a = new Authority();
        a.setAuthPkId(Integer.parseInt(arrayList.get(0)));
        a.setAuthName(arrayList.get(1));
        a.setAuthDescricao(arrayList.get(2));
        boolean b = daoAuthority.atualizar(a);
        return b;
    }

    public void imprimirAuthority(String titulo, String relatorio, Map parametros) throws JRException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void imprimirAuthorities(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public boolean verificaFk(String fk) {
        boolean b = false;
        Integer integer = Integer.parseInt(fk);
        ControllerUser controllerUser = new ControllerUser();
        for (Users users : controllerUser.listar()) {
            if (integer == users.getUserFkAuthorityPkId().getAuthPkId()) {
                b = true;
            }
        }
        return b;
    }
}
