package ru.gb.Lection.task1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExamle1 {
    public static void main(String[] args) throws IOException {
        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket client = new Socket(inetAddress , 1300);
        }catch(UnknownHostException e){
            throw new RuntimeException();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}
