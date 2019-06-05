package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "ciao"; /*Greeting(counter.incrementAndGet(),
                            String.format(template, name));*/
    }
    
    @RequestMapping("/greeting2")
    public String greeting2(@RequestParam(value="name", defaultValue="World") String name) {
        return Prova.prova(); /*Greeting(counter.incrementAndGet(),
                            String.format(template, name));*/
    }
}
