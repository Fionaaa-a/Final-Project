package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

public class Shennong extends Character{
    private int age;
    private HealthBar healthBar;
    private int speed;
    private PImage[] rightFrames;
    private PImage[] leftFrames;
    private int frameIndex;
    private boolean facingRight;    
    
    public Shennong(PApplet p,int x,int y,String name,int age,String imagePath){
        super(p,x,y,32,32,name);
        this.age = age;
        healthBar = new HealthBar(100);
        rightFrames = new PImage[4];
        leftFrames = new PImage[4];
        for(int i = 0; i < 4; i++){
            rightFrames[i] =app.loadImage("image/Shennongright" + (i+1) + ".png");
            leftFrames[i] =app.loadImage("image/Shennongleft" + (i+1) + ".png");
        }
        frameIndex = 0;
        facingRight = true;
    }

    @Override
    public void draw(){
        app.image(getImage(),x,y,64,64);
    }

    public int getHP(){
        return healthBar.getHP();
    }
    
    public PImage getImage(){
        if(facingRight){
            return rightFrames[frameIndex];
        }else{
            return leftFrames[frameIndex];
        }
    }

    public void animate(){
        if(app.frameCount % 6 == 0){
            frameIndex++;
            if(frameIndex >= 4){
                frameIndex = 0;
            }
        }
    }
    
    public void faceLeft(){
        facingRight = false;
    }
    
    public void idle(){
        frameIndex = 0;
    }
    
    public void heal(int amount){
        healthBar.heal(amount);
    }

    public void damage(int amount){
        healthBar.damage(amount);
    }
    
    public void faceRight(){
        facingRight = true;
    }
}
