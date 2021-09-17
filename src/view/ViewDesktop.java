/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author administrador
 */
public class ViewDesktop extends JDesktopPane {

    private final ImageIcon iiImagem;

    public ViewDesktop() {
        iiImagem = new ImageIcon(getClass().getResource("/img/desktop.jpg"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        iiImagem.paintIcon(this, g, 0, 0);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(iiImagem.getIconWidth(), iiImagem.getIconHeight());
    }
}
