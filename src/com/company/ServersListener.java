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
                Color color = command.getC();

                if(draw!=null)
                {
                    storedDraw.setImage(draw.getImage());
                    storedDraw.setColors(draw.getColors());
                }
                else
                {
                    draw = new Painting();
                    draw.setImage(storedDraw.getImage());
                    draw.setColors(storedDraw.getColors());
                }



                if(n == 1)
                    users.add(name);
                if(n == 0)
                    users.remove(name);

                int x = 0;
                for(ObjectOutputStream tempOS : osList)
                {
                    if(n-100 != x && n>=100)
                        commandOut = new CommandFromServer(users, mes, 99, draw);
                    else
                        commandOut = new CommandFromServer(users, mes, n, draw);
                    if(n == 3)
                        commandOut.setC(color);
                    tempOS.writeObject(commandOut);
                    tempOS.reset();

                    x++;
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
