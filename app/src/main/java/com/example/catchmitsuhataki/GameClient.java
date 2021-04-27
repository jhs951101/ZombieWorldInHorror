package com.example.catchmitsuhataki;

import android.view.MotionEvent;
import android.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by 심지훈 on 2017-02-15.
 */

public class GameClient extends Thread {

    private DatagramSocket socket;

    private InetAddress serverIPAddress;
    private int serverPortNumber;

    private Sender sender;
    private Receiver receiver;

    public GameClient(String IP, int port){
        try {
            serverIPAddress = InetAddress.getByName(IP);
            serverPortNumber = port;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            socket = new DatagramSocket(serverPortNumber);

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

        @Override
        public void run(){

            DatagramPacket packet = new DatagramPacket(null, 0, serverIPAddress, serverPortNumber);

            try {
                while (true) {
                    //

                    socket.send(packet);
                }
            } catch (IOException e) {
                       e.printStackTrace();
            }
        }
    }

    class Receiver extends Thread {

        @Override
        public void run(){

            DatagramPacket packet = new DatagramPacket(null, 0, serverIPAddress, serverPortNumber);

            try {
                while (true) {
                    socket.receive(packet);

                    //
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
