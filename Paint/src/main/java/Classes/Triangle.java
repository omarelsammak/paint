/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import Classes.Shapes;
import Classes.Shapes;
import java.awt.Color;
import java.awt.Point;
/**
 *
 * @author LENOVO
 */
public class Triangle extends Shapes{
    private int x1,y1,x2,y2,x3,y3;
    public Triangle(int x1, int y1, int x2, int y2,int x3,int y3,Color color) {
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3= y3;
        
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

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }
int xpoints[]={this.x1,this.x2,this.x3};
int ypoints[]={this.y1,this.y2,this.y3};

    @Override
    public boolean contains(int x1, int x2) {
        java.awt.Polygon t=new java.awt.Polygon(xpoints,ypoints,3);
         if(t.contains(new Point(x1,x2)))
           return true;
       else return false;
    
    }

    
    
  
    
    
}
