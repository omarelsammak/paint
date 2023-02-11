/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.awt.Color;
/**
 *
 * @author omar_
 */
public class Factory {
    public Shapes creator(int x,int y,int x1,int y1, Color color, int mode ){
        if (mode ==0)
            return new Line(x1, y1, x1, y1, color);
        else if (mode ==1 )
            return new Circle(x1, y1, x1, y1, color);
        else if (mode ==2 )
            return new Rectangle(x1, y1, x1, y1, color);
        else if (mode ==3)
            return new Square(x1, y1, x1, y1, color);
        else if (mode ==4)
            return new Triangle(x1, y1, x1, y1, x1, y1, color);
        else if (mode ==5)
            return new Oval(x1, y1, x1, y1, color);
        else return null;
    }
}
