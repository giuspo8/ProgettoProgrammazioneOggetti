import java.util.ArrayList;
import java.util.Scanner;

public class TestProject {

	static String filename = "buonepraticheculturaliregionelazio.csv";
	
	public static void main(String[] args) {
Parser p= new Parser(filename,new Scanner(filename));
p.openStream();
ArrayList<CulturalPractice>cp=p.parseCulturalPractice(p.getFile());
p.openStream();
ArrayList<Institution> partners=p.parsePartners(p.getFile());
p.openStream();
ArrayList<Institution> proponents=p.parseProponents(p.getFile());
p.openStream();
ArrayList<Town> towns=p.parseTowns(p.getFile());

		
		for (CulturalPractice c:cp) 
		{
			System.out.println(c);
		}
		
		for (Institution pr:partners) 
		{
			System.out.println(pr);
		}
		
		for (Institution prop:proponents) 
		{
			System.out.println(prop);
		}
		
		for (Town t:towns) 
		{
			System.out.println(t);
		}
		
	}

}
