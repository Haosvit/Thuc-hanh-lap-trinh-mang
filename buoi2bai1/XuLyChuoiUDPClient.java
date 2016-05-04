package hao.thuchanhltmang.buoi2bai1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class XuLyChuoiUDPClient {
	public static int port = 21;
	public static String hostName = "localhost";
	public static byte[] outBuf = new byte[2048];
	public static byte[] inBuf = new byte[2048];
	

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName(hostName);
			DatagramSocket socket = new DatagramSocket();

			BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				// send packet out
				System.out.print("-------------------\nInput data to send: ");
				String msg = userInputReader.readLine();
				
				if (msg.equals(".")) {
					break;
				}

				outBuf = msg.getBytes();

				DatagramPacket outPacket = new DatagramPacket(outBuf, 0, msg.length(), address, port);
				socket.send(outPacket);
				
				// get packet in
				DatagramPacket receivePacket = new DatagramPacket(inBuf, inBuf.length);
				socket.receive(receivePacket);
				
				String receiveMsg = new String(receivePacket.getData(),0, receivePacket.getLength());
				System.out.println("Receive message: \n" + receiveMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
