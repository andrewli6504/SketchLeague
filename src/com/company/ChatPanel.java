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

    private JLabel lbl_users = new JLabel("Users:");
    private JList list_users = new JList();

    private JLabel lbl_chatBox = new JLabel("Messages:");
    private JScrollPane scr_chatBox = null;
    private JTextArea txt_chatBox = new JTextArea();

    private JLabel lbl_message = new JLabel("Enter Message:");
    private JTextArea txt_message = new JTextArea();
    
    private CommandToServer data;
    private String userName = "";
    private ArrayList<String> users = new ArrayList<String>();
    private ObjectOutputStream os;

    public ChatPanel(String userName, ObjectOutputStream os) throws Exception
    {
        super();
        setSize(480,900);

        this.userName = userName;
        this.os = os;

        data = new CommandToServer(userName, 1, "", null);
        os.writeObject(data);
        os.reset();

        list_users.setListData(users.toArray());
        list_users.setEnabled(false);
        lbl_users.setBounds(340, 30, 130, 20);
        list_users.setBounds(340, 50, 130, 550);

        scr_chatBox = new JScrollPane(txt_chatBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scr_chatBox.setBounds(20, 50, 300, 550);
        lbl_chatBox.setBounds(20, 30, 100, 20);
        txt_chatBox.setEditable(false);

        txt_message.setBounds(20, 650, 300, 80);
        lbl_message.setBounds(20, 630, 100, 20);


        setLayout(null);
        btn_send.setBounds(340, 650, 130, 30);
        btn_exit.setBounds(340, 700, 130, 30);

        add(txt_message);
        add(lbl_message);
        add(lbl_users);
        add(lbl_chatBox);
        add(scr_chatBox);
        add(list_users);
        add(btn_send);
        add(btn_exit);

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
    }

    public void exit()
    {
        try
        {
            data.setTask(0);
            data.setMessage(userName + " has left the room!\n");
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
                data.setMessage(userName + ": " + txt_message.getText() + "\n");

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


    public void updateUsers(ArrayList<String> users, int n)
    {
        if(n == 1)
        {
            this.users.add(users.get(users.size() - 1));
            update(users.get(users.size() - 1) + " has joined the room!\n");
        }
        if(n == 0)
        {
            this.users.clear();
            for(int x = 0; x < users.size(); x++)
            {
                this.users.add(users.get(x));
            }
            if(!this.users.contains(userName))
            {
                if(users.size() > 0)
                    update(users.get(users.size() - 1) + " has left the room!\n");
            }
        }
        list_users.setListData(users.toArray());
        repaint();
    }

    public void update(String message)
    {
        txt_chatBox.append(message);
        repaint();
    }
}
