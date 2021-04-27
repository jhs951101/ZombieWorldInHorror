package com.example.catchmitsuhataki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 심지훈 on 2017-02-03.
 */

public class SelectActivity extends Activity {

    private Button singleBtn;
    private Button multiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        singleBtn = (Button) findViewById(R.id.singlebtn);
        singleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("mode", "single");

                startActivity(intent);  // 'start' 버튼을 클릭하면 게임 화면으로 넘어감
            }
        });

        multiBtn = (Button) findViewById(R.id.multibtn);
        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(), ConnectActivity.class);

                startActivity(intent);*/  // 'start' 버튼을 클릭하면 게임 화면으로 넘어감

                Toast.makeText(getApplicationContext(), "죄송합니다. 준비중입니다ㅠㅜ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}