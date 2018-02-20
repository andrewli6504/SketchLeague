package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class CommandToServer implements Serializable
{
    private String name;

    /**TODO: Make Final Variables
     * over 100 is to change the drawing player
     * 4 is to increase score
     * 3 is to change the color
     * 2 is to get the image when joining
     * 1 is to add a user
     * 0 is to remove a user
     * -1 is to send a message
     * -2 is for drawing a point
     * -3 is for finishing a line
     */
    private int task;
    private Painting draw;
    private String message;
    private Color c;

    public Color getC()
    {
        return c;
    }

    public void setC(Color c)
    {
        this.c = c;
    }

    public CommandToServer(String name, int task, String message, Painting draw)
    {
        this.name = name;
        this.task = task;
        this.message = message;
        this.draw = draw;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getTask()
    {
        return task;
    }

    public void setTask(int task)
    {
        this.task = task;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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
