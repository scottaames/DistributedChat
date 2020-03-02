package p2pchat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NodeSocket {

	private Socket socket;
	private InputStream is;
	private OutputStream os;


	public NodeSocket(String host, int port)
	throws IOException, UnknownHostException {
		this(new Socket(host, port));
	}


    public NodeSocket(Socket sock)
	throws IOException {
		this.socket = sock;
		this.is = this.socket.getInputStream();
		this.os = this.socket.getOutputStream();
	}


	public void close() throws IOException {
		socket.close();
		os.close();
		s.close();
	}

	public int read() throws IOException {
		return is.read();
	}

	public int read(byte[] b) throws IOException {
		return is.read(b);
	}

	public void write(byte[] b) throws IOException {
		os.write(b);
		os.flush();
	}

}
