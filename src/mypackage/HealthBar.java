package mypackage;

/**
 * Stores and manages the player's health points.
 * Allows health to be increased or decreased within limits.
 * 
 * @author Fiona
 */
public class HealthBar {
    // Current health points
    private int hp;

    /**
     * Creates a HealthBar object.
     *
     * @param hp starting health points
     */
    public HealthBar(int hp) {
        // Set initial health
        this.hp = hp;
    }

    /**
     * Returns the current HP.
     *
     * @return current health points
     */
    public int getHP() {
        return hp;
    }

    /**
     * Restores health points.
     * HP cannot exceed 100.
     *
     * @param amount amount of HP to restore
     */
    public void heal(int amount) {
        // Increase HP
        hp += amount;
        // Limit HP to maximum value
        if (hp > 100) {
            hp = 100;
        }
    }

    /**
     * Reduces health points.
     * HP cannot go below 0.
     *
     * @param amount amount of HP to remove
     */
    public void damage(int amount) {
        // Decrease HP
        hp -= amount;
        // Prevent negative HP
        if (hp < 0) {
            hp = 0;
        }
    }
}