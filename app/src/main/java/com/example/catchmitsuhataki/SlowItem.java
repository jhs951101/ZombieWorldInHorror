package com.example.catchmitsuhataki;

import android.graphics.BitmapFactory;

/**
 * Created by 심지훈 on 2017-02-06.
 */

public class SlowItem extends Item {

    public SlowItem(Kotori t, GameView v){
        super(t, v);
        item = BitmapFactory.decodeResource(view.getResources(), R.drawable.itemslow);
    }

    @Override
    public void use(){
        taki.setSpeedSlow();
    }
}
