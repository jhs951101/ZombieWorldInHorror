package com.example.sharping.loveliveinhorror;

import android.content.Context;

/**
 * Created by SharPing on 2017-01-31.
 */

public class ZombieDeadEffect extends SoundEffect {

    public ZombieDeadEffect(Context c){
        soundId = soundPool.load(c, R.raw.zombiedead, 1);
    }
}
