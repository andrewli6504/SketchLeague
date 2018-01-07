package com.company;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CommandToServer
{
    private String name;
    private int task;
    private String message;
    private BufferedImage drawing;

    public CommandToServer(String name, int task, String message, BufferedImage drawing)
    {
        this.name = name;
        this.task = task;
        this.message = message;
        this.drawing = drawing;

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

    public BufferedImage getDrawing()
    {
        return drawing;
    }

    public void setDrawing(BufferedImage drawing)
    {
        this.drawing = drawing;
    }

}
