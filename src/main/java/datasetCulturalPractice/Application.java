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
		DatasetCulturalPractice.setPractices(p.parseCulturalPractice(p.getFile()));
		DatasetCulturalPractice.setMetadata(new ArrayList<Metadata>());
	}

}

