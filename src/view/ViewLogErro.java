/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerLogErro;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author geverson
 */
public class ViewLogErro extends JInternalFrame implements ActionListener {

    private JInternalFrame jifListar;
    private JPanel jpListarCenter, jpListarNorth;
    private JTable jtLogErro;
    private JButton jbReport;
    private ImageIcon iiReport;
    private final ControllerLogErro controllerLogErro;

    public ViewLogErro() {
        controllerLogErro = new ControllerLogErro();
    }

    public JInternalFrame listar() {
        jifListar = new JInternalFrame();
        jifListar.setTitle("Logs dos erros");
        jifListar.setSize(1250, 600);
        jifListar.setClosable(true);
        jifListar.setIconifiable(true);
        jifListar.setMaximizable(true);

        jpListarCenter = new JPanel();
        jpListarNorth = new JPanel();

        jtLogErro = new JTable(controllerLogErro.linhas(), controllerLogErro.colunas());
        jpListarCenter.add(new JScrollPane(jtLogErro));
        jpListarCenter.setLayout(new GridLayout(1, 1));

        jbReport = new JButton("Relatório");
        iiReport = new ImageIcon(getClass().getResource("/img/button_print.png"));
        jbReport.setIcon(iiReport);
        jbReport.addActionListener(this);

        jpListarNorth.add(jbReport);

        jifListar.getContentPane().setLayout(new BorderLayout());
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.SOUTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        return jifListar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbReport) {
                controllerLogErro.imprimirLogErros("Relatório das operações realizadas nos Erros", "/report/relLogError.jasper");
            } else {
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ao solicitar ajuda", exception);
        }
    }

}
