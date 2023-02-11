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
public abstract class Shapes {
    private Color color;
    public Shapes(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public abstract boolean contains(int x1, int y1);
    
}




