package coms309.roundTrip.test.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/")
    public String index()
    {
        return "Greetings from SpringBoot";
    }
}
