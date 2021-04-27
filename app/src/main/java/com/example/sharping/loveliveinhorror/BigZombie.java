package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-07-29.
 */

public class BigZombie extends Zombie {

    public BigZombie(Resources r, Context c, WeaponInfo pw, int x, int xs, int l){
        super(c, pw, x, xs, l);
        setCharacter2(r);
    }

    public void setCharacter2(Resources r){
        zImageLeft1 = BitmapFactory.decodeResource(r, R.drawable.bigzombieleft1);
        zImageLeft2 = BitmapFactory.decodeResource(r, R.drawable.bigzombieleft2);
        zImageRight1 = BitmapFactory.decodeResource(r, R.drawable.bigzombieright1);
        zImageRight2 = BitmapFactory.decodeResource(r, R.drawable.bigzombieright2);
        zImageLeftDead1 = BitmapFactory.decodeResource(r, R.drawable.bigzombieleftdead1);
        zImageLeftDead2 = BitmapFactory.decodeResource(r, R.drawable.bigzombieleftdead2);
        zImageRightDead1 = BitmapFactory.decodeResource(r, R.drawable.bigzombierightdead1);
        zImageRightDead2 = BitmapFactory.decodeResource(r, R.drawable.bigzombierightdead2);

        zombiePower = new HasNoZombiePower();
        zImageLength = new Length(zImageLeft1.getWidth(), zImageLeft1.getHeight());

        y = 0;
        ySpeed = 10;
        health = 150;
        MAX_HEALTH = 150.0;
    }
}
