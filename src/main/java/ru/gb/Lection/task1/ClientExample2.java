package ru.gb.Lection.task1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample2 {
    public static void main(String[] args) {
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket client = new Socket(inetAddress , 1300);
            System.out.println("IP адрес сервера: "+ client.getInetAddress());
            System.out.println("IP адрес клиента: " + client.getLocalAddress().getHostAddress());
            System.out.println("Порт клиента:" + client.getLocalPort());
            InputStream inStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inStream);
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println("Привет");
            System.out.println(dataInputStream.readLine());
            client.close();
        }catch(UnknownHostException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
