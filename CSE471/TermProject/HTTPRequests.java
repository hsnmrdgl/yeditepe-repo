import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HTTPRequests implements Runnable {

	Socket clientSocket;
	BufferedReader proxyToClientBr;
	BufferedWriter proxyToClientBw;
	
	private Thread httpsClientToServer;
	
	public HTTPRequests(Socket clientSocket){
		this.clientSocket = clientSocket;
		try{
			this.clientSocket.setSoTimeout(2000);
			proxyToClientBr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			proxyToClientBw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {

		String requestString;
		try{
			requestString = proxyToClientBr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR] Cannot read data!");
			return;
		}

		System.out.println("[INFO] Request Received " + requestString);
		
		String request = requestString.substring(0, requestString.indexOf(' '));
		String urlString = requestString.substring(requestString.indexOf(' ') + 1);
		
		urlString = urlString.substring(0, urlString.indexOf(' '));
		
		if(!urlString.substring(0, 4).equals("http")){
			String temp = "http://";
			urlString = temp + urlString;
		}

		if(MainProxy.isBlocked(urlString)){
			System.out.println("[INFO] Blocked site requested : " + urlString);
			blockedSiteRequested();
			return;
		}
		
		if(request.equals("CONNECT")){
			System.out.println("[INFO] HTTPS Request for : " + urlString + "\n");
			handleHTTPSRequest(urlString);
		} 

		else{
			File file;
			if((file = MainProxy.getCachedPage(urlString)) != null){
				System.out.println("[INFO] Cached Copy found for : " + urlString + "\n");
				sendCachedPageToClient(file);
			} else {
				System.out.println("[INFO] HTTP GET for : " + urlString + "\n");
				sendNonCachedToClient(urlString);
			}
		}
	} 


	private void sendCachedPageToClient(File cachedFile){
		
		try{
			String response;
			
			BufferedReader cachedFileBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(cachedFile)));

			response = 	"HTTP/1.0 200 OK\n" +
						"Proxy-agent: ProxyServer/1.0\n" +
						"\r\n";
			proxyToClientBw.write(response);
			proxyToClientBw.flush();

			String line;
			while((line = cachedFileBufferedReader.readLine()) != null){
				proxyToClientBw.write(line);
			}
			proxyToClientBw.flush();
			
			if(cachedFileBufferedReader != null){
				cachedFileBufferedReader.close();
			}
			
			if(proxyToClientBw != null){
				proxyToClientBw.close();
			}

		} catch (IOException e) {
			System.out.println("[ERROR] Error Sending Cached file to client");
			e.printStackTrace();
		}
	}
	
	private void sendNonCachedToClient(String urlString){
		try{
			
			int fileExtensionIndex = urlString.lastIndexOf(".");
			String fileExtension;

			fileExtension = urlString.substring(fileExtensionIndex, urlString.length());

			String fileName = urlString.substring(0,fileExtensionIndex);
			
			fileName = fileName.substring(fileName.indexOf('.')+1);			
			fileName = fileName.replace("/", "__");
			fileName = fileName.replace('.','_');
			
			if(fileExtension.contains("/")){
				fileExtension = fileExtension.replace("/", "__");
				fileExtension = fileExtension.replace('.','_');
				fileExtension += ".html";
			}
		
			fileName = fileName + fileExtension;
			
			boolean caching = true;
			File fileToCache = null;
			BufferedWriter fileToCacheBW = null;

			try{
				fileToCache = new File("cached/" + fileName);

				if(!fileToCache.exists()){
					fileToCache.createNewFile();
				}
				
				fileToCacheBW = new BufferedWriter(new FileWriter(fileToCache));
			}
			catch (IOException e){
				System.out.println("[ERROR] Couldn't cache: " + fileName);
				caching = false;
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("[ERROR] Cannot open file");
			}

			URL remoteURL = new URL(urlString);
			HttpURLConnection proxyToServerCon = (HttpURLConnection)remoteURL.openConnection();
			proxyToServerCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			proxyToServerCon.setRequestProperty("Content-Language", "en-US");  
			proxyToServerCon.setUseCaches(false);
			proxyToServerCon.setDoOutput(true);


			BufferedReader proxyToServerBR = new BufferedReader(new InputStreamReader(proxyToServerCon.getInputStream()));
			
			String line = 	"HTTP/1.0 200 OK\n" +
							"Proxy-agent: ProxyServer/1.0\n" +
							"\r\n";
			proxyToClientBw.write(line);
			
			while((line = proxyToServerBR.readLine()) != null){
				proxyToClientBw.write(line);
				
				if(caching){
					fileToCacheBW.write(line);
				}
			}
			
			proxyToClientBw.flush();

			if(proxyToServerBR != null){
				proxyToServerBR.close();
			}

			if(caching){
				fileToCacheBW.flush();
				MainProxy.addCachedPage(urlString, fileToCache);
			}
			
			if(fileToCacheBW != null){
				fileToCacheBW.close();
			}

			if(proxyToClientBw != null){
				proxyToClientBw.close();
			}
		} 

		catch (Exception e){
			e.printStackTrace();
		}
	}

	
	private void handleHTTPSRequest(String urlString){
		
		String url = urlString.substring(7);
		String pieces[] = url.split(":");
		url = pieces[0];
		int port  = Integer.valueOf(pieces[1]);

		try{
			
			for(int i=0;i<5;i++){
				proxyToClientBr.readLine();
			}

			InetAddress address = InetAddress.getByName(url);
			Socket proxyToServerSocket = new Socket(address, port);
			proxyToServerSocket.setSoTimeout(5000);

			String line = "HTTP/1.0 200 Connection established\r\n" +
					"Proxy-Agent: ProxyServer/1.0\r\n" +
					"\r\n";
			proxyToClientBw.write(line);
			proxyToClientBw.flush();
			
			BufferedWriter proxyToServerBW = new BufferedWriter(
					new OutputStreamWriter(proxyToServerSocket.getOutputStream()));
			BufferedReader proxyToServerBR = new BufferedReader(
					new InputStreamReader(proxyToServerSocket.getInputStream()));

			ClientToServerHttpsTransmit clientToServerHttps = new ClientToServerHttpsTransmit(
					clientSocket.getInputStream(), proxyToServerSocket.getOutputStream());
			
			httpsClientToServer = new Thread(clientToServerHttps);
			httpsClientToServer.start();
			
			
			try {
				byte[] buffer = new byte[4096];
				int read;
				do {
					read = proxyToServerSocket.getInputStream().read(buffer);
					if (read > 0) {
						clientSocket.getOutputStream().write(buffer, 0, read);
						if (proxyToServerSocket.getInputStream().available() < 1) {
							clientSocket.getOutputStream().flush();
						}
					}
				} while (read >= 0);
			}
			catch (SocketTimeoutException e) {
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			if(proxyToServerSocket != null){
				proxyToServerSocket.close();
			}

			if(proxyToServerBR != null){
				proxyToServerBR.close();
			}

			if(proxyToServerBW != null){
				proxyToServerBW.close();
			}

			if(proxyToClientBw != null){
				proxyToClientBw.close();
			}
			
			
		} catch (SocketTimeoutException e) {
			String line = 	"HTTP/1.0 504 Timeout Occured after 10s\n" +
							"User-Agent: ProxyServer/1.0\n" +
							"\r\n";
			try{
				proxyToClientBw.write(line);
				proxyToClientBw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} 
		catch (Exception e){
			System.out.println("[ERROR] An error on HTTPS : " + urlString );
			e.printStackTrace();
		}
	}

	
	class ClientToServerHttpsTransmit implements Runnable{
		
		InputStream proxyToClientIS;
		OutputStream proxyToServerOS;

		public ClientToServerHttpsTransmit(InputStream proxyToClientIS, OutputStream proxyToServerOS) {
			this.proxyToClientIS = proxyToClientIS;
			this.proxyToServerOS = proxyToServerOS;
		}

		@Override
		public void run(){
			try {
				
				byte[] buffer = new byte[4096];
				int read;
				do {
					read = proxyToClientIS.read(buffer);
					if (read > 0) {
						proxyToServerOS.write(buffer, 0, read);
						if (proxyToClientIS.available() < 1) {
							proxyToServerOS.flush();
						}
					}
				} while (read >= 0);
			}
			catch (SocketTimeoutException ste) {
				// TODO: handle exception
			}
			catch (IOException e) {
				System.out.println("[WARN] Proxy to client HTTPS read timed out");
				e.printStackTrace();
			}
		}
	}

	
	private void blockedSiteRequested(){
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			String line = 	"HTTP/1.0 403 Access Forbidden \n" +
							"User-Agent: ProxyServer/1.0\n" +
							"\r\n";
			bufferedWriter.write(line);
			bufferedWriter.flush();
		} catch (IOException e) {
			System.out.println("[ERROR] An error occured requesting blocked site");
			e.printStackTrace();
		}
	}
}