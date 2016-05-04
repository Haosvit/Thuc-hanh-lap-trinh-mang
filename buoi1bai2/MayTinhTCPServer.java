package hao.thuchanhltmang.buoi1bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MayTinhTCPServer {
	static ServerSocket socket;
	final static int DEFAULT_PORT = 12;

	static Socket clientSocket;

	private static void initServer() {
		try {
			socket = new ServerSocket(DEFAULT_PORT);
			System.out.println("Server started at " + InetAddress.getLocalHost().getHostAddress() + ":" + DEFAULT_PORT);
			while (true) {
				try {
					listenToClient();
				} catch (IOException e) {

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void listenToClient() throws IOException {
		System.out.println("Listening...");
		clientSocket = socket.accept();
		System.out.println("Connected to " + clientSocket.getInetAddress().getHostAddress());
		DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
		String mathExpression = inStream.readUTF();
		processRequest(mathExpression);
	}

	private static void processRequest(String mathExpression) {
		PolishNotation pn = new PolishNotation();
		double result = pn.calculate(mathExpression);

		sendResultToClient(result + "");
	}

	private static void sendResultToClient(String result) {
		try {
			DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
			outStream.writeUTF(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		initServer();
	}
}
