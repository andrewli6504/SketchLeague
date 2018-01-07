package com.company;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class CommandFromServer implements Serializable
{
    private String messages;
    private ArrayList<String> users;
    private int task;

    public CommandFromServer(ArrayList<String> users, String messages, int task)
    {
        this.setMessages(messages);
        this.setUsers(users);
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
}
