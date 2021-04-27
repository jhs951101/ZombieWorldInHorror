package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-27.
 */

public class HasZombiePower implements ZombiePower {

    private double MAX_ZOMBIE_POWER;
    private int zombiepower;
    private int zImageWidth;

    private WeaponInfo playerWeapon;

    public HasZombiePower(WeaponInfo pw, double mzp, int ziw){
        this.playerWeapon = pw;

        this.MAX_ZOMBIE_POWER = mzp;
        this.zombiepower = (int) mzp;
        this.zImageWidth = ziw;
    }

    @Override
    public double convertToEnemyZombiePower(int n) {
        return n / MAX_ZOMBIE_POWER * zImageWidth;
    }

    @Override
    public double getZombiePower() {
        return zombiepower;
    }

    public void minusZombiePower(){
        zombiepower -= playerWeapon.getAttackPower();
    }
}
