package com.example.catchmitsuhataki;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by SharPing on 2017-01-31.
 */

public class TextHandler {

    private String PATH = Environment.getExternalStorageDirectory().toString();  // PATH: 텍스트가 저장되는 외부 경로
    private String fileName = "maxRecordKotori.txt";

    public void saveMaxRecord(int score){  // 최고 기록을 텍스트에 저장함

        int maxRecord = readMaxRecord();

        if(maxRecord == -1 || score > maxRecord) {  // 최고 기록이 없거나 현재 최고 기록보다 더 좋은 기록이어야 저장됨
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(PATH + "/" + fileName));

                out.write( String.valueOf(score) );
                out.newLine();

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int readMaxRecord(){  // 최고 기록을 텍스트로부터 읽어옴

        String record = "";

        File txtFile = new File(PATH, fileName);

        if(!txtFile.exists()) {  // 텍스트 파일이 존재하지 않으면 텍스트 파일을 새로 만듬
            try {
                FileOutputStream fos = new FileOutputStream(PATH + "/" + fileName);

                fos.write("-1".getBytes());

                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(PATH + "/" + fileName));

            record = in.readLine();

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result;

        if(record == null || record == "")
            result = -1;
        else
            result = Integer.parseInt(record);

        return result;
    }
}
