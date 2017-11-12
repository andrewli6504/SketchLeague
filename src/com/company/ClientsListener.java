package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientsListener implements Runnable
{
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private ChatFrame f;
    private ChatPanel p;

    public ClientsListener(ObjectOutputStream os, ObjectInputStream is, ChatFrame f)
    {
        this.os = os;
        this.is = is;
        this.f = f;
        p = f.getChat();
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                ArrayList<String> users = (ArrayList<String>) is.readObject();
                String mes = (String) is.readObject();
                int n = (int) is.readObject();
                p.update(mes);
                p.updateUsers(users, n);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
