import java.util.ArrayList;
import java.util.Scanner;

public class OnlineArticle {
	
	//FIELDS
	private String nameOfArticle;
	private int DOI;
	
	static ArrayList<OnlineArticle> articleAL = new ArrayList<OnlineArticle>(); 
	
	static Scanner scanner = new Scanner(System.in);
	
	//CONSTRUCTOR
	public OnlineArticle() {
		
		System.out.print("Enter Article Name : ");
		String nameOfArticle = scanner.nextLine();
		this.setNameOfArticle(nameOfArticle);
		System.out.print("Enter DOI : ");
		int DOI = scanner.nextInt();
		this.setDOI(DOI);
		
		System.out.print("Online Article Succesfully Created!\n\n");
	}
	
	
	public String toString() {
		return getDOI() + ": " + getNameOfArticle();
	}
	
	
	//METHODS
	public static void addNewArticle(OnlineArticle article) {
		articleAL.add(article);
	}
	
	public static void getAccess(int user) {
    	int id = user;
    	boolean memberflag = true;
    	boolean articleflag = true;
    	
    	for(RegularMember member : LibraryMain.memberAL) {	
    		
    		if(member.getID() == id && member.getOnlineACount() != 0) {
    			System.out.print("Enter DOI of the article : ");
            	int doi = scanner.nextInt();
            	memberflag = false;
	            
    			for(OnlineArticle article : OnlineArticle.articleAL) {
    				if(article.getDOI() == doi) {
    					articleflag = false;
    					
    					member.getAccessedArticles().add(article);
    					member.setOnlineACount(member.getOnlineACount() - 1);
    					System.out.print("Succesfully Accessed!\n\n");
    					break;
    				}
    			}
    			if(articleflag) {
    				System.out.println("There is no accessed article with this DOI");
    			}
            }
    		
    		else if(member.getID() == id && member.getOnlineACount() == 0) {
    			System.out.print("You reached your access limit!\n\n");
    		}
    	}
    	if(memberflag) {
    		System.out.println("There is no member with this ID");
    	}
	}
	
	
	public static void endAccess(int doi, int user) {
		boolean memberflag = true;
    	boolean articleflag = true;
		
		for(RegularMember member : LibraryMain.memberAL) {
			if(member.getID() == user) {
				for(OnlineArticle article : RegularMember.accessedArticles) {
					if(article.getDOI() == doi){
						RegularMember.accessedArticles.remove(article);
						System.out.println("Access has been end.");
					}
				}
				if(articleflag) {
    				System.out.println("There is no article with this DOI");
    			}
				
			}
		}
		if(memberflag) {
    		System.out.println("There is no member with this ID");
    	}
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
}