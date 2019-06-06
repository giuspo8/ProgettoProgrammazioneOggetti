package hello;
import java.util.ArrayList;

public class CulturalPractice {

	private String title;
	private int number;
	private ArrayList <Institution> partners;
	private Proponent proponent;
	
	public CulturalPractice(String title, int number, ArrayList<Institution> partners, Proponent proponent) {
		super();
		this.title = title;
		this.number = number;
		this.partners = partners;
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


	public ArrayList<Institution> getPartners() {
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
	
	
	
}
