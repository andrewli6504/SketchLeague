package com.company;

import java.util.ArrayList;

public class ChatData
{
    private String messages;
    private ArrayList<String> users;

    public ChatData(String messages, ArrayList<String> users)
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
