import java.util.Scanner;

//ENUM CLASS
enum MenuOptions{
	
	A("(1) Add a new book"),
	B("(2) Add a new Online Article"),
	C("(3) Create a Reader account"),
	D("(4) Reserve a book"),
	E("(5) Get access to an online article"),
	F("(6) Exit!");
	
	private String message;
  
	//CONSTRUCTOR
	MenuOptions(String message){
		this.message = message;
	}
	  
	//GETTER
	public String getMessage(){
		return message;
	}
}


public class LibraryMain {
	
	//OBJECT ARRAYS
	static Reader[] readerArray = new Reader[10];	
	static Book[] bookArray = new Book[10];
	static OnlineArticle[] articleArray = new OnlineArticle[10];
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		MenuOptions choice = null;
				
		while (choice != MenuOptions.F) {
			try{
				
				for (MenuOptions menu: MenuOptions.values()){			      
					System.out.print(menu.getMessage() + "\n");
			    }
				
            	choice = MenuOptions.values()[Integer.parseInt(scanner.nextLine().trim()) - 1];

            	switch (choice){
                    
            		//CHOICE #1
            		case A:
                        //ADD NEW BOOK
                        addNewBook();
                        break;
                    
                    //CHOICE #2
            		case B:
                        //ADD NEW ONLINE ARTICLE
                    	addNewArticle();
                        break;
                    
                    //CHOICE #3
                    case C:
                    	//CREATE READER ACCOUNT
                    	createReader();
                        break;
                    
                    //CHOICE #4
                    case D:
                        //RESERVE BOOK
                    	reserveBook();
                     	break;
                    
                    //CHOICE #5
                    case E:
                        //GET ACCESS TO ONLINE ARTICLE
                    	getAccess();
                        break;
                  
                    //CHOICE #6
                    case F:
                    	//EXIT
                        System.out.print("Terminated...");
                        break;

                    default:
                        System.out.print("Please select valid option!\n");
                }
            }
		
            catch (IllegalArgumentException e){
            	
                System.out.print("[!] An error occured.\n");
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
	public static void createReader() {
		int readerCount = Reader.getReaderCount();
		readerArray[readerCount] = new Reader();
	}
	
	//RESERVE BOOK METHOD
	public static void reserveBook() {
		System.out.print("Enter your reader ID : ");
    	String id = scanner.nextLine();
    	
    	for(int i=0; i<Reader.getReaderCount(); i++) {	
    		
    		if(readerArray[i].getID().equals(id)) {
    			System.out.print("Enter ISBN of the book : ");
            	int isbn = scanner.nextInt();
	            
    			for(int j=0; j<Book.getBookCount(); j++) {
    				if(bookArray[j].getISBN() == isbn) {
    					readerArray[i].setReservedBook(bookArray[j]);
    					System.out.print(readerArray[i].getReservedBook()+ "\n");
    					System.out.print("Succesfully Reserved!\n");
    					break;
    				}
    			}
            }
    	}	
    }
	
	//GET ACCESS ONLINE ARTICLE
	public static void getAccess() {
		System.out.print("Enter your reader ID : ");
    	String id = scanner.nextLine();
    	
    	for(int i=0; i<Reader.getReaderCount(); i++) {	
    		
    		if(readerArray[i].getID().equals(id)) {
    			System.out.print("Enter DOI of the article : ");
            	int doi = scanner.nextInt();
	            
    			for(int j=0; j<OnlineArticle.getArticleCount(); j++) {
    				if(articleArray[j].getDOI() == doi) {
    					readerArray[i].setAccessedArticle(articleArray[j]);
    					System.out.print(readerArray[i].getAccessedArticle() + "\n");
    					System.out.print("Succesfully Accessed!\n");
    					break;
    				}
    			}
            }
    	}
	}
	
}