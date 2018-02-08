package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServersListener implements Runnable
{
    private ObjectInputStream is;
    private static ArrayList<ObjectOutputStream> osList = new ArrayList<ObjectOutputStream>();
    private ObjectOutputStream os;
    private static ArrayList<String> users = new ArrayList<String>();
    private static Painting storedDraw = new Painting();
    CommandToServer command;
    CommandFromServer commandOut;

    public ServersListener(ObjectOutputStream os, ObjectInputStream is)
    {
        this.is = is;
        this.os = os;
        osList.add(os);
    }

    public void run()
    {
        try
        {
            while(true)
            {
                command = (CommandToServer)is.readObject();
                String name = command.getName();
                int n = command.getTask();
                String mes = command.getMessage();
                Painting draw = command.getDraw();

                if(draw!=null)
                {
                    storedDraw.setImage(draw.getImage());
                }
                else
                {
                    draw = new Painting();
                    draw.setImage(storedDraw.getImage());
                }

                if(n == 1)
                    users.add(name);
                if(n == 0)
                    users.remove(name);

                for(ObjectOutputStream tempOS : osList)
                {
                    commandOut = new CommandFromServer(users, mes, n, draw);
                    tempOS.writeObject(commandOut);
                    tempOS.reset();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            osList.remove(os);
        }
    }
}
