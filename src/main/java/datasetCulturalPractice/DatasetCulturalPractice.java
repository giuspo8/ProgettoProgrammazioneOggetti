package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DatasetCulturalPractice implements Filter {
	private static ArrayList<CulturalPractice> practices;
	private static ArrayList<Metadata> metadata;
	private static List<Error> errors;
	
	

	public static ArrayList<Metadata> getMetadata() {
		return metadata;
	}

	public static void setMetadata(ArrayList<Metadata> metadata) {
		metadata.add(new Metadata("title","Titolo buona pratica culturale","string"));
		metadata.add(new Metadata("number","Numero ordine di arrivo domanda","integer"));
		metadata.add(new Metadata("Proponent/name","Soggetto proponente","string"));
		metadata.add(new Metadata("Proponent/site","Sito internet proponente","string"));
		metadata.add(new Metadata("Proponent/Town/name","Provincia sede legale proponente","string"));
		metadata.add(new Metadata("Proponent/Town/province","Comune sede legale proponente ","string"));
		metadata.add(new Metadata("Partner/name","Partner","array of string"));
		DatasetCulturalPractice.metadata = metadata;
	}


	public static List<Error> getErrors() {
		return errors;
	}


	public static void setErrors(List<Error> errors2) {
		DatasetCulturalPractice.errors = errors2;
	}


	@Override
	public String MostFrequently(String name) {
		int max=0;
		Map<String,Integer> map;
		String maxKey=null;
		Method method = null;
		String choice="prepareCount"+name.substring(0, 1).toUpperCase()+name.substring(1);
		
			try {
				method=this.getClass().getMethod(choice);
			} 
			catch (NoSuchMethodException | SecurityException e) 
			{
				e.printStackTrace();
				return "Attributo non presente";
			}
			try {
				map=(Map<String, Integer>) method.invoke(this);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				e.printStackTrace();
				return "Attributo non presente";
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


	@Override
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
		if (type.equals(">=")||type.equals("greater"))
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
		else if (type.equals("<=")||type.equals("smaller"))
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
		Method method = null;
		String choice="prepareCount"+name.substring(0, 1).toUpperCase()+name.substring(1);
		
			try {
				method=this.getClass().getMethod(choice);
			} 
			catch (NoSuchMethodException | SecurityException e) 
			{
				e.printStackTrace();
				return "Attributo non presente";
			}
			try {
				return method.invoke(this);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				e.printStackTrace();
				return "Attributo non presente";
			}
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


	public Map<String, Integer> CountElements(List<String> elementsList) {

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

	public Set<Institution> getPartners()
	{
		Set<Institution> partners=new HashSet<Institution>();

		for (CulturalPractice c:practices) 
		{
			for (Institution i:c.getPartners()) {
				partners.add(i);
			}
		}
		return partners;
	}

	public Set<Town> getTowns()
	{
		Set<Town> towns=new HashSet<Town>();

		for (CulturalPractice c:practices) 
		{
			towns.add(c.getProponent().getTown());
		}
		return towns;
	}
	
	public Set<Institution> getProponents()
	{
		Set<Institution> proponents=new HashSet<Institution>();

		for (CulturalPractice c:practices) 
		{

				proponents.add(c.getProponent());

		}
		return proponents;
	}

	@Override
	public Collection logicalFilter(Object attribute, Object operator, Object value) {
		return null;
	}

	@Override
	public Collection conditionalFilter(Object attribute, Object operator, Object value) {
		return null;
	}






}
