package com.example.catchmitsuhataki;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by SharPing on 2017-01-31.
 */

public abstract class SoundEffect implements MediaPlayer.OnCompletionListener {

    protected Context context;
    protected MediaPlayer player;  // player: 오디오 재생기

    public abstract void playSound();  // 오디오를 재생시키는 함수

    public void stopSound(){  // 오디오를 정지시키는 함수

        try {
            if (player == null)
                return;

            player.stop();
            player.setOnCompletionListener(null);
            player.release();
            player = null;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp){  // 오디오가 재생되다가 끝났을 경우 실행되는 함수
        stopSound();
    }
}
