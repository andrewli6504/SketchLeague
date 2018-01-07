package com.company;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements MouseMotionListener, MouseListener, KeyListener{
    Color c = Color.black;
    RainDrop[] drop = new RainDrop[1];
    int x;
    int y;
    int x1;
    int y1;
    int ct = 1;
    boolean f = true;
    public Panel(){
        super();
        setSize(1000,900);
        addMouseMotionListener(this);
        addMouseListener(this);
        for (int i = 0; i < drop.length; i++) {
            drop[i] = new RainDrop();
        }
    }

    public void paint(Graphics g) {
        if (f == true){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            f = false;
        }
        //Rain(g);
        g.setColor(c);
        g.drawLine(x,y,x1,y1);
        x1 = x;
        y1 = y;
        //System.out.println(x+" "+y);

    }

    public Graphics Rain(Graphics g) {

        if(ct == 10){
            c = new Color((int)(Math.random() * 0x1000000));
        }
        for (int i = 0; i < drop.length; i++) {
            drop[i].fall();
            drop[i].show(g);
            drop[i].color(c);
        }
        try
        {
            Thread.sleep(10);
            ct++;
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        repaint();
        return g;
    }

    public void setC(Color color){c = color;}

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(2);
        x = e.getX();
        y = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x1 = x = e.getX();
        y1 = y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(1);
        x1 = x = e.getX();
        y1 = y = e.getY();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //c = new Color((int)(Math.random() * 0x1000000));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // clear
        //if (e.getButton() == MouseEvent.BUTTON3){
        //    f=true;
        //    repaint();
        //}


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
