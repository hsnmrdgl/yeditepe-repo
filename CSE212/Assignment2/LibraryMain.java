import java.util.Scanner;


public class LibraryMain {
	
	//OBJECT ARRAYS
	static RegularMember[] memberArray = new RegularMember[10];	
	static Book[] bookArray = new Book[10];
	static OnlineArticle[] articleArray = new OnlineArticle[10];
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean loop = true;
		
		while (loop) {
            	
			System.out.print("(1) Add a new Book\n" 
							+ "(2) Add a new Online Article\n"
							+ "(3) Create a Member Account\n"
							+ "(4) Reserve a Book\n"
							+ "(5) Get access to an Online Article\n"
							+ "(6) Display all Accounts\n"
							+ "(7) EXIT !\n");
			
			int choice = scanner.nextInt();
			
        	switch (choice){
        		case 1:
                    //ADD NEW BOOK
                    addNewBook();
                    break;

        		case 2:
                    //ADD NEW ONLINE ARTICLE
                	addNewArticle();
                    break;

                case 3:
                	//CREATE MEMBER ACCOUNT
                	createMember();
                    break;
                    
                case 4:
                    //RESERVE BOOK
                	reserveBook();
                 	break;

                case 5:
                    //GET ACCESS TO ONLINE ARTICLE
                	getAccess();
                    break;
 
                case 6:
                    //DISPLAY ALL ACCOUNTS
	            	displayAll();
	            	break;
                	
                case 7:
                	//EXIT
                    System.out.print("Terminated...");
                    loop = false;
                    break;

                default:
                    System.out.print("Please select valid option!\n\n");
            }
        }
	}	
	

	//NEW BOOK METHOD
	public static void addNewBook() {
		int bookCount = Book.getBookCount();
		bookArray[bookCount] = new Book();
	}
	
	
	//NEW ARTICLE METHOD
	public static void addNewArticle() {
		int articleCount = OnlineArticle.getArticleCount();
		articleArray[articleCount] = new OnlineArticle();
	}
	
	
	//NEW READER METHOD
	public static void createMember() {
		int selection;
		
		System.out.print("1. Create a regular member account\n"
						+ "2. Create a student member account\n"
						+ "3. Create an academic member account\n");
		
		selection = scanner.nextInt();
		if(selection == 1) {
			int memberCount = RegularMember.getMemberCount();
			memberArray[memberCount] = new RegularMember();
		}
		
		else if(selection == 2) {
			int memberCount = RegularMember.getMemberCount();
			memberArray[memberCount] = new Student();
		}
		
		else if(selection == 3) {
			int memberCount = RegularMember.getMemberCount();
			memberArray[memberCount] = new Academic();
		}
		
		else {
			System.out.print("Please select valid option!\n\n");
		}
	}
	
	
	//RESERVE BOOK METHOD
	public static void reserveBook() {
		System.out.print("Enter your reader ID : ");
    	int id = scanner.nextInt();
    	
    	for(int i=0; i<RegularMember.getMemberCount(); i++) {	
    		
    		if(memberArray[i].getID() == id && memberArray[i].getBookCount() != 0) {
    			System.out.print("Enter ISBN of the book : ");
            	int isbn = scanner.nextInt();
	            
    			for(int j=0; j<Book.getBookCount(); j++) {
    				if(bookArray[j].getISBN() == isbn) {
    					memberArray[i].setReservedBook(bookArray[j]);
    					memberArray[i].setBookCount(memberArray[i].getBookCount() - 1);
    					System.out.print(memberArray[i].getReservedBook()+ "\n");
    					System.out.print("Succesfully Reserved!\n\n");
    					break;
    				}
    			}	
            }
    		
    		else if(memberArray[i].getID() == id && memberArray[i].getBookCount() == 0) {
    			System.out.print("You reached your access limit!\n\n");
    		}
    	}	
    }
	
	
	//GET ACCESS ONLINE ARTICLE
	public static void getAccess() {
		System.out.print("Enter your reader ID : ");
    	int id = scanner.nextInt();
    	
    	for(int i=0; i<RegularMember.getMemberCount(); i++) {	
    		
    		if(memberArray[i].getID() == id && memberArray[i].getOnlineACount() != 0) {
    			System.out.print("Enter DOI of the article : ");
            	int doi = scanner.nextInt();
	            
    			for(int j=0; j<OnlineArticle.getArticleCount(); j++) {
    				if(articleArray[j].getDOI() == doi) {
    					memberArray[i].setAccessedArticle(articleArray[j]);
    					memberArray[i].setOnlineACount(memberArray[i].getOnlineACount() - 1);
    					System.out.print(memberArray[i].getAccessedArticle() + "\n");
    					System.out.print("Succesfully Accessed!\n\n");
    					break;
    				}
    			}
            }
    		
    		else if(memberArray[i].getID() == id && memberArray[i].getOnlineACount() == 0) {
    			System.out.print("You reached your access limit!\n\n");
    		}
    	}
	}
	
	
	//DISPLAY ALL MEMBER'S INFO
	public static void displayAll() {
		
		for(int i=0; i<RegularMember.getMemberCount(); i++) {
			System.out.println(memberArray[i].displayInfo());
		}
		System.out.print("\n");
		
	}	
}