/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoLogTeste;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import model.LogTeste;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerLogTeste {

    private final DaoLogTeste daoLogTeste;
    private List<LogTeste> logAcessos;
    private String[] caposLogTeste;
    private String[][] dadosLogTeste;

    public ControllerLogTeste() {
        daoLogTeste = new DaoLogTeste();
    }

    public List<LogTeste> logAcessos() {
        logAcessos = daoLogTeste.listar();
        return logAcessos;
    }

    public String[] colunas() {
        caposLogTeste = new String[9];
        caposLogTeste[0] = "ID";
        caposLogTeste[1] = "ID TESTE";
        caposLogTeste[2] = "OPERAÇÃO";
        caposLogTeste[3] = "CAMPO MODIFICADO";
        caposLogTeste[4] = "VALOR ANTIGO";
        caposLogTeste[5] = "VALOR ATUAL";
        caposLogTeste[6] = "VALOR DATA CADASTRO";
        caposLogTeste[7] = "VALOR DATA MODIFICAÇÃO";
        caposLogTeste[8] = "USUÁRIO REPONSÁVEL";
        return caposLogTeste;
    }

    public String[][] linhas() {
        dadosLogTeste = new String[logAcessos().size()][9];
        for (int i = 0; i < logAcessos().size(); i++) {
            dadosLogTeste[i][0] = String.valueOf(logAcessos().get(i).getLtestPkId());
            dadosLogTeste[i][1] = String.valueOf(logAcessos().get(i).getLtestIdTabela());
            dadosLogTeste[i][2] = String.valueOf(logAcessos().get(i).getLtestOperacao());
            dadosLogTeste[i][3] = logAcessos().get(i).getLtestOQueModificou();
            dadosLogTeste[i][4] = logAcessos().get(i).getLtestValorAntigo();
            dadosLogTeste[i][5] = logAcessos().get(i).getLtestValorAtual();
            dadosLogTeste[i][6] = String.valueOf(logAcessos().get(i).getLtestDataCadastrou());
            dadosLogTeste[i][7] = String.valueOf(logAcessos().get(i).getLtestDataModificou());
            dadosLogTeste[i][8] = logAcessos().get(i).getLtestFkUsersPkId().toString();
        }
        return dadosLogTeste;
    }

    public void imprimirErros(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
