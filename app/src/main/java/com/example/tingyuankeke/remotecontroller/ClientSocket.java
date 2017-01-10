//RepeatListener為繼承於OnClickListener的自刻類別
//用於實現點擊長按的連續點擊功能
package com.example.tingyuankeke.remotecontroller;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.SocketHandler;

/**
 * Created by TingYuanKeklle on 2015/11/09.
 */
public class ClientSocket extends AsyncTask<String, Void, String> {
    public String serverName;
    public static boolean connected = false;
    private Socket socket = null;
    private DataOutputStream streamOut = null;

    private PrintWriter out ;
    private SocketHandler SocketHandler;
    private int serverPort;


    public ClientSocket(String server, int port) {
        serverName = server;
        serverPort = port;
    }

    public boolean Heartbeat(){


        try{
            socket.sendUrgentData(0xFF);
            System.out.println("keep running~~~");
            return true;
        }catch(Exception ex){
            System.out.println("Disconnected!!!");
            stop();
            return false;

        }
    }
    public void sendMessage(String msg) {

        if (connected) {
            try {
                //Convert the string into a byte array for C# to read
                //將STRING轉換成byte讓C#讀取
                byte[] msgBytes = msg.getBytes();
                //將資料傳送
                streamOut.write(msgBytes);

                streamOut.flush();
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }

    private void start() throws IOException {
        streamOut = new DataOutputStream(socket.getOutputStream());
         out = new PrintWriter(socket.getOutputStream(), true);

    }

    public void stop() {
        try {
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
            connected = false;
            if(out != null) out.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }

    @Override
    protected String doInBackground(String... params) {
        System.out.println("Establishing connection to server. Please wait ...");

        try {

            System.out.println("Attempting to connect to " + serverName + ":" + serverPort);
            //連接到C# Server端
            socket = new Socket(serverName, serverPort);
            connected=true;
            System.out.println("Connected~: " + socket);

            start();

        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected IO exception: " + ioe.getMessage());
        } catch (Exception fe) {
            System.out.println("Unexpected fatal exception: " + fe);
        }


        // TODO Auto-generated method stub
        return null;
    }

    public boolean Connected() {
        return connected;
    }




}
