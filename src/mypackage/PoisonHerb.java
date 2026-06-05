package mypackage;
import processing.core.PApplet;

public class PoisonHerb extends Herb {
    private int damage;

    public PoisonHerb(PApplet p,int x,int y,String name,
                      int damage,String imagePath){
        super(p, x, y, name, imagePath);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}