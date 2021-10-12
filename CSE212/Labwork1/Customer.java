import java.util.Scanner;

public class Customer {
	
	String id;
	String name;
	String age;
	
	public Customer() {
		
		Scanner myScanner = new Scanner(System.in);
		
		System.out.print("Enter ID : ");
		id = myScanner.nextLine();
		System.out.print("Enter Name : ");
		name = myScanner.nextLine();
		System.out.print("Enter Age : ");
		age = myScanner.nextLine();
	}
	
	public void displayInfo() {

		System.out.println("ID : " + id );
		System.out.println("Name : " + name );
		System.out.println("Age : " + age );
		
	}
	
}
