/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoLogAuthority;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import model.LogAuthority;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerLogAuthority {

    private final DaoLogAuthority daoLogAuthority;
    private List<LogAuthority> logAuthority;
    private String[] caposLogAuthority;
    private String[][] dadosLogAuthority;

    public ControllerLogAuthority() {
        daoLogAuthority = new DaoLogAuthority();
    }

    public List<LogAuthority> logAuthority() {
        logAuthority = daoLogAuthority.listar();
        return logAuthority;
    }

    public String[] colunas() {
        caposLogAuthority = new String[8];
        caposLogAuthority[0] = "ID";
        caposLogAuthority[1] = "ID PERMISSÃO";
        caposLogAuthority[2] = "OPERAÇÃO";
        caposLogAuthority[3] = "CAMPO MODIFICADO";
        caposLogAuthority[4] = "VALOR ANTIGO";
        caposLogAuthority[5] = "VALOR ATUAL";
        caposLogAuthority[6] = "VALOR DATA CADASTRO";
        caposLogAuthority[7] = "VALOR DATA MODIFICAÇÃO";
        return caposLogAuthority;
    }

    public String[][] linhas() {
        dadosLogAuthority = new String[logAuthority().size()][8];
        for (int i = 0; i < logAuthority().size(); i++) {
            dadosLogAuthority[i][0] = String.valueOf(logAuthority().get(i).getLauthPkId());
            dadosLogAuthority[i][1] = String.valueOf(logAuthority().get(i).getLauthIdTabela());
            dadosLogAuthority[i][2] = String.valueOf(logAuthority().get(i).getLauthOperacao());
            dadosLogAuthority[i][3] = logAuthority().get(i).getLauthOQueModificou();
            dadosLogAuthority[i][4] = logAuthority().get(i).getLauthValorAntigo();
            dadosLogAuthority[i][5] = logAuthority().get(i).getLauthValorAtual();
            dadosLogAuthority[i][6] = String.valueOf(logAuthority().get(i).getLauthDataCadastrou());
            dadosLogAuthority[i][7] = String.valueOf(logAuthority().get(i).getLauthDataModificou());
        }
        return dadosLogAuthority;
    }
     public void imprimirErros(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
