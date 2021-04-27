package com.example.catchmitsuhataki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 심지훈 on 2017-02-07.
 */

public class RoomActivity extends Activity {

    private RoomView view;
    private RoomClientTerminator terminator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String args[] = new String[3];
        Intent intent = getIntent();

        args[0] = intent.getStringExtra("ipNumber");
        args[1] = intent.getStringExtra("portNumber");
        args[2] = intent.getStringExtra("username");

        terminator = new RoomClientTerminator();
        view = new RoomView(this.getApplicationContext(), this, terminator, args);
        setContentView(view);
    }

    @Override
    public void onBackPressed() {
        view.setVisibility(View.GONE);
        terminator.terminate();
        Toast.makeText(getApplicationContext(), "방에서 나가셨습니다.", Toast.LENGTH_SHORT).show();

        super.onBackPressed();
    }
}
