package hello;
import java.util.ArrayList;

public class DatasetProponents implements Filter {

	private static ArrayList<Institution> proponents;
	
	
	@Override
	public String toString() {
		return "DatasetProponents []";
	}


	public static ArrayList<Institution> getProponents() {
		return proponents;
	}


	public static void setProponents(ArrayList<Institution> proponents) {
		DatasetProponents.proponents = proponents;
	}


	@Override
	public Object MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}

}
