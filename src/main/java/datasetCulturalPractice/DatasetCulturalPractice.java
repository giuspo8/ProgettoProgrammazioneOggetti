package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		metadata.add(new Metadata("practice","Titolo buona pratica culturale","string"));
		metadata.add(new Metadata("numbers","Numero ordine di arrivo domanda","integer"));
		metadata.add(new Metadata("proponents","Soggetto proponente","string"));
		metadata.add(new Metadata("site","Sito internet proponente","string"));
		metadata.add(new Metadata("town","Comune sede legale proponente","string"));
		metadata.add(new Metadata("province","Provincia sede legale proponente ","string"));
		metadata.add(new Metadata("partner","Partner","array of string"));
		DatasetCulturalPractice.metadata = metadata;
	}


	public static List<Error> getErrors() {
		return errors;
	}


	public static void setErrors(List<Error> errors) {
		DatasetCulturalPractice.errors = errors;
	}


	@Override
	public String MostFrequently(String name) throws WrongAttributeException {
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


	public Object FindUnique (String name) throws WrongAttributeException {
		List<String> elementsList=new ArrayList<String>();
		return prepareCount(name,elementsList);
	}

	public Map<String,Integer> prepareCount(String choice,List<String> elementsList) throws WrongAttributeException
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

	public Object getTowns(Set<Town> towns)
	{

		for (CulturalPractice c:practices) 
		{
			towns.add(c.getProponent().getTown());
		}
		return towns;
	}

	public Object getProponents(Set<Institution> proponents)
	{
		for (CulturalPractice c:practices) 
		{

			proponents.add(c.getProponent());

		}
		return proponents;
	}

	@Override
	public Object logicalFilter(String[] attribute, String operator, String[] value) throws WrongAttributeException {

		Set<CulturalPractice> setIn=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setIn2=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setNotIn=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setAnd=new HashSet<CulturalPractice>();
		setNotIn.addAll(practices);
		
		for (CulturalPractice c:practices) {
			if (attribute.length>1) {
				if (operator.equals("or")||operator.equals("and")) {
					if (c.getter(attribute[1],value[1]).contains(value[1]))
						setIn2.add(c);
					if (c.getter(attribute[0],value[0]).contains(value[0]))
						setIn.add(c);
				}
				else return "non è possibile specificare più di un attributo con l'operatore scelto";
			}
			else {
				for (int i=0;i<value.length;i++) {
					if (c.getter(attribute[0],value[i]).contains(value[i]))
						setIn.add(c);
					}
			}
		}
		if (operator.equals("in")||operator.equals("or")) {
			setIn.addAll(setIn2);
			return setIn;
		}
		else if (operator.equals("nin")||(operator.equals("not")&&value.length==1)) {
			setNotIn.removeAll(setIn);
			return setNotIn;
		}
		else if (operator.equals("and")) {
			for (CulturalPractice c : setIn) {
	            if(setIn2.contains(c)) {
	            	setAnd.add(c);
	            }
	        }
			return setAnd;
		};
		return "operatore non valido";	
	}


}
