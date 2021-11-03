
public class Student extends RegularMember{
	
	private int bookCount = 2;
	private int onlineACount = 2;
	
	
	//CONSTRUCTOR
	public Student() {
		super();
	}
	

	@Override
	public String displayInfo() {
		return "Student [Name : " + readerName + ", ID : " + ID
				+", Reserve Left : " + bookCount + ", Access Left : " + onlineACount
				+ "]";
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