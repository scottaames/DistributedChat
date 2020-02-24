package app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public abstract class Message {
    public final int JOIN = 999;
    public final int CHAT = 888;
    public final int LEAVE = 777;
    public final int NODE = 555;

    public Message(int msgType, ArrayList<ChatNode> nodeList) {
        switch (msgType) {
            case JOIN:
                break;
            case CHAT:
                break;
            case LEAVE:
                break;
            case NODE:
                break;
        }
    }

    abstract public int getMessageType();
}