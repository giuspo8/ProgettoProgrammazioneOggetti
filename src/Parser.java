import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	private String filename;
	private static final String DELIMITER = ";";
	private Scanner file = null;
	private static final int FIELDNUMBERS = 7;
	private static final String regex = "[;|,]";

	public Parser(String filename) {
		super();
		this.filename = filename;
	}
	

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

	public Scanner openStream() {
		try {
			file = new Scanner(new BufferedReader(new FileReader(filename)));
		}
		catch (FileNotFoundException e)
		{
			System.out.print("File non trovato!!");
			e.printStackTrace();
		}
		return file;
	}

	public ArrayList<CulturalPractice> parseCulturalPractice(Scanner file) {

		ArrayList<CulturalPractice> practices = new ArrayList<CulturalPractice>();
		int count=0;
		while (file.hasNext())
		{
			try {
				count++;
				if (count !=1) {
					ArrayList<Institution> partnersThisPractice = new ArrayList<Institution>();
					String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
					if (a.length < FIELDNUMBERS)
					{
						System.out.println("C'è un errore alla riga "+ count);
					}
					else
					{
						Town t=new Town(a[5],a[4]);
						//towns.add(t);
						Proponent p=new Proponent(a[2],a[3],new Town(a[5],a[4]));
						//proponents.add(p);
						String[] b = a[FIELDNUMBERS-1].split(regex);
						for (int i=0; i<b.length; i++)
						{
							//partners.add(new Partner(b[i]));
							partnersThisPractice.add(new Partner(b[i]));
						}
						practices.add(new CulturalPractice(a[0],Integer.parseInt(a[1]),partnersThisPractice,new Proponent(a[2],a[3],new Town(a[5],a[4]))));
					}
				}
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
				if (file.hasNextLine()) {
					file.nextLine();
				}
				System.out.println("C'è un errore di conversione da stringa a numero alla riga "+ count);
			}

		}

		return practices;
		//Filter partnersTotal=new DatasetPartners(partners);
		//Filter proponentsTotal=new DatasetProponents(proponents);
		//Filter townsTotal=new DatasetTown(towns);
	}

	public ArrayList<Town> parseTowns(Scanner file){

		ArrayList<Town> towns = new ArrayList<Town>();
		int count=0;
		while (file.hasNext())
		{
			try {
				count++;
				if (count !=1) {
					String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
					if (a.length < FIELDNUMBERS)
					{
						//System.out.println("C'è un errore alla riga "+ count);
					}
					else
					{
						Town t=new Town(a[5],a[4]);
						towns.add(t);
					}
				}
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
				if (file.hasNextLine()) {
					file.nextLine();
				}
				//System.out.println("C'è un errore di conversione da stringa a numero alla riga "+ count);
			}

		}

		return towns;
	}

	public ArrayList<Institution> parsePartners(Scanner file){

		ArrayList<Institution> partners = new ArrayList<Institution>();
		int count=0;
		while (file.hasNext())
		{
			try {
				count++;
				if (count !=1) {
					String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
					if (a.length < FIELDNUMBERS)
					{
						//System.out.println("C'è un errore alla riga "+ count);
					}
					else
					{
						String[] b = a[FIELDNUMBERS-1].split(regex);
						for (int i=0; i<b.length; i++)
						{
							Institution p=new Partner(b[i]);
							partners.add(p);
						}
					}
				}
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
				if (file.hasNextLine()) {
					file.nextLine();
				}
				//System.out.println("C'è un errore di conversione da stringa a numero alla riga "+ count);
			}
		}

		return partners;
	}

	public ArrayList<Institution> parseProponents(Scanner file){

		ArrayList<Institution> proponents = new ArrayList<Institution>();
		int count=0;
		while (file.hasNext())
		{
			try {
				count++;
				if (count !=1) {
					String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
					if (a.length < FIELDNUMBERS)
					{
						//System.out.println("C'è un errore alla riga "+ count);
					}
					else
					{

						Institution p=new Proponent(a[2],a[3],new Town(a[5],a[4]));
						proponents.add(p);
					}
				}
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
				if (file.hasNextLine()) {
					file.nextLine();
				}
				//System.out.println("C'è un errore di conversione da stringa a numero alla riga "+ count);
			}

		}
		return proponents;
	}

}
