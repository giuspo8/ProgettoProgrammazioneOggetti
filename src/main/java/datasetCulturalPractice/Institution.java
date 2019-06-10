package datasetCulturalPractice;

/**
 * Classe Astratta che rappresenta tutti gli enti presenti nel dataset
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public abstract class Institution {
	//Attributi
	protected String name;
	//Metodi
	/**
	 * crea un oggetto di tipo Institution
	 * @param name nome dell'ente
	 */
	public Institution(String name) {
		super();
		this.name = name;
	}
	public abstract String getName();
	public abstract void setName(String name);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Institution other = (Institution) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
