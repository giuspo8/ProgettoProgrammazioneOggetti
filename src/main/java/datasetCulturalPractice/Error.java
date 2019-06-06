package datasetCulturalPractice;

public class Error {
	private String type;
	private int line;
	
	public Error(String type, int line) {
		super();
		this.type = type;
		this.line = line;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}

}
