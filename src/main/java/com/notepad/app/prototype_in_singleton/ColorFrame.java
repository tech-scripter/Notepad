package com.notepad.app.prototype_in_singleton;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class ColorFrame extends JFrame {
    public ColorFrame() {
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void showOnRandomPlace() {
        Random random = new Random();
        setLocation(random.nextInt(1200), random.nextInt(700));
        getContentPane().setBackground(getColor());
        repaint();
    }

    public abstract Color getColor();
}
