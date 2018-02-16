package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main
{
    //static ArrayList<String> names =;

    public static void main(String[] args)
    {

        Scanner key = new Scanner(System.in);
        System.out.print("Enter the ip address: ");
        String ip = key.next();
        key.nextLine();
        System.out.print("Enter your name: ");
        String name = key.nextLine();
        while(name.length() < 0 || name.length() > 17 || name.trim().equals(""))
        {
            System.out.print("Not a valid name. Try again:");
            name = key.nextLine();
        }

        try
        {
            Socket socket = new Socket(ip, 2022);
            System.out.println("Connected to Server!");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            ChatFrame f = new ChatFrame(name, os);
            ClientsListener cl = new ClientsListener(os, is, f);
            Thread t = new Thread(cl);
            t.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}