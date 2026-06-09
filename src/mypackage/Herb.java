package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

public class Herb {
    protected int x;
    protected int y;
    protected String name;
    protected PImage image;
    protected PApplet app;
    protected int width;
    protected int height;

    public Herb(PApplet p,int x,int y,int width,int height,String name,String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        image = app.loadImage(imagePath);
        this.width = width;
        this.height = height;
    }

    public void draw(){
        app.image(image,x,y,width,height);
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
    
    public int getWidth(){
    return width;
}

    public int getHeight(){
        return height;
    }
}
