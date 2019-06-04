import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestProject {

	static String filename = "buonepraticheculturaliregionelazio.csv";
	static final String DELIMITER = ";";
	static Scanner file = null;
	static final int FIELDNUMBERS = 7;
	static final String regex = "[;|,]";
	
	public static void main(String[] args) {

		ArrayList<Town> towns = new ArrayList<Town>();
		ArrayList<CulturalPractice> practices = new ArrayList<CulturalPractice>();
		ArrayList<Institution> partners = new ArrayList<Institution>();
		ArrayList<Institution> proponents = new ArrayList<Institution>();
		int count=0;
		
		try {
			file = new Scanner(new BufferedReader(new FileReader(filename)));
		while (file.hasNext())
		{
			try {
				
				count++;
				if (count !=1) {
				ArrayList<Institution> partnersThisPractice = new ArrayList<Institution>();
				String[] a = file.nextLine().split(DELIMITER,FIELDNUMBERS);
				if (a.length < 7)
				{
					System.out.println("C'è un errore alla riga "+ count);
				}
				else
				{
				String[] b = a[6].split(regex);
				for (int i=0; i<b.length; i++)
				{
					partners.add(new Partner(b[i]));
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
		}
		catch (FileNotFoundException e)
		{
			System.out.print("File non trovato!!");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for (CulturalPractice c:practices) 
		{
			//System.out.println(c);
		}
		
		for (Institution p:partners) 
		{
			System.out.println(p);
		}
	}

}
