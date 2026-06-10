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

public class WildBoar extends Character{
    private PImage image;
    private double speed;
    private int leftBound;
    private int rightBound;
    private int upBound;
    private int botBound;
    private boolean movingRight;

    public WildBoar(
            PApplet p,
            int x,
            int y,
            String name,
            String imagePath,
            int leftBound,
            int rightBound,int upBound,int botBound){

        super(p,x,y,40,40,name);

        image = app.loadImage(imagePath);

        speed = 1.8;

        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.upBound = upBound;
        this.botBound = botBound;

        movingRight = true;
     
  
    }

    public void update(){

    if(upBound != botBound){

        if(movingRight){

            y += speed;

            if(y >= botBound){
                movingRight = false;
            }

        }else{

            y -= speed;

            if(y <= upBound){
                movingRight = true;
            }
        }
    }

    else if(leftBound != rightBound){

        if(movingRight){

            x += speed;

            if(x >= rightBound){
                movingRight = false;
            }

        }else{

            x -= speed;

            if(x <= leftBound){
                movingRight = true;
            }
        }
    }

}

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
    
        public PImage getImage() {
        return image;
    }
        
}
