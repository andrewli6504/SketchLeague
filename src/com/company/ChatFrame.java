package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ChatFrame extends JFrame
{
    private ChatPanel cp;
    private Panel p = new Panel();

    public ChatFrame(String userName, ObjectOutputStream os) throws Exception
    {
        super("Chat Client");

        cp = new ChatPanel(userName, os);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);

        p.setBounds(600,0,1000,900);
        setLayout(null);


        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent winEvt)
            {
                try
                {
                    cp.exit();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        add(p);
        add(cp);
        setResizable(false);
        setVisible(true);
    }

    public ChatPanel getChat()
    {
        return cp;
    }
}