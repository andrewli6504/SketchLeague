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

public class Panel extends JPanel implements MouseMotionListener, MouseListener{
    Color c = Color.black;
    int x=0;
    int y=0;
    int x1=0;
    int y1=0;
    int ct = 1;
    boolean f = true;

    private BufferedImage buffer;

    private CommandToServer data = new CommandToServer("", 999,"", null);
    private String userName = "";
    private ArrayList<String> users = new ArrayList<String>();
    private ObjectOutputStream os;
    private Point draw = new Point(0,0);
    private Point point1 = new Point(0,0);
    private Point point2 = new Point(0,0);

    public Panel(ObjectOutputStream os)throws Exception
    {
        super();
        setSize(1000,900);
        this.os = os;

        addMouseMotionListener(this);
        addMouseListener(this);

        buffer = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        repaint();
    }

    public void updateCanvas(Point point)
    {
        point1 = point;
        point2 = point1;
    }

    public void paint(Graphics bg)
    {
        Graphics g = buffer.getGraphics();

        g.setColor(Color.WHITE);
        if (f == true)
        {
            g.fillRect(0, 0, getWidth(), getHeight());
            f = false;
        }
        else
        {
            g.setColor(Color.BLACK);
            g.drawLine(x,y,x1,y1);
            x1 = x;
            y1 = y;

            //g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());
            //System.out.println(x+" "+y);

        }

        bg.drawImage(buffer,0,0,null);

    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
        
        draw.setLocation(x,y);
        data.setDraw(draw);
        data.setTask(-2);
        try
        {
            os.writeObject(data);
            os.reset();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        x1 = x = e.getX();
        y1 = y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        x1 = x = e.getX();
        y1 = y = e.getY();

        draw.setLocation(x,y);
        data.setDraw(draw);
        data.setTask(-2);

        try
        {
            os.writeObject(data);
            os.reset();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        repaint();
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
}
