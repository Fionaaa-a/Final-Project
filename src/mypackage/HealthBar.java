package mypackage;

public class HealthBar {
    private int hp;

    public HealthBar(int hp){
        this.hp = hp;
    }

    public int getHP(){
        return hp;
    }

    public void heal(int amount){
        hp += amount;

        if(hp > 100){
            hp = 100;
        }
    }

    public void damage(int amount){
        hp -= amount;
        if(hp < 0){
            hp = 0;
        }
    }
}