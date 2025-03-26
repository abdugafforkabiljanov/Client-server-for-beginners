package ru.gb.Lection.task1Copy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientExample1 {
    public static void main(String[] args) {
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket socket = new Socket(inetAddress , 1400);
        }catch(IOException e){
            throw new RuntimeException();
        }
    }
}
