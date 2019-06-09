package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		map=prepareCount(name,elementsList);

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
		return prepareCount(name,elementsList);
	}

	public Map<String,Integer> prepareCount(String choice,List<String> elementsList)
	{
		for (CulturalPractice c:practices) 
		{
			if (choice.equals("partner")){
				for (Institution p:c.getPartners()) {
					elementsList.add(p.getName());
				}
			}
			else {
				elementsList.add(c.getter(choice,null));
			}
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
	public Object logicalFilter(String[] attribute, String operator, String[] value) {

		List<CulturalPractice> listIn=new ArrayList<CulturalPractice>();
		List<CulturalPractice> listIn2=new ArrayList<CulturalPractice>();
		List<CulturalPractice> listNotIn=new ArrayList<CulturalPractice>();
		List<CulturalPractice> listAnd=new ArrayList<CulturalPractice>();
		listNotIn.addAll(practices);
		
		for (CulturalPractice c:practices) {
			if (attribute.length>1) {
				if (value[1].contains(c.getter(attribute[1],value[1])))
					listIn2.add(c);
				if (value[0].contains(c.getter(attribute[0],value[0])))
					listIn.add(c);
			}
			else {
				for (int i=0;i<value.length;i++) {
					if (value[i].contains(c.getter(attribute[0],value[i])))
						listIn.add(c);
					}
			}
		}
		if (operator.equals("in")||operator.equals("or")) {
			listIn.addAll(listIn2);
			return listIn;
		}
		else if (operator.equals("nin")||(operator.equals("not")&&value.length==1)) {
			listNotIn.removeAll(listIn);
			return listNotIn;
		}
		else if (operator.equals("and")) {
			for (CulturalPractice c : listIn) {
	            if(listIn2.contains(c)) {
	                listAnd.add(c);
	            }
	        }
			return listAnd;
		};
		return "operatore non valido";	
	}


}
