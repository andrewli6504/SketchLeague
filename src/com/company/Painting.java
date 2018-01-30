package com.company;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Painting implements Serializable
{
    private ArrayList<ArrayList<Point>> image;
    private ArrayList<Point> tempLine;

    public Painting()
    {
        image = new ArrayList<>();
        tempLine = null;
    }

    public void addPoint(Point p)
    {
        if(tempLine==null)
        {
            tempLine = new ArrayList<>();
        }
        tempLine.add(p);
    }

    public void finishLine()
    {
        image.add(tempLine);
        tempLine.clear();
    }

    public void clear()
    {
        image.clear();
    }

    public ArrayList<ArrayList<Point>> getImage()
    {
        return image;
    }
}
