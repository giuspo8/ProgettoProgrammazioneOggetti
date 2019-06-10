package datasetCulturalPractice;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe che gestisce il parsing del file csv
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class Parser {
	//Attributi
	private String filename;//nome del file csv da parsare
	private static final String DELIMITER = ";";//delimitatore che ci servirà per separare i vari attributi
	private Scanner file = null;
	private static final int FIELDNUMBERS = 7;//numero di attributi, l'ultimo (partner) è composto da più occorrenze
	private static final String regex = "[;|,]";//espressione regolare che ci serve per ottenere un miglior parsing dell'attributo partner

	//metodi
	/**
	 * crea un oggetto di tipo Parser con filename assegnato
	 * @param filename nome del file csv da parsare
	 */
	public Parser(String filename) {
		super();
		this.filename = filename;
	}

	/**
	 * crea un oggetto di tipo {@link Parser}
	 * @param filename nome del file csv da parsare
	 */
	public Parser(String filename, Scanner file) {
		super();
		this.filename = filename;
		this.file = file;
	}


	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Scanner getFile() {
		return file;
	}

	public void setFile(Scanner file) {
		this.file = file;
	}

	/**
	 * metodo che non fa altro che aprire lo stream e creare un nuovo oggetto {@link Scanner}
	 * @return un oggetto di tipo {@link Scanner} creato a partire dai Reader del nome del file csv
	 */
	public Scanner openStream() {
		try {
			file = new Scanner(new BufferedReader(new FileReader(filename)));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File non trovato!!");
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * metodo che ci permette di parsare il nostro file csv e ottenere un ArrayList che contiene tutti gli oggetti {@link CulturalPractice} parsati correttamente
	 * inoltre viene popolato anche l'{@link ArrayList} errors della classe {@link DatasetCulturalPractice}
	 * @param file oggetto di tipo Scanner a cui è già stato assegnato qualcosa da leggere
	 * @return un {@link ArrayList} contenente tutti gli oggetti {@link CulturalPractice} che si è riusciti a parsare 
	 * @throws NumberFormatException nel caso non si riesca a castomizzare un elemento della colonna number da {@link String} a {@link Integer}
	 */
	public ArrayList<CulturalPractice> parseCulturalPractice(Scanner file) {

		ArrayList<CulturalPractice> practices = new ArrayList<CulturalPractice>();
		List<Error>errors=new ArrayList<Error>();	
		int count=0;
		while (file.hasNext())
		{
			try {
				count++;
				if (count !=1) {
					//lista che andrà a contenere i partner di questa specifica CulturalPractice
					List<Institution> partnersThisPractice = new ArrayList<Institution>();
					//viene effettuato lo split a partire dal delimiter scelto per un numero massimo di FIeldnumbers elementi
					String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
					//se il parsing ci ritorna un numero di elementi minore di 7 abbiamo un errore
					if (a.length < FIELDNUMBERS)
					{
						errors.add(new Error("Errore sul numero di attributi",count));
					}
					//altrimenti (quindi abbiamo 7 elementi)
					else
					{
						//facciamo un controllo sul campo del sito web
						if ((!a[3].contains("www")&&!a[3].contains("http"))||a[4].contains("www"))
						{
							errors.add(new Error("Errore di formattazione campo sito web",count));
						}
						//se è tutto ok
						else
						{
							//dividiamo l'ultima stringa ottenuta precedentemente in modo ulteriore utilizzando un'espressione regolare
							String[] b = a[FIELDNUMBERS-1].split(regex);
							for (int i=0; i<b.length; i++)
							{
								//e aggiungiamo ogni elemento partner ottenuto nella lista dei partner di questa CulturalPractice
								partnersThisPractice.add(new Partner(b[i]));
							}
							//infine componiamo il tutto per creare un nuovo elemento CulturalPractice e aggiungerlo alla lista finale
							practices.add(new CulturalPractice(a[0],Integer.parseInt(a[1]),partnersThisPractice,new Proponent(a[2],a[3],new Town(a[5],a[4]))));
						}
					}
				}
				//se il count è uguale a uno e quindi ci troviamo alla prima riga che deve essere saltata
				else
				{
					if (file.hasNextLine()) 
					{
						file.nextLine();
					}
				}
			}
			catch (NumberFormatException e)
			{
				if (file.hasNextLine()) 
				{
					file.nextLine();
				}
				errors.add(new Error("Errore nel parsing",count));
			}

		}
		//settiamo la lista degli errori ottenuta come lista di errori del dataset totale
		DatasetCulturalPractice.setErrors(errors);
		return practices;
	}
}
