package ge.edu.btu.server;


import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9091);
        System.out.println("სერვერი დაისტარტა");

        while (true){
            Socket currentSocket = serverSocket.accept();
            System.out.println("კლიენტი მიერთდა");
            ServerMessageThread thread = new ServerMessageThread(currentSocket);
            thread.start();
        }

    }
}
