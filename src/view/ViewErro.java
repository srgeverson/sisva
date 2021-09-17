/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerErro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
public class ViewErro extends JDialog implements ActionListener, MouseListener {

    private JDialog jdSalvar;
    private JInternalFrame jifAlterar, jifListar;
    private JPanel jpCentro, jpRodape, jpTopo, jpListarCenter, jpListarNorth;
    private JButton jbCancelar, jbSalvar, jbAlterar, jbEditar, jbRefresh, jbReport;
    private ImageIcon iiCancelar, iiSalvar, iiAlterar, iiEditar, iiRefresh, iiReport;
    private final JLabel jlPkId, jlUsuario, jlMaquina, jlIpLocal, jlIpExterno, jlObservacao;
    private JTextArea jtaDescricao, jtaObservacao;
    private JTextField jtfPkId, jtfUsuario, jtfNomeMaquina, jtfIpLocal, jtfIpExterno;
    private JTable jtErro;
    private JScrollPane jsp;
    private ArrayList<String> list;
    private InetAddress i;
    private String ip = "Não definido";
    private ControllerErro ce;

    private String erro;
    private ControllerErro controllerErro;
    private JButton jbImprimir;
    private ImageIcon iiImprimir;

    public ViewErro() {
        controllerErro = new ControllerErro();
        jlPkId = new JLabel("Código: ");
        jlUsuario = new JLabel("Usuário: ");
        jlMaquina = new JLabel("Maquina: ");
        jlIpLocal = new JLabel("IP Local: ");
        jlIpExterno = new JLabel("IP Externo: ");
        jlObservacao = new JLabel("Observação: ");
    }

    public void salvarErro(String title, Exception exception) {
        jdSalvar = new JDialog();
        this.jdSalvar.setTitle(title);
        this.jdSalvar.setSize(600, 400);

        erro = "Detalhes"
                + "\nOrigem do erro: " + title
                + "\ngetLocalizedMessage: " + exception.getLocalizedMessage()
                + "\ngetMessage: " + exception.getMessage()
                + "\ntoString: " + exception.toString()
                + "\nExption:\n " + exception;

        list = new ArrayList<>();
        indentificar();
        jpTopo = new JPanel();
        jpCentro = new JPanel();
        jpRodape = new JPanel();

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jbSalvar = new JButton("Salvar");
        iiSalvar = new ImageIcon(getClass().getResource("/img/button_salvar.png"));
        jbSalvar.setIcon(iiSalvar);

        jtaDescricao = new JTextArea(erro);
        jtaDescricao.setForeground(Color.red);
        jtaDescricao.setEditable(false);
        jtfNomeMaquina = new JTextField("" + i);
        jtfNomeMaquina.setEditable(false);
        jtfNomeMaquina.setForeground(Color.WHITE);
        jtfNomeMaquina.setBackground(Color.BLACK);
        jtfIpLocal = new JTextField(list.get(1));
        jtfIpLocal.setEditable(false);
        jtfIpLocal.setForeground(Color.WHITE);
        jtfIpLocal.setBackground(Color.BLACK);
        jtfIpExterno = new JTextField(ip);
        jtfIpExterno.setEditable(false);
        jtfIpExterno.setForeground(Color.WHITE);
        jtfIpExterno.setBackground(Color.BLACK);

        jpTopo.add(new JLabel("Maquina:"));
        jpTopo.add(jtfNomeMaquina);
        jpTopo.add(new JLabel("IP localnet:"));
        jpTopo.add(jtfIpLocal);
        jpTopo.add(new JLabel("IP internet:"));
        jpTopo.add(jtfIpExterno);
        jpTopo.setLayout(new GridLayout(3, 2));

        jpCentro.add(this.jtaDescricao);
        jpCentro.setBorder(BorderFactory.createTitledBorder("Descrição"));
        jpCentro.setLayout(new GridLayout(1, 1));

        jpRodape.add(this.jbCancelar);
        jpRodape.add(this.jbSalvar);
        jbSalvar.addActionListener(this);
        jbCancelar.addActionListener(this);
        this.jdSalvar.getContentPane().setLayout(new BorderLayout());
        this.jdSalvar.getContentPane().add(this.jpTopo, BorderLayout.NORTH);
        this.jdSalvar.getContentPane().add(this.jpCentro, BorderLayout.CENTER);
        this.jdSalvar.getContentPane().add(this.jpRodape, BorderLayout.SOUTH);
        this.jdSalvar.setModal(true);
        this.jdSalvar.setLocationRelativeTo(null);
        this.jdSalvar.setVisible(true);

    }

    private void indentificar() {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            ip = in.readLine();

            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    i = (InetAddress) ee.nextElement();
                    list.add(i.getHostAddress());
                }
            }
            i = InetAddress.getLocalHost();
            byte[] ipAddr = i.getAddress();
            String hostname = i.getHostName();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao identificar a maquina que está acessando", "Erro de identificação", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listarErros() {
        jifListar = new JInternalFrame();
        jifListar = new JInternalFrame();
        jifListar.setTitle("Lista de erro ocorridos");
        jifListar.setSize(1250, 400);
        jifListar.setClosable(true);
        jifListar.setIconifiable(true);

        jbEditar = new JButton("Editar");
        iiEditar = new ImageIcon(getClass().getResource("/img/button_editar.png"));
        jbEditar.setIcon(iiEditar);
        jbEditar.setEnabled(false);

        jbRefresh = new JButton("Atualizar");
        iiRefresh = new ImageIcon(getClass().getResource("/img/button_refresh.png"));
        jbRefresh.setIcon(iiRefresh);

        jbReport = new JButton("Relatório");
        iiReport = new ImageIcon(getClass().getResource("/img/button_report.png"));
        jbReport.setIcon(iiReport);

        jbImprimir = new JButton("Imprimir");
        iiImprimir = new ImageIcon(getClass().getResource("/img/button_print.png"));
        jbImprimir.setIcon(iiImprimir);
        jbImprimir.setEnabled(false);

        jpListarCenter = new JPanel();
        ce = new ControllerErro();
        jtErro = new JTable(ce.linhas(), ce.colunas());
        jsp = new JScrollPane(jtErro);
        jpListarCenter.add(jsp);
        jpListarCenter.setLayout(new GridLayout(1, 1));

        jpListarNorth = new JPanel();
        jpListarNorth.add(jbEditar);
        jpListarNorth.add(jbRefresh);
        jpListarNorth.add(jbReport);
        jpListarNorth.add(jbImprimir);
        jbEditar.addActionListener(this);
        jbRefresh.addActionListener(this);
        jbReport.addActionListener(this);
        jbImprimir.addActionListener(this);

        jtErro.addMouseListener(this);
        jifListar.getContentPane().setLayout(new BorderLayout(5, 5));
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.NORTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifListar);
    }

    private void atualizar() {
        jpListarCenter.removeAll();
        controllerErro = new ControllerErro();
        jtErro = new JTable(ce.linhas(), ce.colunas());
        jsp = new JScrollPane(jtErro);
        jpListarCenter.add(jsp);
        jpListarCenter.setLayout(new GridLayout(1, 1));
        jpListarCenter.revalidate();
        jtErro.addMouseListener(this);
        if (jbEditar.isEnabled() == true) {
            jbEditar.setEnabled(false);
            jbImprimir.setEnabled(false);
        }
    }

    public void alterarErro() {
        jifAlterar = new JInternalFrame();
        jifAlterar = new JInternalFrame();
        jifAlterar.setTitle("Alterar dados");
        jifAlterar.setSize(600, 450);
        jifAlterar.setClosable(true);
        jifAlterar.setIconifiable(true);

        jpTopo = new JPanel();
        jpCentro = new JPanel();
        jpRodape = new JPanel();

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jbAlterar = new JButton("Alterar");
        iiAlterar = new ImageIcon(getClass().getResource("/img/button_confirmar.png"));
        jbAlterar.setIcon(iiAlterar);

        jtfPkId = new JTextField();
        jtfPkId.setForeground(Color.red);
        jtfPkId.setEditable(false);

        jtaDescricao = new JTextArea();
        jtaDescricao.setForeground(Color.red);
        jtaDescricao.setEditable(false);

        jtfUsuario = new JTextField();
        jtfUsuario.setEditable(false);
        jtfUsuario.setForeground(Color.WHITE);
        jtfUsuario.setBackground(Color.BLACK);

        jtfNomeMaquina = new JTextField();
        jtfNomeMaquina.setEditable(false);
        jtfNomeMaquina.setForeground(Color.WHITE);
        jtfNomeMaquina.setBackground(Color.BLACK);

        jtfIpLocal = new JTextField();
        jtfIpLocal.setEditable(false);
        jtfIpLocal.setForeground(Color.WHITE);
        jtfIpLocal.setBackground(Color.BLACK);

        jtfIpExterno = new JTextField();
        jtfIpExterno.setEditable(false);
        jtfIpExterno.setForeground(Color.WHITE);
        jtfIpExterno.setBackground(Color.BLACK);

        jtaObservacao = new JTextArea("...");
        jtaObservacao.setForeground(Color.red);

        jpTopo.add(jlPkId);
        jpTopo.add(jtfPkId);
        jpTopo.add(jlMaquina);
        jpTopo.add(jtfNomeMaquina);
        jpTopo.add(jlUsuario);
        jpTopo.add(jtfUsuario);
        jpTopo.add(jlIpLocal);
        jpTopo.add(jtfIpLocal);
        jpTopo.add(jlIpExterno);
        jpTopo.add(jtfIpExterno);
        jpTopo.add(jlObservacao);
        jpTopo.add(jtaObservacao);
        jpTopo.setLayout(new GridLayout(6, 2));

        jpCentro.add(jtaDescricao);
        jpCentro.setBorder(BorderFactory.createTitledBorder("Descrição"));
        jpCentro.setLayout(new GridLayout(1, 1));

        jpRodape.add(jbAlterar);
        jpRodape.add(jbAlterar);
        jbAlterar.addActionListener(this);
        jifAlterar.getContentPane().setLayout(new BorderLayout());
        jifAlterar.getContentPane().add(jpTopo, BorderLayout.NORTH);
        jifAlterar.getContentPane().add(jpCentro, BorderLayout.CENTER);
        jifAlterar.getContentPane().add(jpRodape, BorderLayout.SOUTH);
        ViewPrincipal.addFrame(jifAlterar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbCancelar) {
                this.jdSalvar.dispose();
            } else if (obj == jbSalvar) {
                ArrayList<String> addErro = new ArrayList<>();
                addErro.add(jtaDescricao.getText());
                addErro.add(ViewPrincipal.user);
                addErro.add(jtfNomeMaquina.getText());
                addErro.add(jtfIpLocal.getText());
                addErro.add(jtfIpExterno.getText());
                controllerErro.salvar(addErro);
                JOptionPane.showMessageDialog(null, "Erro foi salvo com sucesso. O suporte técnico irá avaliar", "Erro salvo", JOptionPane.INFORMATION_MESSAGE);
                this.jdSalvar.dispose();
            } else if (obj == jbEditar) {
                alterarErro();
                int linha;
                linha = jtErro.getSelectedRow();
                jtfPkId.setText(jtErro.getModel().getValueAt(linha, 0).toString());
                jtaDescricao.setText(jtErro.getModel().getValueAt(linha, 1).toString());
                jtfUsuario.setText(jtErro.getModel().getValueAt(linha, 2).toString());
                jtfNomeMaquina.setText(jtErro.getModel().getValueAt(linha, 3).toString());
                jtfIpLocal.setText(jtErro.getModel().getValueAt(linha, 4).toString());
                jtfIpExterno.setText(jtErro.getModel().getValueAt(linha, 5).toString());
                jtaObservacao.setText(jtErro.getModel().getValueAt(linha, 6).toString() + ".");
            } else if (obj == jbRefresh) {
                atualizar();
            } else if (obj == jbAlterar) {
                ArrayList<String> alterarErro = new ArrayList<>();
                alterarErro.add(jtfPkId.getText());
                alterarErro.add(jtaDescricao.getText());
                alterarErro.add(jtfUsuario.getText());
                alterarErro.add(jtfNomeMaquina.getText());
                alterarErro.add(jtfIpLocal.getText());
                alterarErro.add(jtfIpExterno.getText());
                alterarErro.add(jtaObservacao.getText());
                controllerErro.atualizar(alterarErro);
                JOptionPane.showMessageDialog(null, "Erro foi alterado com sucesso. O suporte técnico irá avaliar", "Erro alterardo", JOptionPane.INFORMATION_MESSAGE);
                this.jifAlterar.dispose();
            } else if (obj == jbReport) {
                controllerErro.imprimirErros("Relatório de usuários cadastrados", "/report/relErros.jasper");
            } else if (obj == jbImprimir) {
                int linha;
                linha = jtErro.getSelectedRow();
                Integer erro_pk_id = Integer.parseInt(jtErro.getModel().getValueAt(linha, 0).toString());
                HashMap parametro = new HashMap();
                parametro.put("erro_pk_id", erro_pk_id);
                controllerErro.imprimirErro("Imprimir Erro", "/report/relErro.jasper", parametro);
            } else {
                JOptionPane.showMessageDialog(null, "Ação desconecida nada foi implementado!", "Vazio", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro  na tela de erro", exception);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jtErro) {
            if (jtErro.getSelectedRow() != -1) {
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
