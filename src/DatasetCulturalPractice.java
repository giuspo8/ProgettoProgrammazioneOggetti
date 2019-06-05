import java.util.ArrayList;

public class DatasetCulturalPractice implements Filter {
	private static ArrayList<CulturalPractice> practices;

	
	@Override
	public Object MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}


	public static ArrayList<CulturalPractice> getPractices() {
		return practices;
	}


	public static void setPractices(ArrayList<CulturalPractice> practices) {
		DatasetCulturalPractice.practices = practices;
	}
	
	public static int prova() {
		return 1;
	}


	@Override
	public String toString() {
		return "DatasetCulturalPractice []";
	}
	
	
	

}
