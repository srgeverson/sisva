/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoLogAjuda;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import model.LogAjuda;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerLogAjuda {

    private final DaoLogAjuda daoLogAjuda;
    private List<LogAjuda> logAjudas;
    private String[] caposLogAjuda;
    private String[][] dadosLogAjuda;

    public ControllerLogAjuda() {
        daoLogAjuda = new DaoLogAjuda();
    }

    public List<LogAjuda> logAjudas() {
        logAjudas = daoLogAjuda.listar();
        return logAjudas;
    }

    public String[] colunas() {
        caposLogAjuda = new String[9];
        caposLogAjuda[0] = "ID";
        caposLogAjuda[1] = "ID AJUDA";
        caposLogAjuda[2] = "OPERAÇÃO";
        caposLogAjuda[3] = "CAMPO MODIFICADO";
        caposLogAjuda[4] = "VALOR ANTIGO";
        caposLogAjuda[5] = "VALOR ATUAL";
        caposLogAjuda[6] = "VALOR DATA CADASTRO";
        caposLogAjuda[7] = "VALOR DATA MODIFICAÇÃO";
        caposLogAjuda[8] = "USUÁRIO REPONSÁVEL";
        return caposLogAjuda;
    }

    public String[][] linhas() {
        dadosLogAjuda = new String[logAjudas().size()][9];
        for (int i = 0; i < logAjudas().size(); i++) {
            dadosLogAjuda[i][0] = String.valueOf(logAjudas().get(i).getLajudPkId());
            dadosLogAjuda[i][1] = String.valueOf(logAjudas().get(i).getLajudIdTabela());
            dadosLogAjuda[i][2] = String.valueOf(logAjudas().get(i).getLajudOperacao());
            dadosLogAjuda[i][3] = logAjudas().get(i).getLajudOQueModificou();
            dadosLogAjuda[i][4] = logAjudas().get(i).getLajudValorAntigo();
            dadosLogAjuda[i][5] = logAjudas().get(i).getLajudValorAtual();
            dadosLogAjuda[i][6] = String.valueOf(logAjudas().get(i).getLajudDataCadastrou());
            dadosLogAjuda[i][7] = String.valueOf(logAjudas().get(i).getLajudDataModificou());
            dadosLogAjuda[i][8] = logAjudas().get(i).getLajudFkUsersPkId().toString();
        }
        return dadosLogAjuda;
    }

    public void imprimirErros(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
