package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

public class Shennong extends Character{
    private int age;
    private HealthBar healthBar;
    private int speed;
    private PImage image;
    
    public Shennong(PApplet p,int x,int y,String name,int age,String imagePath){
        super(p,x,y,name);
        this.age = age;
        healthBar = new HealthBar(100);
        this.image = app.loadImage(imagePath);
    }

    @Override
    public void draw(){
        app.image(image, x, y,45,45);
    }

    public int getHP(){
        return healthBar.getHP();
    }
}
