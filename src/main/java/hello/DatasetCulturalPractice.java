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


	public Object Find(String... params) {
		int total = params.length;
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		for (CulturalPractice c: practices)
		{

			for (int i=0;i<total;i++)
			{
				if (params[i].equals(c.getProponent().getTown().getName()))
				{
					listCulturalPractice.add(c);
				}
			}

		}
		return listCulturalPractice;
	}

	public Object Find (int... numbers) {
		if (numbers.length!=2)
		{
			return "il numero di parametri può essere solo 1 o 2";
		}
		else if (numbers[0]>numbers[1]) {
			return "ERRORE! il primo parametro deve essere minore o uguale al secondo";
		}
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		for (CulturalPractice c: practices)
		{
			if (c.getNumber()<=numbers[1]&&c.getNumber()>=numbers[0]) 
			{
				listCulturalPractice.add(c);
			}
		}
		return listCulturalPractice;
	}

	public Object Find (String type, int number) {
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		if (type.equals(">")||type.equals("greater"))
		{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()>=number) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}
		else if (type.equals("<")||type.equals("smaller"))
		{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()<=number) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}
		else return "Operazione non valida";
	}

	public Object Find (Institution partner) {
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		for (CulturalPractice c: practices)
		{
			for (Institution i:c.getPartners()) {
				if (partner.getName().contains(i.getName())) 
				{
					listCulturalPractice.add(c);
				}
			}
		}
		return listCulturalPractice;
	}








}
