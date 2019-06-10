package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta la singola pratica culturale
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class CulturalPractice {

	//Attributi
	
	private String title;//titolo della buona pratica culturale
	private int number;//numero ordine arrivo domanda
	private List<Institution> partners;//lista di partners associati alla buona pratica culturale
	private Proponent proponent;//soggetti proponenti associati alla buona pratica culturale
	
	//Metodi
	
	/**
	 * crea una nuova pratica Culturale
	 * @param title titolo della buona pratica culturale
	 * @param number numero ordine arrivo domanda
	 * @param partnersThisPractice lista di {@link Institution} associati alla buona pratica culturale
	 * @param proponent soggetti proponenti associati alla buona pratica culturale
	 */
	public CulturalPractice(String title, int number, List<Institution> partnersThisPractice, Proponent proponent) {
		super();
		this.title = title;
		this.number = number;
		this.partners = partnersThisPractice;
		this.proponent = proponent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Institution> getPartners() {
		return partners;
	}

	public void setPartners(ArrayList<Institution> partners) {
		this.partners = partners;
	}

	public Proponent getProponent() {
		return proponent;
	}

	public void setProponent(Proponent proponent) {
		this.proponent = proponent;
	}

	@Override
	public String toString() {
		return "CulturalPractice [title=" + title + ", number=" + number + ", partners=" + partners + ", proponent="
				+ proponent + "]";
	}
	
	/**
	 * 
	 * @param attribute Stringa che contiene l'attributo di cui si richiede il valore
	 * @param value rappresenta il valore con cui deve essere confrontato l'attributo <strong>NOTA</strong> viene utilizzato effettivamente solo in caso di attribute.equals("partner")
	 * @return il valore dell'attributo richiesto, oppure nel caso di partner il valore da confrontare in caso di esito di ricerca positiva oppure un messaggio di errore
	 * @throws WrongAttributeException quando {@link attribute} non corrisponde a nessuno degli attributi presenti
	 */
	public String getter(String attribute,String value) throws WrongAttributeException {
		switch (attribute) {
		case "town":
			return getProponent().getTown().getName();
		case "province":
			return getProponent().getTown().getProvince();
		case "practice":
			return getTitle();
		case "proponents":
			return getProponent().getName();
		case "site":
			return getProponent().getSite();
		case "partner":
			for (Institution i:getPartners()) {
				if (i.getName().contains(value))
					return value;
			}
			return "Attributo non presente";
		default: throw new WrongAttributeException();
		}
	}
	
}
