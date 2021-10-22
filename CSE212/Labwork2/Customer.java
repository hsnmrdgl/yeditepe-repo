import java.util.Scanner;

public class Customer {
	
	//FIELDS
	private String name;
	private int age;
	private int ID;

	private static int count=0;
	
	
	Scanner scanner = new Scanner(System.in);
	
	//CONSTRUCTORS
	public Customer() {
		System.out.print("Enter name :");
		name = scanner.nextLine();
		System.out.print("Enter age :");
		age = scanner.nextInt();
		ID = count;
		count++;
	}
	
	public Customer(int id) {
		System.out.print("Enter name :");
		name = scanner.nextLine();
		System.out.print("Enter age :");
		age = scanner.nextInt();
		ID = id;
		count++;
	}
	
	
	
	@Override
	public String toString() {
		return "ID : " + ID + "\nName : " + name + "\nAge : " + age + "\n";
	}

	
	//GETTER & SETTER
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Customer.count = count;
	}
	
}
