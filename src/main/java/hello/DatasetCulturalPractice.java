package hello;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatasetCulturalPractice implements Filter {
	private static ArrayList<CulturalPractice> practices;
	private static List<Error> errors;

	public static List<Error> getErrors() {
		return errors;
	}


	public static void setErrors(List<Error> errors2) {
		DatasetCulturalPractice.errors = errors2;
	}


	@Override
	public String MostFrequently(String choice) {
		int max=0;
		Map<String,Integer> map;
		String maxKey=null;
		switch (choice) {
		case "title":{
			map=prepareCountTitle();
		}
		break;
		case "proponent":{
			map=prepareCountProponent();
		}
		break;
		case "site":{
			map=prepareCountSite();
		}
		break;
		case "province":{
			map=prepareCountProvince();
		}
		break;
		case "town":{
			map=prepareCountTown();
		}
		break;
		case"partner":{
			map=prepareCountPartner();
		}
		break;
		default: return "Attributo non presente";
		}
	
		for (String s:map.keySet())
		{
			if (map.get(s)>max) {
				maxKey=s;
				max=map.get(s);
			}
		}
		return maxKey;
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

	public Object FindUnique (String name) {
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		switch(name) {
		case "title":{
			return prepareCountTitle();
		}
		case "proponent":{
			return prepareCountProponent();
		}
		case "site":{
			return prepareCountSite();
		}
		case "province":{
			return prepareCountProvince();
		}
		case "town":{
			return prepareCountTown();
		}
		case"partner":{
			return prepareCountPartner();
		}
		}
		return "Attributo non presente";

	}

	public Map<String,Integer> prepareCountTitle()
	{
		List<String> elementsList=new ArrayList<String>();
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getTitle());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountSite()
	{
		List<String> elementsList=new ArrayList<String>();
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getProponent().getSite());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountProvince()
	{
		List<String> elementsList=new ArrayList<String>();
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getProponent().getTown().getProvince());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountTown()
	{
		List<String> elementsList=new ArrayList<String>();
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getProponent().getTown().getName());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountPartner()
	{
		List<String> elementsList=new ArrayList<String>();
		for (CulturalPractice c:practices) 
		{
			for (Institution p:c.getPartners()) {
				elementsList.add(p.getName());
			}
		}
		return CountElements(elementsList);
	}
	
	public Map<String,Integer> prepareCountProponent()
	{
		List<String> elementsList=new ArrayList<String>();
		for (CulturalPractice c:practices) 
		{
				elementsList.add(c.getProponent().getName());
		}
		return CountElements(elementsList);
	}
	

	private Map<String, Integer> CountElements(List<String> elementsList) {

		Map<String,Integer> count=new HashMap<String,Integer>();
		for (String s:elementsList)
		{
			if (count.containsKey(s)) {
				count.replace(s,count.get(s)+1 );
			}
			else {
				count.put(s, 1);
			}
		}
		return count;
	}






}
