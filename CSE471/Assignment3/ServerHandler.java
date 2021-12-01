import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class ServerHandler extends Thread {

	private Socket incoming;

	public ServerHandler(Socket incoming) {
		this.incoming = incoming;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out;
			
			String userName = in.readLine();
			ServerApp.getHashMap().put(incoming, userName);
			
			while (true) {
				String str = in.readLine();
				StringTokenizer st = new StringTokenizer(str); 
				
				System.out.println("### Online Users ###");
				for(String s: ServerApp.getHashMap().values()) {
					System.out.println(s);
				}
				
				if (str == null) {
					break;
				} 
				
				//PM MESSAGES
				else if(st.nextToken(" ").equals("PM")) {
					String pmUser = st.nextToken(" ");
					String pmMsg = st.nextToken("\n");
					Socket pmSocket;
					
					for(Entry<Socket, String> s : ServerApp.getHashMap().entrySet()) {
						if(s.getValue().equals(pmUser)) {
							pmSocket = s.getKey();							
							out = new PrintWriter(new OutputStreamWriter(pmSocket.getOutputStream()));
							out.println(ServerApp.getHashMap().get(incoming)+ ": " + pmMsg);
							out.flush();
						}
					}
				}
				
				//GLOBAL MESSAGE
				else {
					for(Socket s: ServerApp.getHashMap().keySet()) {
						if(s.getPort() != incoming.getPort()) {
							out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
							out.println(ServerApp.getHashMap().get(incoming)+ ": " + str);
							out.flush();
						}
					}
				}
			}
			incoming.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}