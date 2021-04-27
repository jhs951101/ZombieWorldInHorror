package com.example.catchmitsuhataki;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 심지훈 on 2017-02-04.
 */

public abstract class Item implements Runnable {

    protected GameView view;
    protected Kotori taki;

    protected Bitmap item;
    protected int itemX;
    protected int itemY;
    protected int Xspeed = 0;
    protected int Yspeed = 0;

    static boolean itemFlag = false;
    boolean setFlag = false;

    public Item(Kotori t, GameView v){

        taki = t;
        view = v;

        Xspeed = 0;
        Yspeed = 20;

        itemFlag = true;
    }

    public void setPosition(){
        itemX = taki.getTakiX() + (taki.getTakiWidth()/2);
        itemY = taki.getTakiY() + (taki.getTakiHeight()/2);

        setFlag = true;
    }

    @Override
    public void run(){
        try{
            while(view.gameFlag && itemFlag){
                if(setFlag) {
                    move();
                    collision();

                    if(view.gameFlag && itemFlag)
                        Thread.sleep(50);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void move(){
        itemX = itemX + Xspeed;
        itemY = itemY + Yspeed;
    }

    protected void collision(){
        if( (itemY + item.getHeight() > view.getRedY() && itemY < view.getRedY() + view.getRedHeight() && itemX < view.getRedWidth())
                || (itemX + item.getWidth() > view.getBlueX() && itemX < view.getBlueX() + view.getBlueWidth() && itemY < view.getBlueHeight())
                || (itemY + item.getHeight() > view.getYellowY() && itemY < view.getYellowY() + view.getYellowHeight() && itemX + item.getWidth() > GameView.WIDTH - view.getYellowWidth())
                || (itemX + item.getWidth() > view.getGreenX() && itemX < view.getGreenX() + view.getGreenWidth() && itemY + item.getHeight() > GameView.HEIGHT - view.getGreenHeight()) ) {  // 아이템이 막대바와 충돌했을 경우
            itemFlag = false;
            use();
        }
        else if( itemX <= 0 || itemX + item.getWidth() >= GameView.WIDTH || itemY <= 0 || itemY + item.getHeight() >= GameView.HEIGHT ) {
           itemFlag = false;
        }
    }

    public void draw(Canvas canvas){
        if(itemFlag && setFlag)
            canvas.drawBitmap(item, itemX, itemY, new Paint());  // !!
    }

    public abstract void use();
}
