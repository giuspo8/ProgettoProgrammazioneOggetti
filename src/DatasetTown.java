import java.util.ArrayList;
import java.util.HashMap;

public class DatasetTown implements Filter {
	private ArrayList<Town> towns;

	
	public DatasetTown(ArrayList<Town> towns) {
		super();
		this.towns = towns;
	}

	@Override
	public Object MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Town> getTowns() {
		return towns;
	}

	public void setTowns(ArrayList<Town> towns) {
		this.towns = towns;
	} 


}
