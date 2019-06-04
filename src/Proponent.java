
public class Proponent extends Institution {

	private String site;
	private Town town;
	
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
		return super.name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Proponent [site=" + site + ", town=" + town + ", name=" + name + "]";
	}
	

}
