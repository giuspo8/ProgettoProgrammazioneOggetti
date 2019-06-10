package datasetCulturalPractice;

/**
 * Classe che ci permette di gestire un oggetto di tipo Metadata
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class Metadata {
	//Attributi
	//abbiamo un oggetto di tipo metadata per ogni attributo(colonna) del nostro dataset
	private String alias;
	private String sourceField;
	private String type;
	//Metodi
	/**
	 * Crea un oggetto Metadata
	 * @param alias nome dato da noi all'attributo e che viene utilizzato per le richieste REST 
	 * @param sourceField nome assegnato all'attributo nel dataset
	 * @param type tipo dell'attributo
	 */
	public Metadata(String alias, String sourceField, String type) {
		super();
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSourceField() {
		return sourceField;
	}
	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
