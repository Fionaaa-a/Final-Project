package mypackage;
import processing.core.PApplet;

/**
 * Base character class for all moving entities in the game.
 * Stores position, size, name, and drawing reference.
 * @author Fiona
 */
public class Character {
    // X-coordinate of the character
    protected int x;
    // Y-coordinate of the character
    protected int y;
    // Width of the character
    protected int width;
    // Height of the character
    protected int height;
    // Character name
    protected String name;
    // Processing application reference
    protected PApplet app;

    /**
     * Creates a character object.
     * 
     * @param p Processing application
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @param width Character width
     * @param height Character height
     * @param name Character name
     */
    public Character(PApplet p, int x, int y, int width, int height, String name) {
        // Store Processing reference
        this.app = p;
        // Set position
        this.x = x;
        this.y = y;
        // Set dimensions
        this.width = width;
        this.height = height;
        // Set character name
        this.name = name;
    }

    /**
     * Moves the character by a specified amount.
     * 
     * @param dx Horizontal movement
     * @param dy Vertical movement
     */
    public void move(int dx, int dy) {
        // Update x position
        x += dx;
        // Update y position
        y += dy;
    }

    /**
     * Returns the x-coordinate.
     * 
     * @return character x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate.
     * 
     * @return character y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Draws the character.
     * Subclasses override this method.
     */
    public void draw() {
    }

    /**
     * Returns the character width.
     * 
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the character height.
     * 
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Checks whether the character collides with a herb.
     * 
     * @param herb herb being checked
     * @return true if collision occurs
     */
    public boolean isCollidingWith(Herb herb) {
        // Check left edge against herb's right edge
        boolean isLeftOfOtherRight =
            x < herb.getX() + herb.getWidth();
        // Check right edge against herb's left edge
        boolean isRightOfOtherLeft =
            x + width > herb.getX();
        // Check top edge against herb's bottom edge
        boolean isAboveOtherBottom =
            y < herb.getY() + herb.getHeight();
        // Check bottom edge against herb's top edge
        boolean isBelowOtherTop =
            y + height > herb.getY();
        // Collision occurs if all conditions are true
        return isLeftOfOtherRight
            && isRightOfOtherLeft
            && isAboveOtherBottom
            && isBelowOtherTop;
    }
}