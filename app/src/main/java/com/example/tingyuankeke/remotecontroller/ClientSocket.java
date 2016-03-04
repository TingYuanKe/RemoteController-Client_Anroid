//RepeatListener為繼承於OnClickListener的自刻類別
//用於實現點擊長按的連續點擊功能
package com.example.tingyuankeke.remotecontroller;
import java.net.*;
import java.io.*;

import android.os.AsyncTask;

/**
 * Created by TingYuanKeke on 2015/11/09.
 */
public class ClientSocket extends AsyncTask<String, Void, String> {
    private Socket socket = null;
    private DataOutputStream streamOut = null;
    private String serverName;
    private int serverPort;
    public boolean connected = false;

    public ClientSocket(String server, int port)
    {
        serverName = server;
        serverPort = port;
    }

    public void sendMessage(String msg) {

        if(connected) {
            try
            {
                //Convert the string into a byte array for C# to readaaa
                //將STRING轉換成byte讓C#讀取
                byte[] msgBytes = msg.getBytes();
                //將資料傳送
                streamOut.write(msgBytes);
                streamOut.flush();
            }
            catch(IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }
    private void start() throws IOException
    {
        streamOut = new DataOutputStream(socket.getOutputStream());

    }

    public void stop() {

        try {
            if (streamOut != null)  streamOut.close();
            if (socket    != null)  socket.close();
            connected= false;
        }
        catch(IOException ioe) {
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
            System.out.println("Connected: " + socket);
            connected = true;
            start();
        }
        catch(UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch(IOException ioe) {
            System.out.println("Unexpected IO exception: " + ioe.getMessage());
        }
        catch(Exception fe) {
            System.out.println("Unexpected fatal exception: " + fe);
        }


        // TODO Auto-generated method stub
        return null;
    }
    public boolean Connected(){
        if(connected = true){
            return true;
        }
        else{
            return false;
        }

    }
}
