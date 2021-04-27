package com.example.catchmitsuhataki;

import android.graphics.BitmapFactory;

/**
 * Created by 심지훈 on 2017-02-06.
 */

public class ShortItem extends Item {

    public ShortItem(Kotori t, GameView v){
        super(t, v);
        item = BitmapFactory.decodeResource(view.getResources(), R.drawable.itemshort);
    }

    @Override
    public void use(){
        view.setBarsShort();
    }
}
