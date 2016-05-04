package hao.thuchanhltmang.buoi2bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MayTinhUDPServer {
private static DatagramSocket serverSocket;

private static final int DEFAULT_PORT = 22;

private static DatagramPacket inPacket;
private static DatagramPacket outPacket;

private static InetAddress clientAddress;
private static int clientPort;
private static byte[] buffer = new byte[512];

public static void main(String[] args) {
	if (initServer()) {
		while (true) {
			listenToClient();
		}
	}
}

private static void listenToClient() {
	inPacket = new DatagramPacket(buffer, buffer.length);
	try {
		System.out.println("Listening...");
		serverSocket.receive(inPacket);
		clientAddress = inPacket.getAddress();
		clientPort = inPacket.getPort();
		System.out.println("Receive packet from " + clientAddress.getHostAddress() + ":" + clientPort);
		String mathExpression = new String(inPacket.getData(), 0, inPacket.getLength());
		System.out.println("Packet content: " + mathExpression);
		processRequest(mathExpression);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

private static void processRequest(String mathExpression) {
	PolishNotation pn = new PolishNotation();
	double result = pn.calculate(mathExpression);
	sendResultToClient(result);
}

private static void sendResultToClient(double result) {
	String resultStr = String.valueOf(result);
	outPacket = new DatagramPacket(resultStr.getBytes(), resultStr.length(), clientAddress, clientPort);
	try {
		serverSocket.send(outPacket);
		System.out.println("Sent result to client. Result: " + result);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

private static boolean initServer() {
	try {
		serverSocket = new DatagramSocket(DEFAULT_PORT);
		try {
			System.out.println("Server started at " + InetAddress.getLocalHost().getHostAddress() + ":" + DEFAULT_PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		};
		return true;
	} catch (SocketException e) {
		e.printStackTrace();
	}
	return false;
}
}
