package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
    private Shennong shennong;
    private int stage = 0;
    private PImage menuBackground;

    public void settings() {
        size(1200,800);
    }

    public void setup() {
        frameRate(60);
        textAlign(CENTER);
        textSize(30);
        shennong = new Shennong(this,500,350,"Shennong",50,"image/Shennong-right.png");
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
        textSize(50);
        text("SHENNONG", width / 2, 180);
        textSize(35);
        text("Tasting the Hundred Herbs", width / 2, 240);
        textSize(25);
        text("Press ENTER to Start", width / 2, 500);
    }

    public void drawStory() {
        background(240);
        fill(0);
        textSize(35);
        text("The Story of Shennong", width / 2, 120);
        textSize(24);
        text(
                "Long ago in ancient China,\n\n"
                + "Shennong wanted to help people cure diseases.\n\n"
                + "He travelled through forests and mountains\n"
                + "and tasted hundreds of herbs.\n\n"
                + "Some herbs were useful medicines,\n"
                + "while others were poisonous.\n\n"
                + "Your mission is to discover medicinal herbs\n"
                + "and complete the Herb Journal.",
                width / 2,
                220
        );
        text("Press ENTER to Begin Your Quest",width / 2,700);
    }

    public void drawGame() {
        background(180, 230, 180);
        fill(0);
        textAlign(LEFT);
        textSize(24);
        text("HP: " + shennong.getHP(), 20, 40);
        shennong.draw();
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
              shennong.move(-10, 0);
            } else if (keyCode == RIGHT) {
              shennong.move(10, 0);
            } else if (keyCode == UP) {
              shennong.move(0, -10);
            } else if (keyCode == DOWN) {
              shennong.move(0, 10);
            }
       }
        }
    }

    public static void main(String[] args) {
        PApplet.main("mypackage.Sketch");
    }

}
