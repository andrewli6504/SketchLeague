package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ChatPanel extends JPanel
{
    private JButton btn_exit = new JButton("Exit");
    private JButton btn_send = new JButton("Send");
    private JButton next_player = new JButton("Next");

    private JLabel lbl_users = new JLabel("Users:");
    private JList list_users = new JList();

    private JLabel lbl_scores = new JLabel("Scores:");
    private JList list_scores = new JList();

    private JLabel lbl_chatBox = new JLabel("Messages:");
    private JScrollPane scr_chatBox = null;
    private JTextArea txt_chatBox = new JTextArea();

    private JLabel lbl_message = new JLabel("Enter Message:");
    private JTextArea txt_message = new JTextArea();

    private JLabel drawer = new JLabel("Drawing: ");

    /**
     * the color selection buttons
     */
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


    private CommandToServer data;
    private String userName = "";
    private ArrayList<String> users = new ArrayList<String>();
    private ArrayList<Integer> scores = new ArrayList<Integer>();

    /**
     * stores the index of the currently drawing player.
     */
    private int currentlyDrawing;
    private ObjectOutputStream os;

    public String getUserName()
    {
        return userName;
    }

    public ChatPanel(String userName, ObjectOutputStream os) throws Exception
    {
        super();
        setSize(600,900);

        this.userName = userName;
        this.os = os;

        data = new CommandToServer(userName, 1, "", null);
        data.setName(userName);
        os.writeObject(data);
        os.reset();

        list_users.setEnabled(false);
        list_users.setBounds(340, 50, 70, 500);
        lbl_users.setBounds(340, 30, 70, 20);

        list_scores.setEnabled(false);
        list_scores.setBounds(420,50,50,500);
        lbl_scores.setBounds(420,30,50,20);

        scr_chatBox = new JScrollPane(txt_chatBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scr_chatBox.setBounds(20, 50, 300, 550);
        lbl_chatBox.setBounds(20, 30, 100, 20);
        txt_chatBox.setEditable(false);

        txt_message.setBounds(20, 650, 300, 80);
        lbl_message.setBounds(20, 630, 100, 20);

        drawer.setBounds(340,550,100,50);

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

            black      .setBounds(480,0  ,45,30);
            gray       .setBounds(480,30 ,45,30);
            lightGray  .setBounds(480,60 ,45,30);
            white      .setBounds(480,90 ,45,30);
            red        .setBounds(480,120,45,30);
            darkRed    .setBounds(480,150,45,30);
            orange     .setBounds(480,180,45,30);
            yellow     .setBounds(480,210,45,30);
            tan        .setBounds(480,240,45,30);
            brown      .setBounds(480,270,45,30);
            lightGreen .setBounds(480,300,45,30);
            green      .setBounds(480,330,45,30);
            darkGreen  .setBounds(480,360,45,30);
            lightBlue  .setBounds(480,390,45,30);
            blue       .setBounds(480,420,45,30);
            darkBlue   .setBounds(480,450,45,30);
            blueGreen  .setBounds(480,480,45,30);
            purple     .setBounds(480,510,45,30);
            lightPurple.setBounds(480,540,45,30);
            pink       .setBounds(480,570,45,30);

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

        btn_send.setBounds(340, 650, 130, 30);
        btn_exit.setBounds(340, 700, 130, 30);
        next_player.setBounds(500,650,80,80);

        add(txt_message);
        add(lbl_message);
        add(lbl_users);
        add(lbl_chatBox);
        add(scr_chatBox);
        add(list_users);
        add(btn_send);
        add(btn_exit);
        add(next_player);
        add(drawer);
        add(list_scores);
        add(lbl_scores);

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

        btn_exit.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        exit();
                    }
                }
        );

        btn_send.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        sendtxt_message();
                    }
                }
        );

        next_player.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        nextPlayer();
                    }
                }
        );

        black.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(0,0,0));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        gray.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(68,68,68));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        lightGray.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(201,202,196));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        white.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(255,255,255));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        red.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(192,0,0));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        darkRed.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(144,14,22));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        orange.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(255,131,9));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        yellow.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(255,255, 0));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        tan.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(214,177,150));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        brown.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(63,34,16));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        lightGreen.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(179,255,128));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        green.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(67,219,36));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        darkGreen.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(2,94,17));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        lightBlue.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(12,186,255));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        blue.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(66,130,201));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        darkBlue.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(0,0,152));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        blueGreen.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(1,109,93));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        purple.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(115,59,158));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        lightPurple.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(214,187,223));
                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        pink.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            data.setTask(3);
                            data.setC(new Color(237, 0, 140));

                            os.writeObject(data);
                            os.reset();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
        );
    }

    public void exit()
    {
        try
        {
            data.setTask(0);
            data.setMessage(" has left the room!");
            data.setName(userName);
            os.writeObject(data);
            os.reset();

            System.exit(0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sendtxt_message()
    {
        try
        {
            if(txt_message.getText() != null && txt_message.getText().compareTo("") != 0)
            {
                data.setTask(-1);

                data.setMessage(txt_message.getText());
                data.setName(userName);

                os.writeObject(data);
                os.reset();

                txt_message.setText("");
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void updateUsers(ArrayList<String> users, ArrayList<Integer> scores, int n)
    {
        if(n == 1)
        {
            if(this.users.isEmpty())
            {
                currentlyDrawing = 0;
            }
            this.scores = scores;
            this.users = users;

            update(" has joined the room!" ,this.users.get(this.users.size() - 1));

        }
        else if(n == 0)
        {
            this.users.clear();
            this.scores.clear();
            for(int x = 0; x < users.size(); x++)
            {
                this.users.add(users.get(x));
                this.scores.add(scores.get(x));
            }
//            This code shouldn't be needed
//            if(!this.users.contains(userName))
//            {
//                if(users.size() > 0)
//                    update(users.get(users.size() - 1) + " has left the room!\n");
//            }
            currentlyDrawing = (int)(Math.random() * this.users.size());
        }
        list_users.setListData(this.users.toArray());
        list_scores.setListData(this.scores.toArray());
        System.out.println(this.users + " "+this.scores);
        repaint();
    }

    public void update(String message, String user)
    {
        if(message.equals(" has joined the room!"))
            txt_chatBox.append(user + message + "\n");
        else if(message.equals(" has left the room!"))
            txt_chatBox.append(user + message + "\n");
        else if(!message.isEmpty())
            txt_chatBox.append(user + ": " + message + "\n");

        repaint();
    }

    public void nextPlayer()
    {
        currentlyDrawing++;
        if(currentlyDrawing>=users.size())
            currentlyDrawing -= users.size();

        drawer.setText("Drawing: "+users.get(currentlyDrawing));

        try
        {
            data.setTask(100+currentlyDrawing);
            os.writeObject(data);
            os.reset();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateDrawer(int n)
    {
        System.out.println(n+"\t"+users);
        currentlyDrawing = n;
        drawer.setText("Drawing: "+users.get(n));
    }

    public void updateScores(ArrayList<Integer> scores)
    {
        this.scores = scores;
        list_scores.setListData(this.scores.toArray());
    }
}
