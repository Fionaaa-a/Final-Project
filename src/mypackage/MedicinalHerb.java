package mypackage;

import processing.core.PApplet;

public class MedicinalHerb extends Herb {
    private int healAmount;

    public MedicinalHerb(PApplet p,int x,int y,String name,
                         int healAmount,String imagePath){
        super(p, x, y, name, imagePath);
        this.healAmount = healAmount;
    }

    public int getHealAmount(){
        return healAmount;
    }
}