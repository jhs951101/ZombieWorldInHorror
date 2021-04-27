package com.example.catchmitsuhataki;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 심지훈 on 2017-02-07.
 */

public class ConnectActivity extends Activity {

    private EditText ipNumberEdit;
    private EditText portNumberEdit;
    private EditText usernameEdit;

    private Button connectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        ipNumberEdit = (EditText) findViewById(R.id.ipNumber);
        ipNumberEdit.setText(getIpAddr());

        portNumberEdit = (EditText) findViewById(R.id.portNumber);
        portNumberEdit.setText("8889");

        usernameEdit = (EditText) findViewById(R.id.username);

        connectBtn = (Button) findViewById(R.id.connectBtn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipNumber = ipNumberEdit.getText().toString();
                String portNumber = portNumberEdit.getText().toString();
                String username = usernameEdit.getText().toString();

                if(ipNumber.equals("")) {
                    Toast.makeText(getApplication(), "IP주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(portNumber.equals("")) {
                    Toast.makeText(getApplication(), "포트번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(username.equals("")) {
                    Toast.makeText(getApplication(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(!checkIPNumber(ipNumber)) {
                    Toast.makeText(getApplication(), "IP주소는 숫자하고 점만 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(!checkPortNumber(portNumber)) {
                    Toast.makeText(getApplication(), "포트번호는 숫자만 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(!checkUsername(username)) {
                    Toast.makeText(getApplication(), "아이디는 영문과 숫자만 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else {  // 서버와 연결 시도
                    Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                    intent.putExtra("ipNumber", ipNumber);
                    intent.putExtra("portNumber", portNumber);
                    intent.putExtra("username", username);

                    startActivity(intent);  // 'start' 버튼을 클릭하면 게임 화면으로 넘어감
                }
            }
        });
    }

    private String getIpAddr() { // IP주소를 자동으로 계산해서 return하는 함수
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String ipString = String.format(
                "%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));
        return ipString;
    }

    private boolean checkIPNumber(String num){
        for(int i=0; i<num.length(); i++){
            if( !( (num.charAt(i) >= 48 && num.charAt(i) <= 57) || num.charAt(i) == '.' ) )
                return false;
        }

        return true;
    }

    private boolean checkPortNumber(String num){
        for(int i=0; i<num.length(); i++){
            if( !(num.charAt(i) >= 48 && num.charAt(i) <= 57) )
                return false;
        }

        return true;
    }

    private boolean checkUsername(String num){
        for(int i=0; i<num.length(); i++){
            if( !( (num.charAt(i) >= 48 && num.charAt(i) <= 57) || (num.charAt(i) >= 65 && num.charAt(i) <= 90) || (num.charAt(i) >= 97 && num.charAt(i) <= 122) ) )
                return false;
        }

        return true;
    }
}
