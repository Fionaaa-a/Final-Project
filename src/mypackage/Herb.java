package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

public class Herb {
    protected int x;
    protected int y;
    protected String name;
    protected PImage image;
    protected PApplet app;

    public Herb(PApplet p,int x,int y,String name,String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        image = app.loadImage(imagePath);
    }

    public void draw(){
        app.image(image, x, y, 30, 30);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getName(){
        return name;
    }
}
