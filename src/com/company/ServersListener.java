package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ServersListener implements Runnable
{
    private ObjectInputStream is;
    private static ArrayList<ObjectOutputStream> osList = new ArrayList<ObjectOutputStream>();
    private ObjectOutputStream os;
    private static ArrayList<String> users = new ArrayList<String>();
    private static ArrayList<Integer> scores = new ArrayList<Integer>();
    private static Painting storedDraw = new Painting();
    private CommandToServer command;
    private CommandFromServer commandOut;
    private static ArrayList<String> pictureList = new ArrayList<String>();
    private static int currentPictureIndex = 0;
    private static String currentPicture;
    private static int currentlyDrawing;

    public ServersListener(ObjectOutputStream os, ObjectInputStream is)
    {
        try
        {
            Scanner file = new Scanner(new File("Pictures/pictureList.txt"));

            while(file.hasNextLine())
            {
                pictureList.add(file.nextLine());
            }
            Collections.shuffle(pictureList);
            currentPicture = pictureList.get(currentPictureIndex);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        this.is = is;
        this.os = os;
        osList.add(os);

        currentlyDrawing = 0;
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

                if(!mes.isEmpty() && mes.toUpperCase().trim().equals(currentPicture.toUpperCase()))
                {
                    currentlyDrawing++;
                    if(currentlyDrawing>=users.size())
                        currentlyDrawing -= users.size();
                    n = 100 + currentlyDrawing;

                    int index = users.indexOf(name);
                    this.scores.set(index, scores.get(index)+100);
                    System.out.println(scores);

                    currentPictureIndex++;
                    if(currentPictureIndex >= pictureList.size())
                    {
                        currentPictureIndex -= pictureList.size();
                    }
                    currentPicture = pictureList.get(currentPictureIndex);
                    System.out.println("NEXT PICTURE: "+currentPicture);
                }

                if(n == 1)
                {
                    scores.add(0);
                    users.add(name);
                }
                if(n == 0)
                {
                    scores.remove((int)users.indexOf(name));
                    users.remove(name);
                }

                int x = 0;
                for(ObjectOutputStream tempOS : osList)
                {
                    commandOut = new CommandFromServer(users, scores, mes, n, draw);

                    commandOut.setCurrDrawing(currentlyDrawing);

                    if(n == 3)
                        commandOut.setC(color);

                    commandOut.setUser(command.getName());
                    if(n == 2 && name.isEmpty())
                        commandOut.setUser(users.get(users.size()-1));

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
