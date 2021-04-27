package com.example.catchmitsuhataki;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by 심지훈 on 2017-02-22.
 */

public class RoomClientTerminator {

    private RoomClient client;

    public void setClient(RoomClient c){
        client = c;
    }

    public void terminate(){
        try {
            client.sender.out.writeUTF("quit " + client.username);
            client.receiver.in.close();
            client.sender.out.close();
            client.socket.close();

            client.sender.interrupt();
            client.receiver.interrupt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
