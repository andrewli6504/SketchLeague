package com.company;

import java.awt.*;

/**
 * Created by Steeve Doss on 9/13/2017.
 */
public class RainDrop {
    double x;
    double y;
    double z;
    double len;
    double yspeed;
    double xspeed;
    int width = 1920;
    int height = 1080;
    Color c = new Color((int)(Math.random() * 0x1000000));

    public RainDrop() {
        x  = random(-100,width);
        y  = random(-500, -50);
        z  = random(0, 20);
        len = map(z, 0, 20, 10, 20);
        yspeed  = map(z, 0, 20, 1, 20);
    }

    public void fall() {
        y = y + yspeed;

        double grav = map(z, 0, 20, 0, 0.2);
        yspeed = yspeed + grav;

        if (y > height) {
            y = random(-200, -100);
            yspeed = map(z, 0, 20, 4, 10);
        }
    }

    public void show(Graphics g) {
        double thick = map(z, 0, 20, 1, 5);
        g.setColor(c);
        g.fillRect((int)x,(int) y,(int)(thick),(int) (len+thick));
        //System.out.println("X"+x);
        //System.out.println("Y"+y);
        //g.fillRect((int)x,(int) y,(int)(thick),(int) (thick+len));
    }

    public int random(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    public double map(double value, double istart, double istop, double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }
    public void color(Color c){
        this.c = c;
    }
}