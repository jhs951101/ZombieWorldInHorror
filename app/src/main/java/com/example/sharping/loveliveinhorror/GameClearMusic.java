package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by SharPing on 2017-01-31.
 */

public class GameClearMusic extends BackgroundMusic {

    public GameClearMusic(Context c){
        context = c;
    }

    @Override
    public void playSound(){

        try {
            stopSound();

            player = MediaPlayer.create(context, R.raw.gameclear);
            player.seekTo(0);
            player.start();

            player.setOnCompletionListener(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
