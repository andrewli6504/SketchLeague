package com.company;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Painting implements Serializable
{
    private ArrayList<Color> colors;
    private ArrayList<ArrayList<Point>> image;
    private ArrayList<Point> tempLine;

    public Painting()
    {
        image = new ArrayList<>();
        tempLine = new ArrayList<>();
        colors = new ArrayList<>();
    }

    public void addPoint(Point p, Color c)
    {
        if(tempLine.isEmpty())
        {
            tempLine = new ArrayList<>();
            tempLine.add(p);
            image.add(tempLine);
            colors.add(c);
        }
        else
        {
            tempLine.add(p);
            image.set(image.size()-1, tempLine);
        }
    }

    public void finishLine()
    {
        tempLine = new ArrayList<Point>();
    }

    public void clear()
    {
        image.clear();
        colors.clear();
    }

    public ArrayList<ArrayList<Point>> getImage()
    {
        return image;
    }

    public void setImage(ArrayList<ArrayList<Point>> image)
    {
        this.image = image;
    }

    public ArrayList<Color> getColors()
    {
        return colors;
    }

    public void setColors(ArrayList<Color> colors)
    {
        this.colors = colors;
    }
}
