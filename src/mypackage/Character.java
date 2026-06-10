package mypackage;
import processing.core.PApplet;

public class Character{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String name;
    protected PApplet app;

    public Character(PApplet p,int x,int y,int width,int height,String name){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void move(int dx,int dy){
        x += dx;
        y += dy;
    }
    
    public int getX() {
        return x;
    }

    
    public int getY() {
        return y;
    }

    public void draw(){
    }
    
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
    
    public boolean isCollidingWith(Herb herb){
        boolean isLeftOfOtherRight =
            x < herb.getX() + herb.getWidth();
        boolean isRightOfOtherLeft =
            x + width > herb.getX();
        boolean isAboveOtherBottom =
            y < herb.getY() + herb.getHeight();
        boolean isBelowOtherTop =
            y + height > herb.getY();
        return isLeftOfOtherRight
            && isRightOfOtherLeft
            && isAboveOtherBottom
            && isBelowOtherTop;
    }
}
