package mypackage;
import processing.core.PApplet;

/**
 * Represents a poisonous herb that damages the player
 * when collected.
 * 
 * @author Fiona
 */
public class PoisonHerb extends Herb {
    // Amount of HP removed when collected
    private int damage;

    /**
     * Creates a poisonous herb object.
     *
     * @param p Processing application
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width herb width
     * @param height herb height
     * @param name herb name
     * @param damage amount of HP lost
     * @param imagePath path to herb image
     */
    public PoisonHerb(PApplet p, int x, int y, int width, int height,
                      String name, int damage, String imagePath) {
        // Call parent Herb constructor
        super(p, x, y, width, height, name, imagePath);
        // Store damage value
        this.damage = damage;
    }

    /**
     * Returns the damage amount of the herb.
     *
     * @return amount of HP removed
     */
    public int getDamage() {
        return damage;
    }
}