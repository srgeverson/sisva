/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerAcesso;
import controller.ControllerUser;
import index.IndexCalendario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Users;

/**
 *
 * @author geverson
 */
public class ViewPrincipal extends JFrame implements ActionListener, WindowListener, KeyListener, FocusListener {

    private static ViewDesktop viewDesktop;
    private JPanel jpRodape;
    private JTextField jtfUsuario;
    private JLabel jlUsuario, jlAuthority, jlData, jlHora, jlSessaoAutority, jlSessaoData, jlSessaoHora;
    private JMenuBar jmbBarra;
    private JMenu jmArquivo, jmCadastro, jmConfiguracao, jmLogs, jmSobre, jmAjuda;
    private ImageIcon iiArquivo, iiCadastro, iiConfiguracao, iiLogs, iiSobre, iiAjuda,
            iiArquivoTrocarUsuario, iiArquivoEncerrarSessao, iiArquivoSair,
            iiCadastroUsuario, iiCadastroAuthority,
            iiConfiguracaoAythority, iiConfiguracaoUsuario, iiConfiguracaoAcesso, iiConfiguracaoErro,
            iiLogAcesso, iiLogAjuda, iiLogAuthority, iiLogUser, iiLogErro,
            iiSobreSistema, iiSobreDesenvolvedor, iiAjudaListadas, iiSobreAjuda;
    private JMenuItem jmiArquivoTrocarUsuario, jmiArquivoEncerrarSessao, jmiArquivoSair,
            jmiCadastroUsuario, jmiCadastroAuthority,
            jmiConfiguracaoAythority, jmiConfiguracaoUsuario, jmiConfiguracaoAcesso, jmiConfiguracaoErro, jmiConfiguracaoTeste,
            jmiLogAcesso, jmiLogAjuda, jmiLogAuthority, jmiLogUser, jmiLogErro,
            jmiSobreSistema, jmiSobreDesenvolvedor, jmiAjudaListar, jmiAjudaCadastrar;
    private IndexCalendario ic;
    public static String user = "UserNaoAuteticado";
    public static String aut = "AuthorityNaoAutenticado";
    public static String sta = "INATIVO";
    private JLabel jlStatus;
    private JLabel jlSessaoStatus;
    private ControllerAcesso controllerAcesso;
    private JMenuItem jmiSobreBibliografia;
    private ImageIcon iiSobreBibliografia;

    public ViewPrincipal(Users u) {
        this.setTitle("Área de Trabalho SisCV - Sistema de Controle de Vendas");
        this.setSize(800, 700);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.setJMenuBar(barraMenu());

        this.addWindowListener(this);
        this.jmiArquivoTrocarUsuario.addActionListener(this);
        this.jmiArquivoEncerrarSessao.addActionListener(this);
        this.jmiArquivoSair.addActionListener(this);
        this.jmiSobreSistema.addActionListener(this);
        this.jmiSobreDesenvolvedor.addActionListener(this);
        this.jmiSobreBibliografia.addActionListener(this);
        this.jmiAjudaCadastrar.addActionListener(this);
        this.jmiAjudaListar.addActionListener(this);
        this.jmiCadastroUsuario.addActionListener(this);
        this.jmiCadastroAuthority.addActionListener(this);
        this.jmiConfiguracaoUsuario.addActionListener(this);
        this.jmiConfiguracaoErro.addActionListener(this);
        this.jmiConfiguracaoTeste.addActionListener(this);
        this.jmiConfiguracaoAcesso.addActionListener(this);
        this.jmiConfiguracaoAythority.addActionListener(this);
        this.jmiLogAcesso.addActionListener(this);
        this.jmiLogAjuda.addActionListener(this);
        this.jmiLogAuthority.addActionListener(this);
        this.jmiLogUser.addActionListener(this);
        this.jmiLogErro.addActionListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centro(), BorderLayout.CENTER);
        this.getContentPane().add(rodape(), BorderLayout.SOUTH);
        this.pack();
        if (this.jtfUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "\tDesculpe, mas não é possível"
                    + "\niniciar o sistema com sessão vazia."
                    + "\nVocê precisa autenticar o usuário.", "Sessão vazia", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            validarPermissoes(u);

        }
    }

    private JDesktopPane centro() {
        ViewPrincipal.viewDesktop = new ViewDesktop();
        return ViewPrincipal.viewDesktop;
    }

    private JPanel rodape() {
        this.jpRodape = new JPanel();
        this.jpRodape.setBorder(BorderFactory.createTitledBorder("Dados da Sessão"));
        this.jlUsuario = new JLabel("Usuario logado: ");
        this.jtfUsuario = new JTextField(user);
        this.jtfUsuario.addKeyListener(this);
        this.jtfUsuario.addFocusListener(this);
        this.jtfUsuario.setEditable(false);
        this.jlAuthority = new JLabel("Permissão: ");
        this.jlSessaoAutority = new JLabel(aut);
        this.jlStatus = new JLabel("Status: ");
        this.jlSessaoStatus = new JLabel(sta);
        this.jlData = new JLabel("Data: ");
        this.jlSessaoData = new JLabel("00/00/0000");
        this.jlHora = new JLabel("Hora: ");
        this.jlSessaoHora = new JLabel("00:00:00");
        mostrarHora();
        this.jlSessaoData.setText(ic.mostraData());
        this.jpRodape.add(this.jlUsuario);
        this.jpRodape.add(this.jtfUsuario);
        this.jpRodape.add(this.jlAuthority);
        this.jpRodape.add(this.jlSessaoAutority);
        this.jpRodape.add(this.jlStatus);
        this.jpRodape.add(this.jlSessaoStatus);
        this.jpRodape.add(this.jlData);
        this.jpRodape.add(this.jlSessaoData);
        this.jpRodape.add(this.jlHora);
        this.jpRodape.add(this.jlSessaoHora);
        this.jpRodape.setLayout(new GridLayout(1, 10));
        return this.jpRodape;
    }

    private JMenuBar barraMenu() {
        jmbBarra = new JMenuBar();
        menu();
        jmbBarra.add(jmArquivo);
        jmbBarra.add(jmCadastro);
        jmbBarra.add(jmConfiguracao);
        jmbBarra.add(jmLogs);
        jmbBarra.add(jmSobre);
        jmbBarra.add(jmAjuda);
        return jmbBarra;

    }

    private void menu() {
        menuItem();
        jmArquivo = new JMenu("Arquivo");
        iiArquivo = new ImageIcon(getClass().getResource("/img/barra_arquivo.png"));
        jmArquivo.setIcon(iiArquivo);
        jmArquivo.setMnemonic('A');

        jmCadastro = new JMenu("Cadastro");
        iiCadastro = new ImageIcon(getClass().getResource("/img/barra_cadastro.png"));
        jmCadastro.setIcon(iiCadastro);
        jmCadastro.setMnemonic('C');

        jmConfiguracao = new JMenu("Configuração");
        iiConfiguracao = new ImageIcon(getClass().getResource("/img/barra_configuracao.png"));
        jmConfiguracao.setIcon(iiConfiguracao);
        jmConfiguracao.setMnemonic('F');

        jmLogs = new JMenu("Logs");
        iiLogs = new ImageIcon(getClass().getResource("/img/barra_log.png"));
        jmLogs.setIcon(iiLogs);
        jmLogs.setMnemonic('L');

        jmSobre = new JMenu("Sobre");
        iiSobre = new ImageIcon(getClass().getResource("/img/barra_sobre.png"));
        jmSobre.setIcon(iiSobre);
        jmSobre.setMnemonic('S');

        jmAjuda = new JMenu("Ajuda");
        iiAjuda = new ImageIcon(getClass().getResource("/img/barra_ajuda.png"));
        jmAjuda.setIcon(iiAjuda);
        jmAjuda.setMnemonic('J');

        jmArquivo.add(jmiArquivoTrocarUsuario);
        jmArquivo.add(jmiArquivoEncerrarSessao);
        jmArquivo.add(jmiArquivoSair);

        jmCadastro.add(jmiCadastroUsuario);
        jmCadastro.add(jmiCadastroAuthority);

        jmConfiguracao.add(jmiConfiguracaoUsuario);
        jmConfiguracao.add(jmiConfiguracaoAythority);
        jmConfiguracao.add(jmiConfiguracaoAcesso);
        jmConfiguracao.add(jmiConfiguracaoErro);
        jmConfiguracao.add(jmiConfiguracaoTeste);

        jmLogs.add(jmiLogAcesso);
        jmLogs.add(jmiLogAjuda);
        jmLogs.add(jmiLogAuthority);
        jmLogs.add(jmiLogUser);
        jmLogs.add(jmiLogErro);

        jmSobre.add(jmiSobreSistema);
        jmSobre.add(jmiSobreDesenvolvedor);
        jmSobre.add(jmiSobreBibliografia);

        jmAjuda.add(jmiAjudaCadastrar);
        jmAjuda.add(jmiAjudaListar);
    }

    private void menuItem() {
        jmiArquivoTrocarUsuario = new JMenuItem("Trocar de Usuário");
        iiArquivoTrocarUsuario = new ImageIcon(getClass().getResource("/img/menu_item_trocar_usuario.png"));
        jmiArquivoTrocarUsuario.setIcon(iiArquivoTrocarUsuario);
        jmiArquivoTrocarUsuario.setMnemonic('T');

        jmiArquivoEncerrarSessao = new JMenuItem("Encerrar Sessão");
        iiArquivoEncerrarSessao = new ImageIcon(getClass().getResource("/img/menu_item_encerrar_sessao.png"));
        jmiArquivoEncerrarSessao.setIcon(iiArquivoEncerrarSessao);
        jmiArquivoEncerrarSessao.setMnemonic('E');

        jmiArquivoSair = new JMenuItem("Sair");
        iiArquivoSair = new ImageIcon(getClass().getResource("/img/menu_item_sair.png"));
        jmiArquivoSair.setIcon(iiArquivoSair);
        jmiArquivoSair.setMnemonic('S');

        jmiCadastroUsuario = new JMenuItem("Cadastro de Usuários");
        iiCadastroUsuario = new ImageIcon(getClass().getResource("/img/menu_item_configuracao_user_new.png"));
        jmiCadastroUsuario.setIcon(iiCadastroUsuario);
        jmiCadastroUsuario.setMnemonic('U');

        jmiCadastroAuthority = new JMenuItem("Cadastro de Permissões");
        iiCadastroAuthority = new ImageIcon(getClass().getResource("/img/menu_item_cadastro_authority.png"));
        jmiCadastroAuthority.setIcon(iiCadastroAuthority);
        jmiCadastroAuthority.setMnemonic('P');

        jmiConfiguracaoAythority = new JMenuItem("Permisões dos usuários");
        iiConfiguracaoAythority = new ImageIcon(getClass().getResource("/img/menu_item_authority.png"));
        jmiConfiguracaoAythority.setIcon(iiConfiguracaoAythority);
        jmiConfiguracaoAythority.setMnemonic('P');

        jmiConfiguracaoErro = new JMenuItem("Erros do sistema");
        iiConfiguracaoErro = new ImageIcon(getClass().getResource("/img/menu_item_erro.png"));
        jmiConfiguracaoErro.setIcon(iiConfiguracaoErro);
        jmiConfiguracaoErro.setMnemonic('E');

        jmiConfiguracaoUsuario = new JMenuItem("Configuração do usuário");
        iiConfiguracaoUsuario = new ImageIcon(getClass().getResource("/img/menu_item_configuracao_user_new.png"));
        jmiConfiguracaoUsuario.setIcon(iiConfiguracaoUsuario);
        jmiConfiguracaoUsuario.setMnemonic('U');

        jmiConfiguracaoAcesso = new JMenuItem("Acessos ao sistema");
        iiConfiguracaoAcesso = new ImageIcon(getClass().getResource("/img/menu_item_configuracao_acesso.png"));
        jmiConfiguracaoAcesso.setIcon(iiConfiguracaoAcesso);
        jmiConfiguracaoAcesso.setMnemonic('A');

        jmiConfiguracaoTeste = new JMenuItem("Testes");
        jmiConfiguracaoTeste.setEnabled(false);

        jmiLogAcesso = new JMenuItem("Log Acessos");
        iiLogAcesso = new ImageIcon(getClass().getResource("/img/menu_item_log_acesso.png"));
        jmiLogAcesso.setIcon(iiLogAcesso);
        jmiLogAcesso.setMnemonic('A');

        jmiLogAjuda = new JMenuItem("Log Ajuda");
        iiLogAjuda = new ImageIcon(getClass().getResource("/img/menu_item_log_ajuda.png"));
        jmiLogAjuda.setIcon(iiLogAjuda);
        jmiLogAjuda.setMnemonic('J');

        jmiLogAuthority = new JMenuItem("Log Permissões");
        iiLogAuthority = new ImageIcon(getClass().getResource("/img/menu_item_log_authority.png"));
        jmiLogAuthority.setIcon(iiLogAuthority);
        jmiLogAuthority.setMnemonic('P');

        jmiLogUser = new JMenuItem("Log Usuários");
        iiLogUser = new ImageIcon(getClass().getResource("/img/menu_item_log_users.png"));
        jmiLogUser.setIcon(iiLogUser);
        jmiLogUser.setMnemonic('U');

        jmiLogErro = new JMenuItem("Log Erros");
        iiLogErro = new ImageIcon(getClass().getResource("/img/menu_item_erro.png"));
        jmiLogErro.setIcon(iiLogErro);
        jmiLogErro.setMnemonic('E');

        jmiSobreSistema = new JMenuItem("Sobre Sistema");
        iiSobreSistema = new ImageIcon(getClass().getResource("/img/sobre_desenvolvedor.png"));
        jmiSobreSistema.setIcon(iiSobreSistema);
        jmiSobreSistema.setMnemonic('S');

        jmiSobreDesenvolvedor = new JMenuItem("Sobre Desenvolvedor");
        iiSobreDesenvolvedor = new ImageIcon(getClass().getResource("/img/sobre_desenvolvedor.png"));
        jmiSobreDesenvolvedor.setIcon(iiSobreDesenvolvedor);
        jmiSobreDesenvolvedor.setMnemonic('D');

        jmiSobreBibliografia = new JMenuItem("Sobre Bibliografia");
        iiSobreBibliografia = new ImageIcon(getClass().getResource("/img/sobre_bibliografia.png"));
        jmiSobreBibliografia.setIcon(iiSobreBibliografia);
        jmiSobreBibliografia.setMnemonic('B');

        jmiAjudaListar = new JMenuItem("Ajuda/Sugestão Cadastradas");
        iiAjudaListadas = new ImageIcon(getClass().getResource("/img/ajuda_listadas.png"));
        jmiAjudaListar.setIcon(iiAjudaListadas);
        jmiAjudaListar.setMnemonic('A');

        jmiAjudaCadastrar = new JMenuItem("Ajuda Sistema");
        iiSobreAjuda = new ImageIcon(getClass().getResource("/img/ajuda_sistema.png"));
        jmiAjudaCadastrar.setIcon(iiSobreAjuda);
        jmiAjudaCadastrar.setMnemonic('S');
    }

    public static void addFrame(JInternalFrame internalFrame) {
        Dimension dmDesktop = viewDesktop.getSize();
        Dimension dmInternal = internalFrame.getSize();
        internalFrame.setLocation((dmDesktop.width - dmInternal.width) / 2, (dmDesktop.height - dmInternal.height) / 2);
        viewDesktop.add(internalFrame);
        internalFrame.setVisible(true);
    }

    private void sairDoSistema() {
        if (viewDesktop.getAllFrames().length > 0) {
            JOptionPane.showMessageDialog(null, "Não é possível fechar o sistema,"
                    + "\npor que há " + viewDesktop.getAllFrames().length + " janela(s) aberta(s)", "Operação impossível", JOptionPane.ERROR_MESSAGE);
        } else {
            int conf = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar"
                    + "\n o sistema?", "Saindo...", JOptionPane.YES_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private void mostrarHora() {
        ic = new IndexCalendario(jlSessaoHora);
        Thread thHora = ic;
        thHora.start();
    }

    public void fechaInternalFrames() {
        JInternalFrame[] frames = viewDesktop.getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] != null && !frames[i].isClosed()) {
                frames[i].dispose();
            }
        }
    }

    private void salvarAcesso() {
        controllerAcesso = new ControllerAcesso();
        jtfUsuario.setText(user);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(jlSessaoData.getText());
        arrayList.add(jlSessaoHora.getText());
        arrayList.add(jtfUsuario.getText());
        controllerAcesso.salvar(arrayList);
    }

    private void validarPermissoes(Users u) {
        ViewPrincipal.user = u.getUserName();
        ViewPrincipal.aut = u.getUserFkAuthorityPkId().toString();
        ViewPrincipal.sta = u.getUserStatus();
        if (sta.equals("INATIVO")) {
            JOptionPane.showMessageDialog(null, "Seu usuário está cadastrado, mas não pode acessar"
                    + "as telas do sistema, \ninforme o administrador."
                    + "\nAcesse o menu Ajuda > Ajuda sistema e descreva o acontecimento!", "Usuário INATIVO", JOptionPane.WARNING_MESSAGE);
            jmCadastro.setEnabled(false);
            jmConfiguracao.setEnabled(false);
            jmLogs.setEnabled(false);
            jmSobre.setEnabled(false);
            jmiAjudaListar.setEnabled(false);
        }
        String permissao = u.getUserFkAuthorityPkId().toString();
        switch (permissao) {
            case "TI":
                JOptionPane.showMessageDialog(null, "Por favor tome cuidado com as alterações que forem feitas,"
                        + "\n pois você não possui restrição de acesso", "Acesso restrito", JOptionPane.WARNING_MESSAGE);
                break;
            case "CONSULTAS":
                jmCadastro.setEnabled(false);
                jmConfiguracao.setEnabled(false);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Permissão desconhecida, até o momento você não tem permissão"
                        + "\n necessária para acessar o sistema, aguarde a próxima atualização", "Acesso violado...", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                break;
        }
        salvarAcesso();
        this.setVisible(true);
    }

    public void autenticarSessao() {
        if (!jtfUsuario.getText().equals(user)) {
            ControllerUser controllerUser = new ControllerUser();
            Users u = controllerUser.logar(jtfUsuario.getText());
            if (!controllerUser.verificaUsuario(jtfUsuario.getText())) {
                String senha = JOptionPane.showInputDialog(null, "Digite a senha para autenticar o usuário", "Autenticação solicitada", JOptionPane.WARNING_MESSAGE);
                if (u.getUserName().equals(jtfUsuario.getText()) && u.getUserPassword().equals(senha)) {
                    validarPermissoes(u);
                } else {
                    JOptionPane.showMessageDialog(null, "Operação incompleta. \nDados do usuário não conferem, repita a operação", "Autenticação inválida", JOptionPane.ERROR_MESSAGE);
                    jtfUsuario.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Este usuário não existe, tente outro", "Usuário inexistente", JOptionPane.ERROR_MESSAGE);
                jtfUsuario.requestFocus();
            }
        } else {
            jtfUsuario.setText(user);
            jtfUsuario.setEditable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jmiArquivoTrocarUsuario) {
                jtfUsuario.setEditable(true);
                jtfUsuario.requestFocus();
            } else if (obj == jmiArquivoEncerrarSessao) {
                this.dispose();
                new ViewLogin().setVisible(true);
            } else if (obj == jmiArquivoSair) {
                sairDoSistema();
            } else if (obj == jmiCadastroUsuario) {
                ViewUsers vu = new ViewUsers();
                addFrame(vu.salvar());
            } else if (obj == jmiCadastroAuthority) {
                ViewAuthority va = new ViewAuthority();
                addFrame(va.salvar());
            } else if (obj == jmiConfiguracaoUsuario) {
                ViewUsers vu = new ViewUsers();
                addFrame(vu.listar());
            } else if (obj == jmiConfiguracaoAcesso) {
                ViewAcesso va = new ViewAcesso();
                addFrame(va.listar());
            } else if (obj == jmiConfiguracaoAythority) {
                ViewAuthority va = new ViewAuthority();
                addFrame(va.listar());
            } else if (obj == jmiConfiguracaoErro) {
                ViewErro viewErro = new ViewErro();
                viewErro.listarErros();
            } else if (obj == jmiConfiguracaoTeste) {
                ViewTeste vt = new ViewTeste();
                vt.listar();
            } else if (obj == jmiLogAcesso) {
                ViewLogAcesso vla = new ViewLogAcesso();
                addFrame(vla.listar());
            } else if (obj == jmiLogAjuda) {
                ViewLogAjuda vla = new ViewLogAjuda();
                addFrame(vla.listar());
            } else if (obj == jmiLogAuthority) {
                ViewLogAuthority vla = new ViewLogAuthority();
                addFrame(vla.listar());
            } else if (obj == jmiLogUser) {
                ViewLogUsers vlu = new ViewLogUsers();
                addFrame(vlu.listar());
            } else if (obj == jmiLogErro) {
                ViewLogErro vle = new ViewLogErro();
                addFrame(vle.listar());
            } else if (obj == jmiSobreSistema) {
                ViewSobre vs = new ViewSobre();
                vs.sobreSistema();
            } else if (obj == jmiSobreDesenvolvedor) {
                ViewSobre vs = new ViewSobre();
                vs.sobreDesenvolvedor();
            } else if (obj == jmiSobreBibliografia) {
                ViewSobre vs = new ViewSobre();
                vs.sobreBibliografia();
            } else if (obj == jmiAjudaCadastrar) {
                ViewAjuda va = new ViewAjuda();
                addFrame(va.cadastrar());
            } else if (obj == jmiAjudaListar) {
                ViewAjuda va = new ViewAjuda();
                addFrame(va.listar());
            } else {
                JOptionPane.showMessageDialog(null, "Ação desconecida nada foi implementado!", "Vazio...", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro na tela principal...", exception);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        sairDoSistema();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            autenticarSessao();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == jtfUsuario) {
            if (user.equals(jtfUsuario.getText())) {
                jtfUsuario.setText(user);
                jtfUsuario.setEditable(false);
            }
        }
    }

}
