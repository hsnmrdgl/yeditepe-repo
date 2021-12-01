import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientApp {
	
	public static void main(String[] args) {
		try {
			System.out.println("Client is working");
			String host = "localhost";
			Socket socket = new Socket(host, 6789);
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your username first : ");
			String userName = inFromUser.readLine();
			out.println(userName);
			out.flush();
			System.out.println("Start chat now!");
			
			new ClientHandler(in).start();
			String str;
			while (true) {
				str = inFromUser.readLine();
				out.println(str);
				out.flush();	
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}