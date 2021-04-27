package com.example.catchmitsuhataki;

/**
 * Created by 심지훈 on 2017-02-06.
 */

public class NoItem extends Item {

    public NoItem(Kotori t, GameView v){
        super(t, v);
        itemFlag = false;
    }

    @Override
    public void use(){}
}
