package com.example.l3uri.opencv_detection;

import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {
    private Socket socket = null;
    private BufferedReader readFromServer = null;
    private PrintWriter writeToServer = null;
    private Message message = null;

    public ClientThread(Socket socket, Message message) {
        try {
            this.message = message;
            this.socket = socket;
            /*readFromServer = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));*/
            writeToServer = new PrintWriter(
                    socket.getOutputStream(), true);
            new Thread(this).start();

        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String msgString;
                synchronized (message) {
                    this.message.wait();
                    msgString = message.getMsg();
                }
                writeToServer.println(msgString);
                writeToServer.flush();
                if (message.getMsg().equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


/*        try {
            while(true) {
                String msg = readFromServer.readLine();
                if(!msg.equalsIgnoreCase("exit")) {
                    System.out.println(msg);
                } else {
                    System.exit(0);
                }
            }
        } catch(Exception exp) {
            exp.printStackTrace();
        }*/
    }
}