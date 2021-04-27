package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by SharPing on 2017-06-28.
 */

public class GameMusic extends BackgroundMusic {

    public GameMusic(GameView gv, Context c){
        gameView = gv;
        context = c;
    }

    @Override
    public void playSound(){

        try {
            stopSound();

            player = MediaPlayer.create(context, R.raw.gamebg);
            player.seekTo(0);
            player.start();

            player.setOnCompletionListener(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}