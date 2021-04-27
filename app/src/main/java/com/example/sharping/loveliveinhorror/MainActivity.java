package com.example.sharping.loveliveinhorror;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private GameView view;

    @Override
    public void onBackPressed(){
        view.backPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GameView(this, getApplicationContext());
        setContentView(view);
    }
}
