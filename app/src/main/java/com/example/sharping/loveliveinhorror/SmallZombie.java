package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-05-09.
 */

public class SmallZombie extends Zombie {

    public SmallZombie(Resources r, Context c, WeaponInfo pw, int x, int l){
        super(c, pw, x, l);
        setCharacter2(r);
    }

    public SmallZombie(Resources r, Context c, WeaponInfo pw, int x, int xs, int l){
        super(c, pw, x, xs, l);
        setCharacter2(r);
    }

    public void setCharacter2(Resources r){
        zImageLeft1 = BitmapFactory.decodeResource(r, R.drawable.smallzombieleft1);
        zImageLeft2 = BitmapFactory.decodeResource(r, R.drawable.smallzombieleft2);
        zImageRight1 = BitmapFactory.decodeResource(r, R.drawable.smallzombieright1);
        zImageRight2 = BitmapFactory.decodeResource(r, R.drawable.smallzombieright2);
        zImageLeftDead1 = BitmapFactory.decodeResource(r, R.drawable.smallzombieleftdead1);
        zImageLeftDead2 = BitmapFactory.decodeResource(r, R.drawable.smallzombieleftdead2);
        zImageRightDead1 = BitmapFactory.decodeResource(r, R.drawable.smallzombierightdead1);
        zImageRightDead2 = BitmapFactory.decodeResource(r, R.drawable.smallzombierightdead2);

        zombiePower = new HasNoZombiePower();
        zImageLength = new Length(zImageLeft1.getWidth(), zImageLeft1.getHeight());

        y = 0;
        ySpeed = 30;
        health = 50;
        MAX_HEALTH = 50.0;
    }
}