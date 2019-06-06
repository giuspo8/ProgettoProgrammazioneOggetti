package hello;

public class Town {


	private String name;
	private String province;


	public Town(String name) {
		super();
		this.name = name;
	}

	public Town(String name, String province) {
		this.name = name;
		this.province = province;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Town [name=" + name + ", province=" + province + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Town)
		{
			if (name.equals(((Town) obj).getName())&&province.equals(((Town) obj).getProvince()))
				return true;
			else return false;
		}
		else return super.equals(obj);
	}



}
