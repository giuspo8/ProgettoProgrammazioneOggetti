package datasetCulturalPractice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/data")
	public Object data(){
		return DatasetCulturalPractice.getPractices();
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public Object filter(@RequestParam("attribute") String attribute,@RequestParam("operator") String operator,@RequestParam("value") String value)
	{
		return null;
	}
	
	@RequestMapping(value = "/filterbyTown", method = RequestMethod.GET)
	public Object filterByTowm(@RequestParam("name") String name)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return c.Find(name);
	}
	
	@RequestMapping(value = "/filterbyTownOr", method = RequestMethod.GET)
	public Object filterByTowmOr(@RequestParam("name") String[] name)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return  c.Find(name);
	}
	
	@RequestMapping(value = "/filterByNumbers", method = RequestMethod.GET)
	public Object filterByTowmOr(@RequestParam("numbers") int[] numbers)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return c.Find(numbers);
	}
	
	@RequestMapping(value = "/filterByNumber", method = RequestMethod.GET)
	public Object filterByTowmOr(@RequestParam("type") String type ,@RequestParam("number") int number)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return c.Find(type,number);
	}
	
	@RequestMapping(value = "/filterByPartner", method = RequestMethod.GET)
	public Object filterByPartner(@RequestParam("partner") String name)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		Institution partner=new Partner(name);
		return c.Find(partner);
	}
	
	@RequestMapping(value = "/countUnique", method = RequestMethod.GET)
	public Object FindUniqueElement(@RequestParam("attribute") String name)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.FindUnique(name);
	}
	
	@RequestMapping(value = "/mostFrequently", method = RequestMethod.GET)
	public Object MostFrequently(@RequestParam("attribute") String name)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.MostFrequently(name);
	}
	
	@RequestMapping(value = "/data/errors", method = RequestMethod.GET)
	public Object ShowErrors()
	{
		return DatasetCulturalPractice.getErrors();
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public Object Count()
	{
		return "il numero di elementi con formattazione valida nel DataSet è pari a "+DatasetCulturalPractice.getPractices().size();
	}
	
	@RequestMapping(value = "/partners", method = RequestMethod.GET)
	public Object partners()
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getPartners();
	}
	
	@RequestMapping(value = "/towns", method = RequestMethod.GET)
	public Object towns()
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getTowns();
	}
	
	@RequestMapping(value = "/proponents", method = RequestMethod.GET)
	public Object proponents()
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getProponents();
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public Object metadata()
	{
		return DatasetCulturalPractice.getMetadata();
	}
}
