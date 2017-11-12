package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(2022); // port 2022

            while(true)
            {
                Socket socket = serverSocket.accept();
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                ServersListener sl = new ServersListener(os, is);
                Thread t = new Thread(sl);
                t.start();
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //temp
}
