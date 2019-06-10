package datasetCulturalPractice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Rappresenta l'insieme di tutte le pratiche culturali, di tutti i metadati e di tutti gli errori riscontrati nel parsing
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class DatasetCulturalPractice implements Filter<String,String[]> {
	private static ArrayList<CulturalPractice> practices;//Lista contenente tutte le pratiche culturali del dataset
	private static ArrayList<Metadata> metadata;//lista contenente tutti i metadati
	private static List<Error> errors;//lista contenente tutti gli errori riscontrati nel Parsing



	public static ArrayList<Metadata> getMetadata() {
		return metadata;
	}
/**
 * Serve per popolare il nostro insieme di {@link Metadata}
 * @param metadata inizialmente vuoto viene riempito all'interno del costruttore
 */
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

	/**
	  * {@inheritDoc}
	  * ci permette di trovare il valore che compare piu' frequentemente in una colonna scelta previa attributo
	  * @param name nome dell'attributo del quale vogliamo trovare il valore che compare più di frequente
	  * @return il valore che compare più di frequente tra tutte le {@link CulturalPractice} per l'attributo scelto
	  * @throws WrongAttributeException quando l'attributo scelto non e' presente
	  */
	@Override
	public String MostFrequently(String name) throws WrongAttributeException {
		List<String> elementsList=new ArrayList<String>();
		int max=0;
		Map<String,Integer> map;
		String maxKey=null;
		map=prepareCount(name,elementsList);//ci restituisce la mappa chiave valore degli elementi della colonna scelta come chiave e con il numero di volte in cui compaiono nella colonna come valore

		//andiamo semplicemente a trovare la chiave con il valore più alto e la restituiamo
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

	/**
	  * {@inheritDoc}
	  * Ci consente di filtrare il dataSet in base al numero di domanda utilizzando gli operatori condizionali di maggiore minore etc.
	  * @param operator operazione di filtraggio che vogliamo effettuare
	  * @param numbers valori limite del filtro scelto. Possono essere uno oppure due nel caso di =><= (compreso tra)
	  * @return la lista di tutte le {@link CulturalPractice} filtrate per numero di pratica rispetto all'operatore e ai valori scelti oppure un messaggio di errore se l'operatore scelto non e' tra quelli disponibili (<,>,>=,<=,=><=)(minore,maggiore,minore uguale,maggiore uguale, compreso tra)
	  */
	@Override
	public Object conditionalFilter (String operator,int... numbers) {
		List<CulturalPractice> listCulturalPractice = new ArrayList<CulturalPractice>();
		switch(operator) {
		case "=><=":
		{
			//se il numero di valori passati è diverso da 2 questo filtro non è applicabile
			if (numbers.length!=2)
				return "ERRORE! devi inserire due numeri";
			else if (numbers[0]>numbers[1]) 
				return "ERRORE! il primo parametro deve essere minore o uguale al secondo";
			//altrimenti facciamo i confronti e andiamo ad aggiungere nell'ArrayList vuoto i valori che corrispondono al filtro richiesto
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
		//nel caso l'operatore scelto non è tra questi
		default: return "Operazione non valida";
		}
	}

/**
 * metodo che viene chiamato per primo in seguito a richiesta di effettuare il conteggio di elementi unici
 * @param name attributo scelto su cui effettuare il conteggio degli elementi unici
 * @return una mappa che ci rappresenta con una tipologia Chiave Valore il conteggio degli elementi unici
 * @throws WrongAttributeException quando l'attributo scelto non e' presente
 */
	public Object FindUnique (String name) throws WrongAttributeException {
		List<String> elementsList=new ArrayList<String>();
		return prepareCount(name,elementsList);
	}

	/**
	 * prepara il conteggio dei valori unici mettendo tutte le stringhe di una data colonna in una lista e chiamando un metodo per effettuare il conteggio
	 * @param choice attributo scelto 
	 * @param elementsList lista inizialmente vuota passata dall'esterno con una {@link Dependency Injection}
	 * @return un oggetto {@link Map} che rappresenta il conteggio degli elementi unici dell'attributo scelto con chiave=elemento e valore=numero di occorenze
	 * @throws WrongAttributeException quando viene scelto un attributo non presente
	 */
	public Map<String,Integer> prepareCount(String choice,List<String> elementsList) throws WrongAttributeException
	{
		for (CulturalPractice c:practices) 
		{
			//nel caso partner la lista con tutti gli elementi viene creata direttamente qui
			if (choice.equals("partner")){
				for (Institution p:c.getPartners()) {
					elementsList.add(p.getName());
				}
			}
			else {
				//negli altri casi si fa affidamento al metodo getter della classe CulturalPractice
				elementsList.add(c.getter(choice,null));
			}
		}
		return CountElements(elementsList);
	}

/**
 * data una lista di stringhe effettua il conteggio degli elementi unici e ci ritorna la mappa chiave valore degli elementi con la loro numerosita'
 * @param elementsList lista che contiene tutti i valori assunti dagli elementi dell'attributo scelto
 * @return un oggetto {@link Map} che rappresenta il conteggio degli elementi unici dell'attributo scelto con chiave=elemento e valore=numero di occorrenze
 */
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

	/**
	 * ci permette di ottenere la lista dei {@link Partner} non ripetuti
	 * @param partners Dependency Injection di un {@link Set} vuoto dall'esterno
	 * @return {@link Set} di elementi che contengono tutti i {@link Partner} di tutte le {@link CulturalPractice} non ripetuti
	 */
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
/**
 * otteniamo la lista delle {@link Town} non ripetute
 * @param towns Dependency Injection di un {@link Set} vuoto dall'esterno
 * @return {@link Set} di elementi che contengono tutti le {@link Town} di tutte le {@link CulturalPractice} non ripetute
 */
	public Object getTowns(Set<Town> towns)
	{

		for (CulturalPractice c:practices) 
		{
			towns.add(c.getProponent().getTown());
		}
		return towns;
	}

	/**
	 * otteniamo la lista dei {@link Proponents} non ripetuti
	 * @param towns Dependency Injection di un Set vuoto dall'esterno
	 * @return {@link Set} di elementi che contengono tutti i {@link Proponents} di tutte le {@link CulturalPractice} non ripetute
	 */
	public Object getProponents(Set<Institution> proponents)
	{
		for (CulturalPractice c:practices) 
		{

			proponents.add(c.getProponent());

		}
		return proponents;
	}
/**
 * {@inheritDoc}
 * metodo che ci permette di effettuare un filtraggio di tipo logico del nostro dataset dati attributi da filtrare,operatori logici da applicare (not,in,nin,or,and) e valori su cui applicare il filtro
 * @param attribute insieme di attributi su cui applicare il filtro. Deve essere coerente con la scelta del numero di valori e dell'operatore
 * @param operator operazione di filtraggio da effettuare (in,nin,not,or,and)
 * @param value valori su cui applicare l'operazione scelta
 * @return un sottoinsieme del dataset filtrato in base alle specifiche oppure un messaggio di errore in caso di richiesta di filtraggio non corretta
 * @throws WrongAttributeException se uno o più degli attributi scelti non e' presente
 * @throws ArrayIndexOutOfBoundsException se vengono inseriti due attributi e un solo valore
 */
	@Override
	public Object logicalFilter(String[] attribute, String operator, String[] value) throws WrongAttributeException {

		//istanzio una serie di insiemi che mi serviranno per i vari filtri
		Set<CulturalPractice> setIn=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setIn2=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setNotIn=new HashSet<CulturalPractice>();
		Set<CulturalPractice> setAnd=new HashSet<CulturalPractice>();
		//popolo l'insieme setNotIn con tutte le CulturalPractice del mio dataset
		setNotIn.addAll(practices);
		
		for (CulturalPractice c:practices) {
			//se abbiamo più di un attributo gli unici operatori applicabili sono l'or e and 
			if (attribute.length>1) {
				if (operator.equals("or")||operator.equals("and")) {
					//vengono considerati comunque solo i primi due attributi
					if (c.getter(attribute[1],value[1]).contains(value[1]))
						//si riempie un set con i valori filtrati rispetto al secondo attributo e secondo valore
						setIn2.add(c);
					if (c.getter(attribute[0],value[0]).contains(value[0]))
						//e un set con i valori filtrati rispetto al primo attributo e al primo valore
						setIn.add(c);
				}
				else return "non è possibile specificare più di un attributo con l'operatore scelto";
			}
			else {
				//altrimenti si riempie solo il primo set ma in questo caso si vanno a valutare più valori (operatori in e nin)
				for (int i=0;i<value.length;i++) {
					if (c.getter(attribute[0],value[i]).contains(value[i]))
						setIn.add(c);
					}
			}
		}
		//se l'operatore scelto è un or l'insieme filtrato non sarà nient'altro che l'unione dei due insiemi
		//nel caso dell'in solo del primo insieme ed essendo il secondo vuoto la sua aggiunta non ha effetto alcuno
		if (operator.equals("in")||operator.equals("or")) {
			setIn.addAll(setIn2);
			return setIn;
		}
		//altrimenti se l'operatore scelto è nin oppure not (che però ha come vincolo la presenza di un solo valore)
		//allora possiamo ottenere l'insieme filtrato eliminando dall'insieme completo(setNotIn) tutti gli elementi che compaiono nell'insieme dei presenti (setIn)
		else if (operator.equals("nin")||(operator.equals("not")&&value.length==1)) {
			setNotIn.removeAll(setIn);
			return setNotIn;
		}
		//infine se l'operatore scelto è l'and l'insieme filtrato lo creiamo tramite un terzo insieme che popoliamo con gli elementi comuni ai due insiemi ottenuti
		else if (operator.equals("and")) {
			for (CulturalPractice c : setIn) {
	            if(setIn2.contains(c)) {
	            	setAnd.add(c);
	            }
	        }
			return setAnd;
		};
		//se l'operatore scelto non è tra questi viene visualizzato un messaggio di errore
		return "operatore non valido";	
	}


}
