package com.company;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.ObjectOutputStream;
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

    private BufferedImage buffer;

    private CommandToServer data = new CommandToServer("", 999,"");
    private String userName = "";
    private ArrayList<String> users = new ArrayList<String>();
    private ObjectOutputStream os;

    public Panel(ObjectOutputStream os)throws Exception
    {
        super();
        setSize(1000,900);
        this.os = os;
        addMouseMotionListener(this);
        addMouseListener(this);

        buffer = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        for (int i = 0; i < drop.length; i++) {
            drop[i] = new RainDrop();
        }
    }

    public void updateCanvas(BufferedImage drawing)
    {

    }

    public void paint(Graphics bg)
    {
        Graphics g = buffer.getGraphics();

        if (f == true){
            g.setColor(Color.WHITE);
            g.fillRect(600, 0, getWidth(), getHeight());
            f = false;
        }
        //Rain(g);
        g.setColor(c);
        g.drawLine(x,y,x1,y1);
        x1 = x;
        y1 = y;
        //System.out.println(x+" "+y);

        data.setTask(-2);

        try
        {
            os.writeObject(data);
            os.reset();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        bg.drawImage(buffer,0,0,null);

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
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            x = e.getX();
            y = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x1 = x = e.getX();
        y1 = y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(1);
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
        if (e.getButton() == MouseEvent.BUTTON3){
            f=true;
            repaint();
        }


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
