
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner myScanner = new Scanner(System.in);
		
		int selection;
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		while (true) {
			System.out.print("(1) Create a new customer.\n" + 
							"(2) Display customer information.\n" + 
							"(0) Exit.\n" + 
								">>");
			
			selection = myScanner.nextInt();
			
			if(selection == 1) {
				Customer newCustomer = new Customer();
				customerList.add(newCustomer);
			}
			
			else if (selection == 2) {
				
				for(Customer customer : customerList) {
		            
					customer.displayInfo();
					System.out.print("\n");
				}
			}
			
			else if (selection == 0) {
				break;
			}
			
			else {
				System.out.print("Please enter valid selection!\n");
			}
		}
	}

}
