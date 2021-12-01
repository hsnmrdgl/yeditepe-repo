import java.net.*;
import java.util.*;

public class ServerApp {
	
	private static HashMap<Socket, String> hashMap = new HashMap<Socket, String>();
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("Server is working...");
			ServerSocket serverSocket = new ServerSocket(6789);
			String userName = hashMap.values().toString();
			
			while (true) {
				Socket incoming = serverSocket.accept();
				getHashMap().put(incoming, userName);
				new ServerHandler(incoming).start();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static synchronized HashMap<Socket, String> getHashMap() {
		return hashMap;
	}

	public static synchronized void setHashMap(HashMap<Socket, String> hashMap) {
		ServerApp.hashMap = hashMap;
	}
}
