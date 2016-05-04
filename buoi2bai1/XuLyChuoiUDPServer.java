package hao.thuchanhltmang.buoi2bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.StringTokenizer;

public class XuLyChuoiUDPServer {

	public static int port = 21;
	
	public static void main(String[] args) {
		byte[] buf = new byte[2048];
		try {
			DatagramSocket serverSocket = new DatagramSocket(port);
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("Server started at port " + port);
			
			while (true) {
				try {
					serverSocket.receive(receivePacket);
					String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
					
					StringTokenizer st = new StringTokenizer(clientMsg, " ");
					int numOfWords = st.countTokens();
					
					String returnMsg = "Original message: " + clientMsg + "\n"
							+ "Proccessed:\n"
							+ clientMsg.toUpperCase() + "\n" 
							+ clientMsg.toLowerCase() + "\n" 
							+ "Number of words: "
							+ numOfWords;

					DatagramPacket outPacket = new DatagramPacket(returnMsg.getBytes(), returnMsg.length(), receivePacket.getAddress(), receivePacket.getPort());
					
					serverSocket.send(outPacket);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
