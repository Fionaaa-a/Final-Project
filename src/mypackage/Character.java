package mypackage;
import processing.core.PApplet;

public class Character{
    protected int x;
    protected int y;
    protected String name;
    protected PApplet app;

    public Character(PApplet p,int x,int y,String name){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;  
    }

    public void move(int dx,int dy){
        x += dx;
        y += dy;
    }

    public void draw(){

    }
}
