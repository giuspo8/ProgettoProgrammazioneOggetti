package hello;
import java.util.ArrayList;
import java.util.HashMap;

public class DatasetTown implements Filter {
	private static ArrayList<Town> towns;

	@Override
	public String toString() {
		return "DatasetTown []";
	}

	@Override
	public String MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Town> getTowns() {
		return towns;
	}

	public static void setTowns(ArrayList<Town> towns) {
		DatasetTown.towns = towns;
	}

	public Object Find(String value) {
		// TODO Auto-generated method stub
		return null;
	} 


}
