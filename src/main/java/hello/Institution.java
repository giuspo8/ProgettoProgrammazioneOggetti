package hello;

public abstract class Institution {
	
	protected String name;

	public Institution(String name) {
		super();
		this.name = name;
	}
	public abstract String getName();
	public abstract void setName(String name);

}
