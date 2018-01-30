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
        tempLine = new ArrayList<>();
    }

    public void addPoint(Point p)
    {
        if(tempLine.isEmpty())
        {
            tempLine = new ArrayList<>();
            tempLine.add(p);
            image.add(tempLine);
        }
        else
        {
            tempLine.add(p);
            image.set(image.size()-1, tempLine);
        }
    }

    public void finishLine()
    {
        image.add(tempLine);
        tempLine = new ArrayList<Point>();
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
