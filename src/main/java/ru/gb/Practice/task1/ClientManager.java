package ru.gb.Practice.task1;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientManager implements Runnable {
    private static final CopyOnWriteArrayList<ClientManager> clients = new CopyOnWriteArrayList<>();
    private static final String LOG_FILE = "informationHistory.txt";

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
                readFromFile();
                message = bufferedReader.readLine();
                if (message != null) {
                    broadcastMessage(message);
                    writeToFile(message);
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

    private void writeToFile(String message) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            fileWriter.write(message);
            fileWriter.newLine();
        } catch (IOException e) {
            System.err.println("Xatolik: Faylga yozib bo‘lmadi!");
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
    public void readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("ERROR READ FILE");
        }
    }
}
