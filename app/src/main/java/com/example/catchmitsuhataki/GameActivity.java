package com.example.catchmitsuhataki;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends Activity {

    private GameView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GameView(this.getApplicationContext());
        setContentView(view);
    }

    @Override
    public void onBackPressed() {
        view.setVisibility(View.GONE);
        view.gameFlag = false;

        view.playsoundeffect.stopSound();
        view.countsoundeffect.stopSound();
        view.deadsoundeffect.stopSound();

        view.playsoundeffect = null;
        view.countsoundeffect = null;
        view.deadsoundeffect = null;

        Toast.makeText(getApplicationContext(), "게임에서 나가셨습니다.", Toast.LENGTH_SHORT).show();

        System.exit(0);
    }
}