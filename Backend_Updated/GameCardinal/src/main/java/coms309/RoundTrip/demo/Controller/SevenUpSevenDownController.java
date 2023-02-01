package coms309.RoundTrip.demo.Controller;

import coms309.RoundTrip.demo.Model.Game;
import coms309.RoundTrip.demo.Model.GameUser;
import coms309.RoundTrip.demo.Model.SevenUpSevenDown;
import coms309.RoundTrip.demo.Repository.GameRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author ranais
 *
 * This class represents a move of the game SUSD
 */
@RestController
public class SevenUpSevenDownController
{
    @Autowired
    GameRepository gr;

    /**
     * gets the SUS Game by its ID
     * @param ID the integer that uniquley identifies the user
     * @return An HTTP Response Entity that stores the Game object
     */
    @GetMapping("/susd/{id}")
    @ApiOperation(value = "gets the SUSD Game by its ID", response = ResponseEntity.class)
    public ResponseEntity<Game> getGameUser(@RequestBody int ID)
    {
        Optional<Game> ogu = gr.findById(ID);

        if(ogu.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status((HttpStatus.OK)).body(ogu.get());
    }

    /**
     * REST Endpoint that saves the users game
     * @param game The Game object that should be passed as a JSON object.
     * @return An HTTP ResponseEntity that contains the users Game and code 200 if everything is OK
     */
    @PostMapping("/susd")
    public ResponseEntity<SevenUpSevenDown> susdMove(@RequestBody SevenUpSevenDown game)
    {
       return ResponseEntity.ok(gr.save(game));
    }


}
