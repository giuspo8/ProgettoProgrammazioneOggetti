package datasetCulturalPractice;

/**
 * Classe che gestisce gli errori dovuti all'errata formattazione del file csv
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class Error {
	//Attributi
	private String type;//tipo di errore
	private int line;//riga a cui si è verificato l'errore
	
	//Metodi
	/**
	 * crea un nuovo errore 
	 * @param type tipo di errore
	 * @param line riga a cui si e' verificato l'errore
	 */
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
