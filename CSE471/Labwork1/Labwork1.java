import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;

public class HTMLTest {

	public static void main(String[] args) throws Exception {
		String address;
		String hostName;
		String httpMessage;
		String newhttpMessage;
		String path;
		String lastModified = null;
		int data;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please Enter the URL address of the web page: ");
		address = inFromUser.readLine();

		if (!address.startsWith("http://")) {
			address = "http://" + address;
		}

		URL url = new URL(address);
		hostName = url.getHost();
		path = url.getPath().isEmpty() ? "/" : url.getPath();

		httpMessage = "GET " + path + " HTTP/1.1\r\n" + "Host: "
					+ hostName + "\r\n" + "Connection: close\r\n\r\n";

		Socket clientSocket = new Socket(hostName, 8080);
		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		System.out.print(httpMessage);
		outToServer.writeBytes(httpMessage);

		byte[] dataArr = new byte[500000];
		byte[] headerArr = new byte[500000];
 		
		int counterHead = 0;
		int counterData = 0;
		boolean flag = true;
		
		//http://10.2.10.202:8080/3dsk4fsljnw4.jpg
		
		while ((data = inFromServer.read()) != -1) {
			if(flag) {
				headerArr[counterHead++] = (byte) data;
			}
			else {
				dataArr[counterData++] = (byte) data;
			}
			if (flag && headerArr[counterHead - 1] == '\n' && headerArr[counterHead - 2] == '\r' &&
					headerArr[counterHead - 3] == '\n'
					&& headerArr[counterHead - 4] == '\r') {
				counterData = 0;
				flag = false;
			}
		}
		
		
		FileOutputStream fout = new FileOutputStream("image.jpg");
		fout.write(dataArr);
		fout.close();
		
		
		String header = new String(headerArr, 0, counterHead);
		System.out.println(header);		
		
		/*
		StringTokenizer st = new StringTokenizer("\r\n");
		lastModified = "";
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			int a = line.indexOf(':');
			String key = line.substring(0, a);
			String value = line.substring(a + 2);
			if (key.equalsIgnoreCase("Last Modified")) {
				lastModified = value;
			}
		}*/
		
		lastModified = header.substring(157,187);
		
		newhttpMessage = "GET " + path + " HTTP/1.1\r\n" + "Host: "
						+ hostName + "\r\n" + "Connection: close\r\n"
						+ "If-Modified-Since: " + lastModified +"\r\n\r\n";
		
		System.out.println(newhttpMessage);
		outToServer.writeBytes(newhttpMessage);
		
		while ((data = inFromServer.read()) != -1) {
			
			if(flag) {
				headerArr[counterHead++] = (byte) data;
			}

			if (flag && headerArr[counterHead - 1] == '\n' && headerArr[counterHead - 2] == '\r' &&
					headerArr[counterHead - 3] == '\n'
					&& headerArr[counterHead - 4] == '\r') {
				flag = false;
			}
		}
		
		
		String newHeader = new String(headerArr, 0, counterHead);
		
		if(newHeader.contains("304")) {
			System.out.println("File is up-to-date");
		}
		
		else {
			while ((data = inFromServer.read()) != -1) {
				if(flag) {
					headerArr[counterHead++] = (byte) data;
				}
				else {
					dataArr[counterData++] = (byte) data;
				}
				if (flag && headerArr[counterHead - 1] == '\n' && headerArr[counterHead - 2] == '\r' &&
						headerArr[counterHead - 3] == '\n'
						&& headerArr[counterHead - 4] == '\r') {
					counterData = 0;
					flag = false;
				}
			}
		}
		
		clientSocket.close();
	}
}
