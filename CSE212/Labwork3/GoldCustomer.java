import java.util.ArrayList;

public class GoldCustomer extends Customer {
	
	
	GoldCustomer()	{
		super();
		kota=3;
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
	
	
	public void displayInfo(){
		super.displayInfo();
	}
	
}
