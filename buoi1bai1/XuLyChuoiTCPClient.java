package hao.thuchanhltmang.buoi1bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class XuLyChuoiTCPClient {
	static int port = 11;
	static String host = "localhost";
	static String msg = "Demo TCP/IP progrAmming.";
public static void main(String args[])
{
	try {
		Socket sk = new Socket(host, port);
		System.out.println("Connected to server at port " + port);
		DataOutputStream dout = new DataOutputStream(sk.getOutputStream());
		DataInputStream din = new DataInputStream(sk.getInputStream());
		
		dout.writeUTF(msg);
		
		String returnMsg = din.readUTF();
		
		System.out.println("Server returns: \n" + returnMsg);
		
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

}
