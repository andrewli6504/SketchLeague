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
    static ArrayList<String> users = new ArrayList<String>();
    static ArrayList<Integer> scores = new ArrayList<Integer>();
    private String name;

    public ClientsListener(ObjectOutputStream os, ObjectInputStream is, ChatFrame f)
    {
        this.os = os;
        this.is = is;
        this.f = f;
        cp = f.getChat();
        p = f.getDrawings();
        name = cp.getUserName();
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                command = (CommandFromServer)is.readObject();
                this.users = command.getUsers();
                this.scores = command.getScores();
                String mes = command.getMessages();
                int n = command.getTask();
                Painting draw = command.getDraw();
                Color c = command.getC();
                String player = command.getUser();
                int currDraw = command.getCurrDrawing();

                int x = users.indexOf(name);
                if(x == currDraw)
                {
                    System.out.println(true + " " + name + " " + x + " " + currDraw);
                    p.updateCurrentDrawer(true);
                }
                else
                {
                    System.out.println(false + " " + name + " " + x + " " + currDraw);
                    p.updateCurrentDrawer(false);
                }

                if(n >= 0 && n <= 1)
                {
                    cp.update(mes, player);
                    cp.updateUsers(users, scores, n);
                }
                else if(n == -1)
                {
                    cp.update(mes, player);
                }
                else if(n == 2)
                {
                    p.recieveName(player);
                }
                else if(n>=100)
                {
                    cp.updateDrawer(n-100);
                    cp.updateScores(scores);
                    cp.update(mes, player);
                }
                else if(n==99)
                {
                    cp.updateScores(scores);
                    p.updateCurrentDrawer(false);
                    cp.update(mes, player);
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