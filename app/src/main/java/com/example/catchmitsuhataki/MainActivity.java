package com.example.catchmitsuhataki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextHandler texthandler;  // texthandler: 텍스트 파일 입출력을 담당하는 클래스 변수
    private Button startBtn;  // startBtn: 게임 시작 버튼
    private TextView maxRecordVIew;  // maxRecordVIew: 최고 기록을 나타내는 텍스트 라벨

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // xml파일과 java class를 서로 연결

        startBtn = (Button) findViewById(R.id.startbtn);  // 변수를 xml 레이아웃 내의 element와 서로 연결시킴
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);  // 'start' 버튼을 클릭하면 게임 화면으로 넘어감
            }
        });

        texthandler = new TextHandler();
        int maxRecord = texthandler.readMaxRecord();

        String text;  // text: 텍스트 라벨에 출력시킬 문구
        if(maxRecord == -1)  // maxRecord가 -1 이라는 것은 플레이 기록이 없다는 뜻
            text = "Max Record: None";
        else
            text = "Max Record: " + maxRecord;

        maxRecordVIew = (TextView) findViewById(R.id.maxRecord);
        maxRecordVIew.setText(text);
    }
}
