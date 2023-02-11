/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Color;
import java.awt.Point;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author omar_
 */
public class Line extends Shapes{
    private int  x1,y1,x2,y2;

    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public boolean contains(int x1, int x2) {
        
        int xpoints[]={this.x1,this.x2};
        int ypoints[]={this.y1,this.y2};
     java.awt.Polygon l= new java.awt.Polygon(xpoints,ypoints,1);
       if(l.contains(new Point(x1,x2)))
           return true;
       else return false;
    }}

  