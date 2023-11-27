package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
        private static List<ServerThread> clients = new ArrayList<>();

        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Server sta aspettando un client...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("client connesso.");

                    ServerThread s = new ServerThread(clientSocket);
                    clients.add(s);
                    new Thread(s).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            // s=sender quello che manda, per ogni client che riceve viene visualizzato il nome del mittente + il messaggio inviato
        public static void broadcastMessage(String message, ServerThread s) {
        {
            for (ServerThread client : clients)
                if (message.contains("@"+client)) 
                {
                    client.sendMessage(s.getClientName() + ": " + message);

                }
            for (ServerThread client : clients) {
                if (message.contains("@"+client)) 
                {
                    client.sendMessage(s.getClientName() + ": " + message);

                }
                System.out.println("SONO NEL BROADCAST MESSAGE");
                if (client != s) {
                    client.sendMessage(s.getClientName() + ": " + message);
                }
            }
            
        }
    }
}