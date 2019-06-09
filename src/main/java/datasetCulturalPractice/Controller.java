package datasetCulturalPractice;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

	@ExceptionHandler (NumberFormatException.class)
	public Object conflict(){
		return "i dati inseriti non sono nel formato corretto!";
	}
	
	@ExceptionHandler (wrongAttributeException.class)
	public Object conflict2(){
		return "attributo non corretto!";
	}
	
	@RequestMapping("/data")
	public Object data(){
		return DatasetCulturalPractice.getPractices();
	}

	@RequestMapping(value = "/conditionalFilter", method = RequestMethod.GET)
	public Object filterConditional(@RequestParam("numbers") int[] numbers,@RequestParam("operator")String operator)
	{
			DatasetCulturalPractice c = new DatasetCulturalPractice ();
			return c.conditionalFilter(operator,numbers);
	}
	

	@RequestMapping(value = "/logicalFilter", method = RequestMethod.GET)
	public Object filterLogical(@RequestParam("attribute") String[] attribute,@RequestParam("operator")String operator,
			@RequestParam("value")String[] value) throws wrongAttributeException
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return c.logicalFilter(attribute,operator,value);
	}


	@RequestMapping(value = "/countUnique", method = RequestMethod.GET)
	public Object FindUniqueElement(@RequestParam("attribute") String name) throws wrongAttributeException
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.FindUnique(name);
	}

	@RequestMapping(value = "/mostFrequently", method = RequestMethod.GET)
	public Object MostFrequently(@RequestParam("attribute") String name) throws wrongAttributeException
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
		Set<Institution> partners=new HashSet<Institution>();
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getPartners(partners);
	}

	@RequestMapping(value = "/towns", method = RequestMethod.GET)
	public Object towns()
	{
		Set<Town> towns=new HashSet<Town>();
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getTowns(towns);
	}

	@RequestMapping(value = "/proponents", method = RequestMethod.GET)
	public Object proponents()
	{
		Set<Institution> proponents=new HashSet<Institution>();
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getProponents(proponents);
	}

	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public Object metadata()
	{
		return DatasetCulturalPractice.getMetadata();
	}
}
