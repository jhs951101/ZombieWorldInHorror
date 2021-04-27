package com.example.catchmitsuhataki;

import android.graphics.BitmapFactory;

/**
 * Created by 심지훈 on 2017-02-06.
 */

public class LongItem extends Item {

    public LongItem(Kotori t, GameView v){
        super(t, v);
        item = BitmapFactory.decodeResource(view.getResources(), R.drawable.itemlong);
    }

    @Override
    public void use(){
        view.setBarsLong();
    }
}
