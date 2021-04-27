package com.example.catchmitsuhataki;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by 심지훈 on 2017-02-04.
 */

public class Kotori implements Runnable {

    private GameView view;

    private ItemManager itemManager;
    private Honoka honoka;

    private SoundEffect countsoundeffect;

    private String msg;

    private Bitmap kotori;
    private int kotoriX;
    private int kotoriY;

    private int OriginalXSpeed = 40;
    private int OriginalYSpeed = 40;

    private int Xspeed = OriginalXSpeed;
    private int Yspeed = OriginalYSpeed;

    private boolean collisionInRed = false;
    private boolean collisionInBlue = false;
    private boolean collisionInYellow = false;
    private boolean collisionInGreen = false;

    private int cnt;

    static boolean countFlag = true;
    boolean setFlag = false;

    public Kotori(GameView v, ItemManager im, Honoka m, SoundEffect se){
        view = v;
        itemManager = im;
        honoka = m;
        countsoundeffect = se;

        kotori = BitmapFactory.decodeResource(view.getResources(), R.drawable.kotori_ball);
    }

    public void setWidthHeight(){

        kotoriX = GameView.WIDTH/2;
        kotoriY = GameView.HEIGHT/2;

        setFlag = true;
    }

    @Override
    public void run(){
        try{
            while(GameView.gameFlag){
                if(countFlag) {
                    cnt = 3;
                    while (cnt >= 1 && GameView.gameFlag) {
                        msg = String.valueOf(cnt);

                        if(countsoundeffect instanceof SoundEffect)
                            countsoundeffect.playSound();

                        Thread.sleep(1000);
                        cnt--;
                    }

                    msg = "START!";

                    if(GameView.gameFlag) {
                        if(countsoundeffect instanceof SoundEffect)
                            countsoundeffect.playSound();

                        Thread.sleep(1000);
                    }

                    msg = "";
                    countFlag = false;
                }
                else if(setFlag) {
                    move();
                    collision();
                    Thread.sleep(50);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void move(){
        if(setFlag && !countFlag) {
            kotoriX = kotoriX + Xspeed;
            kotoriY = kotoriY + Yspeed;
        }
    }

    private void collision(){

        if(setFlag && !countFlag){

            if( (kotoriY + kotori.getHeight() > view.getRedY() && kotoriY < view.getRedY() + view.getRedHeight())
                    && kotoriX < view.getRedWidth() ) {  // 공이 빨강색 막대바와 충돌했을 경우
                collisionInRed = true;
            }
            if( (kotoriX + kotori.getWidth() > view.getBlueX() && kotoriX < view.getBlueX() + view.getBlueWidth())
                    && kotoriY < view.getBlueHeight() ) {  // 공이 파랑색 막대바와 충돌했을 경우
                collisionInBlue = true;
            }
            if( (kotoriY + kotori.getHeight() > view.getYellowY() && kotoriY < view.getYellowY() + view.getYellowHeight())
                    && kotoriX + kotori.getWidth() > GameView.WIDTH - view.getYellowWidth() ) {  // 공이 노랑색 막대바와 충돌했을 경우
                collisionInYellow = true;
            }
            if( (kotoriX + kotori.getWidth() > view.getGreenX() && kotoriX < view.getGreenX() + view.getGreenWidth())
                    && kotoriY + kotori.getHeight() > GameView.HEIGHT - view.getGreenHeight() ) {  // 공이 초록색 막대바와 충돌했을 경우
                collisionInGreen = true;
            }

            if( collisionInRed || collisionInBlue || collisionInYellow || collisionInGreen ) {

                boolean check = false;

                if (collisionInRed && collisionInBlue) {
                    if(Xspeed < 0) {
                        Xspeed = Xspeed * -1;
                        check = true;
                    }
                    if(Yspeed < 0) {
                        Yspeed = Yspeed * -1;
                        check = true;
                    }

                    if(check)
                        view.setScore( view.getScore() + 50 );

                    view.setScore( view.getScore() + 100 );
                }
                else if (collisionInBlue && collisionInYellow) {
                    if(Xspeed > 0) {
                        Xspeed = Xspeed * -1;
                        check = true;
                    }
                    if(Yspeed < 0) {
                        Yspeed = Yspeed * -1;
                        check = true;
                    }

                    if(check)
                        view.setScore( view.getScore() + 50 );

                    view.setScore( view.getScore() + 100 );
                }
                else if (collisionInYellow && collisionInGreen) {
                    if(Xspeed > 0) {
                        Xspeed = Xspeed * -1;
                        check = true;
                    }
                    if(Yspeed > 0) {
                        Yspeed = Yspeed * -1;
                        check = true;
                    }

                    if(check)
                        view.setScore( view.getScore() + 50 );

                    view.setScore( view.getScore() + 100 );
                }
                else if (collisionInGreen && collisionInRed) {
                    if(Xspeed < 0) {
                        Xspeed = Xspeed * -1;
                        check = true;
                    }
                    if(Yspeed > 0) {
                        Yspeed = Yspeed * -1;
                        check = true;
                    }

                    if(check)
                        view.setScore( view.getScore() + 50 );

                    view.setScore( view.getScore() + 100 );
                }
                else if (collisionInRed) {
                    if(Xspeed < 0) {
                        Xspeed = Xspeed * -1;
                        view.setScore( view.getScore() + 50 );
                    }
                }
                else if (collisionInBlue) {
                    if(Yspeed < 0) {
                        Yspeed = Yspeed * -1;
                        view.setScore( view.getScore() + 50 );
                    }
                }
                else if (collisionInYellow) {
                    if(Xspeed > 0) {
                        Xspeed = Xspeed * -1;
                        view.setScore( view.getScore() + 50 );
                    }
                }
                else if (collisionInGreen) {
                    if(Yspeed > 0) {
                        Yspeed = Yspeed * -1;
                        view.setScore( view.getScore() + 50 );
                    }
                }

                setAngleRandom();

                collisionInRed = false;
                collisionInBlue = false;
                collisionInYellow = false;
                collisionInGreen = false;

                itemManager.response = false;
            }
            else if( !itemManager.response &&
                    ( (kotoriY + kotori.getHeight() >= honoka.getMitsuhaY() && kotoriY <= honoka.getMitsuhaY() + honoka.getMitsuhaHeight()
                            && kotoriX + kotori.getWidth() >= honoka.getMitsuhaX() && kotoriX <= honoka.getMitsuhaX() + honoka.getMitsuhaWidth())
                            || (kotoriX + kotori.getWidth() >= honoka.getMitsuhaX() && kotoriX <= honoka.getMitsuhaX() + honoka.getMitsuhaWidth()
                            && kotoriY + kotori.getHeight() >= honoka.getMitsuhaY() && kotoriY <= honoka.getMitsuhaY() + honoka.getMitsuhaHeight())
                            || (kotoriY + kotori.getHeight() >= honoka.getMitsuhaY() && kotoriY <= honoka.getMitsuhaY() + honoka.getMitsuhaHeight()
                            && kotoriX <= honoka.getMitsuhaX() + honoka.getMitsuhaWidth() && kotoriX + kotori.getWidth() >= honoka.getMitsuhaX())
                            || (kotoriX + kotori.getWidth() >= honoka.getMitsuhaX() && kotoriX <= honoka.getMitsuhaX() + honoka.getMitsuhaWidth()
                            && kotoriY <= honoka.getMitsuhaY() + honoka.getMitsuhaHeight() && kotoriY + kotori.getHeight() >= honoka.getMitsuhaY()) ) ){  // 공이 미츠하하고 충돌했을 경우
                itemManager.signal = true;
                view.setScore( view.getScore() + 100 );
            }
            else if( kotoriX <= 0 || kotoriX + kotori.getWidth() >= GameView.WIDTH || kotoriY <= 0 || kotoriY + kotori.getHeight() >= GameView.HEIGHT ){  // 공이 벽과 충돌했을 경우
                Item.itemFlag = false;
                countFlag = true;
                itemManager.response = false;

                view.item = new NoItem(this, view);

                view.setLife( view.getLife() - 1 );

                if(view.getLife() <= 0)
                    GameView.gameFlag = false;

                kotoriX = GameView.WIDTH/2;
                kotoriY = GameView.HEIGHT/2;

                setSpeedDefault();
                view.setBarsDefault();
            }
        }
    }

    public void draw(Canvas canvas){
        if(countFlag) {
            Paint textStyle = new Paint();
            textStyle.setTextSize(150f);
            textStyle.setColor(Color.YELLOW);
            canvas.drawText(msg, GameView.WIDTH/2-150, GameView.HEIGHT/2, textStyle);
        }
        else if(setFlag) {
            canvas.drawBitmap(kotori, kotoriX, kotoriY, new Paint());
        }
    }

    private void setAngleRandom(){

        int n = (int)(Math.random() * 2) + 1;

        boolean Xnegative = false;
        boolean Ynegative = false;

        if(Xspeed < 0) {
            Xnegative = true;
            Xspeed = Xspeed * -1;
        }
        if(Yspeed < 0) {
            Ynegative = true;
            Yspeed = Yspeed * -1;
        }

        if(n == 1){
            if(Xspeed < OriginalXSpeed + 10 && Yspeed > OriginalYSpeed - 10) {
                Xspeed = OriginalXSpeed + 10;
                Yspeed = OriginalYSpeed - 10;
            }
        }
        else if(n == 2){
            if(Xspeed > OriginalXSpeed - 10 && Yspeed < OriginalYSpeed + 10) {
                Xspeed = OriginalXSpeed - 10;
                Yspeed = OriginalYSpeed + 10;
            }
        }

        if(Xnegative)
            Xspeed = Xspeed * -1;
        if(Ynegative)
            Yspeed = Yspeed * -1;

    }

    public void setSpeedSlow(){

        if(OriginalXSpeed > 30 && OriginalYSpeed > 30) {

            boolean Xnegative = false;
            boolean Ynegative = false;

            if (Xspeed < 0) {
                Xnegative = true;
                Xspeed = Xspeed * -1;
            }
            if (Yspeed < 0) {
                Ynegative = true;
                Yspeed = Yspeed * -1;
            }

            Xspeed = Xspeed - 20;
            Yspeed = Yspeed - 20;

            if (Xnegative)
                Xspeed = Xspeed * -1;
            if (Ynegative)
                Yspeed = Yspeed * -1;

            OriginalXSpeed = 30;
            OriginalYSpeed = 30;
        }
    }

    public void setSpeedDefault(){

        if(OriginalXSpeed < 40 && OriginalYSpeed < 40) {

            boolean Xnegative = false;
            boolean Ynegative = false;

            if (Xspeed < 0) {
                Xnegative = true;
                Xspeed = Xspeed * -1;
            }
            if (Yspeed < 0) {
                Ynegative = true;
                Yspeed = Yspeed * -1;
            }

            Xspeed = Xspeed + 20;
            Yspeed = Yspeed + 20;

            if (Xnegative)
                Xspeed = Xspeed * -1;
            if (Ynegative)
                Yspeed = Yspeed * -1;

            OriginalXSpeed = 40;
            OriginalYSpeed = 40;
        }
    }

    public int getTakiX(){
        return this.kotoriX;
    }

    public int getTakiY(){
        return this.kotoriY;
    }

    public int getTakiWidth(){
        return this.kotori.getWidth();
    }

    public int getTakiHeight(){
        return this.kotori.getHeight();
    }
}
