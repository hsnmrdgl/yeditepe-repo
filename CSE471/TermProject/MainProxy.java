import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainProxy implements Runnable, ActionListener {
	
	static JMenu fileMenu, helpMenu;  
	static JMenuItem start, stop, report, addHost, displayHost, exit, about;
	
	public static void main(String[] args) {
		 
	    JFrame frame = new JFrame("Menu and MenuItem Example");  
	    JMenuBar menuBar = new JMenuBar();  
	    
	    fileMenu = new JMenu("File");  
	    helpMenu = new JMenu("Help");  
	    
	    start = new JMenuItem("Start");  
	    start.addActionListener(null);
	    stop = new JMenuItem("Stop");
	    stop.addActionListener(null);
	    report = new JMenuItem("Report");
	    report.addActionListener(null);
	    addHost = new JMenuItem("Add host to filter");
	    addHost.addActionListener(null);
	    displayHost = new JMenuItem("Display current filtered hosts");
	    displayHost.addActionListener(null);
	    exit = new JMenuItem("Exit");
	    exit.addActionListener(null);
	    about = new JMenuItem("About");
	    about.addActionListener(null);
	    
	    fileMenu.add(start);
	    fileMenu.add(stop);
	    fileMenu.add(report);
	    fileMenu.add(addHost);
	    fileMenu.add(displayHost);
	    fileMenu.add(exit);
	    helpMenu.add(about);
	    
	    menuBar.add(fileMenu);
	    menuBar.add(helpMenu);
	    frame.setJMenuBar(menuBar);  
	    frame.setSize(400,400);  
	    frame.setLayout(null);  
	    frame.setVisible(true);    
		
		MainProxy proxyTest = new MainProxy(8888);
		proxyTest.listen();	
	}
	
	
	private ServerSocket serverSocket;
	private volatile boolean running = true;
	
	static HashMap<String, File> cache;
	static HashMap<String, String> blockedSites;
	static ArrayList<Thread> servicingThreads;


	public MainProxy (int port) {
		
		cache = new HashMap<>();
		blockedSites = new HashMap<>();
		servicingThreads = new ArrayList<>();
		
		new Thread(this).start();
	
		try{
			
			File cachedSites = new File("cachedSites.txt");
			if(!cachedSites.exists()){
				System.out.println("[INFO] Cached Sites file not found! Creating new file");
				cachedSites.createNewFile();
			}
			
			else {
				FileInputStream fileInputStream = new FileInputStream(cachedSites);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				cache = (HashMap<String,File>)objectInputStream.readObject();
				fileInputStream.close();
				objectInputStream.close();
			}
	
			File blockedSitesTxtFile = new File("blockedSites.txt");
			if(!blockedSitesTxtFile.exists()){
				System.out.println("[INFO] Blocked Sites file not found! Creating new file");
				blockedSitesTxtFile.createNewFile();
			}
			
			else {
				FileInputStream fileInputStream = new FileInputStream(blockedSitesTxtFile);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				blockedSites = (HashMap<String, String>)objectInputStream.readObject();
				fileInputStream.close();
				objectInputStream.close();
			}
			
		} catch (IOException e) {
			System.out.println("[ERROR] Cannot load cached sites");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] An error occured about cache class");
			e.printStackTrace();
		}
	
		try {
			
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..");
			running = true;
		} 
		catch (SocketException se) {
			System.out.println("[ERROR] Socket exception");
			se.printStackTrace();
		}
		catch (SocketTimeoutException ste) {
			System.out.println("[ERROR] Socket timeout exception");
		} 
		catch (IOException io) {
			System.out.println("[ERROR] IO exception");
		}
	}

	public void listen(){
	
		while(running){
			try {
				
				Socket socket = serverSocket.accept();
				Thread thread = new Thread(new HTTPRequests(socket));
				servicingThreads.add(thread);
				thread.start();	
			} 
			catch (SocketException e) {
				System.out.println("[INFO] Socket Exception. Server closed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void closeServer(){
		System.out.println("\nServer closing..");
		running = false;
		try{
			FileOutputStream fileOutputStream = new FileOutputStream("cachedSites.txt");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	
			objectOutputStream.writeObject(cache);
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("[INFO] Cached Sites saved");
	
			FileOutputStream fileOutputStream2 = new FileOutputStream("blockedSites.txt");
			ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
			objectOutputStream2.writeObject(blockedSites);
			objectOutputStream2.close();
			fileOutputStream2.close();
			System.out.println("[INFO] Blocked Sites saved");
			try{
				
				for(Thread thread : servicingThreads){
					if(thread.isAlive()){
						System.out.print("[INFO] Waiting on "+  thread.getId()+" to close..");
						thread.join();
						System.out.println(" closed");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			} catch (IOException e) {
				System.out.println("[ERROR] An error occured when saving cache/blocked sites");
				e.printStackTrace();
			}
	
			try{
				System.out.println("[INFO] Connection terminated");
				serverSocket.close();
			} catch (Exception e) {
				System.out.println("[ERROR] An exception while closing server socket");
				e.printStackTrace();
			}
	
		}
	
		public static File getCachedPage(String url){
			return cache.get(url);
		}
	
		public static void addCachedPage(String urlString, File fileToCache){
			cache.put(urlString, fileToCache);
		}
		
		public static boolean isBlocked (String url){
			if(blockedSites.get(url) != null){
				return true;
			} else {
				return false;
			}
		}
			
		@Override
		public void run() {
			
			Scanner scanner = new Scanner(System.in);
			
			int choice;
			while(running){
				System.out.println(	"(1) Add host to block\n"
									+"(2) Show blocked hosts\n"
									+"(3) Show cached hosts\n"
									+"(4) EXIT!\n");
				
				choice = scanner.nextInt();
				
				if(choice == 1) {
					String hostname = JOptionPane.showInputDialog(null, "Enter site");
					blockedSites.put(hostname, hostname);
					System.out.println("\n" + hostname + " blocked successfully \n");
				}
				
				else if(choice == 2){
					System.out.println("\nCurrently Blocked Sites");
					for(String key : blockedSites.keySet()){
						System.out.println(key);
					}
					System.out.println();
				} 
	
				else if(choice == 3){
					System.out.println("\nCurrently Cached Sites");
					for(String key : cache.keySet()){
						System.out.println(key);
					}
					System.out.println();
				}
	
	
				else if(choice == 4){
					running = false;
					closeServer();
				}
	
				else {
					System.out.println("Please enter valid option!");
				}
				
			}
			scanner.close();
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == start) {
				running = true;
			}
			
			if(e.getSource() == stop) {
				running = false;
			}
			
			if(e.getSource() == about) {
				JOptionPane.showMessageDialog(null, "Made by Hasan Caglar Muradoglu\n\t20160702053");
			}
			
			if(e.getSource() == exit) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?","Message",JOptionPane.YES_OPTION);
	
				if(dialogButton == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		}
}