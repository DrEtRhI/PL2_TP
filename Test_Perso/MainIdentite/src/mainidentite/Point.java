/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainidentite;

/**
 *
 * @author laura
 */
public class Point {
    int x;
    int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void translater(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    public void remiseAZero(){
        this.x = 0;
        this.y = 0;
    }
    
    public void foo(int d, Point p){
        p.translater (20, 20);
        d += 20;
        p = new Point(100, 100);
        p.remiseAZero();
    }
    
    public void coordonneesPoint(){
        System.out.println("x : " + this.x);
        System.out.println("y : " + this.y);
    }
}
