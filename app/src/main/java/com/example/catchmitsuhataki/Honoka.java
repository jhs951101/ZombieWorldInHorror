package com.example.catchmitsuhataki;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 심지훈 on 2017-02-05.
 */

public class Honoka extends Thread {

    private GameView view;

    private Bitmap honoka;

    private int honokaX;
    private int honokaY;

    boolean setFlag = false;

    public Honoka(GameView v){

        view = v;

        honoka = BitmapFactory.decodeResource(view.getResources(), R.drawable.honoka_brick);
    }

    @Override
    public void run(){
        try{
            while(GameView.gameFlag){
                if(!Kotori.countFlag){
                    int n = (int)(Math.random() * 3) + 1;
                    setPosition(n);
                    Thread.sleep(2000);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Canvas canvas){
        if(!Kotori.countFlag && setFlag)
            canvas.drawBitmap(honoka, honokaX, honokaY, new Paint());
    }

    public void setWidthHeight(){

        honokaX = GameView.WIDTH/2 - (honoka.getWidth()/2);

        int n = (int)(Math.random() * 2) + 1;
        setPosition(n);

        setFlag = true;
    }

    public void setPosition(int n){
        if(n == 1)
            honokaY = GameView.HEIGHT/4 - (honoka.getHeight()/2);
        else if(n == 2)
            honokaY = GameView.HEIGHT * 3/4 - (honoka.getHeight()/2);
        else if(n == 3)
            honokaY = GameView.HEIGHT/2 - (honoka.getHeight()/2);
    }

    public int getMitsuhaX(){
        return this.honokaX;
    }

    public int getMitsuhaY(){
        return this.honokaY;
    }

    public int getMitsuhaWidth(){
        return honoka.getWidth();
    }

    public int getMitsuhaHeight(){
        return honoka.getHeight();
    }
}
