package report;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

public class ReportUtil extends JDialog {

    public static void openReport(String titulo, InputStream inputStream, Map parametros, Connection conexao) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, conexao);
        viewReportFrame(titulo, print);

    }

    private static void viewReportFrame(String titulo, JasperPrint print) {
        JRViewer viewer = new JRViewer(print);
        JPanel jp = new JPanel();
        JDialog frameRelatorio = new JDialog();
        frameRelatorio.setTitle(titulo);
        frameRelatorio.setSize(1000, 650);
        frameRelatorio.setModal(true);
        frameRelatorio.setLocationRelativeTo(null);
        
        jp.add(new JScrollPane(viewer));
        jp.setLayout(new GridLayout(1, 1));
        frameRelatorio.getContentPane().setLayout(new BorderLayout());
        frameRelatorio.getContentPane().add(jp, BorderLayout.CENTER);
        frameRelatorio.setVisible(true);
    }
}
