package hao.thuchanhltmang.buoi2bai3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ChatUDPServer {
	static int port = 23;
	static byte[] inBuf = new byte[2048];
	static DatagramSocket socket;
	static ArrayList<User> inUsers = new ArrayList<User>();

	static class User {
		private InetAddress address;
		private int port;

		public User(InetAddress address, int port) {
			super();
			this.address = address;
			this.port = port;
		}

		public InetAddress getAddress() {
			return address;
		}

		public void setAddress(InetAddress address) {
			this.address = address;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}

	public static void main(String[] args) {
		try {
			socket = new DatagramSocket(port);
			System.out.println("Server started at port " + port);

			DatagramPacket inPacket = new DatagramPacket(inBuf, inBuf.length);
			while (true) {
				try {
					socket.receive(inPacket);
					boolean firstJoin = true;
					User user = new User(inPacket.getAddress(), inPacket.getPort());
					System.out.println("User port: " + user.getPort());

					for (User u : inUsers) {
						if (u.getPort() == user.getPort() && u.getAddress().equals(user.getAddress())) {
							firstJoin = false;
						}
					}

					System.out.println("inPackets length: " + inUsers.size());

					String inMsg = new String(inPacket.getData(), 0, inPacket.getLength());

					// send msg to chat room
					StringTokenizer st = new StringTokenizer(inMsg, "\t");

					String senderName = st.nextToken();
					String msg = st.nextToken();

					if (msg.equals("~leave")) {
						// user leaves chat room
						for (User u : inUsers) {
							if (u.getAddress().equals(user.getAddress()) && u.getPort() == user.getPort()) {
								user = u;
							}
						}
						inUsers.remove(user);
						sendMsg(senderName + " left the room!\n");
					} else {
						// notify first join of a sender
						if (firstJoin) {
							inUsers.add(user);
							sendMsg(senderName + " has joined the room!\n");
							firstJoin = false;
						}

						else {

							String outMsg = senderName + ": " + msg + "\n";
							System.out.println(outMsg);
							sendMsg(outMsg);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	private static void sendMsg(String outMsg) {
		DatagramPacket outPacket = new DatagramPacket(outMsg.getBytes(), outMsg.length(), null, 0);
		for (User user : inUsers) {

			outPacket.setAddress(user.getAddress());
			outPacket.setPort(user.getPort());

			if (socket != null) {
				try {
					socket.send(outPacket);
					System.out.println("Sent to chatroom: " + outPacket.getAddress().toString() + " "
							+ outPacket.getPort() + "\n" + new String(outPacket.getData(), 0, outPacket.getLength()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
