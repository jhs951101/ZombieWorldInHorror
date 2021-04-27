package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-07-18.
 */

public class GameManager extends Thread {

    private Resources resources;
    private Context context;

    private ArrayList<Zombie> zombies;
    private WeaponInfo playerWeapon;
    private Length smallZombieLength;

    private long startedTime;
    private int level;
    private int stage;
    private int currentNumOfZombie;

    public GameManager(Resources r, Context context, WeaponInfo pw, ArrayList<Zombie> c, int l, int s){
        this.resources = r;
        this.context = context;
        this.playerWeapon = pw;
        this.zombies = c;
        this.level = l;
        this.stage = s;

        Bitmap smallZombie = BitmapFactory.decodeResource(r, R.drawable.smallzombieleft1);
        smallZombieLength = new Length(smallZombie.getWidth(), smallZombie.getHeight());

        smallZombie.recycle();
        smallZombie = null;

        if(level == 1 && stage == 1) {  // Level: 1 & Stage: 1
            currentNumOfZombie = 15;
        }
        else if(level == 1 && stage == 5) {  // Level: 1 & Stage: 5
            currentNumOfZombie = 1;
        }
    }

    public int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max-min+1)) + min;
    }

    @Override
    public void run(){
        startedTime = System.currentTimeMillis();

        if(level == 1 && stage == 1) {  // Level: 1 & Stage: 1
            int x = 200;
            int xChange = 100;

            while(GameView.gameFlag == 1){
                while(System.currentTimeMillis() - startedTime >= 1500 && GameView.gameFlag == 1){
                    SmallZombie smallZombie1 = new SmallZombie(resources, context, playerWeapon, x, level);
                    smallZombie1.start();
                    zombies.add(smallZombie1);

                    if(!(x > 100 && x < GameView.WIDTH - smallZombieLength.getWidth() - 100))
                        xChange *= -1;

                    x += xChange;

                    startedTime = System.currentTimeMillis();
                }
            }
        }
        else if(level == 1 && stage == 5) {  // Level: 1 & Stage: 5
            int xSpeed = 10;
            int ySpeed = 10;

            if(getRandomNumber(0, 1) == 1)
                xSpeed *= -1;
            if(getRandomNumber(0, 1) == 1)
                ySpeed *= -1;

            BossZombie bossZombie = new BossZombie(resources, context, playerWeapon, 100, 50, 250, xSpeed, ySpeed, level);
            // Resources r, Context c, WeaponInfo pw, double mzp, int x, int y, int xs, int ys, int l
            bossZombie.start();
            zombies.add(bossZombie);
        }
    }

    public void minusCurrentNumOfZombie(){
        this.currentNumOfZombie -= 1;
    }

    public int getCurrentNumOfZombie(){
        return this.currentNumOfZombie;
    }
}

