package hao.thuchanhltmang.buoi1bai2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MayTinhTCPClient {
	static Socket socket;
	static String defaultHost = "";
	static final int DEFAULT_PORT = 12;
	
	static int tryCount = 10;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (tryCount > 0) {
			if (createConnection()) {
				while (true) {
					System.out.println();
					System.out.println("Nhập biểu thức toán: ");
					String mathExpression = scanner.nextLine();
					if (mathExpression.length() == 0) {
						break;
					}
					sendToServer(mathExpression);
					receiveResult();
				}
			}
		}
	}

	private static void receiveResult() {
		try {
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			String result = inStream.readUTF();
			System.out.println("Result: " + result);
		} catch (IOException e) {
			System.out.println("Lỗi kết nối");
		}
		
	}

	private static void sendToServer(String mathExpression) {
		try {
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			outStream.writeUTF(mathExpression);
		} catch (IOException e) {
			System.out.println("Lỗi kết nối!");
		}
	}

	private static boolean createConnection() {
		try {
			defaultHost = InetAddress.getLocalHost().getHostAddress();
			socket = new Socket(defaultHost, DEFAULT_PORT);
			System.out.println("Connected to " + defaultHost + ":" + DEFAULT_PORT);
			tryCount = 10;
			return true;
		} catch (UnknownHostException e) {
			System.out.println("Lỗi: host sai!");
		} catch (IOException e) {
			tryCount--;
			System.out.println("Lỗi: Không thiết lập được kết nối! Thử lại: " + tryCount);
		}
		return false;
	}
}
