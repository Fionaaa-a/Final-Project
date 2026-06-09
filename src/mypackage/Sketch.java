package mypackage;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Sketch extends PApplet {
    private Shennong shennong;
    private int stage = 0;
    private PImage menuBackground;
    private final int MAP_WIDTH = 1200;
    private final int MAP_HEIGHT = 800;
    private int cameraX;
    private int cameraY;
    private PImage backgroundMap;
    private PImage collisionMap;
    private ArrayList<Herb> herbs;

    public void settings() {
        size(400,350);
    }

    public void setup() {
        frameRate(60);
        textAlign(CENTER);
        textSize(30);
        shennong = new Shennong(this,115,650,"Shennong",50,"image/Shennong-right.png");  
        backgroundMap = loadImage("image/Background.png");
        collisionMap = loadImage("image/Collision.png");
        herbs = new ArrayList<>();
        herbs.add(new MedicinalHerb(this,120,115,30,30,"Ginseng",20,"image/Ginseng.png"));
        herbs.add(new MedicinalHerb(this,190,365,30,30,"Ginger",20,"image/Ginger.png"));
        herbs.add(new MedicinalHerb(this,514,577,30,30,"Goji Berry",20,"image/Goji Berry.png"));
        herbs.add(new MedicinalHerb(this,532,393,30,30,"Lingzhi",20,"image/Lingzhi.png"));
        herbs.add(new MedicinalHerb(this,667,77,30,30,"Mint",20,"image/Mint.png"));
        herbs.add(new PoisonHerb(this,754,434,30,30,"Nightshade",20,"image/Nightshade.png"));
        herbs.add(new PoisonHerb(this,966,661,30,30,"Toxic Mushroom",20,"image/Toxic Mushroom.png"));
        herbs.add(new PoisonHerb(this,1017,312,30,30,"Poison Ivy",20,"image/Poison Ivy.png"));
    }

    public void draw() {
        background(220);
        if (stage == 0) {
            drawMenu();
        } else if (stage == 1) {
            drawStory();
        } else if (stage == 2) {
            drawGame();
        }
    }

    public void drawMenu() {
        background(170, 220, 170);
        fill(0);
        textSize(30);
        text("SHENNONG", width / 2, 100);
        textSize(25);
        text("Tasting the Hundred Herbs", width / 2, 140);
        textSize(15);
        text("Press ENTER to Start", width / 2, 200);
    }

    public void drawStory() {
        background(240);
        fill(0);
        textSize(25);
        text("The Story of Shennong", width / 2, 30);
        textSize(12);
        text("Long ago in ancient China,\n\n"
                + "Shennong wanted to help people cure diseases.\n\n"
                + "He travelled through forests and mountains\n"
                + "and tasted hundreds of herbs.\n\n"
                + "Some herbs were useful medicines,\n"
                + "while others were poisonous.\n\n"
                + "Your mission is to discover medicinal herbs\n"
                + "and complete the Herb Journal.",
                width / 2,
                60);
        text("Press ENTER to Begin Your Quest",width / 2,250);
    }

    public void drawGame() {
        cameraX = shennong.getX() - width / 2;
        cameraY = shennong.getY() - height / 2;
        cameraX = constrain(cameraX,0,MAP_WIDTH - width);
        cameraY = constrain(cameraY,0,MAP_HEIGHT - height);
        image(backgroundMap,-cameraX,-cameraY);
        for(Herb h : herbs){
            image(h.image,h.getX() - cameraX,h.getY() - cameraY,30,30);
        }
        image(shennong.getImage(),shennong.getX() - cameraX,shennong.getY() - cameraY,32,32);
        fill(255);
        rect(5,5,80,25);
        fill(0);
        textAlign(LEFT);
        textSize(14);
        text("HP: " + shennong.getHP(),10,22);
        checkHerbCollisions();
    }

    public void keyPressed() {
        if (keyCode == ENTER) {
            if (stage < 2) {
                stage++;
            }
        }
        if (stage == 2) {
            if (keyPressed) {
                if (keyCode == LEFT) {
                    int newX = shennong.getX() - 4;
                    if (canMoveTo(newX, shennong.getY())) {
                        shennong.move(-4, 0);
                    }
                } else if (keyCode == RIGHT) {
                    int newX = shennong.getX() + 4;
                    if (canMoveTo(newX, shennong.getY())) {
                        shennong.move(4, 0);
                    }
                } else if (keyCode == UP) {
                    int newY = shennong.getY() - 4;
                    if (canMoveTo(shennong.getX(), newY)) {
                        shennong.move(0, -4);
                    }
                } else if (keyCode == DOWN) {
                    int newY = shennong.getY() + 4;
                    if (canMoveTo(shennong.getX(), newY)) {
                        shennong.move(0, 4);
                    }
                }
            }
        }
    }
    
    public boolean canMoveTo(int x, int y) {
        int w = 22;
        int h = 28;
        return isWalkable(x, y)
            && isWalkable(x + w, y)
            && isWalkable(x, y + h)
            && isWalkable(x + w, y + h);
    }

    public boolean isWalkable(int px, int py) {
        if(px < 0 || px >= MAP_WIDTH){
            return false;
        }
        if(py < 0 || py >= MAP_HEIGHT){
            return false;
        }
        int c = collisionMap.get(px, py);
        return alpha(c) == 0;
    }
    
    public static void main(String[] args) {
        PApplet.main("mypackage.Sketch");
    }
    
    public void checkHerbCollisions(){
        for(int i = herbs.size()-1; i >= 0; i--){
            Herb h = herbs.get(i);
            if(shennong.isCollidingWith(h)){
                if(h instanceof MedicinalHerb){
                    MedicinalHerb mh =
                        (MedicinalHerb) h;
                    shennong.heal(
                        mh.getHealAmount()
                    );
                }
                else if(h instanceof PoisonHerb){
                    PoisonHerb ph =
                        (PoisonHerb) h;
                    shennong.damage(
                        ph.getDamage()
                    );
                }
                herbs.remove(i);
            }
        }
    }
}
