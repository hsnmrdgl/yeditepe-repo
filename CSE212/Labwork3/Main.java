import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int choice=0;
		int option=0;
		int a=0;
		
		int inList = 0;
		
		ArrayList <Customer> customerAList = new ArrayList<Customer>();
		
		while(true) {
			System.out.printf("%s\n","(1) Create a new customer.\n");
			System.out.printf("%s\n","(2) Display customer information.\n");
			System.out.printf("%s\n","(3) Buy a content.\n");
			System.out.printf("%s\n","(0) Exit\n");
			
			Scanner sc = new Scanner(System.in);
			choice= sc.nextInt();
			if(choice==1) {
				System.out.println("1.Create regular customer\n"
								+"2.Create silver customer\n"
								+"3.Create gold customer\n");
				option=sc.nextInt();
				if(option==1) 
					customerAList.add(new Customer());
				
				else if(option==2) {
					customerAList.add(new SilverCustomer());
				}
				
				else if(option==3) {
					customerAList.add(new GoldCustomer());
				}
				else {
					System.out.println("Select Valid Option!");
				}
			}
			
			else if(choice==2) {
				for(Customer customer : customerAList) {
					customer.displayInfo();
				}	
			}
			
			else if(choice==3) {
				System.out.println("Enter your id:");
				int id = sc.nextInt();
				
				
				for(Customer customer : customerAList) {
					if(id == customer.id) {
						customer.buyAContent();
						inList = 1;
					}
				}
				if (inList != 1) {
					System.out.println("There is no user!");
				}
			}
			
			else if(choice==0) {
				break;
			}
			
			else {
				System.out.println("Select Valid Option!");
			}
		}
	}

}
