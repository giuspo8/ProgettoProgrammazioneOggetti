package datasetCulturalPractice;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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
			System.out.println("File non trovato!!");
			e.printStackTrace();
		}
		return file;
	}

	public ArrayList<CulturalPractice> parseCulturalPractice(Scanner file) {

		ArrayList<CulturalPractice> practices = new ArrayList<CulturalPractice>();
		List<Error>errors=new ArrayList<Error>();	
		int count=0;
		while (file.hasNext())
		{
			try {
				count++;
				if (count !=1) {
					List<Institution> partnersThisPractice = new ArrayList<Institution>();
					String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
					if (a.length < FIELDNUMBERS)
					{
						errors.add(new Error("Errore sul numero di attributi",count));
					}
					else
					{
						if ((!a[3].contains("www")&&!a[3].contains("http"))||a[4].contains("www"))
						{
							errors.add(new Error("Errore di formattazione campo sito web",count));
						}
						else
						{
							String[] b = a[FIELDNUMBERS-1].split(regex);
							for (int i=0; i<b.length; i++)
							{
								partnersThisPractice.add(new Partner(b[i]));
							}
							practices.add(new CulturalPractice(a[0],Integer.parseInt(a[1]),partnersThisPractice,new Proponent(a[2],a[3],new Town(a[5],a[4]))));
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
				if (file.hasNextLine()) 
				{
					file.nextLine();
				}
				errors.add(new Error("Errore nel parsing",count));
			}

		}
		DatasetCulturalPractice.setErrors(errors);
		return practices;
	}
}
