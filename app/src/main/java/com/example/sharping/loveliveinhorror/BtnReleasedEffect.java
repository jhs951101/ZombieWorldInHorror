package com.example.sharping.loveliveinhorror;

import android.content.Context;

/**
 * Created by SharPing on 2017-01-31.
 */

public class BtnReleasedEffect extends SoundEffect {

    public BtnReleasedEffect(GameView gv, Context c){
        gameView = gv;
        soundId = soundPool.load(c, R.raw.btnreleased, 1);
    }
}
