
public class Academic extends RegularMember{
	
	private int bookCount = 3;
	private int onlineACount = 3;
	
	
	//CONSTRUCTOR
	public Academic() {
		super();
	}

	
	@Override
	public void displayInfo() {
		System.out.println("Academic [Name : " + this.getReaderName() + ", ID : " + this.getID()
		+", Reserve Left : " + bookCount + ", Access Left : " + onlineACount + "]\n"
		+ "Reserved Books :");
		super.displayBooks();
		System.out.println("Accessed Articles :");
		super.displayArticles();
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