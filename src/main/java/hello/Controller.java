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
	public ArrayList<CulturalPractice> data(){
		return DatasetCulturalPractice.getPractices();
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public List<CulturalPractice> filterByTowm(@RequestParam("name") String name)
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return (List<CulturalPractice>) c.Find(name);
	}
	
}
