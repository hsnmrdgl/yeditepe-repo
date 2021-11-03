
public class Academic extends RegularMember{
	
	private int bookCount = 3;
	private int onlineACount = 3;
	
	
	//CONSTRUCTOR
	public Academic() {
		super();
	}

	
	@Override
	public String displayInfo() {
		return "Academic [Name : " + readerName + ", ID : " + ID
				+", Reserve Left : " + bookCount + ", Access Left : " + onlineACount
				+ "]";
	}


	
	//GETTER & SETTER
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