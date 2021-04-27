package com.example.catchmitsuhataki;

import android.graphics.BitmapFactory;

/**
 * Created by 심지훈 on 2017-02-07.
 */

public class ReturnItem extends Item {

    public ReturnItem(Kotori t, GameView v){
        super(t, v);
        item = BitmapFactory.decodeResource(view.getResources(), R.drawable.itemreturn);
    }

    @Override
    public void use(){
        view.setBarsDefault();
        taki.setSpeedDefault();
    }
}
