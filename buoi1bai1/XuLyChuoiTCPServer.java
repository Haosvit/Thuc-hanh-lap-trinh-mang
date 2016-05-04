package hao.thuchanhltmang.buoi1bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class XuLyChuoiTCPServer {
	static int port = 11;
	static ServerSocket ssk;

	public static void main(String args[]) {
		try {
			ssk = new ServerSocket(port);
			System.out.println("Server started at " + port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			try {
				Socket cSocket = ssk.accept();
				DataOutputStream dout = new DataOutputStream(cSocket.getOutputStream());
				DataInputStream din = new DataInputStream(cSocket.getInputStream());

				String clientMsg = din.readUTF();
				
				StringTokenizer st = new StringTokenizer(clientMsg, " ");
				int numOfWords = st.countTokens();
				
				String returnMsg = "Original message: " + clientMsg + "\n"
						+ "Proccessed:\n"
						+ clientMsg.toUpperCase() + "\n" 
						+ clientMsg.toLowerCase() + "\n" 
						+ "Number of words: "
						+ numOfWords;

				dout.writeUTF(returnMsg);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
