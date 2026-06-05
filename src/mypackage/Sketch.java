package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
    private Shennong shennong;
    private int stage = 0;
    private PImage menuBackground;
    private final int MAP_WIDTH = 1200;
    private final int MAP_HEIGHT = 800;
    private int cameraX;
    private int cameraY;
    private PImage backgroundMap;

    public void settings() {
        size(400,300);
    }

    public void setup() {
        frameRate(60);
        textAlign(CENTER);
        textSize(30);
        shennong = new Shennong(this,115,650,"Shennong",50,"image/Shennong-right.png");  
        backgroundMap = loadImage("image/Background.png");
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
        image(shennong.getImage(),shennong.getX() - cameraX,shennong.getY() - cameraY,35,35);
        fill(255);
        rect(5,5,80,25);
        fill(0);
        textAlign(LEFT);
        textSize(14);
        text("HP: " + shennong.getHP(),10,22);
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
                  shennong.move(-5, 0);
                } else if (keyCode == RIGHT) {
                  shennong.move(5, 0);
                } else if (keyCode == UP) {
                  shennong.move(0, -5);
                } else if (keyCode == DOWN) {
                  shennong.move(0, 5);
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("mypackage.Sketch");
    }
}
