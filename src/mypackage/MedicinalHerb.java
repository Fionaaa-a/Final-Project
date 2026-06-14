package mypackage;

import processing.core.PApplet;

/**
 * Represents a medicinal herb that restores health
 * when collected by the player.
 * 
 * @author Fiona
 */
public class MedicinalHerb extends Herb {
    // Amount of HP restored when collected
    private int healAmount;

    /**
     * Creates a medicinal herb object.
     *
     * @param p Processing application
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width herb width
     * @param height herb height
     * @param name herb name
     * @param healAmount amount of HP restored
     * @param imagePath path to herb image
     */
    public MedicinalHerb(PApplet p, int x, int y, int width, int height,
                         String name, int healAmount, String imagePath) {
        // Call parent Herb constructor
        super(p, x, y, width, height, name, imagePath);
        // Store healing value
        this.healAmount = healAmount;
    }

    /**
     * Returns the healing amount of the herb.
     *
     * @return amount of HP restored
     */
    public int getHealAmount() {
        return healAmount;
    }
}