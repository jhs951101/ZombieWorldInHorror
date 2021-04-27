package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-27.
 */

public class HasNoZombiePower implements ZombiePower {

    @Override
    public double convertToEnemyZombiePower(int n) {
        System.out.println("Error: no zombie power");
        return -1;
    }

    @Override
    public double getZombiePower() {
        System.out.println("Error: no zombie power");
        return -1;
    }

    @Override
    public void minusZombiePower() {
        System.out.println("Error: no zombie power");
    }
}
