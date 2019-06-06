package hello;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	static String filename = "buonepraticheculturaliregionelazio.csv";
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Parser p= new Parser(filename,new Scanner(filename));
		p.openStream();
		ArrayList<CulturalPractice> practices;

		practices = p.parseCulturalPractice(p.getFile());
		p.openStream();
		ArrayList<Institution> partners=p.parsePartners(p.getFile());
		p.openStream();
		ArrayList<Proponent> proponents=p.parseProponents(p.getFile());
		p.openStream();
		ArrayList<Town> towns=p.parseTowns(p.getFile());
		DatasetCulturalPractice.setPractices(practices);
		DatasetPartners.setPartners(partners);
		DatasetProponents.setProponents(proponents);
		DatasetTown.setTowns(towns);

		for (CulturalPractice c:DatasetCulturalPractice.getPractices()) 
		{
			System.out.println(c);
		}

		//for (Institution pr:DatasetPartners.getPartners()) 
		//{
			//System.out.println(pr);
		//}

		//for (Institution prop:DatasetProponents.getProponents()) 
		//{
			//System.out.println(prop);
		//}

		//for (Town t:DatasetTown.getTowns()) 
		//{
			//System.out.println(t);
		//}

	}

}

