package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class CommandToServer implements Serializable
{
    private String name;

    /**
     * 1 is to add a user
     * 0 is to remove a user
     * -1 is to send a message
     * -2 is for drawing
     */
    private int task;
    private Point draw;
    private String message;

    public CommandToServer(String name, int task, String message, Point draw)
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

    public Point getDraw()
    {
        return draw;
    }

    public void setDraw(Point draw)
    {
        this.draw = draw;
    }
}
