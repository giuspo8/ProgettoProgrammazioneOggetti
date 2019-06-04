import java.util.ArrayList;

public class CulturalPractice {

	String title;
	int number;
	ArrayList <Institution> partners;
	Institution proponent;
	
	public CulturalPractice(String title, int number, ArrayList<Institution> partners, Institution proponent) {
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


	public Institution getProponent() {
		return proponent;
	}


	public void setProponent(Institution proponent) {
		this.proponent = proponent;
	}
}
