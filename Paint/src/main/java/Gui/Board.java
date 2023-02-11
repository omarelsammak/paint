/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Classes.Line;
import Classes.Rectangle;
import Classes.Shapes;
import Classes.Square;
import Classes.Circle;
import Classes.Factory;
import Classes.Oval;
import Classes.Triangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author omar_
 */
public class Board extends JPanel implements MouseListener, MouseMotionListener {
Factory f = new Factory();
    Color current = Color.BLUE;
    int mode = -1;
int fill=0;
    int x1, x2, x3;
    boolean first = false;
    int y1, y2, y3;
    Object selected = null;
 
Stack <Shapes> undo = new Stack<>();
Stack <Shapes> redo = new Stack<>();
 
   ArrayList<Shapes> shapes  = new ArrayList<>();;

 Iterator <Shapes> w= shapes.iterator();
  
  
    public Board() {
        addMouseListener(this);
        addMouseMotionListener(this);

    }
    
// 
    @Override

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Line) {
                Line l = (Line) shapes.get(i);
                g.setColor(l.getColor());
                g.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
            } else if (shapes.get(i) instanceof Rectangle) {
                Rectangle r = (Rectangle) shapes.get(i);
                g.setColor(r.getColor());
                if (r.getX1() < r.getX2() && r.getY1() < r.getY2()) {
                    g.drawRect(r.getX1(), r.getY1(), r.getX2() - r.getX1(), r.getY2() - r.getY1());
                } else if (r.getX1() > r.getX2() && r.getY1() > r.getY2()) {
                    g.drawRect(r.getX2(), r.getY2(), Math.abs(r.getX1() - r.getX2()), Math.abs(r.getY1() - r.getY2()));
                } else if (r.getX1() > r.getX2() && r.getY1() < r.getY2()) {
                    g.drawRect(r.getX2(), r.getY1(), Math.abs(r.getX1() - r.getX2()), r.getY2() - r.getY1());
                } else if (r.getX1() < r.getX2() && r.getY1() > r.getY2()) {
                    g.drawRect(r.getX1(), r.getY2(), r.getX2() - r.getX1(), Math.abs(r.getY1() - r.getY2()));
                }
              }else if (shapes.get(i) instanceof Square) {
                Square s = (Square) shapes.get(i);
                g.setColor(s.getColor());
                if (s.getX1() < s.getX2() && s.getY1() < s.getY2()) {
                    g.drawRect(s.getX1(), s.getY1(), Math.abs(s.getX2() - s.getX1()),Math.abs (s.getX2() - s.getX1()));
                } else if (s.getX1() < s.getX2() && s.getY1() > s.getY2()) {
                    g.drawRect(s.getX1(), s.getY2(), Math.abs(s.getY1() - s.getY2()), Math.abs(s.getY1() - s.getY2()));
                } 
                else if (s.getX1() > s.getX2() && s.getY1() < s.getY2()) {
                    g.drawRect(s.getX2(), s.getY1(), Math.abs(s.getX2() - s.getX1()),Math.abs (s.getX2() - s.getX1()));
                } 
                else if ( s.getX1() > s.getX2() && s.getY1() > s.getY2()) {
                    g.drawRect(s.getX2(), s.getY2(), Math.abs(s.getX2() - s.getX1()), Math.abs(s.getX2()- s.getX2()));
                }
              

            } else if (shapes.get(i) instanceof Circle) {
                Circle c = (Circle) shapes.get(i);
                g.setColor(c.getColor());
                if (c.getX1() < c.getX2() && c.getY1() < c.getY2()) {
                    g.drawOval(c.getX1(), c.getY1(), c.getX2() - c.getX1(), c.getX2() - c.getX1());
                }
//                 else  if (c.getX1()>c.getX2() && c.getY1()>c.getY2())                   
//                g.drawOval( c.getX2(),  c.getX2(), Math.abs(c.getY1()-c.getY2()), Math.abs(c.getX1()-c.getX2()));

            } else if (shapes.get(i) instanceof Oval) {
                Oval o = (Oval) shapes.get(i);
                g.setColor(o.getColor());
                if (o.getX1() < o.getX2() && o.getY1() < o.getY2()) {
                    g.drawOval(o.getX1(), o.getY1(), o.getX2() - o.getX1(), o.getY2() - o.getY1());
                } else if (o.getX1() > o.getX2() && o.getY1() > o.getY2()) {
                    g.drawOval(o.getX2(), o.getY2(), Math.abs(o.getX1() - o.getX2()), Math.abs(o.getY1() - o.getY2()));
                } else if (o.getX1() > o.getX2() && o.getY1() < o.getY2()) {
                    g.drawOval(o.getX2(), o.getY1(), Math.abs(o.getX1() - o.getX2()), o.getY2() - o.getY1());
                } else if (o.getX1() < o.getX2() && o.getY1() > o.getY2()) {
                    g.drawOval(o.getX1(), o.getY2(), o.getX2() - o.getX1(), Math.abs(o.getY1() - o.getY2()));
                }
            }
            if (shapes.get(i) instanceof Triangle) {
                Triangle t = (Triangle) shapes.get(i);

                g.setColor(t.getColor());

                int[] xpoints = {t.getX1(), t.getX2(), t.getX3()};
                int[] ypoints = {t.getY1(), t.getY2(), t.getY2()};
                g.drawPolygon(xpoints, ypoints, 3);

            }
        }
undo.addAll(shapes);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
public void undo(){
    if (!undo.isEmpty())
    {
        redo.push(undo.pop());
        shapes.remove(shapes.size()-1);
        repaint();
        
    }
}

public void redo(){
    if (!redo.isEmpty()){
      Shapes  temp = redo.pop();
        undo.push(temp);
        shapes.add(temp);
        repaint();
    }
}
    @Override
    public void mousePressed(MouseEvent e) {
          x1 = e.getX();
        y1 = e.getY();

if (mode <6)
{ shapes.add(f.creator(x1, y1, x1, y1, current, mode));

            repaint();}


       else if (mode == 10) {

            for (int i = shapes.size() - 1; i >= 0; i--) {

                if (shapes.get(i).contains(x1, y1)) {
                    System.out.println("select");
                    selected = shapes.get(i);
                    break;
                }}}

       else if (mode == 20) {
           for (int i = shapes.size() - 1; i >= 0; i--) {
              if (shapes.get(i).contains(x1, y1)) {
                    System.out.println("select");
                    selected = shapes.get(i);
                    break;
                }}

            if (selected != null) {if (selected instanceof Rectangle) {
//                    Rectangle r = new Rectangle(0, 0, 0, 0, current);
//                Rectangle z = new Rectangle(0, 0, 0, 0, current);
//      Shapes z =(Rectangle) f.creator(0, 0, 0, 0, current, 2);
//                   Rectangle r = (Rectangle)f.creator(0, 0, 0, 0, current, 2)  ;
//                   r = (Rectangle) selected;
//                   Rectangle  z = new Rectangle(r.getX1() + 10, r.getY1() + 10, r.getX2() + 10, r.getY2() + 10, r.getColor());
//                   shapes.add(r);
                try { Rectangle H = (Rectangle)f.creator(0, 0, 0, 0, current, 2)  ;
                H = (Rectangle) selected;
                    Rectangle z = (Rectangle) H.clone();
                    z.setX1(z.getX1()+10);z.setX2(z.getX2()+10); z.setY1(z.getY1()+10);z.setY2(z.getY2()+10);
                    shapes.add(z);
                    shapes.add(H);
                    repaint();
                    
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }

//shapes.add(z);
//                   repaint();
                }

                if (selected instanceof Square) {

                    Square s1 = new Square(0, 0, 0, 0, current);
                    Square s2 = new Square(0, 0, 0, 0, current);
                    s1 = (Square) selected;
                    s2 = new Square(s1.getX1() + 10, s1.getY1() + 10, s1.getX2() + 10, s1.getY2() + 10, s1.getColor());

                    shapes.add(s1);
                    shapes.add(s2);

                    repaint();

                }

                Circle c = new Circle(0, 0, 0, 0, current);
                Circle d = new Circle(0, 0, 0, 0, current);
                if (selected instanceof Circle) {
                    c = (Circle) selected;
                    d = new Circle(c.getX1() + 10, c.getY1() + 10, c.getX2() + 10, c.getY2() + 10, current);
                    shapes.add(c);
                    shapes.add(d);
                    repaint();
                }

                Line l = new Line(0, 0, 0, 0, current);
                Line m = new Line(0, 0, 0, 0, current);
                if (selected instanceof Line) {
                    l = (Line) selected;
                    m = new Line(l.getX1() + 30, l.getY1() + 30, l.getX2() + 30, l.getY2() + 30, current);
                    shapes.add(l);
                    shapes.add(m);
                    repaint();
                }

                Oval o = new Oval(0, 0, 0, 0, current);
                Oval p = new Oval(0, 0, 0, 0, current);

                if (selected instanceof Oval) {
                    o = (Oval) selected;
                    p = new Oval(o.getX1() + 10, o.getY1() + 10, o.getX2() + 10, o.getY2() + 10, current);
                    shapes.add(o);
                    shapes.add(p);
                    repaint();
                } } }

        else if (mode == 30) {
            boolean check=false;
            int delete=0;
            for (int i = shapes.size() - 1; i >= 0; i--) {

                if (shapes.get(i).contains(x1, y1)) {
                    { System.out.println("select");
                    selected = shapes.get(i);
                    delete=i;
                    check=true;
                    break;
                    }}}
              if (check==true)
                    {
                      for (int i = delete; i <shapes.size()-1; i++) 
               {
               shapes.set(i, shapes.get(i+1));
               }        
                     Shapes  temp= shapes.remove(shapes.size()-1);
                      redo.push(temp);
                      repaint();
                      check=false;
                    
                    }
             else if (mode == 40) {

            for (int i = shapes.size() - 1; i >= 0; i--)
      while (w.hasNext()){
Shapes d = w.next();
                if (d.contains(x1, y1)) {
                    System.out.println("select");
                    selected = d;
                    break;
                }

            }
        }
                    
                    
                    
                    
                    
                }
                
             
                
                
                
                
                
                
                

            }

        
   

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    @Override
    public void mouseReleased(MouseEvent e) {

        selected = null;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        x2 = e.getX();
        y2 = e.getY();
        if (mode == 0) {

            Shapes h = shapes.get(shapes.size() - 1);

            if (h instanceof Line) {
                Line l = (Line) h;
                l.setX2(x2);
                l.setY2(y2);

            }
        } else if (mode == 1) {
            Shapes h = shapes.get(shapes.size() - 1);
            if (h instanceof Circle) {
                Circle c = (Circle) h;

                c.setX2(x2);
                c.setY2(y2);

            }
        } else if (mode == 2) {
            Shapes a = shapes.get(shapes.size() - 1);
            if (a instanceof Rectangle) {
                Rectangle r = (Rectangle) a;
                r.setX2(x2);
                r.setY2(y2);
            }
        } else if (mode == 3) {

            Shapes a = shapes.get(shapes.size() - 1);
            if (a instanceof Square) {
                Square s = (Square) a;
                s.setX2(x2);
                s.setY2(y2);
            }
        } else if (mode == 4) {
            x2 = e.getX();
            y2 = e.getY();
            x3 = e.getX();
            y3 = e.getY();
            Shapes h = shapes.get(shapes.size() - 1);

            if (h instanceof Triangle) {

                Triangle t = (Triangle) h;
                t.setX2(x2);
                t.setY2(x2);

                t.setX2(x3);
                t.setY2(x3);

            }
        } else if (mode == 5) {
            Shapes h = shapes.get(shapes.size() - 1);
            if (h instanceof Oval) {
                Oval c = (Oval) h;

                c.setX2(x2);
                c.setY2(y2);

            }
        } else if (mode == 10) {

            if (selected != null) {
                if (selected instanceof Rectangle) {

                    Rectangle r = (Rectangle) selected;

                    r.setX1(r.getX1() + x2 - x1);
                    r.setY1(r.getY1() + y2 - y1);
                    r.setX2(r.getX2() + x2 - x1);
                    r.setY2(r.getY2() + y2 - y1);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Square) {

                    Square r = (Square) selected;

                    r.setX1(r.getX1() + x2 - x1);
                    r.setY1(r.getY1() + y2 - y1);
                    r.setX2(r.getX2() + x2 - x1);
                    r.setY2(r.getY2() + y2 - y1);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Line) {
                    Line l = (Line) selected;

                    l.setX1(l.getX1() + x2 - x1);
                    l.setY1(l.getY1() + y2 - y1);
                    l.setX2(l.getX2() + x2 - x1);
                    l.setY2(l.getY2() + y2 - y1);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Oval) {

                    Oval l = (Oval) selected;

                    l.setX1(l.getX1() + x2 - x1);
                    l.setY1(l.getY1() + y2 - y1);
                    l.setX2(l.getX2() + x2 - x1);
                    l.setY2(l.getY2() + y2 - y1);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Triangle) {

                    Triangle l = (Triangle) selected;

                    l.setX1(l.getX1() + x2 - x1);
                    l.setY1(l.getY1() + y2 - y1);
                    l.setX2(l.getX2() + x2 - x1);
                    l.setY2(l.getY2() + y2 - y1);
                    l.setX3(l.getX3() + x3 - x2);
                    l.setY3(y2);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Circle) {
                    Circle l = (Circle) selected;
                    l.setX1(l.getX1() + x2 - x1);
                    l.setY1(l.getY1() + y2 - y1);
                    l.setX2(l.getX2() + x2 - x1);
                    l.setY2(l.getY2() + y2 - y1);

                    x1 = x2;
                    y1 = y2;
                }
            } 
            else if (mode == 40) {

            if (selected != null) {
                if (selected instanceof Rectangle) {

                    Rectangle r = (Rectangle) selected;

                    r.setX1(r.getX1() + x2 - x1);
                    r.setY1(r.getY1() + y2 - y1);
                    r.setX2(r.getX2() );
                    r.setY2(r.getY2() );

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Square) {

                    Square r = (Square) selected;

                    r.setX1(r.getX1() + x2 - x1);
                    r.setY1(r.getY1() );
                    r.setX2(r.getX2() );
                    r.setY2(r.getY2() );

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Line) {
                    Line l = (Line) selected;

                    l.setX1(l.getX1() );
                    l.setY1(l.getY1() );
                    l.setX2(l.getX2() + x2 - x1);
                    l.setY2(l.getY2() + y2 - y1);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Oval) {

                    Oval l = (Oval) selected;

                    l.setX1(l.getX1()+ x2 - x1 );
                    l.setY1(l.getY1() );
                    l.setX2(l.getX2());
                    l.setY2(l.getY2());

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Triangle) {

                    Triangle l = (Triangle) selected;

                    l.setX1(l.getX1() + x2 - x1);
                    l.setY1(l.getY1() + y2 - y1);
                    l.setX2(l.getX2() + x2 - x1);
                    l.setY2(l.getY2() + y2 - y1);
                    l.setX3(l.getX3() + x3 - x2);
                    l.setY3(y2);

                    x1 = x2;
                    y1 = y2;

                }

                if (selected instanceof Circle) {
                    Circle l = (Circle) selected;
                    l.setX1(l.getX1() + x2 - x1);
                    l.setY1(l.getY1() );
                    l.setX2(l.getX2() );
                    l.setY2(l.getY2() );

                    x1 = x2;
                    y1 = y2;
                }
            }
        }
        }
        repaint();
        first = false;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
