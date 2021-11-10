import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Book {
	
	//FIELDS
	private String nameOfBook;
	private String ISBN;
	private Date dueDate;
		
	static ArrayList<Book> bookAL = new ArrayList<Book>(); 
	
	static Scanner scanner = new Scanner(System.in);
	
	//CONSTRUCTOR
	public Book() {
		
		System.out.print("Enter Book Name : ");
		String nameOfBook = scanner.nextLine();
		this.setNameOfBook(nameOfBook);
		System.out.print("Enter ISBN : ");
		String ISBN = scanner.nextLine();
		this.setISBN(ISBN);
		
		System.out.print("Book Succesfully Created!\n\n");
	}
	

	
	public String toString() {
		return getISBN() + ": " + getNameOfBook() + " (Due: " + dueDate + ")";
	}

	
	//METHODS
	public static void addNewBook(Book book) {
		bookAL.add(book);
	}
	
	public static void reserveBook(int user) {
		int id = user;
    	boolean memberflag = true;
    	boolean bookflag = true;
    	
    	for(RegularMember member : LibraryMain.memberAL) {	
    		
    		if(member.getID() == id && member.getBookCount() != 0) {
    			System.out.print("Enter ISBN of the book : ");
            	String isbn = scanner.nextLine();
            	memberflag = false;
            	
    			for(Book book : Book.getBookAL()) {
    				if(book.getISBN().equals(isbn)) {
    					bookflag = false;
    					
    					System.out.print("Enter day: ");
    					int day = scanner.nextInt();
    					System.out.print("Enter month: ");
    					int month = scanner.nextInt();
    					System.out.print("Enter year: ");
    					int year = scanner.nextInt();
    					
    					book.setDueDate(new Date(day, month, year));
    					
    					member.getReservedBooks().add(book);
    					
    					member.setBookCount(member.getBookCount() - 1);
    					System.out.print("Succesfully Reserved!\n\n");
    					break;
    				}
    			}
    			if(bookflag) {
    				System.out.println("There is no book with this ISBN");
    			}
            }
    		
    		else if(member.getID() == id && member.getBookCount() == 0) {
    			System.out.print("You reached your access limit!\n\n");
    			memberflag = true;
    		}
    	}
    	
    	if(memberflag) {
    		System.out.println("There is no member with this ID");
    	}
		
	}
	
	public static void returnBook(String isbn, int user) {
		
		boolean memberflag = true;
    	boolean bookflag = true;
		
    	Iterator<Book> bookiter = RegularMember.reservedBooks.iterator();
    	Iterator<RegularMember> memberiter = LibraryMain.memberAL.iterator();
    	
		while(memberiter.hasNext()) {
			if(memberiter.next().getID() == user) {
				memberflag = false;
				while(bookiter.hasNext()) {
					if(isbn.equals(bookiter.next().getISBN())){
						bookflag = false;
						RegularMember.reservedBooks.remove(bookiter.next());
						System.out.println("Book has been returned.");
					}
				}
				if(bookflag) {
    				System.out.println("There is no reserved book with this ISBN");
    			}
				
			}
		}
		if(memberflag) {
    		System.out.println("There is no member with this ID");
    	}
	}
	
	
	//GETTER & SETTER
	public String getNameOfBook() {
		return nameOfBook;
	}
	
	public void setNameOfBook(String nameOfBook) {
		this.nameOfBook = nameOfBook;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String iSBN2) {
		this.ISBN = iSBN2;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public static ArrayList<Book> getBookAL() {
		return bookAL;
	}

	public void setBookAL(ArrayList<Book> bookAL) {
		Book.bookAL = bookAL;
	}
}