package hello;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/data")
	public ArrayList<CulturalPractice> data(){
		return DatasetCulturalPractice.getPractices();
	}
}
