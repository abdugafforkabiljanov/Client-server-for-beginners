package ru.gb.Practice.task1;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientManager implements Runnable {
    private static final CopyOnWriteArrayList<ClientManager> clients = new CopyOnWriteArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.name = bufferedReader.readLine();
            if (this.name == null || this.name.isBlank()) {
                this.name = "NoName";
            }

            clients.add(this);
            broadcastMessage(name + " chatga qo‘shildi!");

        } catch (IOException e) {
            closeEverything();
        }
    }

    @Override
    public void run() {
        String message;
        while (socket.isConnected()) {
            try {
                message = bufferedReader.readLine();
                if (message != null) {
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                closeEverything();
                break;
            }
        }
    }

    private void broadcastMessage(String message) {
        for (ClientManager client : clients) {
            try {
                client.bufferedWriter.write(message);
                client.bufferedWriter.newLine();
                client.bufferedWriter.flush();
            } catch (IOException e) {
                client.closeEverything();
            }
        }
    }

    private void closeEverything() {
        clients.remove(this);
        broadcastMessage("❌ " + name + " chatdan chiqdi!");

        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
