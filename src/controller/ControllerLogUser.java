/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoLogUsers;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import model.LogUsers;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerLogUser {

    private final DaoLogUsers daoLogUsers;
    private List<LogUsers> logUsers;
    private String[] caposLogUsers;
    private String[][] dadosLogUsers;

    public ControllerLogUser() {
        daoLogUsers = new DaoLogUsers();
    }

    public List<LogUsers> logUsers() {
        logUsers = daoLogUsers.listar();
        return logUsers;
    }

    public String[] colunas() {
        caposLogUsers = new String[9];
        caposLogUsers[0] = "ID";
        caposLogUsers[1] = "ID USÁRIO";
        caposLogUsers[2] = "OPERAÇÃO";
        caposLogUsers[3] = "CAMPO MODIFICADO";
        caposLogUsers[4] = "VALOR ANTIGO";
        caposLogUsers[5] = "VALOR ATUAL";
        caposLogUsers[6] = "DATA CADASTRO";
        caposLogUsers[7] = "DATA MODIFICAÇÃO";
        caposLogUsers[8] = "USUÁRIO REPONSÁVEL";
        return caposLogUsers;
    }

    public String[][] linhas() {
        dadosLogUsers = new String[logUsers().size()][9];
        for (int i = 0; i < logUsers().size(); i++) {
            dadosLogUsers[i][0] = String.valueOf(logUsers().get(i).getLuserPkId());
            dadosLogUsers[i][1] = String.valueOf(logUsers().get(i).getLuserIdTabela());
            dadosLogUsers[i][2] = String.valueOf(logUsers().get(i).getLuserOperacao());
            dadosLogUsers[i][3] = logUsers().get(i).getLuserOQueModificou();
            dadosLogUsers[i][4] = logUsers().get(i).getLuserValorAntigo();
            dadosLogUsers[i][5] = logUsers().get(i).getLuserValorAtual();
            dadosLogUsers[i][6] = String.valueOf(logUsers().get(i).getLuserDataCadastrou());
            dadosLogUsers[i][7] = String.valueOf(logUsers().get(i).getLuserDataModificou());
            dadosLogUsers[i][8] = logUsers().get(i).getLuserFkUsersPkId().toString();
        }
        return dadosLogUsers;
    }
     public void imprimirLogUsers(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
