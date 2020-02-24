public class ChatMessage extends Message {
    public final int CHAT = 888;
    public String msgText;

    public ChatMessage(String msg) {
        msgText = msg;
    }

    public int getMessageType() {

        return CHAT;
    }
}