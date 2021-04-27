package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-03.
 */

public class HitPosition extends Position {

    private int green = 255;
    private int health = 100;
    private long limitedTime;

    private WeaponInfo playerWeapon;

    public HitPosition(WeaponInfo pw, int x, int y, int h, long lt){
        super(x, y);
        this.playerWeapon = pw;
        this.health = h;
        this.limitedTime = lt;
    }

    public void minusHealth(){
        health -= playerWeapon.getAttackPower();
    }

    public int getHealth(){
        return this.health;
    }

    public int getGreen(){
            return this.green;
        }

    public void setGreen(long time){
        green = (int)((255 - time) / (double)limitedTime * 255);
    }
}
