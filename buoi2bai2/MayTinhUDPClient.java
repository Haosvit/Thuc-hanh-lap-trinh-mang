package hao.thuchanhltmang.buoi2bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MayTinhUDPClient {
	private static DatagramSocket clientSocket;

	private static int hostPort = 22;
	private static String hostName = "localhost";
	private static DatagramPacket inPacket;
	private static DatagramPacket outPacket;

	private static InetAddress hostAddress;

	private static byte[] buffer = new byte[512];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (initClient(hostName)) {
				System.out.println("Nhập biểu thức toán: ");
				String mathExpression = scanner.nextLine();
				sendToServer(mathExpression);
			}
		}
	}

	private static void sendToServer(String mathExpression) {
		outPacket = new DatagramPacket(mathExpression.getBytes(), mathExpression.length(), hostAddress, hostPort);
		try {
			clientSocket.send(outPacket);
			receiveResult();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void receiveResult() {
		inPacket = new DatagramPacket(buffer, buffer.length);
		try {
			clientSocket.receive(inPacket);
			String result = new String(inPacket.getData(), 0, inPacket.getLength());
			
			System.out.println("Result: " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean initClient(String hostName) {
		try {
			clientSocket = new DatagramSocket();
			hostAddress = InetAddress.getByName(hostName);
			return true;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}
}
