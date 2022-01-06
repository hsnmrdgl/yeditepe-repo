
public class Words {
	
	private String word, hint, nisc;
	
	
	public Words(String word, String hint, String nisc) {
		this.word = word;
		this.hint = hint;
		this.nisc = nisc;
	}


	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getNisc() {
		return nisc;
	}
	public void setNisc(String nisc) {
		this.nisc = nisc;
	}
}
