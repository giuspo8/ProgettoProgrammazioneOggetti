
public class Town {
	
	private String name;
	private String province;

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
	
	

}
