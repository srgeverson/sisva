/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import javax.swing.UIManager;
import view.ViewErro;
import view.ViewLogin;
import view.ViewPrincipal;

/**
 *
 * @author geverson
 */
public class IndexInicio {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            new ViewLogin().setVisible(true);
//            new ViewPrincipal().setVisible(true);
        } catch (Exception e) {
            ViewErro viewErro = new ViewErro();
            viewErro.salvarErro("Erro ocorrido ao iniciar sistema", e);
        }
    }
}
