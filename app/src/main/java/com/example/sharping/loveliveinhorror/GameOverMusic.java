package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by SharPing on 2017-01-31.
 */

public class GameOverMusic extends BackgroundMusic {

    public GameOverMusic(Context c){
        context = c;
    }

    @Override
    public void playSound(){

        try {
            stopSound();

            player = MediaPlayer.create(context, R.raw.gameover);
            player.seekTo(0);
            player.start();

            player.setOnCompletionListener(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
