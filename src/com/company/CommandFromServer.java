package com.company;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CommandFromServer
{
    private String messages;
    private ArrayList<String> users;
    private int task;
    private BufferedImage drawing;

    public CommandFromServer(ArrayList<String> users, String messages, int task, BufferedImage drawing)
    {
        this.setMessages(messages);
        this.setUsers(users);
        this.setDrawing(drawing);
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

    public BufferedImage getDrawing()
    {
        return drawing;
    }

    public void setDrawing(BufferedImage drawing)
    {
        this.drawing = drawing;
    }
}
