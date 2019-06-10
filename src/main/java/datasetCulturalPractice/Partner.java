package datasetCulturalPractice;

public class Partner extends Institution {


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
