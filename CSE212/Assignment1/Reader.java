import java.util.Scanner;

public class Reader {
	
	private String readerName;
	private String ID;
	private Book reservedBook;
	private OnlineArticle accessedArticle;
	
	private static int readerCount;
	
	Scanner scanner = new Scanner(System.in);
	
	//CONSTRUCTOR
	public Reader() {
		
		System.out.print("Enter Name : ");
		String readerName = scanner.nextLine();
		this.setReaderName(readerName);
		System.out.print("Enter ID : ");
		String ID = scanner.nextLine();
		this.setID(ID);
		
		System.out.print("Reader Succesfully Created!\n");
		readerCount += 1;
	}
	
	
	
	//GETTER & SETTER
	public String getReaderName() {
		return readerName;
	}
	
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
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

	public static int getReaderCount() {
		return readerCount;
	}

	public static void setReaderCount(int readerCount) {
		Reader.readerCount = readerCount;
	}	
	
}