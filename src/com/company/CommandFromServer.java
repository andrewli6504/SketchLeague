package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class CommandFromServer implements Serializable
{
    private String messages;
    private ArrayList<String> users;
    private ArrayList<Integer> scores;
    private int task;
    private Painting draw;
    private Color c;
    private String user;
    private String imageName;

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public int getCurrDrawing()
    {
        return currDrawing;
    }

    public void setCurrDrawing(int currDrawing)
    {
        this.currDrawing = currDrawing;
    }

    private int currDrawing;

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Color getC()
    {
        return c;
    }

    public void setC(Color c)
    {
        this.c = c;
    }

    public ArrayList<Integer> getScores()
    {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores)
    {
        this.scores = scores;
    }

    public CommandFromServer(ArrayList<String> users, ArrayList<Integer> scores, String messages, int task, Painting draw)
    {
        this.users = users;
        this.scores = scores;
        this.messages = messages;
        this.task = task;
        this.draw = draw;
    }

    public String getMessages()
    {
        return messages;
    }

    public void setMessages(String messages)
    {
        this.messages = messages;
    }

    public ArrayList<String> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<String> users)
    {
        this.users = users;
    }

    public int getTask()
    {
        return task;
    }

    public void setTask(int task)
    {
        this.task = task;
    }

    public Painting getDraw()
    {
        return draw;
    }

    public void setDraw(Painting draw)
    {
        this.draw = draw;
    }
}
