/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import view.ViewErro;

/**
 *
 * @author administrador
 */
public class IndexCalendario extends Thread {

    private JLabel jlHora;
    private String stringData;

    public IndexCalendario() {
    }

    public IndexCalendario(JLabel hora) {
        this.jlHora = hora;
    }

    public String mostraData() {
        DateFormat df = DateFormat.getDateInstance();
        Date hoje = new Date();
        stringData = df.format(hoje);
        return stringData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Date vtDate = new Date();
                StringBuffer vtStringBuffer = new StringBuffer();
                SimpleDateFormat vtSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                this.jlHora.setText(vtStringBuffer.toString() + vtSimpleDateFormat.format(vtDate));
                Thread.sleep(1000);
                this.jlHora.revalidate();
            }
        } catch (Exception ie) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro no horário", ie);
        }
    }
}
