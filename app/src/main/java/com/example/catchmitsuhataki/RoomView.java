package com.example.catchmitsuhataki;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 심지훈 on 2017-02-07.
 */

public class RoomView extends View {

    private int WIDTH;
    private int HEIGHT;

    private String username;

    private RoomClient client;
    RoomClientTerminator terminator;

    Bitmap startBtn;
    private int startBtnX;
    private int startBtnY;

    RoomActivity activity;

    HashMap<String, UserInfo> userinfos;

    static boolean setFlag = false;
    boolean oneFlag = true;

    public RoomView(Context context, RoomActivity a, RoomClientTerminator t, String[] args) {
        super(context);
        terminator = t;
        setAndConnect(a, args[0], args[1], args[2]);
    }

    public RoomView(Context context, AttributeSet attrs, RoomActivity a, RoomClientTerminator t, String[] args) {
        super(context, attrs);
        terminator = t;
        setAndConnect(a, args[0], args[1], args[2]);
    }

    private void setAndConnect(RoomActivity a, String ip, String port, String un){
        setBackgroundResource(R.drawable.roombg);
        startBtn = BitmapFactory.decodeResource(getResources(), R.drawable.readybtn);

        activity = a;

        username = un;
        userinfos = new HashMap<String, UserInfo>();

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                activity.onBackPressed();
            }
        };

        client = new RoomClient(this, activity, handler, ip, Integer.parseInt(port), un);
        terminator.setClient(client);

        client.start();
    }

    @Override
    public void onDraw(Canvas canvas){

        setMaxLength(getWidth(), getHeight());

        if(setFlag) {
            Paint titleStyle = new Paint();
            titleStyle.setColor(Color.YELLOW);
            titleStyle.setTextSize(100);

            canvas.drawText(" Room", 0, 120, titleStyle);

            setUserList(canvas);

            canvas.drawBitmap(startBtn, 20, HEIGHT-startBtn.getHeight(), new Paint());
        }

        invalidate();
    }

    private void setMaxLength(int mw, int mh){  /* view 화면의 가로 길이 및 세로 길이를 지정함
                                                   (생성자에서 호출하면 getWidth(), getHeight()가 0을 리턴하더라구요ㅠㅜ) */
        if (oneFlag){
            WIDTH = mw;
            HEIGHT = mh;

            oneFlag = false;
        }
    }

    private void setUserList(Canvas canvas){

        Iterator<String> iter = userinfos.keySet().iterator();

        Paint listStyle = new Paint();
        listStyle.setColor(Color.WHITE);
        listStyle.setTextSize(80);

        int a = -200;
        while(iter.hasNext()){
            String username = iter.next();
            UserInfo info = userinfos.get(username);

            String text = username;

            if(info.isManager)
                text += " Manager";
            else if(info.beReady)
                text += " Ready";

            canvas.drawText(text, 0, getHeight() / 2 + a, listStyle);
            a = a + 100;
        }
    }

    public int getStartBtnX(){
        return this.startBtnX;
    }

    public int getStartBtnY(){
        return this.startBtnY;
    }
}
