import java.util.Scanner;

public class CustomerTest {
	
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Customer customerArray[] = new Customer[10];
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("(1) Create a new customer.\r\n" + 
						"(2) Create a new customer with an ID.\r\n" + 
						"(3) Display all customers.\r\n" + 
						"(0) Exit.");
			
			int input = scanner.nextInt();
			
			if(input == 1) {
				Customer newCustomer = new Customer();
				int count = Customer.getCount();
				customerArray[count] = newCustomer;
			}
			
			else if(input == 2) {
				System.out.print("Enter ID : ");
				int id = scanner.nextInt();
				Customer newCustomer = new Customer(id);
				int count = Customer.getCount();
				customerArray[count] = newCustomer;
			}
			
			else if(input == 3) {
				
				for(Customer customer : customerArray) {
					
					if(customer == null) {
						continue;
					}
					System.out.println(customer);
				}
			}
			
			else if(input == 0) {
				break;
			}
			
			else {
				System.out.print("Please select valid option!\n");
			}
			
		}
		
		scanner.close();
	}

}
