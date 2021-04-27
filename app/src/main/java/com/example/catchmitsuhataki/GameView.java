package com.example.catchmitsuhataki;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 심지훈 on 2017-02-03.
 */

public class GameView extends View {

    public static int WIDTH;
    public static int HEIGHT;

    private int score;
    private int numOfLife;

    private int redX;
    private int blueY;
    private int yellowX;
    private int greenY;

    private Bitmap redBar;
    private int redY;

    private Bitmap blueBar;
    private int blueX;

    private Bitmap yellowBar;
    private int yellowY;

    private Bitmap greenBar;
    private int greenX;

    private int greenGap;
    private boolean gTouched = false;

    private Bitmap restartBtn;
    private int btnX;
    private int btnY;

    SoundEffect playsoundeffect;
    SoundEffect deadsoundeffect;
    SoundEffect countsoundeffect;

    private TextHandler texthandler;

    private Kotori kotori;

    Thread kotoriMover;

    Honoka honoka;
    ItemManager itemManager;

    Item item;

    static boolean gameFlag = true;
    boolean oneFlag1 = true;
    boolean oneFlag2 = true;

    public GameView(Context context) {
        super(context);
        gameStart();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameStart();
    }

    private void gameStart(){

        score = 0;
        numOfLife = 3;

        gameFlag = true;
        oneFlag1 = true;
        oneFlag2 = true;

        texthandler = new TextHandler();

        setBackgroundResource(R.drawable.gamebg);

        redBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbar);
        blueBar = BitmapFactory.decodeResource(getResources(), R.drawable.bluebar);
        yellowBar = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbar);
        greenBar = BitmapFactory.decodeResource(getResources(), R.drawable.greenbar);
        restartBtn = BitmapFactory.decodeResource(getResources(), R.drawable.restartbtn);

        playsoundeffect = new PlaySoundEffect(getContext());
        deadsoundeffect = new DeadSoundEffect(getContext());
        countsoundeffect = new CountSoundEffect(getContext());

        if(playsoundeffect instanceof SoundEffect)
            playsoundeffect.playSound();

        itemManager = new ItemManager(this);
        honoka = new Honoka(this);
        kotori = new Kotori(this, itemManager, honoka, countsoundeffect);

        kotoriMover = new Thread(kotori);

        item = new NoItem(kotori, this);

        itemManager.setTaki(kotori);

        kotoriMover.start();
        honoka.start();
        itemManager.start();
    }

    @Override
    public void onDraw(Canvas canvas){

        setMaxLength(getWidth(), getHeight());

        if(gameFlag) {
            honoka.draw(canvas);
            kotori.draw(canvas);

            if( !(item instanceof NoItem) )
                item.draw(canvas);

            drawBars(canvas);
        }
        else{
            setGameState();
            GameOver(canvas);
        }

        Paint textStyle = new Paint();
        textStyle.setColor(Color.YELLOW);
        textStyle.setTextSize(80);

        String text1 = " Score: " + score;
        canvas.drawText(text1, 0, 100, textStyle);

        String text2 = " Life: ";
        for(int i=1; i<=numOfLife; i++)
            text2 += "♥";

        canvas.drawText(text2, 0, HEIGHT-100, textStyle);

        invalidate();
    }

    private void drawBars(Canvas canvas){

        canvas.drawBitmap(greenBar, greenX, greenY, new Paint());
        canvas.drawBitmap(redBar, redX, redY, new Paint());
        canvas.drawBitmap(blueBar, blueX, blueY, new Paint());
        canvas.drawBitmap(yellowBar, yellowX, yellowY, new Paint());
    }

    private void setGameState(){  // 게임이 종료되었을 경우 실행되는 함수
        if(oneFlag2){
            Item.itemFlag = false;
            oneFlag2 = false;

            texthandler.saveMaxRecord(score);

            if(deadsoundeffect instanceof SoundEffect)
                deadsoundeffect.playSound();
        }
    }

    private void GameOver(Canvas canvas){

        Paint textStyle = new Paint();
        textStyle.setColor(Color.YELLOW);
        textStyle.setTextSize(100);

        String text1 = "  GAME OVER...";
        canvas.drawText(text1, 0, getHeight()/2 - 200, textStyle);  // 지정한 스타일을 적용해 텍스트를 그림

        String text2 = "  Score: " + score;
        canvas.drawText(text2, 0, getHeight()/2 - 100, textStyle);

        canvas.drawBitmap(restartBtn, btnX, btnY, new Paint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getRawX();
        int Y = (int) event.getRawY();

        if(gameFlag){
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    if ((X > greenX && X < greenX + greenBar.getWidth())
                            && Y > greenY) {
                        greenGap = X - greenX;
                        gTouched = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!(greenX >= 0)) {
                        greenX = 0;
                    } else if (!(greenX <= WIDTH - greenBar.getWidth())) {
                        greenX = WIDTH - greenBar.getWidth();
                    }
                    translateRectangles();
                    gTouched = false;
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                    if (!(greenX >= 0)) {
                        greenX = 0;
                    } else if (!(greenX <= WIDTH - greenBar.getWidth())) {
                        greenX = WIDTH - greenBar.getWidth();
                    }
                    translateRectangles();
                    gTouched = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (gTouched) {
                        greenX = X - greenGap;
                        translateRectangles();
                    }
                    break;
            }
        }
        else{
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    if ( (X > btnX && X < btnX + restartBtn.getWidth())
                            && (Y > btnY && Y < btnY + restartBtn.getHeight()) ) {
                        gameStart();
                    }
                    break;
            }
        }

        return true;
    }

    private void translateRectangles(){
        blueX = WIDTH - greenX - blueBar.getWidth();

        yellowY = PercantageToValue( ValueToPercantage(greenX) );

        redY = HEIGHT - yellowY - redBar.getHeight();
    }

    private int ValueToPercantage(int value){
        return value * 100 / (WIDTH - greenBar.getWidth());
    }

    private int PercantageToValue(int percentage){
        return percentage * (HEIGHT - redBar.getHeight()) / 100;
    }

    private void setMaxLength(int mw, int mh){  /* view 화면의 가로 길이 및 세로 길이를 지정함
                                                   (생성자에서 호출하면 getWidth(), getHeight()가 0을 리턴하더라구요ㅠㅜ) */
        if (oneFlag1){
            WIDTH = mw;
            HEIGHT = mh;

            kotori.setWidthHeight();
            honoka.setWidthHeight();

            greenX = WIDTH - greenBar.getWidth();
            greenY = HEIGHT - greenBar.getHeight();

            redX = 0;
            redY = 0;

            blueX = 0;
            blueY = 0;

            yellowX = WIDTH - yellowBar.getWidth();
            yellowY = HEIGHT - yellowBar.getHeight();

            btnX = 50;
            btnY = getHeight()/2;

            oneFlag1 = false;
        }
    }

    public void setBarsLong(){
        redBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbarlong);
        blueBar = BitmapFactory.decodeResource(getResources(), R.drawable.bluebarlong);
        yellowBar = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbarlong);
        greenBar = BitmapFactory.decodeResource(getResources(), R.drawable.greenbarlong);
    }

    public void setBarsShort(){
        redBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbarshort);
        blueBar = BitmapFactory.decodeResource(getResources(), R.drawable.bluebarshort);
        yellowBar = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbarshort);
        greenBar = BitmapFactory.decodeResource(getResources(), R.drawable.greenbarshort);
    }

    public void setBarsDefault(){
        redBar = BitmapFactory.decodeResource(getResources(), R.drawable.redbar);
        blueBar = BitmapFactory.decodeResource(getResources(), R.drawable.bluebar);
        yellowBar = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbar);
        greenBar = BitmapFactory.decodeResource(getResources(), R.drawable.greenbar);
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int s){
        this.score = s;
    }

    public int getLife(){
        return this.numOfLife;
    }

    public void setLife(int l){
        this.numOfLife = l;
    }

    public int getRedY(){
        return this.redY;
    }

    public int getRedWidth(){
        return this.redBar.getWidth();
    }

    public int getRedHeight(){
        return this.redBar.getHeight();
    }

    public int getBlueX(){
        return this.blueX;
    }

    public int getBlueWidth(){
        return this.blueBar.getWidth();
    }

    public int getBlueHeight(){
        return this.blueBar.getHeight();
    }

    public int getYellowY(){
        return this.yellowY;
    }

    public int getYellowWidth(){
        return this.yellowBar.getWidth();
    }

    public int getYellowHeight(){
        return this.yellowBar.getHeight();
    }

    public int getGreenX(){
        return this.greenX;
    }

    public int getGreenWidth(){
        return this.greenBar.getWidth();
    }

    public int getGreenHeight(){
        return this.greenBar.getHeight();
    }
}
