package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-23.
 */

public class WeaponInfo {
    private char wId;
    private int attackPower;
    private int attackCount;
    private int reloadTime;
    private int attackRange;

    public WeaponInfo(char wi, int ap, int ac, int rt, int ar){
        this.wId = wi;
        this.attackPower = ap;
        this.attackCount = ac;
        this.reloadTime = rt;
        this.attackRange = ar;
    }

    public char getWId(){
        return this.wId;
    }

    public int getAttackPower(){
        return this.attackPower;
    }

    public int getAttackCount(){
        return this.attackCount;
    }

    public int getReloadTime(){
        return this.reloadTime;
    }

    public int getAttackRange(){
        return this.attackRange;
    }
}
