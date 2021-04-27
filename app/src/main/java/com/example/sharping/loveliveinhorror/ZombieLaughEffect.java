package com.example.sharping.loveliveinhorror;

import android.content.Context;

/**
 * Created by SharPing on 2017-01-31.
 */

public class ZombieLaughEffect extends SoundEffect {

    public ZombieLaughEffect(Context c){
        soundId = soundPool.load(c, R.raw.zombielaugh, 1);
    }
}
