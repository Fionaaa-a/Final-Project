/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import processing.core.PApplet;

/**
 *
 * @author user
 */
public class HealthBar
{
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
