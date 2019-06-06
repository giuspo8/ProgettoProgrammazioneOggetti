package hello;
import java.util.ArrayList;
import java.util.List;

public class DatasetCulturalPractice implements Filter {
	private static ArrayList<CulturalPractice> practices;

	
	@Override
	public String MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}


	public static ArrayList<CulturalPractice> getPractices() {
		return practices;
	}


	public static void setPractices(ArrayList<CulturalPractice> practices) {
		DatasetCulturalPractice.practices = practices;
	}
	

	@Override
	public String toString() {
		return "DatasetCulturalPractice []";
	}


	public Object Find(String value) {
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		for (CulturalPractice c: practices)
		{
			if (value.equals(c.getProponent().getTown().getName()))
					{
						listCulturalPractice.add(c);
					}
		}
		return listCulturalPractice;
	}
	
	
	

}
