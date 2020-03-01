package p2pchat;

public class Peer {
    private int id;
    private String host;
    private int port;

    public Peer(int id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }


    /**
     * @return int
     */
    public int getId() {
        return id;
    }


    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return String
     */
    public String getHost() {
        return host;
    }


    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * @return int
     */
    public int getPort() {
        return port;
    }


    /**
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }


    /**
     * @return String
     */
    public String toString() {
        return id + " (" + host + ":" + port + ")";
    }
}