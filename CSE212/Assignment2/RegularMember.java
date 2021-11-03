import java.util.Scanner;

public class RegularMember {
	
	protected String readerName;
	protected int ID;
	protected Book reservedBook;
	protected OnlineArticle accessedArticle;
	
	protected int bookCount = 1;
	protected int onlineACount = 1;
	
	protected static int memberCount;
	
	
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
		memberCount += 1;
	}
	
	
	

	public String displayInfo() {
		return "RegularMember [Name : " + readerName + ", ID : " + ID
				+", Reserve Left : " + bookCount + ", Access Left : " + onlineACount
				+ "]";
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

	public static int getMemberCount() {
		return memberCount;
	}

	public static void setMemberCount(int memberCount) {
		RegularMember.memberCount = memberCount;
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
	
}