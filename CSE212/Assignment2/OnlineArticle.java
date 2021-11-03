import java.util.Scanner;

public class OnlineArticle {
	
	//FIELDS
	private String nameOfArticle;
	private int DOI;
	
	private static int articleCount; 
	
	Scanner scanner = new Scanner(System.in);
	
	//CONSTRUCTOR
	public OnlineArticle() {
		
		System.out.print("Enter Article Name : ");
		String nameOfArticle = scanner.nextLine();
		this.setNameOfArticle(nameOfArticle);
		System.out.print("Enter DOI : ");
		int DOI = scanner.nextInt();
		this.setDOI(DOI);
		
		System.out.print("Online Article Succesfully Created!\n\n");
		articleCount += 1;
	}
	
	
	public String toString() {
		return "OnlineArticle [Article Name : " + nameOfArticle + ", DOI :" + DOI + "]";
	}


	//GETTER & SETTER
	public String getNameOfArticle() {
		return nameOfArticle;
	}

	public void setNameOfArticle(String nameOfArticle) {
		this.nameOfArticle = nameOfArticle;
	}

	public int getDOI() {
		return DOI;
	}

	public void setDOI(int doi) {
		DOI = doi;
	}

	public static int getArticleCount() {
		return articleCount;
	}

	public static void setArticleCount(int articleCount) {
		OnlineArticle.articleCount = articleCount;
	}
	
}