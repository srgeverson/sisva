/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerLogTeste;
import controller.ControllerTeste;
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
import model.Users;

/**
 *
 * @author geverson
 */
public class ViewTeste extends JInternalFrame implements ActionListener, MouseListener {

    private JInternalFrame jifSalvar, jifListar, jifBuscar, jifDeletar;
    private final ControllerTeste controllerTeste;
    private final ControllerLogTeste controllerLogTeste;
    private JPanel jpListarCenter, jpListarNorth, jpBuscarNorth, jpBuscarCenter, jpSalvarCenter;
    private JButton jbAdd, jbBuscar, jbRefresh, jbEditar, jbReport, jbImprimir, jbLogTeste, jbBuscarConfirma, jbSalvar, jbCancelar;
    private ImageIcon iiAdd, iiBusca, iiRefresh, iiEditar, iiReport, iiImprimir, iiLogTeste, iiBuscaConfirma, iiSalvar, iiCancelar;
    private JTable jtTeste, jtTesteBuscar;
    private JTextField jtfBuscar;
    private JScrollPane jsp;
    private JTextField jtfId, jtfHora, jtfData, jtf;
    private final JLabel jlId, jlHora, jlData, jlFkUsers;
    private JComboBox jcbFkUsers;
    private JInternalFrame jifAlterar;
    private JPanel jpAlterarCenter;
    private JButton jbAlterar;
    private ImageIcon iiAlterar;
    private JButton jbDeletar;
    private ImageIcon iiDeletar;

    public ViewTeste() {
        controllerTeste = new ControllerTeste();
        controllerLogTeste = new ControllerLogTeste();
        jlId = new JLabel();
        jlHora = new JLabel();
        jlData = new JLabel();
        jlFkUsers = new JLabel();
    }

    public void alterar() {
        jifAlterar = new JInternalFrame();
        jifAlterar.setTitle("Alterar dados de usuário");
        jifAlterar.setClosable(true);
        jifAlterar.setIconifiable(true);
        jifAlterar.setSize(300, 200);

        jpAlterarCenter = new JPanel();

        jtfId = new JTextField();
        jtfId.setEnabled(false);
        jtfHora = new JTextField();
        jtfData.setEnabled(false);
        jcbFkUsers = new JComboBox<>();

        jbAlterar = new JButton("Alterar");
        iiAlterar = new ImageIcon(getClass().getResource("/img/button_confirmar.png"));
        jbAlterar.setIcon(iiAlterar);

        jbDeletar = new JButton("Deletar");
        iiDeletar = new ImageIcon(getClass().getResource("/img/button_deletar.png"));
        jbDeletar.setIcon(iiDeletar);

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jpAlterarCenter.add(jlId);
        jpAlterarCenter.add(jtfId);
        jpAlterarCenter.add(jlData);
        jpAlterarCenter.add(jtfData);
        jpAlterarCenter.add(jlHora);
        jpAlterarCenter.add(jtfHora);
        jpAlterarCenter.add(jlFkUsers);
        jpAlterarCenter.add(jcbFkUsers);
        jpAlterarCenter.add(jbAlterar);
        jpAlterarCenter.add(jbCancelar);
        jpAlterarCenter.setLayout(new GridLayout(5, 2));

        jbAlterar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jifAlterar.getContentPane().setLayout(new BorderLayout());
        jifAlterar.getContentPane().add(jpAlterarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifAlterar);
    }

    public void salvar() {
        jifSalvar = new JInternalFrame();
        jifSalvar.setTitle("Cadastrar teste");
        jifSalvar.setClosable(true);
        jifSalvar.setIconifiable(true);
        jifSalvar.setSize(300, 300);

        jpSalvarCenter = new JPanel();

        jtfId = new JTextField();
        jtfId.setEnabled(false);
        jtfData = new JTextField();
        jtfHora = new JTextField();
        jcbFkUsers = new JComboBox<>();

        jbSalvar = new JButton("Salvar");
        iiSalvar = new ImageIcon(getClass().getResource("/img/button_salvar.png"));
        jbSalvar.setIcon(iiSalvar);

        jbCancelar = new JButton("Cancelar");
        iiCancelar = new ImageIcon(getClass().getResource("/img/button_cancelar.png"));
        jbCancelar.setIcon(iiCancelar);

        jpSalvarCenter.add(jlId);
        jpSalvarCenter.add(jtfId);
        jpSalvarCenter.add(jlData);
        jpSalvarCenter.add(jtfData);
        jpSalvarCenter.add(jlHora);
        jpSalvarCenter.add(jtfHora);
        jpSalvarCenter.add(jlFkUsers);
        jpSalvarCenter.add(jcbFkUsers);
        jpSalvarCenter.add(jbSalvar);
        jpSalvarCenter.add(jbCancelar);
        jpSalvarCenter.setLayout(new GridLayout(5, 2));

        jbSalvar.addActionListener(this);
        jbCancelar.addActionListener(this);

        jifSalvar.getContentPane().setLayout(new BorderLayout());
        jifSalvar.getContentPane().add(jpSalvarCenter, BorderLayout.CENTER);
    }

    public void deletar() {
        controllerTeste.deletar(jtfId.getText());
    }

    public void listar() {
        jifListar = new JInternalFrame();
        jifListar.setTitle("Testes do sistema");
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

        jbLogTeste = new JButton("Logs Dos Testes");
        jbLogTeste.setEnabled(false);

        jtTeste = new JTable(controllerTeste.linhas(), controllerTeste.colunas());
        jpListarCenter.add(new JScrollPane(jtTeste));
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
        jtTeste.addMouseListener(this);
        jbImprimir.addActionListener(this);
        jifListar.getContentPane().setLayout(new BorderLayout(5, 5));
        jifListar.getContentPane().add(jpListarNorth, BorderLayout.NORTH);
        jifListar.getContentPane().add(jpListarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifListar);
    }

    public void buscar() {
        jifBuscar = new JInternalFrame();
        jifBuscar.setTitle("Consultar testes");
        jifBuscar.setSize(1250, 400);
        jifBuscar.setClosable(true);
        jifBuscar.setIconifiable(true);
        jifBuscar.setMaximizable(true);

        jpBuscarNorth = new JPanel();
        jpBuscarCenter = new JPanel();

        jtfBuscar = new JTextField();
        controllerTeste.setListarBusca(jtfBuscar.getText());

        jbBuscarConfirma = new JButton();
        iiBuscaConfirma = new ImageIcon(getClass().getResource("/img/button_search.png"));
        jbBuscarConfirma.setIcon(iiBuscaConfirma);
        jbBuscarConfirma.addActionListener(this);

        jpBuscarNorth.add(jtfBuscar);
        jpBuscarNorth.add(jbBuscarConfirma);
        jpBuscarNorth.setLayout(new GridLayout(1, 2));

        jtTesteBuscar = new JTable(controllerTeste.linhasBusca(), controllerTeste.colunas());
        jsp = new JScrollPane(jtTesteBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));

        jifBuscar.getContentPane().setLayout(new BorderLayout());
        jifBuscar.getContentPane().add(jpBuscarNorth, BorderLayout.NORTH);
        jifBuscar.getContentPane().add(jpBuscarCenter, BorderLayout.CENTER);
        ViewPrincipal.addFrame(jifBuscar);
    }

    public void refresh() {
        jpListarCenter.removeAll();
        jtTeste = new JTable(controllerTeste.linhas(), controllerTeste.colunas());
        jpListarCenter.add(new JScrollPane(jtTeste));
        jpListarCenter.setLayout(new GridLayout(1, 1));
        jpListarCenter.revalidate();
        jtTeste.addMouseListener(this);
        if (jbEditar.isEnabled() == true) {
            jbEditar.setEnabled(false);
            jbImprimir.setEnabled(false);
        }
    }

    public void confirmaBusca() {
        controllerTeste.setListarBusca(jtfBuscar.getText());
        jpBuscarCenter.removeAll();
        jtTesteBuscar = new JTable(controllerTeste.linhasBusca(), controllerTeste.colunas());
        jsp = new JScrollPane(jtTesteBuscar);
        jpBuscarCenter.add(jsp);
        jpBuscarCenter.setLayout(new GridLayout(1, 1));
        jsp.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object obj = e.getSource();
            if (obj == jbAdd) {
                salvar();
            } else if (obj == jbEditar) {
                alterar();
            } else if (obj == jbBuscar) {
                buscar();
            } else if (obj == jbRefresh) {
                refresh();
            } else if (obj == jbReport) {
                controllerTeste.imprimirTestes("Relatório de usuários cadastrados", "/report/relUsers.jasper");
            }else if (obj == jbImprimir) {
                int linha;
                linha = jtTeste.getSelectedRow();
                Integer auth_pk_id = Integer.parseInt(jtTeste.getModel().getValueAt(linha, 0).toString());
                HashMap parametro = new HashMap();
                parametro.put("auth_pk_id", auth_pk_id);
                controllerTeste.imprimirTeste("Imprimir Permissão", "/report/relAuthority.jasper", parametro);
            } else if (obj == jbAlterar) {
            } else if (obj == jbSalvar) {
                ArrayList<String> salva = new ArrayList<>();
                salva.add(jtfData.getText());
                salva.add(jtfHora.getText());
                salva.add(String.valueOf(jcbFkUsers.getSelectedItem()));
                controllerTeste.salvar(salva);
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
            } else if (obj == jbDeletar) {
                deletar();
                this.dispose();
            } else if (obj == jbCancelar) {
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Nada implementado para esta solicitação", "Vazio...", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exception) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jtTeste) {
            if (jtTeste.getSelectedRow() != -1) {
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
