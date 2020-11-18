package ge.edu.btu.server;

import ge.edu.btu.model.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerMessageThread extends Thread {

    private final Socket clientSocket;

    public ServerMessageThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {
                Message receivedText = (Message) objectInputStream.readObject();
                String receivedQuestion = receivedText.getMessage();
                switch (receivedQuestion) {
                    case "გამარჯობა":
                        objectOutputStream.writeObject(new Message("მოგესალმებით, რით შემიძლია დაგეხმაროთ?"));
                        break;
                    case "მაჩვენე კურსი":
                        objectOutputStream.writeObject(new Message("12.21"));
                        break;
                    case "მაჩვენე ფილიალები":
                        objectOutputStream.writeObject(new Message("[იასამნის ქუჩა 12, სატესტო ქუჩა 22, ჯემალას ქუჩა12]"));
                        break;
                    case "ნახვამდის":
                        objectOutputStream.writeObject(new Message("გმადლობთ რომ დაგვიკავშირდით, ნახვამდის!"));
                        objectInputStream.close();
                        objectOutputStream.close();
                        clientSocket.close();
                        break;
                    default:
                        objectOutputStream.writeObject(new Message("სამწუხაროდ ამ კითხვაზე პასუხი არ მაქვს."));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
