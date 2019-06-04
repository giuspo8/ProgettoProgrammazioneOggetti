
public class Proponent extends Institution {

	String site;
	Town town;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

}
