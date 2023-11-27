package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ServerThread extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

    public ServerThread(Socket socket) {
        try {
            clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // chiede al client il suo nome
            out.println("Enter your name:");
            clientName = in.readLine();
            out.println("Welcome, " + clientName + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
            //get
    public String getClientName() {
        return clientName;
    }
            //manda il messaggio prendendo la stringa message
    public void sendMessage(String message) {
        out.println(message);
    }
            
    @Override
            //controllo su message che non sia nullo 
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                Server.broadcastMessage(message, this);
                if (message.contains("@")) 
                {
                    

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
    
}