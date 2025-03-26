package ru.gb.Lection.task1Copy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerExample1 {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(1400);
            Socket socket = server.accept();
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println("Hello");
            server.close();
            socket.close();
        }catch(UnknownHostException e){
            throw new RuntimeException(e);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }


    }
}
