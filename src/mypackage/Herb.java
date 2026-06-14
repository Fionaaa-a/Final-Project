package mypackage;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Base class for all herbs in the game.
 * Stores herb information such as position, size, name, and image.
 * 
 * @author Fiona
 */
public class Herb {
    // X-coordinate of the herb
    protected int x;
    // Y-coordinate of the herb
    protected int y;
    // Name of the herb
    protected String name;
    // Herb image
    protected PImage image;
    // Processing application reference
    protected PApplet app;
    // Width of the herb image
    protected int width;
    // Height of the herb image
    protected int height;
    // Counts the total number of herbs created
    public static int totalHerbs = 0;

    /**
     * Creates a Herb object.
     *
     * @param p Processing application
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width herb width
     * @param height herb height
     * @param name herb name
     * @param imagePath path to the herb image
     */
    public Herb(PApplet p, int x, int y, int width, int height,
                String name, String imagePath) {
        // Store Processing reference
        this.app = p;
        // Set position
        this.x = x;
        this.y = y;
        // Set herb name
        this.name = name;
        // Load herb image
        image = app.loadImage(imagePath);
        // Set dimensions
        this.width = width;
        this.height = height;
        // Increase total herb count
        totalHerbs++;
    }

    /**
     * Draws the herb on the screen.
     */
    public void draw() {
        // Display herb image
        app.image(image, x, y, width, height);
    }

    /**
     * Returns the herb's x-coordinate.
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the herb's y-coordinate.
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the herb's name.
     *
     * @return herb name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the herb's width.
     *
     * @return herb width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the herb's height.
     *
     * @return herb height
     */
    public int getHeight() {
        return height;
    }
}