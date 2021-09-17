/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerAcesso;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author geverson
 */
public class ViewAcesso extends JInternalFrame implements ActionListener, MouseListener {

    private JInternalFrame jifListar, jifBuscar;
    private JPanel jpListarCenter, jpListarNorth, jpBuscarNorth, jpBuscarCenter;
    private JButton jbAdd, jbBuscar, jbRefresh, jbEditar, jbReport, jbBuscarConfirma, jbImprimir;
    private ImageIcon iiAdd, iiBusca, iiRefresh, iiEditar, iiReport, iiBuscaConfirma, iiImprimir;
    private JTable jtAcesso, jtAcessoBuscar;
    private final ControllerAcesso ca;
    private JTextField jtfBuscar;
    private JScrollPane jsp;

    public ViewAcesso() {
        ca = new ControllerAcesso();
    }

    private void atualizar() {
        jpListarCenter.removeAll();
        jtAcesso = new JTable(ca.linhas(), ca.colunas());
        jpListarCenter.add(new JScrollPane(jtAcesso));
        jpListarCenter.setLayout(new GridLayout(1, 1));
        jpListarCenter.revalidate();
        jtAcesso.addMouseListener(this);
        if (jbEditar.isEnabled() == true) {
            jbEditar.setEnabled(false);
            jbImprimir.setEnabled(false);
        }
    }

    private void buttonBuscarConfirma() {
        ca.setListarBusca(jtfBuscar.getText());
        jpBuscarCenter.removeAll();
        jtAcessoBuscar = new JTable(ca.linhasBusca(), ca.colunas());
        jsp = new JScrollPane(jtAcessoBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));
        jsp.revalidate();
    }

    private void buscar() {
        jifBuscar = new JInternalFrame();
        jifBuscar.setTitle("Consultar acessos");
        jifBuscar.setSize(1250, 400);
        jifBuscar.setClosable(true);
        jifBuscar.setIconifiable(true);
        jifBuscar.setMaximizable(true);

        jpBuscarNorth = new JPanel();
        jpBuscarCenter = new JPanel();

        jtfBuscar = new JTextField();
        ca.setListarBusca(jtfBuscar.getText());

        jbBuscarConfirma = new JButton();
        iiBuscaConfirma = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscarConfirma.setIcon(iiBuscaConfirma);
        jbBuscarConfirma.addActionListener(this);

        jpBuscarNorth.add(jtfBuscar);
        jpBuscarNorth.add(jbBuscarConfirma);
        jpBuscarNorth.setLayout(new GridLayout(1, 2));

        jtAcessoBuscar = new JTable(ca.linhasBusca(), ca.colunas());
        jsp = new JScrollPane(jtAcessoBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));

        jifBuscar.getContentPane().setLayout(new BorderLayout());
        jifBuscar.getContentPane().add(jpBuscarNorth, BorderLayout.NORTH);
        jifBuscar.getContentPane().add(jpBuscarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifBuscar);
    }

    public JInternalFrame listar() {
        jifListar = new JInternalFrame();
        jifListar.setTitle("Acessos ao sistema");
        jifListar.setSize(1250, 400);
        jifListar.setClosable(true);
        jifListar.setIconifiable(true);
        jifListar.setMaximizable(true);

        jpListarCenter = new JPanel();
        jpListarNorth = new JPanel();

        jbAdd = new JButton("Novo");
        iiAdd = new ImageIcon(getClass().getResource("/img/button_add.png"));
        jbAdd.setIcon(iiAdd);
        jbAdd.setEnabled(false);

        jbBuscar = new JButton("Buscar");
        iiBusca = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscar.setIcon(iiBusca);

        jbRefresh = new JButton("Atualizar");
        iiRefresh = new ImageIcon(getClass().getResource("/img/button_refresh.png"));
        jbRefresh.setIcon(iiRefresh);

        jbEditar = new JButton("Editar");
        iiEditar = new ImageIcon(getClass().getResource("/img/button_editar.png"));
        jbEditar.setIcon(iiEditar);
        jbEditar.setEnabled(false);

        jbReport = new JButton("Relatório");
        iiReport = new ImageIcon(getClass().getResource("/img/button_report.png"));
        jbReport.setIcon(iiReport);

        jbImprimir = new JButton("Imprimir");
        iiImprimir = new ImageIcon(getClass().getResource("/img/button_print.png"));
        jbImprimir.setIcon(iiImprimir);
        jbImprimir.setEnabled(false);

        jtAcesso = new JTable(ca.linhas(), ca.colunas());
        jpListarCenter.add(new JScrollPane(jtAcesso));
        jpListarCenter.setLayout(new GridLayout(1, 1));

        jpListarNorth.add(jbAdd);
        jpListarNorth.add(jbBuscar);
        jpListarNorth.add(jbRefresh);
        jpListarNorth.add(jbEditar);
        jpListarNorth.add(jbImprimir);
        jpListarNorth.add(jbReport);

        jbAdd.addActionListener(this);
        jbBuscar.addActionListener(this);
        jbRefresh.addActionListener(this);
        jbEditar.addActionListener(this);
        jbReport.addActionListener(this);
        jtAcesso.addMouseListener(this);
        jbImprimir.addActionListener(this);
        jifListar.getContentPane().setLayout(new BorderLayout(5, 5));
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.NORTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        return jifListar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbReport) {
                ca.imprimirAcessos("Relatório de todos acessos ao sistema", "/report/relAcessos.jasper");
            } else if (obj == jbRefresh) {
                atualizar();
            } else if (obj == jbBuscar) {
                buscar();
            } else if (obj == jbBuscarConfirma) {
                buttonBuscarConfirma();
                buttonBuscarConfirma();
            } else if (obj == jbImprimir) {
                int linha;
                linha = jtAcesso.getSelectedRow();
                Integer aces_pk_id = Integer.parseInt(jtAcesso.getModel().getValueAt(linha, 0).toString());
                HashMap parametro = new HashMap();
                parametro.put("aces_pk_id", aces_pk_id);
                ca.imprimirAcesso("Imprimir Acesso", "/report/relAcesso.jasper", parametro);
            } else {
                JOptionPane.showMessageDialog(null, "Nada implementado para essa ação", "Vazio...", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro erro na tela de Acesso", exception);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jtAcesso) {
            if (jtAcesso.getSelectedRow() != -1) {
                jbEditar.setEnabled(true);
                jbImprimir.setEnabled(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
