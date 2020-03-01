package p2pchat;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Connection {

    private Peer peer;
    private Socket socket;

    public Connection(Peer peer, Socket socket) {
        this.peer = peer;
        this.socket = socket;
    }


    /**
     * @param msg
     */
    public void sendMessage(Message msg) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(msg);
        } catch (Exception e) {
            System.out.println("Error sending message. See:\n" + e);
        }
    }


    /**
     * @return Message
     */
    public Message receiveMessage() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) in.readObject();
            return msg;
        } catch (Exception e) {
            System.out.println("Error receiving message, see:\n" + e);
            return null;
        }
    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Error closing connecton, see:\n" + e);
            }
        }
    }

    public Peer getPeer() {
        return peer;
    }
}