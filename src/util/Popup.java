/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author ezequiel
 */

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Popup {

    public static void show(Window parentWindow, String mensagem) {
        JWindow window = new JWindow(parentWindow);

        JLabel label = new JLabel(mensagem, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(60, 63, 65)); // cor estilo "toast"
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        window.add(label);
        window.pack();

        // Centraliza na parte inferior da janela ativa
        int x = parentWindow.getX() + (parentWindow.getWidth() - window.getWidth()) / 2;
        int y = parentWindow.getY() + parentWindow.getHeight() - window.getHeight() - 50;
        window.setLocation(x, y);

        window.setAlwaysOnTop(true);
        window.setVisible(true);

        // Fecha automaticamente após 3 segundos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                window.setVisible(false);
                window.dispose();
            }
        }, 3000);
    }
}
