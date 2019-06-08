package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DatasetCulturalPractice implements Filter<String,String[]> {
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


	public static void setErrors(List<Error> errors) {
		DatasetCulturalPractice.errors = errors;
	}


	@Override
	public String MostFrequently(String name) {
		List<String> elementsList=new ArrayList<String>();
		int max=0;
		Map<String,Integer> map;
		String maxKey=null;
		Method method = null;
		String choice="prepareCount"+name.substring(0, 1).toUpperCase()+name.substring(1);

		try {
			method=this.getClass().getMethod(choice,List.class);
		} 
		catch (NoSuchMethodException | SecurityException e) 
		{
			e.printStackTrace();
			return "Attributo non presente";
		}
		try {
			map=(Map<String, Integer>) method.invoke(this,elementsList);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			e.printStackTrace();
			return "Errore di programmazione";
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
	public Object conditionalFilter (String operator,int... numbers) {
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		switch(operator) {
		case "=><=":
		{
			if (numbers.length!=2)
				return "ERRORE! devi inserire due numeri";
			else if (numbers[0]>numbers[1]) 
				return "ERRORE! il primo parametro deve essere minore o uguale al secondo";
			else {
				for (CulturalPractice c: practices)
				{
					if (c.getNumber()<=numbers[1]&&c.getNumber()>=numbers[0]) 
					{
						listCulturalPractice.add(c);
					}
				}
				return listCulturalPractice;
			}
		}

		case ">=":{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()>=numbers[0]) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}

		case ">":{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()>numbers[0]) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}

		case "<=":{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()<=numbers[0]) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}

		case "<":{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()<numbers[0]) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}

		case "==":{
			for (CulturalPractice c: practices)
			{
				if (c.getNumber()==numbers[0]) 
				{
					listCulturalPractice.add(c);
				}
			}
			return listCulturalPractice;
		}
		default: return "Operazione non valida";
		}
	}


	public Object FindUnique (String name) {
		List<String> elementsList=new ArrayList<String>();
		Method method = null;
		String choice="prepareCount"+name.substring(0, 1).toUpperCase()+name.substring(1);

		try {
			method=this.getClass().getMethod(choice,List.class);
		} 
		catch (NoSuchMethodException | SecurityException e) 
		{
			e.printStackTrace();
			return "Attributo non presente";
		}
		try {
			return method.invoke(this,elementsList);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			e.printStackTrace();
			return "Attributo non presente";
		}
	}

	public Map<String,Integer> prepareCountTitle(List<String> elementsList)
	{
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getTitle());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountSite(List<String> elementsList)
	{
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getProponent().getSite());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountProvince(List<String> elementsList)
	{
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getProponent().getTown().getProvince());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountTown(List<String> elementsList)
	{
		for (CulturalPractice c:practices) 
		{
			elementsList.add(c.getProponent().getTown().getName());
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountPartner(List<String> elementsList)
	{
		for (CulturalPractice c:practices) 
		{
			for (Institution p:c.getPartners()) {
				elementsList.add(p.getName());
			}
		}
		return CountElements(elementsList);
	}

	public Map<String,Integer> prepareCountProponent(List<String> elementsList)
	{
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

	public Object getPartners(Set<Institution> partners)
	{
		for (CulturalPractice c:practices) 
		{
			for (Institution i:c.getPartners()) {
				partners.add(i);
			}
		}
		return partners;
	}

	public Set<Town> getTowns(Set<Town> towns)
	{

		for (CulturalPractice c:practices) 
		{
			towns.add(c.getProponent().getTown());
		}
		return towns;
	}

	public Set<Institution> getProponents(Set<Institution> proponents)
	{
		for (CulturalPractice c:practices) 
		{

			proponents.add(c.getProponent());

		}
		return proponents;
	}

	@Override
	public Object logicalFilter(String attribute, String operator, String[] value) {

		Set<CulturalPractice> setIn=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setNotIn=new HashSet<CulturalPractice>();
		setNotIn.addAll(practices);
		switch(attribute) {
		case "town":
			for (CulturalPractice c:practices) {
				for (int i=0;i<value.length;i++) {
					if (value[i].equals(c.getProponent().getTown().getName()))
						setIn.add(c);
				}
			}
			break;
		case"partner":
			for (CulturalPractice c:practices) {
				for (int i=0;i<value.length;i++) {
					for (Institution p:c.getPartners()) {
						if (value[i].equals(p.getName()))
							setIn.add(c);
					}

				}
			}
			break;
		case"proponents":
			for (CulturalPractice c:practices) {
				for (int i=0;i<value.length;i++) {
					if (value[i].equals(c.getProponent().getName()))
						setIn.add(c);
				}
			}
			break;
		case"practice":
			for (CulturalPractice c:practices) {
				for (int i=0;i<value.length;i++) {
					if (value[i].equals(c.getTitle()))
						setIn.add(c);
				}
			}
			break;
		case "province":
			for (CulturalPractice c:practices) {
				for (int i=0;i<value.length;i++) {
					if (value[i].equals(c.getProponent().getTown().getProvince()))
						setIn.add(c);
				}
			}
			break;
		case "site":
			for (CulturalPractice c:practices) {
				for (int i=0;i<value.length;i++) {
					if (value[i].equals(c.getProponent().getSite()))
						setIn.add(c);
				}
			}
			break;
		default: return "attributo non valido";
		}
		if (operator.equals("in")) return setIn;
		else if (operator.equals("nin")||(operator.equals("not")&&value.length==1)) {
			setNotIn.removeAll(setIn);
			return setNotIn;
		}
		else return "operatore non valido";	
}



}
