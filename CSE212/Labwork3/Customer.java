import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

	int id;
	String name;
	int age;
	protected int kota;
	protected ArrayList<Content> cont;
	int contCount=0;
	Scanner mySc;

	public Customer() {
		mySc = new Scanner(System.in);
		System.out.println("Enter id");
		id = mySc.nextInt();
		mySc.nextLine();
		System.out.println("Enter name");
		name = mySc.nextLine();
		System.out.println("Enter age");
		age = mySc.nextInt();
		mySc.nextLine();
		kota=1;
		cont = new ArrayList<Content>();
		
	}
	public void buyAContent() {
		if(contCount < kota) {
			cont.add(new Content());
			System.out.println("You have purchased below content:");
			cont.get(contCount).displayContentInfo();
			contCount++;
		}
		
		else
			System.out.println("You cant purchase anymore content!");

	}
	
	public void displayInfo() {
		String type="";
		if(kota==1)
			type="Regular Customer";
		else if(kota==2)
			type="Silver Customer";
		else if(kota==3) {
			type="Gold Customer";
		}
		
		System.out.println("id:"+id+"\n"
					+"name:"+ name +"\n"
					+"age:"+ age +"\n"
					+"type: "+ type +"\n");
		
	}
	
}

