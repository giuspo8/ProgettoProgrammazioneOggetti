import java.util.ArrayList;

public class DatasetProponents implements Filter {

	private ArrayList<Institution> proponents;
	
	public DatasetProponents(ArrayList<Institution> proponents) {
		super();
		this.proponents = proponents;
	}

	
	public ArrayList<Institution> getProponents() {
		return proponents;
	}


	public void setProponents(ArrayList<Institution> proponents) {
		this.proponents = proponents;
	}


	@Override
	public Object MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}

}
