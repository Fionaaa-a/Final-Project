/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author user
 */
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
        app.image(image, x, y);
    }

    public int getHP(){
        return healthBar.getHP();
    }
}
