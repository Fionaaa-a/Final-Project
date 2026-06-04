package mypackage;
import processing.core.PApplet;

public class HealthBar{
    private int hp;

    public HealthBar(int hp){
        this.hp = hp;
    }

    public int getHP(){
        return hp;
    }

    public void loseHealth(int damage){
        hp -= damage;
    }

    public void gainHealth(int amount){
        hp += amount;
    }
}
