import java.util.Scanner;

public class Book {
	
	//FIELDS
	private String nameOfBook;
	private int ISBN;
	private Date dueDate;
	
	private static int bookCount;
	
	Scanner scanner = new Scanner(System.in);
	
	//CONSTRUCTOR
	public Book() {
		
		System.out.print("Enter Book Name : ");
		String nameOfBook = scanner.nextLine();
		this.setNameOfBook(nameOfBook);
		System.out.print("Enter ISBN : ");
		int ISBN = scanner.nextInt();
		this.setISBN(ISBN);
		System.out.print("Enter Due Date (day): ");
		int day = scanner.nextInt();
		System.out.print("Enter Due Date (month): ");
		int month = scanner.nextInt();
		System.out.print("Enter Due Date (year): ");
		int year = scanner.nextInt();
		
		dueDate = new Date(day, month, year);
		
		System.out.print("Book Succesfully Created!\n\n");
		bookCount += 1;
	}
	

	
	public String toString() {
		return "Book [Book Name : " + nameOfBook + ", ISBN : " + ISBN + ", Due Date : " + dueDate + "]";
	}


	//GETTER & SETTER
	public String getNameOfBook() {
		return nameOfBook;
	}
	
	public void setNameOfBook(String nameOfBook) {
		this.nameOfBook = nameOfBook;
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public static int getBookCount() {
		return bookCount;
	}

	public static void setBookCount(int bookCount) {
		Book.bookCount = bookCount;
	}
	
}