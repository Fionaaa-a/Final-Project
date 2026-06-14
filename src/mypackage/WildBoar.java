package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a wild boar enemy.
 * The boar moves back and forth between specified boundaries
 * and can damage the player upon collision.
 *
 * @author Fiona
 */
public class WildBoar extends Character {
    // Boar image
    private PImage image;
    // Movement speed
    private double speed;
    // Horizontal patrol boundaries
    private int leftBound;
    private int rightBound;
    // Vertical patrol boundaries
    private int upBound;
    private int botBound;
    // Current movement direction
    private boolean movingRight;

    /**
     * Creates a wild boar with custom movement boundaries.
     *
     * @param p Processing application
     * @param x starting x-coordinate
     * @param y starting y-coordinate
     * @param name boar name
     * @param imagePath image file path
     * @param leftBound left patrol limit
     * @param rightBound right patrol limit
     * @param upBound upper patrol limit
     * @param botBound lower patrol limit
     */
    public WildBoar(PApplet p, int x, int y, String name,
                    String imagePath,
                    int leftBound, int rightBound,
                    int upBound, int botBound) {
        // Call parent constructor
        super(p, x, y, 40, 40, name);
        // Load boar image
        image = app.loadImage(imagePath);
        // Set movement speed
        speed = 1.8;
        // Store patrol boundaries
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.upBound = upBound;
        this.botBound = botBound;
        // Start moving forward
        movingRight = true;
    }

    /**
     * Overloaded constructor that creates a boar
     * using default values.
     *
     * @param p Processing application
     * @param x starting x-coordinate
     * @param y starting y-coordinate
     * @param name boar name
     */
    public WildBoar(PApplet p, int x, int y, String name) {
        // Call the main constructor with default settings
        this(p, x, y, name,
             "image/Wild Boar-right.png",
             0, 100, 0, 0);
    }

    /**
     * Updates the boar's position.
     * The boar patrols horizontally or vertically
     * depending on the boundaries provided.
     */
    public void update() {
        // Vertical movement
        if (upBound != botBound) {
            if (movingRight) {
                // Move downward
                y += speed;
                // Reverse direction at lower bound
                if (y >= botBound) {
                    movingRight = false;
                }
            } else {
                // Move upward
                y -= speed;
                // Reverse direction at upper bound
                if (y <= upBound) {
                    movingRight = true;
                }
            }
        }

        // Horizontal movement
        else if (leftBound != rightBound) {
            if (movingRight) {
                // Move right
                x += speed;
                // Reverse direction at right boundary
                if (x >= rightBound) {
                    movingRight = false;
                }
            } else {
                // Move left
                x -= speed;
                // Reverse direction at left boundary
                if (x <= leftBound) {
                    movingRight = true;
                }
            }
        }
    }

    /**
     * Returns the boar's width.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the boar's height.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the boar image.
     *
     * @return boar image
     */
    public PImage getImage() {
        return image;
    }
}