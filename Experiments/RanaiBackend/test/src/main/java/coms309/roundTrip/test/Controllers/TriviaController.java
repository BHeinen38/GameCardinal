package coms309.roundTrip.test.Controllers;

import coms309.roundTrip.test.model.Trivia;
import coms309.roundTrip.test.repository.TriviaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class TriviaController
{
    @Autowired
    TriviaRepository tr;

    @GetMapping("/trivia/all")
    List<Trivia> getAllTrivia()
    {
        return tr.findAll();
    }

    @PostMapping("/trivia/post/{q}/{a}")
    Trivia PostTriviaByPath(@PathVariable String q, @PathVariable String a)
    {
        Trivia trivia = new Trivia();
        trivia.setQuestion(q);
        trivia.setAnswer(a);
        tr.save(trivia);
        return trivia;
    }

    @PostMapping("/trivia/post")
    Trivia PostTriviaByBody(@RequestBody Trivia newTrivia)
    {
        tr.save(newTrivia);
        return newTrivia;
    }

}
