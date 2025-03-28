package ru.gb.Practice.task1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 {
    private static final String LOG_FILE = "informationHistory.txt";
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10); // 10 ta foydalanuvchi uchun havuz

    public Server2(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void runServer() {
        System.out.println("Server ishga tushdi...");

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Yangi mijoz ulandi!");

                ClientManager clientManager = new ClientManager(socket);
                threadPool.execute(clientManager);
            }
        } catch (IOException e) {
            System.out.println("Serverda xatolik yuz berdi: " + e.getMessage());
            closeServer();
        }
    }

    public void closeServer() {
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
                threadPool.shutdown();
                System.out.println("Server yopildi.");
            }
        } catch (IOException e) {
            System.err.println("Serverni yopishda xatolik: " + e.getMessage());
        }
    }


}
