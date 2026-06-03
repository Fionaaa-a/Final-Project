/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import processing.core.PApplet;

/**
 *
 * @author user
 */
public class Character
{
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
