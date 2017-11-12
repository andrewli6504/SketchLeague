package com.company;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    public Frame(String frameName, int panelWidth, int panelHeight){

        super(frameName);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        pack();

        Panel p = new Panel();

        Insets frameInsets = getInsets();

        int frameWidth = panelWidth + (frameInsets.left + frameInsets.right);

        int frameHeight = panelHeight + (frameInsets.top + frameInsets.bottom);

        setPreferredSize(new Dimension(frameWidth, frameHeight));

        setLayout(null);

        add(p);

        pack();

        setVisible(true);

    }
}
