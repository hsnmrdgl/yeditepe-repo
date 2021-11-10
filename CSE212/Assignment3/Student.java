
public class Student extends RegularMember{
	
	private int bookCount = 2;
	private int onlineACount = 2;
	
	
	//CONSTRUCTOR
	public Student() {
		super();
	}
	

	@Override
	public void displayInfo() {
		System.out.println("Student [Name : " + this.getReaderName() + ", ID : " + this.getID()
				+", Reserve Left : " + bookCount + ", Access Left : " + onlineACount + "]\n"
				+ "Reserved Books :");
		super.displayBooks();
		System.out.println("Accessed Articles :");
		super.displayArticles();
	}
	
	
	
	//GETTER & SETTER
	public  int getBookCount() {
		return bookCount;
	}

	public  void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public  int getOnlineACount() {
		return onlineACount;
	}

	public void setOnlineACount(int onlineACount) {
		this.onlineACount = onlineACount;
	}
	
}