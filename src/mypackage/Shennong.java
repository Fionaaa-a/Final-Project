package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the player character Shennong.
 * Handles health, animation, and character direction.
 * 
 * @author Fiona
 */
public class Shennong extends Character {
    // Age of Shennong
    private int age;
    // Stores the player's health
    private HealthBar healthBar;
    // Movement speed (reserved for future use)
    private int speed;
    // Animation frames facing right
    private PImage[] rightFrames;
    // Animation frames facing left
    private PImage[] leftFrames;
    // Current animation frame
    private int frameIndex;
    // Tracks which direction the player is facing
    private boolean facingRight;

    /**
     * Creates a Shennong object.
     *
     * @param p Processing application
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @param name Character name
     * @param age Character age
     * @param imagePath Image path (unused after animation upgrade)
     */
    public Shennong(PApplet p, int x, int y, String name, int age, String imagePath) {
        // Call parent constructor
        super(p, x, y, 32, 32, name);
        // Store age
        this.age = age;
        // Create health bar with 100 HP
        healthBar = new HealthBar(100);
        // Create animation frame arrays
        rightFrames = new PImage[4];
        leftFrames = new PImage[4];
        // Load all walking animation frames
        for (int i = 0; i < 4; i++) {
            // Load right-facing frames
            rightFrames[i] =
                app.loadImage("image/Shennongright" + (i + 1) + ".png");
            // Load left-facing frames
            leftFrames[i] =
                app.loadImage("image/Shennongleft" + (i + 1) + ".png");
        }
        // Start with first frame
        frameIndex = 0;
        // Face right by default
        facingRight = true;
    }

    /**
     * Draws Shennong on the screen.
     */
    @Override
    public void draw() {
        // Display current animation frame
        app.image(getImage(), x, y, 64, 64);
    }

    /**
     * Returns the current HP.
     *
     * @return current health points
     */
    public int getHP() {
        return healthBar.getHP();
    }

    /**
     * Returns the correct image based on direction and frame.
     *
     * @return current animation frame image
     */
    public PImage getImage() {
        // Use right-facing animation
        if (facingRight) {
            return rightFrames[frameIndex];
        }
        // Use left-facing animation
        return leftFrames[frameIndex];
    }

    /**
     * Advances the walking animation.
     */
    public void animate() {
        // Change frame every 6 game frames
        if (app.frameCount % 6 == 0) {
            frameIndex++;
            // Loop back to first frame
            if (frameIndex >= 4) {
                frameIndex = 0;
            }
        }
    }

    /**
     * Changes the character's direction to left.
     */
    public void faceLeft() {
        // Face left
        facingRight = false;
    }

    /**
     * Returns animation to idle frame.
     */
    public void idle() {
        // Show standing frame
        frameIndex = 0;
    }

    /**
     * Heals the player.
     *
     * @param amount amount of HP restored
     */
    public void heal(int amount) {

        // Increase HP
        healthBar.heal(amount);
    }

    /**
     * Damages the player.
     *
     * @param amount amount of HP lost
     */
    public void damage(int amount) {
        // Decrease HP
        healthBar.damage(amount);
    }

    /**
     * Changes the character's direction to right.
     */
    public void faceRight() {
        // Face right
        facingRight = true;
    }
}