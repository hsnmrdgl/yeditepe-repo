import java.util.ArrayList;
import java.util.Scanner;

public class RegularMember {
	
	private String readerName;
	private int ID;
	private Book reservedBook;
	private OnlineArticle accessedArticle;
	
	private int bookCount = 1;
	private int onlineACount = 1;
	
	static ArrayList<Book> reservedBooks = new ArrayList<Book>();
	static ArrayList<OnlineArticle> accessedArticles = new ArrayList<OnlineArticle>();
	
	Scanner scanner = new Scanner(System.in);
	
	
	//CONSTRUCTOR
	public RegularMember() {
		
		System.out.print("Enter Name : ");
		String readerName = scanner.nextLine();
		this.setReaderName(readerName);
		System.out.print("Enter ID : ");
		int ID = scanner.nextInt();
		this.setID(ID);
		
		System.out.print("Reader Succesfully Created!\n\n");
	}
	
	
	
	//METHODS
	public void displayInfo() {
		System.out.println("RegularMember [Name : " + readerName + ", ID : " + ID
				+", Reserve Left : " + bookCount + ", Access Left : " + onlineACount + "]\n"
				+ "Reserved Books :");
		displayBooks();
		System.out.println("Accessed Articles :");
		displayArticles();
	}

	public void displayBooks() {
		for(Book book : reservedBooks) {
			if(book == null) continue;
			
			else {
				System.out.println(book);
			}
		}
	}
	
	public void displayArticles() {
		for(OnlineArticle article : accessedArticles) {
			if(article == null) continue;
			
			else {
				System.out.println(article);
			}
		}
	}
	
	

	//GETTER & SETTER
	public String getReaderName() {
		return readerName;
	}
	
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public Book getReservedBook() {
		return reservedBook;
	}
	
	public void setReservedBook(Book reservedBook) {
		this.reservedBook = reservedBook;
	}
	
	public OnlineArticle getAccessedArticle() {
		return accessedArticle;
	}
	
	public void setAccessedArticle(OnlineArticle accessedArticle) {
		this.accessedArticle = accessedArticle;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	
	public int getOnlineACount() {
		return onlineACount;
	}
	
	public void setOnlineACount(int onlineACount) {
		this.onlineACount = onlineACount;
	}

	public ArrayList<Book> getReservedBooks() {
		return reservedBooks;
	}

	public void setReservedBooks(ArrayList<Book> reservedBooks) {
		RegularMember.reservedBooks = reservedBooks;
	}

	public ArrayList<OnlineArticle> getAccessedArticles() {
		return accessedArticles;
	}

	public void setAccessedArticles(ArrayList<OnlineArticle> accessedArticles) {
		RegularMember.accessedArticles = accessedArticles;
	}
	
}