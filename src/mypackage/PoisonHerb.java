package mypackage;
import processing.core.PApplet;

public class PoisonHerb extends Herb {
    private int damage;

    public PoisonHerb(PApplet p,int x,int y,int width, int height, String name,
                      int damage,String imagePath){
        super(p, x, y, width, height, name, imagePath);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}