    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerAuthority;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author geverson
 */
public class ViewAuthority extends JInternalFrame implements ActionListener, MouseListener {

    private JInternalFrame jifListar, jifSalvar, jifAlterar;
    private JPanel jpListarCenter, jpListarNorth, jpSalvarCenter, jpAlterarCenter, jpAlterarSouth;
    private final JLabel jlPkId, jlName, jlDescricao;
    private JButton jbBuscar, jbRefresh, jbEditar, jbReport, jbCancelar, jbSalvar, jbAlterar, jbDeletar, jbImprimir;
    private ImageIcon iiBuscar, iiRefresh, iiEditar, iiReport, iiCancelar, iiSalvar, iiAlterar, iiDeletar, iiImprimir;
    private JTable jtAuthority;
    private final ControllerAuthority controllerAuthority;
    private JTextField jtfPkId, jtfName, jtfDescricao;

    public ViewAuthority() {
        controllerAuthority = new ControllerAuthority();
        jlPkId = new JLabel("Id: ");
        jlName = new JLabel("Nome da Permissão: ");
        jlDescricao = new JLabel("Descrição: ");

    }

    private void atualizar() {
        jpListarCenter.removeAll();
        jtAuthority = new JTable(controllerAuthority.linhas(), controllerAuthority.colunas());
        jpListarCenter.add(new JScrollPane(jtAuthority));
        jpListarCenter.setLayout(new GridLayout(1, 1));
        jpListarCenter.revalidate();
        jtAuthority.addMouseListener(this);
        if (jbEditar.isEnabled() == true) {
            jbEditar.setEnabled(false);
            jbImprimir.setEnabled(false);
        }
    }

    public JInternalFrame listar() {
        jifListar = new JInternalFrame();
        jifListar.setTitle("Permissões cadastradas");
        jifListar.setSize(1250, 400);
        jifListar.setClosable(true);
        jifListar.setIconifiable(true);
        jifListar.setMaximizable(true);

        jpListarCenter = new JPanel();
        jpListarNorth = new JPanel();

        jbBuscar = new JButton("Buscar");
        iiBuscar = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscar.setIcon(iiBuscar);
        jbBuscar.setEnabled(false);

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

        jtAuthority = new JTable(controllerAuthority.linhas(), controllerAuthority.colunas());
        jpListarCenter.add(new JScrollPane(jtAuthority));
        jpListarCenter.setLayout(new GridLayout(1, 1));

        jpListarNorth.add(jbBuscar);
        jpListarNorth.add(jbRefresh);
        jpListarNorth.add(jbEditar);
        jpListarNorth.add(jbReport);
        jpListarNorth.add(jbImprimir);

        jtAuthority.addMouseListener(this);

        jbBuscar.addActionListener(this);
        jbRefresh.addActionListener(this);
        jbEditar.addActionListener(this);
        jbReport.addActionListener(this);
        jbImprimir.addActionListener(this);
        jifListar.getContentPane().setLayout(new BorderLayout(5, 5));
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.NORTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        return jifListar;
    }

    public JInternalFrame salvar() {
        jifSalvar = new JInternalFrame();
        jifSalvar.setTitle("Cadastrar Permissão");
        jifSalvar.setClosable(true);
        jifSalvar.setIconifiable(true);
        jifSalvar.setSize(300, 200);

        jpSalvarCenter = new JPanel();

        jtfPkId = new JTextField();
        jtfPkId.setEnabled(false);
        jtfName = new JTextField();
        jtfDescricao = new JTextField();

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
        jpSalvarCenter.add(jlDescricao);
        jpSalvarCenter.add(jtfDescricao);
        jpSalvarCenter.add(jbCancelar);
        jpSalvarCenter.add(jbSalvar);
        jpSalvarCenter.setLayout(new GridLayout(4, 2));

        jbSalvar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jifSalvar.getContentPane().setLayout(new BorderLayout());
        jifSalvar.getContentPane().add(jpSalvarCenter, BorderLayout.CENTER);
        pack();
        return jifSalvar;
    }

    public void deletar() {
        boolean b = controllerAuthority.deletar(Integer.parseInt(jtfPkId.getText()));
        if (b == false) {
            JOptionPane.showMessageDialog(null, "Permissão não foi possível remover esta permissão", "Removendo...", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Permissão removida com sucesso", "Removendo...", JOptionPane.INFORMATION_MESSAGE);
            jifAlterar.dispose();
        }
    }

    public void editar() {
        jifAlterar = new JInternalFrame();
        jifAlterar.setTitle("Alterar dados de usuário");
        jifAlterar.setClosable(true);
        jifAlterar.setIconifiable(true);
        jifAlterar.setSize(350, 200);

        jpAlterarCenter = new JPanel();
        jpAlterarSouth = new JPanel();

        jtfPkId = new JTextField();
        jtfPkId.setEnabled(false);
        jtfName = new JTextField();
        jtfName.setEnabled(false);
        jtfDescricao = new JTextField();

        jbAlterar = new JButton("Alterar");
        iiAlterar = new ImageIcon(getClass().getResource("/img/button_confirmar.png"));
        jbAlterar.setIcon(iiAlterar);

        jbDeletar = new JButton("Deletar");
        iiDeletar = new ImageIcon(getClass().getResource("/img/button_deletar.png"));
        jbDeletar.setIcon(iiDeletar);
        jbDeletar.setEnabled(false);

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jpAlterarCenter.add(jlPkId);
        jpAlterarCenter.add(jtfPkId);
        jpAlterarCenter.add(jlName);
        jpAlterarCenter.add(jtfName);
        jpAlterarCenter.add(jlDescricao);
        jpAlterarCenter.add(jtfDescricao);
        jpAlterarCenter.setLayout(new GridLayout(3, 2));

        jpAlterarSouth.add(jbAlterar);
        jpAlterarSouth.add(jbDeletar);
        jpAlterarSouth.add(jbCancelar);

        jbAlterar.addActionListener(this);
        jbDeletar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jifAlterar.getContentPane().setLayout(new BorderLayout());
        jifAlterar.getContentPane().add(jpAlterarCenter, BorderLayout.CENTER);
        jifAlterar.getContentPane().add(jpAlterarSouth, BorderLayout.SOUTH);
        pack();
        ViewPrincipal.addFrame(jifAlterar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbReport) {
                controllerAuthority.imprimirAuthorities("Relatório das permissões cadastradas", "/report/relAuthorities.jasper");
            } else if (obj == jbSalvar) {
                if (jtfName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Há campos obrigatórios que não foram peenchidos!", "Dados obrigatórios", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (!controllerAuthority.verificaAuthority(jtfName.getText())) {
                        JOptionPane.showMessageDialog(null, "Este usuário já existe, por favor tente outro!", "Usuário existente...", JOptionPane.WARNING_MESSAGE);
                    } else {
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(jtfName.getText());
                        arrayList.add(jtfDescricao.getText());
                        controllerAuthority.salvar(arrayList);
                        JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
                        jifSalvar.dispose();
                    }
                }
            } else if (obj == jbCancelar) {
                jtfName.setText(null);
                jtfName.requestFocus();
                jtfDescricao.setText(null);
            } else if (obj == jbEditar) {
                editar();
                int linha;
                linha = jtAuthority.getSelectedRow();
                jtfPkId.setText(jtAuthority.getModel().getValueAt(linha, 0).toString());
                jtfName.setText(jtAuthority.getModel().getValueAt(linha, 1).toString());
                jtfDescricao.setText(jtAuthority.getModel().getValueAt(linha, 2).toString());
            } else if (obj == jbDeletar) {
                deletar();
            } else if (obj == jbRefresh) {
                atualizar();
            } else if (obj == jbAlterar) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(jtfPkId.getText());
                arrayList.add(jtfName.getText());
                arrayList.add(jtfDescricao.getText());
                boolean b = controllerAuthority.editar(arrayList);
                if (b == true) {
                    JOptionPane.showMessageDialog(null, "Alteração Realizada Com Sucesso!", "Alteração Realizada", JOptionPane.INFORMATION_MESSAGE);
                    jifAlterar.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Houve um erro al realizar alteração", "Alterando...", JOptionPane.ERROR_MESSAGE);
                }
            } else if (obj == jbImprimir) {
                int linha;
                linha = jtAuthority.getSelectedRow();
                Integer auth_pk_id = Integer.parseInt(jtAuthority.getModel().getValueAt(linha, 0).toString());
                HashMap parametro = new HashMap();
                parametro.put("auth_pk_id", auth_pk_id);
                controllerAuthority.imprimirAuthority("Imprimir Permissão", "/report/relAuthority.jasper", parametro);
            } else {
                JOptionPane.showMessageDialog(null, "Ação desconecida nada foi implementado!", "Vazio...", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro na tela de Permissões", exception);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jtAuthority) {
            if (jtAuthority.getSelectedRow() != -1) {
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
