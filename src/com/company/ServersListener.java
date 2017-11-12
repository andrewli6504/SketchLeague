package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServersListener implements Runnable
{
    private ObjectInputStream is;
    private static ArrayList<ObjectOutputStream> osList = new ArrayList<ObjectOutputStream>();
    private ObjectOutputStream os;
    private static ArrayList<String> users = new ArrayList<String>();

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
                String name = (String) is.readObject();
                int n = (int) is.readObject();
                String mes = (String) is.readObject();

                if(n == 1)
                    users.add(name);
                if(n == 0)
                    users.remove(name);

                for(ObjectOutputStream tempOS : osList)
                {
                    tempOS.writeObject(users);
                    tempOS.reset();
                    tempOS.writeObject(mes);
                    tempOS.reset();
                    tempOS.writeObject(n);
                    tempOS.reset();
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
