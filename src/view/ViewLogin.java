/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerUser;
import index.IndexCalendario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Users;

/**
 *
 * @author geverson
 */
public final class ViewLogin extends JFrame implements ActionListener {

    private JPanel jpTopo, jpCentro, jpRodape;
    private ImageIcon iiLogo, iiEntrar, iiCancelar;
    private JLabel jlLogo, jlUsuario, jlSenha, jlHora, jlData, jlHoraReal, jlDataReal;
    private JTextField jtfUsuarioNome;
    private JButton jbEntrar, jbCancelar;
    private JPasswordField jpfUsuarioSenha;
    private IndexCalendario ic;
    private final ControllerUser controllerUser;

    public ViewLogin() {
        this.setTitle("Login - SisCV");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        controllerUser = new ControllerUser();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(topo(), BorderLayout.NORTH);
        this.getContentPane().add(centro(), BorderLayout.CENTER);
        this.getContentPane().add(rodape(), BorderLayout.SOUTH);
        this.pack();

        this.jbEntrar.addActionListener(this);
        this.jbCancelar.addActionListener(this);
    }

    private JPanel topo() {
        this.jpTopo = new JPanel();
        this.jlLogo = new JLabel();
        this.jlLogo.setToolTipText("SisCV - Sistema de Controle de Vendas");
        this.iiLogo = new ImageIcon(getClass().getResource("/img/logo_siscv.png"));
        this.jlLogo.setIcon(this.iiLogo);
        this.jpTopo.add(this.jlLogo);
        return this.jpTopo;
    }

    private JPanel centro() {
        this.jpCentro = new JPanel();
        this.jpCentro.setBorder(BorderFactory.createTitledBorder("Autenticação"));
        this.jlUsuario = new JLabel("Nome do Usuário: ");
        this.jtfUsuarioNome = new JTextField();
        this.jlSenha = new JLabel("Senha do Usuário: ");
        this.jpfUsuarioSenha = new JPasswordField();

        this.jbEntrar = new JButton("Entrar");
        this.iiEntrar = new ImageIcon(getClass().getResource("/img/button_login.png"));
        this.jbEntrar.setIcon(iiEntrar);

        this.jbCancelar = new JButton("Cancelar");
        this.iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        this.jbCancelar.setIcon(iiCancelar);

        this.jpCentro.add(this.jlUsuario);
        this.jpCentro.add(this.jtfUsuarioNome);
        this.jpCentro.add(this.jlSenha);
        this.jpCentro.add(this.jpfUsuarioSenha);
        this.jpCentro.add(this.jbEntrar);
        this.jpCentro.add(this.jbCancelar);
        this.jpCentro.setLayout(new GridLayout(3, 2));
        return this.jpCentro;
    }

    private JPanel rodape() {
        this.jpRodape = new JPanel();
        this.jlHora = new JLabel("Hora: ");
        this.jlHoraReal = new JLabel();
        mostrarHora();
        this.jlData = new JLabel("Data: ");
        this.jlDataReal = new JLabel(ic.mostraData());
        this.jpRodape.add(this.jlData);
        this.jpRodape.add(this.jlDataReal);
        this.jpRodape.add(this.jlHora);
        this.jpRodape.add(this.jlHoraReal);
        this.jpRodape.setLayout(new GridLayout(1, 4));
        return this.jpRodape;
    }

    private void mostrarHora() {
        ic = new IndexCalendario(jlHoraReal);
        Thread thHora = ic;
        thHora.start();
    }

    private void preencheSessao() {
        Users u = new Users();
        u = controllerUser.logar(jtfUsuarioNome.getText());
        if (u != null) {
            if (u.getUserName().equals(jtfUsuarioNome.getText()) && u.getUserPassword().equals(jpfUsuarioSenha.getText())) {
                ViewPrincipal vp = new ViewPrincipal(u);
                vp.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e Senha não conferem", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não existe", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbEntrar) {
                preencheSessao();
            } else if (obj == jbCancelar) {
                jtfUsuarioNome.setText(null);
                jpfUsuarioSenha.setText(null);
                jtfUsuarioNome.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Desculpe, mas esta ação não foi implementada!", "Ação desconhecida", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro na tela de login", exception);
        }
    }

}
