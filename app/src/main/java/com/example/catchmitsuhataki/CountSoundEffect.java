package com.example.catchmitsuhataki;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by 심지훈 on 2017-03-02.
 */

public class CountSoundEffect extends SoundEffect {

    public CountSoundEffect(Context c){
        context = c;
    }

    @Override
    public void playSound(){

        try {
            stopSound();

            player = MediaPlayer.create(context, R.raw.count);
            player.seekTo(0);
            player.start();

            player.setOnCompletionListener(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
