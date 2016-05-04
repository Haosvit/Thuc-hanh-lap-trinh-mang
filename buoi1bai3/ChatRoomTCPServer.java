package hao.thuchanhltmang.buoi1bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ChatRoomTCPServer {
	private static final int DEFAULT_PORT = 13;
	private static ServerSocket serverSocket;

	Map connectedClients = new HashMap();

	public ChatRoomTCPServer() {
		if (initServer()) {
			listenToClients();
		}
	}

	private void listenToClients() {
		while (true) {
			try {

				Socket cSocket = serverSocket.accept();
				new ClientHandler(cSocket).start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean initServer() {
		try {
			serverSocket = new ServerSocket(DEFAULT_PORT);
			System.out.println("Server started at " + InetAddress.getLocalHost().getHostAddress() + ":" + DEFAULT_PORT);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private class ClientHandler extends Thread {
		Socket cSocket;

		public ClientHandler(Socket socket) {
			cSocket = socket;
			if (!connectedClients.containsKey(cSocket)) {
				connectedClients.put(cSocket, "");
			}
		}

		@Override
		public void run() {
			super.run();
			

			DataInputStream inStream;
			try {
				inStream = new DataInputStream(cSocket.getInputStream());

				while (true) {
					StringTokenizer tokenizer = new StringTokenizer(inStream.readUTF(), "#");

					try {
						String userName = tokenizer.nextToken();
						if (connectedClients.get(cSocket).toString() == ""){
							connectedClients.put(cSocket, userName);
						}
						String msg = tokenizer.nextToken();
						String msgToClients =userName + ": " + msg;

						sendMessageToClients(msgToClients);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception e1) {
				String noti = connectedClients.get(cSocket).toString() + " left the room!";
				System.out.println(noti);				
				connectedClients.remove(cSocket);
				sendMessageToClients(noti);
			}
		}

		private void sendMessageToClients(String msgToClients) {
			msgToClients = new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(new Date()) + " " + msgToClients;
			for (Object clObject : connectedClients.keySet().toArray()){
				Socket client = (Socket) clObject;
				try {
					DataOutputStream outStream = new DataOutputStream(client.getOutputStream());
					outStream.writeUTF(msgToClients);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new ChatRoomTCPServer();
	}
}
