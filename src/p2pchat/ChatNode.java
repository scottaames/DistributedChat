import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class ChatNode {

    /* MESSAGE TYPES */
    public static final String JOIN = "JOIN";
    public static final String EXIT = "EXIT";
    public static final String CHAT = "CHAT";
    public static final String LIST = "LIST";

    // hash of all peers connected
    private Hashtable<String, PeerData> peers;

    // hash of all the message handlers
    private Hashtable<String, MessageHandler> handlers;

    // this nodes peer data
    private PeerData currPeerData;

    // node status
    private boolean isRunning;

    public ChatNode(PeerData data) {
        if (data.getHost() == null) {
            data.setHost(getHostName());
        }
        if (data.getId() == null) {
            data.setId(data.getHost() + ":" + data.getPort());
        }
        this.currPeerData = data;
        this.isRunning = true;

        this.peers = new Hashtable<String, PeerData>();
        this.handlers = new Hashtable<String, MessageHandler>();

        handlers.put(JOIN, new JoinHandler());

    }

    public ChatNode(int port) {
        this(new PeerData(port));
    }

    private String getHostName() {
        String host = "";
        try {
            Socket socket = new Socket("www.google.com", 80);
            host = socket.getLocalAddress().getHostAddress();
        } catch (Exception e) {
            System.out.println("Error getting host name. See:\n" + e);
        }
        return host;
    }

    public ServerSocket makeNodeServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        server.setReuseAddress(true);
        return server;
    }

    public void sendMessage(PeerData peer, String msgType, String msgContent) {
        try {
            Connection conn = new Connection(peer);
            Message toSend = new Message(msgType, msgContent);
            conn.sendMessage(toSend);
        } catch (Exception e) {
            System.out.println("Error sending message. See exception:\n");
        }
    }

    public void mainLoop() {
        try {
            ServerSocket serverSocket = makeNodeServer(currPeerData.getPort());
            serverSocket.setSoTimeout(2000);

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    clientSocket.setSoTimeout(0);

                    PeerHandler peerHandler = new PeerHandler(clientSocket);
                    peerHandler.start();
                } catch (Exception e) {
                    System.out.println("Error accepting client socket.\n" + e);
                    continue;
                }
            }
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error accepting client socket.\n" + e);
        }
        isRunning = false;
    }

    public boolean addPeer(PeerData peerData) {
        return addPeer(peerData.getId(), peerData);
    }

    public boolean addPeer(String key, PeerData peerData) {
        if (!peers.containsKey(key)) {
            peers.put(key, peerData);
            return true;
        }
        return false;
    }

    public PeerData getPeer(String key) {
        return peers.get(key);
    }

    private class PeerHandler extends Thread {
        private Socket socket;

        public PeerHandler(Socket socket) {
            this.socket = socket;
        }

        public PeerHandler(String host, int port) {
            this.socket = new Socket(host, port);
        }

        public void run() {
            Connection conn = new Connection(null, socket);
            Message msg = conn.receiveMessage();
            if (handlers.containsKey(msg.getMessageType())) {
                handlers.get(msg.getMessageType()).handleMessage(conn, msg);
            } else {
                System.out.println("That message type is not supported. Please try again.");
            }
            conn.close();
        }
    }
}