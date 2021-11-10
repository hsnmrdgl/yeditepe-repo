import java.util.Scanner;

public class Content {

	int id;
	String title;
	String artist;
	int length;
	Scanner myScanner = new Scanner(System.in);
	
	public Content(){
		System.out.printf("%s\n", "Enter id");
		id = myScanner.nextInt();
		myScanner.nextLine();
		System.out.printf("%s\n", "Enter title");
		title = myScanner.nextLine();
		System.out.printf("%s\n", "Enter artist");
		artist = myScanner.nextLine();
		System.out.printf("%s\n", "Enter length");
		length = myScanner.nextInt();
		myScanner.nextLine();
	}

	
	public void displayContentInfo() {
		System.out.println("id:"+id+"\n"+ 
				"title:"+title+"\n"+
				"artist:"+artist+"\n"+
				"length(in minutes):"+length+"\n");
	}
	
}