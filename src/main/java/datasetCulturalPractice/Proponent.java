package datasetCulturalPractice;

/**
 * Un soggetto proponente di una {@link CulturalPractice}
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class Proponent extends Institution {
	//Attributi
	private String site;
	private Town town;

	//Metodi
	/**
	 * crea un nuovo soggetto proponente
	 * @param name nome del soggetto proponente
	 * @param site sito del soggetto proponente
	 * @param town citta' del soggetto proponente
	 */
	public Proponent(String name, String site, Town town) {
		super(name);
		this.site = site;
		this.town = town;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	@Override
	public String getName() {
		return super.name;
	}

	@Override
	public void setName(String name) {
		super.name=name;
	}

	@Override
	public String toString() {
		return "Proponent [site=" + site + ", town=" + town + ", name=" + name + "]";
	}



}
