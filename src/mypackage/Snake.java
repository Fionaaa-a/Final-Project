package mypackage;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a snake enemy.
 * The snake patrols between boundaries and can damage
 * the player upon collision.
 *
 * @author Fiona
 */
public class Snake extends Character {
    // Snake image
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
     * Creates a snake enemy.
     *
     * @param p Processing application
     * @param x starting x-coordinate
     * @param y starting y-coordinate
     * @param name snake name
     * @param imagePath image file path
     * @param leftBound left patrol limit
     * @param rightBound right patrol limit
     * @param upBound upper patrol limit
     * @param botBound lower patrol limit
     */
    public Snake(PApplet p, int x, int y, String name,
                 String imagePath, int leftBound,
                 int rightBound, int upBound, int botBound) {
        // Call parent constructor
        super(p, x, y, 30, 30, name);
        // Load snake image
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
     * Updates the snake's position.
     * The snake patrols horizontally or vertically
     * depending on its boundaries.
     */
    public void update() {
        // Vertical movement
        if (upBound != botBound) {
            if (movingRight) {
                // Move downward
                y += speed;
                // Reverse direction at lower boundary
                if (y >= botBound) {
                    movingRight = false;
                }
            } else {
                // Move upward
                y -= speed;
                // Reverse direction at upper boundary
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
     * Returns the snake's width.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the snake's height.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the snake image.
     *
     * @return snake image
     */
    public PImage getImage() {
        return image;
    }
}