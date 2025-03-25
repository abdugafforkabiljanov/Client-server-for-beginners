package ru.gb.Practice.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 1400;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(" Server ishga tushdi. Port: " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Yangi mijoz ulandi!");

                ClientManager clientManager = new ClientManager(socket);
                threadPool.execute(clientManager);
            }

        } catch (IOException e) {
            System.out.println("Serverda xatolik yuz berdi: " + e.getMessage());
        }
    }
}
