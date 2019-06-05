import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
@RequestMapping("/data")
public int alldata(){
	return DatasetCulturalPractice.prova();
}
}
