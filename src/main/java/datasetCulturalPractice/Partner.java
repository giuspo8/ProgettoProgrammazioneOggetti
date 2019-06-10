package datasetCulturalPractice;

/**
 * un Partner di una {@link CulturalPractice}
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class Partner extends Institution {

/**
 * crea un nuovo Partner
 * @param name nome del Partner
 */
	public Partner(String name) {
		super(name);
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
		return "Partner [name=" + name + "]";
	}

}
