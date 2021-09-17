/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoErro;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Erro;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import report.ReportUtil;
import setup.Conexao;
import setup.HibernateUtil;

/**
 *
 * @author geverson
 */
public class ControllerErro {

    private final DaoErro daoErro;
    private List<Erro> listErro;
    private String[] camposErro;
    private String[][] dadosErro;

    public ControllerErro() {
        daoErro = new DaoErro();
    }

    public List<Erro> listarErro() {
        listErro = daoErro.listar();
        return listErro;
    }

    public void salvar(ArrayList<String> arrayList) {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Erro e = new Erro();
        e.setErroDescricao(arrayList.get(0));
        e.setErroUsuario(arrayList.get(1));
        e.setErroMaquina(arrayList.get(2));
        e.setErroIpLocal(arrayList.get(3));
        e.setErroIpExterno(arrayList.get(4));
        e.setErroObservacao("p/ análise");
        daoErro.inserir(e);
    }

    public String[] colunas() {
        camposErro = new String[7];
        camposErro[0] = "CÓDIGO";
        camposErro[1] = "DESCRIÇÃO";
        camposErro[2] = "USUÁRIO";
        camposErro[3] = "MÁQUINA";
        camposErro[4] = "IP LOCAL";
        camposErro[5] = "IP EXTERNO";
        camposErro[6] = "OBSERVAÇÕES";
        return camposErro;
    }

    public String[][] linhas() {
        dadosErro = new String[listarErro().size()][7];
        for (int i = 0; i < listarErro().size(); i++) {
            dadosErro[i][0] = String.valueOf(listarErro().get(i).getErroPkId());
            dadosErro[i][1] = listarErro().get(i).getErroDescricao();
            dadosErro[i][2] = listarErro().get(i).getErroUsuario();
            dadosErro[i][3] = listarErro().get(i).getErroMaquina();
            dadosErro[i][4] = listarErro().get(i).getErroIpLocal();
            dadosErro[i][5] = listarErro().get(i).getErroIpExterno();
            dadosErro[i][6] = listarErro().get(i).getErroObservacao();
        }
        return dadosErro;
    }

    public void atualizar(ArrayList<String> arrayList) throws Exception {
        SessionFactory sf = HibernateUtil.getFabrica();
        Session session = sf.openSession();
        session.beginTransaction();
        Erro e = new Erro();
        e.setErroPkId(Integer.valueOf(arrayList.get(0)));
        e.setErroDescricao(arrayList.get(1));
        e.setErroUsuario(arrayList.get(2));
        e.setErroMaquina(arrayList.get(3));
        e.setErroIpLocal(arrayList.get(4));
        e.setErroIpExterno(arrayList.get(5));
        e.setErroObservacao(arrayList.get(6));
        daoErro.atualizar(e);
    }

    public void imprimirErro(String titulo, String relatorio, Map parametros) throws JRException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }

    public void imprimirErros(String titulo, String relatorio) throws ClassNotFoundException, JRException, SQLException {
        InputStream inputStream = getClass().getResourceAsStream(relatorio);
        Map parametros = new HashMap();
        ReportUtil.openReport(titulo, inputStream, parametros, Conexao.getConnection());
    }
}
