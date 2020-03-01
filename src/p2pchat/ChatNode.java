import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ChatNode {

    private class PeerHandler extends Thread {
        private Socket socket;

        public PeerHandler(Socket socket) {
            this.socket = socket;
        }

        public PeerHandler(String host, int port) {
            this.socket = new Socket()
        }

        public void run() {
            Connection conn = new Connection(null, socket);
            Message msg = conn.receiveMessage();

        }
    }
    final private UUID ID;
    private Socket socket;


    public ChatNode(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.ID = UUID.randomUUID();
    }
}