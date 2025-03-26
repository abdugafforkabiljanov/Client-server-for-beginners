package ru.gb.Lection.task1Copy;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample2 {
    public static void main(String[] args) {
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket client = new Socket(inetAddress , 1400);
            System.out.println("IP адрес сервера: "+ client.getInetAddress());
            System.out.println("IP адрес клиента: "+ client.getLocalAddress().getHostAddress());
            OutputStream outputStream = client.getOutputStream();
            InputStream inputStream = client.getInputStream();
            PrintStream printStream = new PrintStream(outputStream);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            printStream.println("Hello too");
            System.out.println(dataInputStream.readLine());
        }catch(UnknownHostException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
