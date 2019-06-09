package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.List;

public class CulturalPractice {

	private String title;
	private int number;
	private List<Institution> partners;
	private Proponent proponent;
	
	public CulturalPractice(String title, int number, List<Institution> partnersThisPractice, Proponent proponent) {
		super();
		this.title = title;
		this.number = number;
		this.partners = partnersThisPractice;
		this.proponent = proponent;
	}


	boolean IsNumberPartner (int n)
	{
		return true;
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
	
	public String getter(String attribute,String value) {
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
		case "title":
			return getTitle();
		case "partner":
			for (Institution i:getPartners()) {
				if (i.getName().contains(value))
					return value;
			}
			return "Attributo non presente";
		default: return "Attributo non presente";
		}
	}
	
}
