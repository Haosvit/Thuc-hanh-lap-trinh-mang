package hao.thuchanhltmang.buoi1bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatRoomTCPClient {
	Socket clientSocket;
	String hostName = "localhost";
	int hostPort = 13;
	Scanner scanner;
	String userName = "Dev";
	
	static int tryCount = 10;

	public ChatRoomTCPClient() {
		scanner = new Scanner(System.in);
		while (tryCount > 0) {
			if (initClient()) {
				// get nickname
				System.out.print("Enter your nickname: ");
				userName = scanner.nextLine();

				// start listener
				ClientListener listener = new ClientListener();
				listener.start();
				while (true) {
					String msg = writeMessage();
					sendToServer(msg);
				}
			}
		}
	}

	private String writeMessage() {
		System.err.print(">> ");
		return scanner.nextLine();
	}

	private void sendToServer(String msg) {
		DataOutputStream outStream;
		try {
			outStream = new DataOutputStream(clientSocket.getOutputStream());
			String msgToServer = userName + "#" + msg;
			outStream.writeUTF(msgToServer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean initClient() {
		try {
			clientSocket = new Socket(hostName, hostPort);
			System.out.println("Connected to " + InetAddress.getByName(hostName).getHostAddress() + ":" + hostPort);
			tryCount = 10;
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can not connect! Retry " + tryCount);
			tryCount--;
		}		
		return false;
	}

	class ClientListener extends Thread {
		@Override
		public void run() {
			try {
				DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());

				while (true) {
					String msg = inStream.readUTF();
					System.out.println(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			super.run();
		}
	}

	public static void main(String[] args) {
		new ChatRoomTCPClient();
	}

}
