import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class LibraryMain {
	
	static Scanner scanner = new Scanner(System.in);
	
	static ArrayList<RegularMember> memberAL = new ArrayList<RegularMember>();
	
	public static void main(String[] args) {
		
		boolean loop = true;
		int selectedUser = 0;
		
		while (loop) {
            
			System.out.print( "(1)  Add a new Book\n" 
							+ "(2)  Add a new Online Article\n"
							+ "(3)  Create a Member Account\n"
							+ "(4)  Select User to Process\n"
							+ "(5)  Reserve a Book\n"
							+ "(6)  Return a Book\n"
							+ "(7)  Get access to an Online Article\n"
							+ "(8)  End an Online Article access\n"
							+ "(9)  Display all Accounts\n"
							+ "(10) EXIT !\n");
			
			int choice = scanner.nextInt();
			
        	switch (choice){
        		case 1:
                    //ADD NEW BOOK
        			Book.addNewBook(new Book());
                    break;

        		case 2:
                    //ADD NEW ONLINE ARTICLE
        			OnlineArticle.addNewArticle(new OnlineArticle());
                    break;

                case 3:
                	//CREATE MEMBER ACCOUNT
                	createMember();
                    break;
                
                case 4:
                	//SELECT USER
                	selectedUser = selectUser();
                	break;
                
                case 5:
                    //RESERVE BOOK
                	if(selectedUser != 0) {
                    	Book.reserveBook(selectedUser);
                     	break;
                	}
                	else {
                		System.out.println("Select a user first!");
                		break;
                	}
                
                case 6:
                	//RETURN BOOK
                	returnBookMain(selectedUser);
                	break;
                	
                case 7:
                    //GET ACCESS TO ONLINE ARTICLE
                	if(selectedUser != 0) {
                		OnlineArticle.getAccess(selectedUser);
                     	break;
                	}
                	else {
                		System.out.println("Select a user first!");
                		break;
                	}
                
                case 8:
                	//END ACCESS ONLINE ARTICLE
                	endAccessMain(selectedUser);
                	break;
                	
                case 9:
                    //DISPLAY ALL ACCOUNTS
	            	displayAll();
	            	break;
                	
                case 10:
                	//EXIT
                    System.out.print("Terminated...");
                    loop = false;
                    break;

                default:
                    System.out.print("Please select valid option!\n\n");
            }
        }
	}
	
	
	
	//NEW READER METHOD
	public static void createMember() {
		int selection;
		
		System.out.print("1. Create a regular member account\n"
						+ "2. Create a student member account\n"
						+ "3. Create an academic member account\n");
		
		selection = scanner.nextInt();
		if(selection == 1) {
			LibraryMain.memberAL.add(new RegularMember());
		}
		
		else if(selection == 2) {
			LibraryMain.memberAL.add(new Student());
		}
		
		else if(selection == 3) {
			LibraryMain.memberAL.add(new Academic());
		}
		
		else {
			System.out.print("Please select valid option!\n\n");
		}
	}
	
	
	//SELECT USER METHOD
	public static int selectUser()	{
		System.out.println("Enter you member ID : ");
		int id = scanner.nextInt();
		int user = 0;
		
		boolean flag = true;
		
		Iterator<RegularMember> iter = LibraryMain.memberAL.iterator();
		
		while (iter.hasNext()) {
	    	if(id == iter.next().getID()) {
	    		user = id;
	    		flag = false;
	    		
	    	}
	    }
	    if(flag) {
	    	System.out.println("There is no member with this ID");
	    }
	    
	    return user;
	}
	
	
	
	//RETURN BOOK
	public static void returnBookMain(int user) {
		String isbn = scanner.nextLine();
		isbn = "";
		
		if(isbn.isEmpty()) {
			System.out.print("Enter the ISBN of the book you want to return : ");
			isbn = scanner.nextLine();
		}
		
		Book.returnBook(isbn, user);

	}
	
	
	//END ACCESS ONLINE ARTICLE
	public static void endAccessMain(int user)	{
		System.out.println("Enter the DOI of the article you want to end access : ");
		int doi = scanner.nextInt();
		
		OnlineArticle.endAccess(doi, user);
	}
	
	
	//DISPLAY ALL MEMBER'S INFO
	public static void displayAll() {
		
		Iterator<RegularMember> iter = LibraryMain.memberAL.iterator();
		
	    while (iter.hasNext()) {
	        iter.next().displayInfo();
	    }
		System.out.println("");
	}	
}