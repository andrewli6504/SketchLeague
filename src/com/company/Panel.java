package com.company;

import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements MouseMotionListener, MouseListener
{
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;


    private BufferedImage buffer;

    private CommandToServer data = new CommandToServer("", 999, "", null);
    private String userName = "";
    private ArrayList<String> users = new ArrayList<String>();
    private ObjectOutputStream os;
    private Painting draw = new Painting();
    private Point point1 = new Point(0, 0);
    private Point point2 = new Point(0, 0);

    private ButtonGroup colors = new ButtonGroup();
    private JRadioButtonMenuItem black      ;
    private JRadioButtonMenuItem gray       ;
    private JRadioButtonMenuItem lightGray  ;
    private JRadioButtonMenuItem white      ;
    private JRadioButtonMenuItem red        ;
    private JRadioButtonMenuItem darkRed    ;
    private JRadioButtonMenuItem orange     ;
    private JRadioButtonMenuItem yellow     ;
    private JRadioButtonMenuItem tan        ;
    private JRadioButtonMenuItem brown      ;
    private JRadioButtonMenuItem lightGreen ;
    private JRadioButtonMenuItem green      ;
    private JRadioButtonMenuItem darkGreen  ;
    private JRadioButtonMenuItem lightBlue  ;
    private JRadioButtonMenuItem blue       ;
    private JRadioButtonMenuItem darkBlue   ;
    private JRadioButtonMenuItem blueGreen  ;
    private JRadioButtonMenuItem purple     ;
    private JRadioButtonMenuItem lightPurple;
    private JRadioButtonMenuItem pink       ;

    public Panel(ObjectOutputStream os) throws Exception
    {
        super();
        setSize(1130, 900);
        this.os = os;

        addMouseMotionListener(this);
        addMouseListener(this);

        setLayout(null);

        try
        {
            black       = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/black.png"))));
            gray        = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/gray.png"))));
            lightGray   = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/lightGray.png"))));
            white       = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/white.png"))));
            red         = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/red.png"))));
            darkRed     = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/darkRed.png"))));
            orange      = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/orange.png"))));
            yellow      = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/yellow.png"))));
            tan         = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/tan.png"))));
            brown       = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/brown.png"))));
            lightGreen  = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/lightGreen.png"))));
            green       = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/green.png"))));
            darkGreen   = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/darkGreen.png"))));
            lightBlue   = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/lightBlue.png"))));
            blue        = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/blue.png"))));
            darkBlue    = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/darkBlue.png"))));
            blueGreen   = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/blueGreen.png"))));
            purple      = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/purple.png"))));
            lightPurple = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/lightPurple.png"))));
            pink        = new JRadioButtonMenuItem(new ImageIcon(ImageIO.read(new File("Colors/pink.png"))));

            black      .setBounds(520,0  ,45,30);
            gray       .setBounds(520,30 ,45,30);
            lightGray  .setBounds(520,60 ,45,30);
            white      .setBounds(520,90 ,45,30);
            red        .setBounds(520,120,45,30);
            darkRed    .setBounds(520,150,45,30);
            orange     .setBounds(520,180,45,30);
            yellow     .setBounds(520,210,45,30);
            tan        .setBounds(520,240,45,30);
            brown      .setBounds(520,270,45,30);
            lightGreen .setBounds(520,300,45,30);
            green      .setBounds(520,330,45,30);
            darkGreen  .setBounds(520,360,45,30);
            lightBlue  .setBounds(520,390,45,30);
            blue       .setBounds(520,420,45,30);
            darkBlue   .setBounds(520,450,45,30);
            blueGreen  .setBounds(520,480,45,30);
            purple     .setBounds(520,510,45,30);
            lightPurple.setBounds(520,540,45,30);
            pink       .setBounds(520,570,45,30);

            colors.add(black      );
            colors.add(gray       );
            colors.add(lightGray  );
            colors.add(white      );
            colors.add(red        );
            colors.add(darkRed    );
            colors.add(orange     );
            colors.add(yellow     );
            colors.add(tan        );
            colors.add(brown      );
            colors.add(lightGreen );
            colors.add(green      );
            colors.add(darkGreen  );
            colors.add(lightBlue  );
            colors.add(blue       );
            colors.add(darkBlue   );
            colors.add(blueGreen  );
            colors.add(purple     );
            colors.add(lightPurple);
            colors.add(pink       );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        add(black      );
        add(gray       );
        add(lightGray  );
        add(white      );
        add(red        );
        add(darkRed    );
        add(orange     );
        add(yellow     );
        add(tan        );
        add(brown      );
        add(lightGreen );
        add(green      );
        add(darkGreen  );
        add(lightBlue  );
        add(blue       );
        add(darkBlue   );
        add(blueGreen  );
        add(purple     );
        add(lightPurple);
        add(pink       );


        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        repaint();
    }

    public void updateCanvas(Painting image)
    {
        draw = image;
        repaint();
    }

    public void paint(Graphics bg)
    {
        Graphics g = buffer.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(130, 0, getWidth(), getHeight());

        g.setColor(Color.black);
        for (ArrayList<Point> line : draw.getImage())
        {
            for (int x = 1; x < line.size(); x++)
            {
                if(line.get(x - 1).x>130 && line.get(x).x>130)
                    g.drawLine(line.get(x - 1).x, line.get(x - 1).y, line.get(x).x, line.get(x).y);
            }
        }

        //g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());
        //System.out.println(x+" "+y);


        bg.drawImage(buffer, 0, 0, null);
    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        x1 = e.getX();
        y1 = e.getY();
        point1.setLocation(x1, y1);

        draw.addPoint(point1);
        data.setDraw(draw);
        data.setTask(-2);
        try
        {
            os.writeObject(data);
            os.reset();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            x1 = x2 = e.getX();
            y1 = y2 = e.getY();

            point1.setLocation(x1, y1);

            draw.addPoint(point1);
            data.setDraw(draw);
            data.setTask(-2);

            try
            {
                os.writeObject(data);
                os.reset();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        // clear
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            draw.clear();
            data.setDraw(draw);
            data.setTask(-4);

            try
            {
                os.writeObject(data);
                os.reset();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

            repaint();
        }
        else if (e.getButton() == MouseEvent.BUTTON1)
        {
            draw.finishLine();
            data.setDraw(draw);
            data.setTask(-3);

            try
            {
                os.writeObject(data);
                os.reset();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
