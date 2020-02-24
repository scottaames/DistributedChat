public class JoinMessage extends Message {
    private final int JOIN = 999;

    public JoinMessage(String ip, int port){

    }

    public int getMessageType() {

        return JOIN;
    }
}