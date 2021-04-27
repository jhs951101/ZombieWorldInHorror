package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-07-27.
 */

public abstract class Zombie extends Thread {
    
    protected double MAX_HEALTH;

    protected int x;
    protected int y;

    protected int xSpeed;
    protected int ySpeed;

    protected int health;
    protected int level;

    protected Bitmap zImageLeft1;
    protected Bitmap zImageLeft2;
    protected Bitmap zImageRight1;
    protected Bitmap zImageRight2;
    protected Bitmap zImageLeftDead1;
    protected Bitmap zImageLeftDead2;
    protected Bitmap zImageRightDead1;
    protected Bitmap zImageRightDead2;
    protected Length zImageLength;

    protected int zImageChange;
    // 20이 되면 이미지가 바뀜

    protected ZombiePower zombiePower;
    protected WeaponInfo playerWeapon;
    protected SoundEffect zombieLaughEffect;
    protected SoundEffect zombieDeadEffect;
    protected DeadEffectTimer deadEffectTimer;
    protected ZombieDeadEffectShow zombieDeadEffectShow;

    protected boolean live = true;
    public boolean flag = true;

    short zImageDirection;
    /*
    0: left
    1: right
    */

    short zImageState;
    /*
    0: zombie1
    1: zombie2
    */

    public Zombie(Context c, WeaponInfo pw, int x, int l){
        this.playerWeapon = pw;
        this.x = x;
        this.level = l;
        this.xSpeed = 0;

        setCharacter1(c);
    }

    public Zombie(Context c, WeaponInfo pw, int x, int xs, int l){
        this.playerWeapon = pw;
        this.x = x;
        this.level = l;
        this.xSpeed = xs;

        setCharacter1(c);
    }

    public void setCharacter1(Context c){
        zImageChange = 0;
        zImageState = 0;

        if(xSpeed > 0)
            zImageDirection = 1;
        else
            zImageDirection = 0;

        zombieLaughEffect = new ZombieLaughEffect(c);
        zombieDeadEffect = new ZombieDeadEffect(c);
        zombieDeadEffectShow = new ZombieDeadEffectShow();
    }

    public void move(){
        x += xSpeed;
        y += ySpeed;
    }

    public void collision(){
        if(x <= 0 || x + zImageLength.getWidth() >= GameView.WIDTH) {
            xSpeed *= -1;
            zImageDirection = (short)(1 - zImageDirection);
        }
    }

    @Override
    public void run(){
        try {
            while(GameView.gameFlag == 1 && flag) {
                collision();
                move();

                zImageChange++;

                if(zImageChange >= 20) {
                    zImageState = (short)(1 - zImageState);
                    zImageChange = 0;
                }

                if(GameView.gameFlag == 1 && flag)
                    Thread.sleep(50);
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

    public boolean minusStamina(int damage){  // true: 승리
        health -= damage;

        if(health <= 0) {
            return true;
        }

        return false;
    }

    public void draw(Canvas canvas){
        if(GameView.gameFlag == 1 && flag) {
            if(zImageState == 0){
                if(zImageDirection == 0){
                    if(GameView.gameFlag == 1 && flag && zombieDeadEffectShow.show)
                        canvas.drawBitmap(zImageLeftDead1, x, y, null);
                    else if(GameView.gameFlag == 1 && flag)
                        canvas.drawBitmap(zImageLeft1, x, y, null);
                }

                else if(zImageDirection == 1){
                    if(GameView.gameFlag == 1 && flag && zombieDeadEffectShow.show)
                        canvas.drawBitmap(zImageRightDead1, x, y, null);
                    else if(GameView.gameFlag == 1 && flag)
                        canvas.drawBitmap(zImageRight1, x, y, null);
                }
            }
            else if(zImageState == 1) {
                if(zImageDirection == 0){
                    if(GameView.gameFlag == 1 && flag && zombieDeadEffectShow.show)
                        canvas.drawBitmap(zImageLeftDead2, x, y, null);
                    else if(GameView.gameFlag == 1 && flag)
                        canvas.drawBitmap(zImageLeft2, x, y, null);
                }
                else if(zImageDirection == 1){
                    if(GameView.gameFlag == 1 && flag && zombieDeadEffectShow.show)
                        canvas.drawBitmap(zImageRightDead2, x, y, null);
                    else if(GameView.gameFlag == 1 && flag)
                        canvas.drawBitmap(zImageRight2, x, y, null);
                }
            }
        }
    }

    public void turnOnDeadEffectTImer(ArrayList<Zombie> zs){
        live = false;
        deadEffectTimer = new DeadEffectTimer(zs, this, zombieDeadEffectShow);
        deadEffectTimer.setLimitTime(100);
        deadEffectTimer.start();
        zombieDeadEffect.playSound();
    }

    public void createCircles(){}

    public boolean isFailure(){
        return false;
    }

    public void circleTouch(int x, int y){}

    public boolean HPTimerUsed(){
        return false;
    }

    public double convertToEnemyHealth(int n){
        return n / MAX_HEALTH * zImageLength.getWidth();
    }

    public double convertToEnemyZombiePower(int n){
        return zombiePower.convertToEnemyZombiePower(n);
    }

    public void playLaughEffect(){
        zombieLaughEffect.playSound();
    }

    public int getHealth(){
        return this.health;
    }

    public int getZombiePower(){
        return (int)(zombiePower.getZombiePower());
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return zImageLength.getWidth();
    }

    public int getHeight(){
        return zImageLength.getHeight();
    }

    public boolean isLive(){
        return this.live;
    }

    public void setLive(boolean l){
        this.live = l;
    }
}
