/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerUser;
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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author geverson
 */
public class ViewUsers extends JInternalFrame implements ActionListener, MouseListener {

    private JInternalFrame jifAlterar, jifBuscar, jifListar, jifSalvar;
    private final JLabel jlPkId, jlName, jlPassword, jlStatus, jlAuthority;
    private JTextField jtfPkId, jtfName, jtfBuscar;
    private JPasswordField jpfPassword;
    private JComboBox jcbStatus, jcbAuthority;
    private JButton jbEditar, jbAlterar, jbCancelar, jbBuscarConfirma, jbSalvar, jbBuscar, jbRefresh, jbReport;
    private ImageIcon iiEditarr, iiCancelar, iiBuscaConfirma, iiSalvar, iiBusca, iiRefresh, iiReport, iiEditar;
    private JPanel jpAlterarCenter, jpListarNorth, jpListarCenter, jpBuscarCenter, jpBuscarNorth, jpSalvarCenter;
    private final String ststus[] = {"INATIVO", "ATIVO"};
    private JTable jtUsers, jtUsersBuscar;
    private final ControllerUser controllerUser;
    private JScrollPane jsp;
    private JButton jbImprimir;
    private ImageIcon iiImprimir;

    public ViewUsers() {
        jlPkId = new JLabel("Id: ");
        jlName = new JLabel("Nome do Usuário: ");
        jlPassword = new JLabel("Senha do Usuário: ");
        jlStatus = new JLabel("Status: ");
        jlAuthority = new JLabel("Permissão: ");
        controllerUser = new ControllerUser();
    }

    public void alterar() {
        jifAlterar = new JInternalFrame();
        jifAlterar.setTitle("Alterar dados de usuário");
        jifAlterar.setClosable(true);
        jifAlterar.setIconifiable(true);
        jifAlterar.setSize(300, 200);

        jpAlterarCenter = new JPanel();

        jtfPkId = new JTextField();
        jtfPkId.setEnabled(false);
        jtfName = new JTextField();
        jtfName.setEnabled(false);
        jpfPassword = new JPasswordField();
        jcbStatus = new JComboBox<>(ststus);
        jcbAuthority = new JComboBox<>(controllerUser.getFkAuthorityPkId());

        jbAlterar = new JButton("Alterar");
        iiEditarr = new ImageIcon(getClass().getResource("/img/button_confirmar.png"));
        jbAlterar.setIcon(iiEditarr);

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jpAlterarCenter.add(jlPkId);
        jpAlterarCenter.add(jtfPkId);
        jpAlterarCenter.add(jlName);
        jpAlterarCenter.add(jtfName);
        jpAlterarCenter.add(jlPassword);
        jpAlterarCenter.add(jpfPassword);
        jpAlterarCenter.add(jlStatus);
        jpAlterarCenter.add(jcbStatus);
        jpAlterarCenter.add(jlAuthority);
        jpAlterarCenter.add(jcbAuthority);
        jpAlterarCenter.add(jbAlterar);
        jpAlterarCenter.add(jbCancelar);
        jpAlterarCenter.setLayout(new GridLayout(6, 2));

        jbAlterar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jifAlterar.getContentPane().setLayout(new BorderLayout());
        jifAlterar.getContentPane().add(jpAlterarCenter, BorderLayout.CENTER);
        pack();
        ViewPrincipal.addFrame(jifAlterar);
    }

    private void atualizar() {
        jpListarCenter.removeAll();
        jtUsers = new JTable(controllerUser.linhas(), controllerUser.colunas());
        jpListarCenter.add(new JScrollPane(jtUsers));
        jpListarCenter.setLayout(new GridLayout(1, 1));
        jpListarCenter.revalidate();
        jtUsers.addMouseListener(this);
        if (jbEditar.isEnabled() == true) {
            jbEditar.setEnabled(false);
            jbImprimir.setEnabled(false);
        }
    }

    private void buttonBuscarConfirma() {
        controllerUser.setListarBusca(jtfBuscar.getText());
        jpBuscarCenter.removeAll();
        jtUsersBuscar = new JTable(controllerUser.linhasBusca(), controllerUser.colunas());
        jsp = new JScrollPane(jtUsersBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));
        jsp.revalidate();
    }

    private void buscar() {
        jifBuscar = new JInternalFrame();
        jifBuscar.setTitle("Consultar Usuários");
        jifBuscar.setSize(1250, 400);
        jifBuscar.setClosable(true);
        jifBuscar.setIconifiable(true);
        jifBuscar.setMaximizable(true);

        jpBuscarNorth = new JPanel();
        jpBuscarCenter = new JPanel();

        jtfBuscar = new JTextField();
        controllerUser.setListarBusca(jtfBuscar.getText());

        jbBuscarConfirma = new JButton();
        iiBuscaConfirma = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscarConfirma.setIcon(iiBuscaConfirma);
        jbBuscarConfirma.addActionListener(this);

        jpBuscarNorth.add(jtfBuscar);
        jpBuscarNorth.add(jbBuscarConfirma);
        jpBuscarNorth.setLayout(new GridLayout(1, 2));

        jtUsersBuscar = new JTable(controllerUser.linhasBusca(), controllerUser.colunas());
        jsp = new JScrollPane(jtUsersBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));

        jifBuscar.getContentPane().setLayout(new BorderLayout());
        jifBuscar.getContentPane().add(jpBuscarNorth, BorderLayout.NORTH);
        jifBuscar.getContentPane().add(jpBuscarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifBuscar);
    }

    public JInternalFrame listar() {
        jifListar = new JInternalFrame();
        jifListar.setTitle("Usuários Cadastrados");
        jifListar.setSize(1250, 400);
        jifListar.setClosable(true);
        jifListar.setIconifiable(true);
        jifListar.setMaximizable(true);

        jpListarCenter = new JPanel();
        jpListarNorth = new JPanel();

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

        jtUsers = new JTable(controllerUser.linhas(), controllerUser.colunas());
        jpListarCenter.add(new JScrollPane(jtUsers));
        jpListarCenter.setLayout(new GridLayout(1, 1));

        jpListarNorth.add(jbBuscar);
        jpListarNorth.add(jbRefresh);
        jpListarNorth.add(jbEditar);
        jpListarNorth.add(jbReport);
        jpListarNorth.add(jbImprimir);

        jbBuscar.addActionListener(this);
        jbRefresh.addActionListener(this);
        jbEditar.addActionListener(this);
        jbReport.addActionListener(this);
        jbImprimir.addActionListener(this);
        jtUsers.addMouseListener(this);

        jifListar.getContentPane().setLayout(new BorderLayout(5, 5));
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.NORTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        return jifListar;
    }

    public JInternalFrame salvar() {
        jifSalvar = new JInternalFrame();
        jifSalvar.setTitle("Cadastrar Usuários");
        jifSalvar.setClosable(true);
        jifSalvar.setIconifiable(true);
        jifSalvar.setSize(300, 200);

        jpSalvarCenter = new JPanel();

        jtfPkId = new JTextField();
        jtfPkId.setEnabled(false);
        jtfName = new JTextField();
        jpfPassword = new JPasswordField();
        jcbStatus = new JComboBox<>(ststus);
        jcbAuthority = new JComboBox<>(controllerUser.getFkAuthorityPkId());

        jbSalvar = new JButton("Salvar");
        iiSalvar = new ImageIcon(getClass().getResource("/img/button_salvar.png"));
        jbSalvar.setIcon(iiSalvar);

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jpSalvarCenter.add(jlPkId);
        jpSalvarCenter.add(jtfPkId);
        jpSalvarCenter.add(jlName);
        jpSalvarCenter.add(jtfName);
        jpSalvarCenter.add(jlPassword);
        jpSalvarCenter.add(jpfPassword);
        jpSalvarCenter.add(jlStatus);
        jpSalvarCenter.add(jcbStatus);
        jpSalvarCenter.add(jlAuthority);
        jpSalvarCenter.add(jcbAuthority);
        jpSalvarCenter.add(jbSalvar);
        jpSalvarCenter.add(jbCancelar);
        jpSalvarCenter.setLayout(new GridLayout(6, 2));

        jbSalvar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jifSalvar.getContentPane().setLayout(new BorderLayout());
        jifSalvar.getContentPane().add(jpSalvarCenter, BorderLayout.CENTER);
        pack();
        return jifSalvar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbSalvar) {
                ArrayList<String> novoUser = new ArrayList<>();
                novoUser.add(jtfName.getText());
                novoUser.add(jpfPassword.getText());
                novoUser.add((String) jcbStatus.getSelectedItem());
                novoUser.add((String) jcbAuthority.getSelectedItem());
                if (jtfName.getText().equals("") || jpfPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Há campos obrigatórios que não foram peenchidos!", "Dados obrigatórios", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (controllerUser.verificaUsuario(jtfName.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Este usuário já existe, por favor tente outro!", "Usuário existente...", JOptionPane.WARNING_MESSAGE);
                    } else {
                        controllerUser.saveUsers(novoUser);
                        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
                        jifSalvar.dispose();
                    }
                }
            } else if (obj == jbCancelar) {
                jtfName.setText("");
                jpfPassword.setText("");
                jcbStatus.setSelectedIndex(0);
                jcbStatus.setSelectedIndex(0);
            } else if (obj == jbAlterar) {
                ArrayList<String> novoUser = new ArrayList<>();
                novoUser.add(jtfPkId.getText());
                novoUser.add(jtfName.getText());
                novoUser.add(jpfPassword.getText());
                novoUser.add((String) jcbStatus.getSelectedItem());
                novoUser.add((String) jcbAuthority.getSelectedItem());
                controllerUser.editar(novoUser);
                JOptionPane.showMessageDialog(null, "Alteração Realizada Com Sucesso!", "Alteração Realizada", JOptionPane.INFORMATION_MESSAGE);
                jifAlterar.dispose();
            } else if (obj == jbBuscar) {
                buscar();
            } else if (obj == jbBuscarConfirma) {
                buttonBuscarConfirma();
            } else if (obj == jbRefresh) {
                atualizar();
            } else if (obj == jbEditar) {
                alterar();
                int linha;
                linha = jtUsers.getSelectedRow();
                jtfPkId.setText(jtUsers.getModel().getValueAt(linha, 0).toString());
                jtfName.setText(jtUsers.getModel().getValueAt(linha, 1).toString());
                jpfPassword.setText(jtUsers.getModel().getValueAt(linha, 2).toString());
                jcbStatus.setSelectedItem(jtUsers.getModel().getValueAt(linha, 3).toString());
                jcbAuthority.setSelectedItem(jtUsers.getModel().getValueAt(linha, 4).toString());
            } else if (obj == jbReport) {
                controllerUser.imprimirUser("Relatório de usuários cadastrados", "/report/relUsers.jasper");
            } else if (obj == jbImprimir) {
                int linha;
                linha = jtUsers.getSelectedRow();
                Integer user_pk_id = Integer.parseInt(jtUsers.getModel().getValueAt(linha, 0).toString());
                HashMap parametro = new HashMap();
                parametro.put("user_pk_id", user_pk_id);
                controllerUser.imprimirUsers("Imprimir Usuario", "/report/relUser.jasper", parametro);
            } else {
                JOptionPane.showMessageDialog(null, "Ação desconecida nada foi implementado!", "Vazio...", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro na tela User", exception);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jtUsers) {
            if (jtUsers.getSelectedRow() != -1) {
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
