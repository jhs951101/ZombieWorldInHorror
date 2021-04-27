package com.example.catchmitsuhataki;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by 심지훈 on 2017-02-07.
 */

public class RoomClient extends Thread {

    private RoomActivity activity;
    private RoomView view;

    private Handler handler;

    private String ipNumber;
    private int portNumber;

    private String notice;

    Socket socket;
    Sender sender;
    Receiver receiver;

    String username;

    public RoomClient(RoomView v, RoomActivity a, Handler h, String ip, int port, String un){
        view = v;
        activity = a;
        handler = h;
        ipNumber = ip;
        portNumber = port;
        username = un;
    }

    @Override
    public void run(){
        try {
            socket = new Socket(ipNumber, portNumber);

            sender = new Sender();
            receiver = new Receiver();

            sender.start();
            receiver.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //

    class Sender extends Thread {

        DataOutputStream out;
        private boolean streamCreated = false;

        public Sender(){
            try {
                out = new DataOutputStream(socket.getOutputStream());
                streamCreated = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            if(streamCreated) {
                try {
                    out.writeUTF("join " + username);

                    view.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {

                            boolean success = false;

                            try {
                                int X = (int) event.getRawX();
                                int Y = (int) event.getRawY();

                                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                    case MotionEvent.ACTION_UP:
                                        if ((X > view.getStartBtnX() && X < view.getStartBtnX() + view.startBtn.getWidth())
                                                && Y > view.getStartBtnY()) {

                                            UserInfo uinfo = view.userinfos.get(username);

                                            if (uinfo.isManager) {
                                                out.writeUTF("startGame");
                                            }
                                            else if (!uinfo.beReady) {
                                                uinfo.beReady = true;
                                                view.userinfos.put(username, uinfo);
                                                view.startBtn = BitmapFactory.decodeResource(view.getResources(), R.drawable.cancelbtn);
                                                out.writeUTF("setReady " + username + " true");
                                            }
                                            else {
                                                uinfo.beReady = false;
                                                view.userinfos.put(username, uinfo);
                                                view.startBtn = BitmapFactory.decodeResource(view.getResources(), R.drawable.readybtn);
                                                out.writeUTF("setReady " + username + " false");
                                            }
                                        }

                                        break;
                                }

                                success = true;

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            return success;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();

                    try {
                        socket.close();
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    class Receiver extends Thread {

        DataInputStream in;
        private boolean streamCreated = false;

        public Receiver() {
            try {
                in = new DataInputStream(socket.getInputStream());
                streamCreated = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            if(streamCreated) {
                try {
                    String msg;
                    String[] orderInfo;

                    while (true) {
                        msg = in.readUTF();
                        orderInfo = msg.split(" ");

                        if(orderInfo[0].equals("setUnable")) {
                            if(orderInfo[1].equals("full"))
                                notice = "ERROR: 해당 서버에 인구수가 꽉 찼습니다.";
                            else if(orderInfo[1].equals("duplicateID"))
                                notice = "ERROR: 중복되는 아이디입니다.";

                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(view.getContext(), notice, Toast.LENGTH_SHORT).show();
                                }
                            });

                            handler.sendEmptyMessage(1);
                            break;
                        }
                        else if(orderInfo[0].equals("errorMessage")){
                            if(orderInfo[1].equals("lackUser"))
                                notice = "ERROR: 유저의 수가 2명 또는 4명이 아닙니다.";
                            else if(orderInfo[1].equals("notReady"))
                                notice = "ERROR: 아직 준비가 안된 유저가 있습니다.";

                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(view.getContext(), notice, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else if(orderInfo[0].equals("load")){
                            if(orderInfo[1].equals(username))
                                view.startBtn = BitmapFactory.decodeResource(view.getResources(), R.drawable.gamestartbtn);
                            else
                                view.startBtn = BitmapFactory.decodeResource(view.getResources(), R.drawable.readybtn);

                            int numOfUsers = Integer.parseInt(orderInfo[2]);
                            int a = 2;

                            for (int i = 1; i <= numOfUsers; i++) {
                                int b = i + a;

                                UserInfo uinfo = new UserInfo();

                                if (orderInfo[b].equals(orderInfo[1])) {
                                    uinfo.isManager = true;
                                    uinfo.beReady = false;
                                }
                                else if (orderInfo[b+1].equals("true")) {
                                    uinfo.beReady = true;
                                }

                                view.userinfos.put(orderInfo[b], uinfo);

                                a++;
                            }

                            view.setFlag = true;
                        }
                        else if(orderInfo[0].equals("join")){
                                view.userinfos.put(orderInfo[1], new UserInfo());
                        }
                        else if(orderInfo[0].equals("setReady")){
                            UserInfo userinfo = view.userinfos.get(orderInfo[1]);
                            userinfo.beReady = Boolean.valueOf(orderInfo[2]);

                            view.userinfos.put(orderInfo[1], userinfo);
                        }
                        else if(orderInfo[0].equals("giveAutority")){
                            UserInfo uinfo = view.userinfos.get(orderInfo[1]);
                            uinfo.isManager = true;
                            uinfo.beReady = false;
                            view.userinfos.put(orderInfo[1], uinfo);

                            String notice = "";

                            if(orderInfo[1].equals(username))
                                notice = "당신은 방장이 되었습니다.";
                            else
                                notice = orderInfo[1] + " 님이 방장이 되었습니다.";

                            Toast.makeText(view.getContext(), notice, Toast.LENGTH_SHORT).show();
                        }
                        else if(orderInfo[0].equals("quit")){
                            view.userinfos.remove(orderInfo[1]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}