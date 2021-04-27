package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-05-09.
 */

public class BossZombie extends Zombie {

    private final int HITPOINT_RADIUS = 140;

    private ArrayList<HitPosition> hitPoints;
    private long startedTime;

    private HitPointTimer hpTimer;
    private int hitPointTime;
    private int hitPointType;
    /*
    0: type 1
    1: type 2
    2: type 3
    */

    private KnockOutTimer koTimer;

    public BossZombie(Resources r, Context c, WeaponInfo pw, double mzp, int x, int y, int xs, int ys, int l){
        super(c, pw, x, xs, l);

        this.y = y;
        this.ySpeed = ys;

        setCharacter2(r, mzp);
    }

    public void setCharacter2(Resources r, double mzp){
        zImageLeft1 = BitmapFactory.decodeResource(r, R.drawable.bosszombieleft1);
        zImageLeft2 = BitmapFactory.decodeResource(r, R.drawable.bosszombieleft2);
        zImageRight1 = BitmapFactory.decodeResource(r, R.drawable.bosszombieright1);
        zImageRight2 = BitmapFactory.decodeResource(r, R.drawable.bosszombieright2);
        zImageLeftDead1 = BitmapFactory.decodeResource(r, R.drawable.bosszombieleftdead1);
        zImageLeftDead2 = BitmapFactory.decodeResource(r, R.drawable.bosszombieleftdead2);
        zImageRightDead1 = BitmapFactory.decodeResource(r, R.drawable.bosszombierightdead1);
        zImageRightDead2 = BitmapFactory.decodeResource(r, R.drawable.bosszombierightdead2);

        zombiePower = new HasZombiePower(playerWeapon, mzp, zImageLeft1.getWidth());
        zImageLength = new Length(zImageLeft1.getWidth(), zImageLeft1.getHeight());

        health = 100000;
        MAX_HEALTH = 100000.0;

        hitPoints = new ArrayList<>();
        hpTimer = new HitPointTimer(hitPoints);
        koTimer = new KnockOutTimer(this);
        hitPointTime = 8000;
        startedTime = System.currentTimeMillis();
    }

    public void collision(){
        if(x <= 0 || x + zImageLength.getWidth() >= GameView.WIDTH) {
            xSpeed *= -1;

            if(!hpTimer.isTimerUsed())
                zImageDirection = (short)(1 - zImageDirection);
        }

        if(y <= 200 || y + zImageLength.getHeight() + 150 >= GameView.HEIGHT)
            ySpeed *= -1;
    }

    @Override
    public void run(){
        try {
            while(GameView.gameFlag == 1 && flag) {
                if(health > 0) {
                    collision();
                    move();

                    if(!hpTimer.isTimerUsed()) {
                        zImageChange++;

                        if (zImageChange >= 20) {
                            zImageState = (short) (1 - zImageState);
                            zImageChange = 0;
                        }
                    }

                    if (GameView.gameFlag == 1 && flag)
                        Thread.sleep(50);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zImageLeft1.recycle();
        zImageLeft2.recycle();
        zImageRight1.recycle();
        zImageRight2.recycle();
        zImageLeftDead1.recycle();
        zImageLeftDead2.recycle();
        zImageRightDead1.recycle();
        zImageRightDead2.recycle();

        zImageLeft1 = null;
        zImageLeft2 = null;
        zImageRight1 = null;
        zImageRight2 = null;
        zImageLeftDead1 = null;
        zImageLeftDead2 = null;
        zImageRightDead1 = null;
        zImageRightDead2 = null;
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        if(GameView.gameFlag == 1 && flag) {
            for (int i = 0; i < hitPoints.size(); i++) {
                HitPosition p = hitPoints.get(i);

                Paint circleStyle = new Paint();
                circleStyle.setColor(Color.rgb(255, p.getGreen(), 0));
                circleStyle.setStyle(Paint.Style.STROKE);
                circleStyle.setStrokeWidth(30);

                canvas.drawCircle(x + p.getX(), y + p.getY(), HITPOINT_RADIUS, circleStyle);
            }
        }
    }

    public void startHitPointTimer(){
        long limitedTime = 5000;

        hpTimer = new HitPointTimer(hitPoints);
        hpTimer.setLimitTime(limitedTime);

        if(hitPointType == 0) {
            zImageDirection = 0;
            zImageState = 0;

            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth() / 2 - 34 + 100, zImageLength.getHeight() / 2 - 47 + 80, 100, limitedTime));
            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth() - 110, 30 + 80, 100, limitedTime));
            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth() / 2 + 60 + 200, zImageLength.getHeight() / 2 - 50 + 80, 100, limitedTime));

            hitPointType = 1;
        }
        else if(hitPointType == 1) {
            zImageDirection = 0;
            zImageState = 1;
            hitPoints.add(new HitPosition(playerWeapon, 50 + 80, 60 + 80, 200, limitedTime));
            hitPointType = 2;
        }
        else if(hitPointType == 2) {
            zImageDirection = 0;
            zImageState = 1;

            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth()/2 + 7 + 80, zImageLength.getHeight()/2 + 45 + 80, 100, limitedTime));
            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth()/2 + 90 + 160, zImageLength.getHeight()/2 - 70, 100, limitedTime));
            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth()/2 + 70, zImageLength.getHeight() - 100, 100, limitedTime));
            hitPoints.add(new HitPosition(playerWeapon, zImageLength.getWidth()/2 + 220, zImageLength.getHeight() - 100, 100, limitedTime));

            hitPointType = 0;
        }

        hpTimer.start();
    }

    public void createCircles(){
        if(flag) {
            long elapsedTime = System.currentTimeMillis() - startedTime;

            if (elapsedTime >= hitPointTime && health > 0) {
                startHitPointTimer();
                startedTime = System.currentTimeMillis();
            }
        }
    }

    public boolean isFailure(){
        if(flag && hpTimer.isFailure()){
            hitPoints.clear();
            hpTimer.returnFailure();
            return true;
        }

        return false;
    }

    public void stopCounter(){
        hpTimer.flag = false;
    }

    public void circleTouch(int x, int y){
        for(int i=0; i<hitPoints.size(); i++){
            HitPosition p = hitPoints.get(i);

            if(x >= this.x + p.getX() - HITPOINT_RADIUS && x <= this.x + p.getX() + HITPOINT_RADIUS
                    && y >= this.y + p.getY() - HITPOINT_RADIUS && y <= this.y + p.getY() + HITPOINT_RADIUS){
                p.minusHealth();
                hitPoints.set(i,p);

                if(p.getHealth() < 0){
                    hitPoints.remove(i);

                    if(hitPoints.size() <= 0 && live) {
                        stopCounter();
                        minusStamina(30000);
                        turnOnDeadEffectTImer(null);
                    }
                }
            }
        }
    }

    public boolean minusStamina(int damage){  // true: 승리
        health -= damage;

        if(zombiePower.getZombiePower() <= 0) {
            return true;
        }
        else if(health <= 0){
            if(koTimer.isTimerUsed()){
                zombiePower.minusZombiePower();
            }
            else {
                koTimer = new KnockOutTimer(this);
                koTimer.setLimitTime(10000);
                koTimer.start();
            }
        }

        return false;
    }

    public void revive(){
        health = 1000;
    }

    public void initializeStartedTime(){
        startedTime = System.currentTimeMillis();
    }

    public boolean HPTimerUsed(){
        return hpTimer.isTimerUsed();
    }
}