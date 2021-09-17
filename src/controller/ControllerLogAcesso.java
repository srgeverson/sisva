/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoLogAcesso;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import model.LogAcesso;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import report.ReportUtil;
import setup.Conexao;

/**
 *
 * @author administrador
 */
public class ControllerLogAcesso {

    private final DaoLogAcesso daoLogAcesso;
    private List<LogAcesso> logAcessos;
    private String[] caposLogAcesso;
    private String[][] dadosLogAcesso;

    public ControllerLogAcesso() {
        daoLogAcesso = new DaoLogAcesso();
    }

    public List<LogAcesso> logAcessos() {
        logAcessos = daoLogAcesso.listar();
        return logAcessos;
    }

    public String[] colunas() {
        caposLogAcesso = new String[9];
        caposLogAcesso[0] = "ID";
        caposLogAcesso[1] = "ID ACESSO";
        caposLogAcesso[2] = "OPERAÇÃO";
        caposLogAcesso[3] = "CAMPO MODIFICADO";
        caposLogAcesso[4] = "VALOR ANTIGO";
        caposLogAcesso[5] = "VALOR ATUAL";
        caposLogAcesso[6] = "VALOR DATA CADASTRO";
        caposLogAcesso[7] = "VALOR DATA MODIFICAÇÃO";
        caposLogAcesso[8] = "USUÁRIO REPONSÁVEL";
        return caposLogAcesso;
    }

    public String[][] linhas() {
        dadosLogAcesso = new String[logAcessos().size()][9];
        for (int i = 0; i < logAcessos().size(); i++) {
            dadosLogAcesso[i][0] = String.valueOf(logAcessos().get(i).getLacesPkId());
            dadosLogAcesso[i][1] = String.valueOf(logAcessos().get(i).getLacesIdTabela());
            dadosLogAcesso[i][2] = String.valueOf(logAcessos().get(i).getLacesOperacao());
            dadosLogAcesso[i][3] = logAcessos().get(i).getLacesOQueModificou();
            dadosLogAcesso[i][4] = logAcessos().get(i).getLacesValorAntigo();
            dadosLogAcesso[i][5] = logAcessos().get(i).getLacesValorAtual();
            dadosLogAcesso[i][6] = String.valueOf(logAcessos().get(i).getLacesDataCadastrou());
            dadosLogAcesso[i][7] = String.valueOf(logAcessos().get(i).getLacesDataModificou());
            dadosLogAcesso[i][8] = logAcessos().get(i).getLacesFkUsersPkId().toString();
        }
        return dadosLogAcesso;
    }

    public void imprimirErros(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
