public class LeaveMessage extends Message {
    public final int LEAVE = 777;

    public LeaveMessage() {
        return
    }

    public int getMessageType() {
        return LEAVE;
    }
}