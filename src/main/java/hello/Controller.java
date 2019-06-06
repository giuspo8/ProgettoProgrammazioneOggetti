package hello;
import java.util.ArrayList;
import java.util.List;

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
}
