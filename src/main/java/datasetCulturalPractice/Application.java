package datasetCulturalPractice;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	static String filename = "buonepraticheculturaliregionelazio.csv";
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Download d=new Download("https://www.dati.gov.it/api/3/action/package_show?id=e17d8738-fd76-4888-94c1-c28319a3fb2c\n");
		d.getFile();
		Parser p= new Parser(filename,new Scanner(filename));
		p.openStream();
		ArrayList<CulturalPractice> practices;
		
		practices = p.parseCulturalPractice(p.getFile());
		p.openStream();
		DatasetCulturalPractice.setPractices(practices);
		DatasetCulturalPractice.setMetadata(new ArrayList<Metadata>());

		for (CulturalPractice c:DatasetCulturalPractice.getPractices()) 
		{
			System.out.println(c);
		}

		/*for (Institution pr:DatasetPartners.getPartners()) 
		{
			System.out.println(pr);
		}

		for (Institution prop:DatasetProponents.getProponents()) 
		{
			System.out.println(prop);
		}

		for (Town t:DatasetTown.getTowns()) 
		{
			System.out.println(t);
		}*/

	}

}

