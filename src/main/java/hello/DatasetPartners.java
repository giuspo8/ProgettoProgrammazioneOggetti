package hello;
import java.util.ArrayList;

public class DatasetPartners implements Filter {

	private static ArrayList<Institution> partners;
	

	@Override
	public String toString() {
		return "DatasetPartners []";
	}

	@Override
	public String MostFrequently(String choice) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Institution> getPartners() {
		return partners;
	}

	public static void setPartners(ArrayList<Institution> partners) {
		DatasetPartners.partners = partners;
	}

	public Object Find(String value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
