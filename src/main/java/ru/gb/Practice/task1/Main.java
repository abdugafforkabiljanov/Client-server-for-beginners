package ru.gb.Practice.task1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ismingizni kiriting: ");
            String name = scanner.nextLine();

            InetAddress inetAddress = InetAddress.getLocalHost();
            int port = 1400;

            if (!isServerAvailable(inetAddress, port)) {
                System.out.println("Server ishlamayapti! Avval serverni ishga tushiring.");
                return;
            }

            Socket socket = new Socket(inetAddress, port);
            Client client = new Client(socket, name);
            client.listenForMessages();
            client.sendMessage();

        } catch (UnknownHostException e) {
            System.out.println("Xost nomi aniqlanmadi. Tarmoq sozlamalarini tekshiring.");
        } catch (IOException e) {
            System.out.println("Ulanish xatosi: " + e.getMessage());
        }
    }

    private static boolean isServerAvailable(InetAddress address, int port) {
        try (Socket testSocket = new Socket(address, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
