/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerAjuda;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author geverson
 */
public class ViewAjuda extends JInternalFrame implements ActionListener, MouseListener {

    private JInternalFrame jifAlterar, jifCadastrar, jifListar, jifBuscar;
    private JPanel jpAlterarCenter, jpAlterarLeft, jpCadatrarCenter, jpCadastrarSouth, jpListarCenter, jpListarNorth, jpBuscarNorth, jpBuscarCenter;
    private JTextField jtfPkId, jtfFkUsuario, jtfBuscar;
    private JTextArea jtaDescricao;
    private JCheckBox jcbStatus;
    private JButton jbAlterar, jbCancelar, jbSalvar, jbRefresh, jbEditar, jbReport, jbBuscarConfirma, jbBuscar;
    private ImageIcon iiEditarr, iiCancelar, iiSalvar, iiRefresh, iiEditar, iiReport, iiBuscarConfirma, iiBuscar;
    private JTable jtAjuda, jtAjudaBuscar;
    private final ControllerAjuda controllerAjuda;
    private JScrollPane jsp;
    private JButton jbImprimir;
    private ImageIcon iiImprimir;

    public ViewAjuda() {
        controllerAjuda = new ControllerAjuda();
    }

    public JInternalFrame alterar() {
        jifAlterar = new JInternalFrame();
        jifAlterar.setTitle("Solicitação");
        jifAlterar.setClosable(true);
        jifAlterar.setIconifiable(true);
        jifAlterar.setSize(600, 150);

        jpAlterarLeft = new JPanel();
        jpAlterarCenter = new JPanel();

        jtfPkId = new JTextField();
        jtfPkId.setEnabled(false);
        jtaDescricao = new JTextArea();
        jcbStatus = new JCheckBox("Visualizado");
        jtfFkUsuario = new JTextField();
        jtfFkUsuario.setEnabled(false);

        jbAlterar = new JButton("Alterar");
        iiEditarr = new ImageIcon(getClass().getResource("/img/button_confirmar.png"));
        jbAlterar.setIcon(iiEditarr);

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jbAlterar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jpAlterarLeft.add(new JLabel("ID: "));
        jpAlterarLeft.add(jtfPkId);
        jpAlterarLeft.add(new JLabel("Status: "));
        jpAlterarLeft.add(jcbStatus);
        jpAlterarLeft.add(new JLabel("Usuário Solicitante: "));
        jpAlterarLeft.add(jtfFkUsuario);
        jpAlterarLeft.add(jbCancelar);
        jpAlterarLeft.add(jbAlterar);
        jpAlterarLeft.setLayout(new GridLayout(4, 2));

        jpAlterarCenter.add(new JScrollPane(jtaDescricao));
        jpAlterarCenter.setLayout(new GridLayout(1, 1));

        jifAlterar.getContentPane().setLayout(new BorderLayout());
        jifAlterar.getContentPane().add(jpAlterarLeft, BorderLayout.WEST);
        jifAlterar.getContentPane().add(jpAlterarCenter, BorderLayout.CENTER);
        pack();
        return jifAlterar;
    }

    public JInternalFrame cadastrar() {
        jifCadastrar = new JInternalFrame();
        jifCadastrar.setTitle("Cadastrar/Solicita Ajuda");
        jifCadastrar.setClosable(true);
        jifCadastrar.setIconifiable(true);
        jifCadastrar.setSize(300, 300);

        jpCadastrarSouth = new JPanel();
        jpCadatrarCenter = new JPanel();

        jtaDescricao = new JTextArea();

        jbSalvar = new JButton("Salvar");
        iiSalvar = new ImageIcon(getClass().getResource("/img/button_salvar.png"));
        jbSalvar.setIcon(iiSalvar);

        jbSalvar.addActionListener(this);

        jpCadatrarCenter.add(new JScrollPane(jtaDescricao));
        jpCadatrarCenter.setLayout(new GridLayout(1, 1));
        jpCadastrarSouth.add(jbSalvar);

        jifCadastrar.getContentPane().setLayout(new BorderLayout());
        jifCadastrar.getContentPane().add(jpCadatrarCenter, BorderLayout.CENTER);
        jifCadastrar.getContentPane().add(jpCadastrarSouth, BorderLayout.SOUTH);
        pack();
        return jifCadastrar;
    }

    private void atualizar() {
        jpListarCenter.removeAll();
        jtAjuda = new JTable(controllerAjuda.linhas(), controllerAjuda.colunas());
        jpListarCenter.add(new JScrollPane(jtAjuda));
        jpListarCenter.setLayout(new GridLayout(1, 1));
        jpListarCenter.revalidate();
        jtAjuda.addMouseListener(this);
        if (jbEditar.isEnabled() == true) {
            jbEditar.setEnabled(false);
            jbImprimir.setEnabled(false);
        }
    }

    public JInternalFrame listar() {
        jifListar = new JInternalFrame();
        jifListar.setTitle("Ajudas solicitadas");
        jifListar.setSize(1250, 400);
        jifListar.setClosable(true);
        jifListar.setIconifiable(true);
        jifListar.setMaximizable(true);

        jpListarCenter = new JPanel();
        jpListarNorth = new JPanel();

        jbBuscar = new JButton("Buscar");
        iiBuscar = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscar.setIcon(iiBuscar);

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

        jtAjuda = new JTable(controllerAjuda.linhas(), controllerAjuda.colunas());
        jpListarCenter.add(new JScrollPane(jtAjuda));
        jpListarCenter.setLayout(new GridLayout(1, 1));

        jpListarNorth.add(jbBuscar);
        jpListarNorth.add(jbEditar);
        jpListarNorth.add(jbRefresh);
        jpListarNorth.add(jbReport);
        jpListarNorth.add(jbImprimir);

        jbBuscar.addActionListener(this);
        jbEditar.addActionListener(this);
        jbRefresh.addActionListener(this);
        jbReport.addActionListener(this);
        jbImprimir.addActionListener(this);
        jtAjuda.addMouseListener(this);

        jifListar.getContentPane().setLayout(new BorderLayout(5, 5));
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.NORTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        return jifListar;
    }

    private void buscarConfirma() {
        controllerAjuda.setListarBusca(jtfBuscar.getText());
        jpBuscarCenter.removeAll();
        jtAjudaBuscar = new JTable(controllerAjuda.linhasBusca(), controllerAjuda.colunas());
        jsp = new JScrollPane(jtAjudaBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));
        jsp.revalidate();
    }

    private void buscar() {
        jifBuscar = new JInternalFrame();
        jifBuscar.setTitle("Consultar Ajudas");
        jifBuscar.setSize(1250, 400);
        jifBuscar.setClosable(true);
        jifBuscar.setIconifiable(true);
        jifBuscar.setMaximizable(true);

        jpBuscarNorth = new JPanel();
        jpBuscarCenter = new JPanel();

        jtfBuscar = new JTextField();
        controllerAjuda.setListarBusca(jtfBuscar.getText());

        jbBuscarConfirma = new JButton();
        iiBuscarConfirma = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscarConfirma.setIcon(iiBuscarConfirma);
        jbBuscarConfirma.addActionListener(this);

        jpBuscarNorth.add(jtfBuscar);
        jpBuscarNorth.add(jbBuscarConfirma);
        jpBuscarNorth.setLayout(new GridLayout(1, 2));

        jtAjudaBuscar = new JTable(controllerAjuda.linhasBusca(), controllerAjuda.colunas());
        jsp = new JScrollPane(jtAjudaBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));

        jifBuscar.getContentPane().setLayout(new BorderLayout());
        jifBuscar.getContentPane().add(jpBuscarNorth, BorderLayout.NORTH);
        jifBuscar.getContentPane().add(jpBuscarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifBuscar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbEditar) {
                alterar();
                int linha;
                linha = jtAjuda.getSelectedRow();
                jtfPkId.setText(jtAjuda.getModel().getValueAt(linha, 0).toString());
                jtaDescricao.setText(jtAjuda.getModel().getValueAt(linha, 1).toString());
                if (jtAjuda.getModel().getValueAt(linha, 2).toString().equals("não visualizado")) {
                    jcbStatus.setSelected(false);
                } else {
                    jcbStatus.setSelected(true);
                }
                jtfFkUsuario.setText(jtAjuda.getModel().getValueAt(linha, 3).toString());
                ViewPrincipal.addFrame(jifAlterar);
            } else if (obj == jbReport) {
                controllerAjuda.imprimirAjudas("Relatório das operações realizadas nos Userss", "/report/relAjudas.jasper");
            } else if (obj == jbAlterar) {
                ArrayList<String> novoAjuda = new ArrayList<>();
                novoAjuda.add(jtfPkId.getText());
                novoAjuda.add(jtaDescricao.getText());
                novoAjuda.add(String.valueOf(jcbStatus.isSelected()));
                novoAjuda.add(jtfFkUsuario.getText());
                controllerAjuda.atualizar(novoAjuda);
                JOptionPane.showMessageDialog(null, "Alteração Realizada Com Sucesso!", "Alteração Realizada", JOptionPane.INFORMATION_MESSAGE);
                jifAlterar.dispose();
            } else if (obj == jbRefresh) {
                atualizar();
            } else if (obj == jbSalvar) {
                if (jtaDescricao.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Não é permitido salvar com falta de dados", "Dados obrigatórios", JOptionPane.WARNING_MESSAGE);
                } else {
                    ArrayList<String> addAjuda = new ArrayList<>();
                    addAjuda.add(jtaDescricao.getText());
                    addAjuda.add("false");
                    addAjuda.add(ViewPrincipal.user);
                    controllerAjuda.salvar(addAjuda);
                    JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!", "Registro salvo", JOptionPane.INFORMATION_MESSAGE);
                    jifCadastrar.dispose();
                }
            } else if (obj == jbCancelar) {
                int confir = JOptionPane.showConfirmDialog(null, "Tem cesteza que deseja cancelar edição?", "Confirmar...", JOptionPane.YES_NO_OPTION);
                if (confir == JOptionPane.YES_OPTION) {
                    jifAlterar.dispose();
                }
            } else if (obj == jbBuscar) {
                buscar();
            } else if (obj == jbBuscarConfirma) {
                buscarConfirma();
            } else if (obj == jbImprimir) {
                int linha;
                linha = jtAjuda.getSelectedRow();
                Integer ajud_pk_id = Integer.parseInt(jtAjuda.getModel().getValueAt(linha, 0).toString());
                HashMap parametro = new HashMap();
                parametro.put("ajud_pk_id", ajud_pk_id);
                controllerAjuda.imprimirAjuda("Imprimir Ajuda", "/report/relAjuda.jasper", parametro);
            } else {
                JOptionPane.showMessageDialog(null, "Ação não tratada!", "Vazio", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro na tela de Ajuda", exception);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jtAjuda) {
            if (jtAjuda.getSelectedRow() != -1) {
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
