import java.util.ArrayList;
import java.util.HashMap;

public class DatasetTown implements Filter {
	private static ArrayList<Town> towns;

	@Override
	public String toString() {
		return "DatasetTown []";
	}

	@Override
	public Object MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Town> getTowns() {
		return towns;
	}

	public static void setTowns(ArrayList<Town> towns) {
		DatasetTown.towns = towns;
	} 


}
