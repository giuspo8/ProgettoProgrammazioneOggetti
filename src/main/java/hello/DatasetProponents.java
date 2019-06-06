package hello;
import java.util.ArrayList;

public class DatasetProponents implements Filter {

	private static ArrayList<Proponent> proponents;
	
	
	@Override
	public String toString() {
		return "DatasetProponents []";
	}


	public static ArrayList<Proponent> getProponents() {
		return proponents;
	}


	public static void setProponents(ArrayList<Proponent> proponents2) {
		DatasetProponents.proponents = proponents2;
	}


	@Override
	public String MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object Find(String value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
