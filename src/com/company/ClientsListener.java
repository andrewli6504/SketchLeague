package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientsListener implements Runnable
{
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private ChatFrame f;
    private ChatPanel cp;
    private Panel p;
    CommandFromServer command;

    public ClientsListener(ObjectOutputStream os, ObjectInputStream is, ChatFrame f)
    {
        this.os = os;
        this.is = is;
        this.f = f;
        cp = f.getChat();
        p = f.getDrawings();
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                command = (CommandFromServer)is.readObject();
                ArrayList<String> users = command.getUsers();
                ArrayList<Integer> scores = command.getScores();
                String mes = command.getMessages();
                int n = command.getTask();
                Painting draw = command.getDraw();
                Color c = command.getC();

                if(n > -2 && n <= 1)
                {
                    cp.update(mes);
                    cp.updateUsers(users, scores, n);
                }
                else if(n>=100)
                {
                    p.updateCurrentDrawer(true);
                    cp.updateDrawer(n-100);
                }
                else if(n==99)
                {
                    p.updateCurrentDrawer(false);
                }
                else
                {
                    p.updateCanvas(draw, c);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
