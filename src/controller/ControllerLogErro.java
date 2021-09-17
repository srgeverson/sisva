/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.DaoLogErro;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.LogErro;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author geverson
 */
public class ControllerLogErro {
    private final DaoLogErro daoLogErro;
    private List<LogErro> logErro;
    private String[] caposLogErro;
    private String[][] dadosLogErro;

    public ControllerLogErro() {
        daoLogErro = new DaoLogErro();
    }

    public List<LogErro> logErro() {
        logErro = daoLogErro.listar();
        return logErro;
    }

    public String[] colunas() {
        caposLogErro = new String[8];
        caposLogErro[0] = "ID";
        caposLogErro[1] = "ID ERRO";
        caposLogErro[2] = "OPERAÇÃO";
        caposLogErro[3] = "CAMPO MODIFICADO";
        caposLogErro[4] = "VALOR ANTIGO";
        caposLogErro[5] = "VALOR ATUAL";
        caposLogErro[6] = "DATA CADASTRO";
        caposLogErro[7] = "DATA MODIFICAÇÃO";
        return caposLogErro;
    }

    public String[][] linhas() {
        dadosLogErro = new String[logErro().size()][8];
        for (int i = 0; i < logErro().size(); i++) {
            dadosLogErro[i][0] = String.valueOf(logErro().get(i).getLerroPkId());
            dadosLogErro[i][1] = String.valueOf(logErro().get(i).getLerroIdTabela());
            dadosLogErro[i][2] = String.valueOf(logErro().get(i).getLerroOperacao());
            dadosLogErro[i][3] = logErro().get(i).getLerroOQueModificou();
            dadosLogErro[i][4] = logErro().get(i).getLerroValorAntigo();
            dadosLogErro[i][5] = logErro().get(i).getLerroValorAtual();
            dadosLogErro[i][6] = String.valueOf(logErro().get(i).getLerroDataCadastrou());
            dadosLogErro[i][7] = String.valueOf(logErro().get(i).getLerroDataModificou());
        }
        return dadosLogErro;
    } 
     public void imprimirLogErros(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
