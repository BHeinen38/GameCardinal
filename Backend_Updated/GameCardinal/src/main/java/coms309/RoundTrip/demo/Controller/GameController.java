package coms309.RoundTrip.demo.Controller;

import coms309.RoundTrip.demo.Exception.NoSuchPlayerException;
import coms309.RoundTrip.demo.Model.Game;
import coms309.RoundTrip.demo.Model.GameType;
import coms309.RoundTrip.demo.Model.GameUser;
import coms309.RoundTrip.demo.Model.TicTacToe;
import coms309.RoundTrip.demo.Repository.GameRepository;
import coms309.RoundTrip.demo.Repository.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.Optional;

@Controller
public class GameController
{

    @Autowired
    GameRepository gRepo;

    @Autowired
    GameUserRepository guRepo;

    @PostMapping("/new/TTT/{p1ID}/{p2ID}")
    public ResponseEntity<Game> newTTT(@PathVariable("p1ID") String p1, @PathVariable("p2ID") String p2)
    {
        TicTacToe game;
        try
        {
            Optional<GameUser> player1 = guRepo.findById(Integer.parseInt(p1));
            Optional<GameUser> player2 = guRepo.findById(Integer.parseInt(p2));
            if(player1.isEmpty())
            {
                System.out.println("====================Incorrect Player 1 passed============================");
                throw new NoSuchPlayerException("IDs passed for player 1 is not valid");
            }
            if(player2.isEmpty())
            {
                System.out.println("====================Incorrect Player 2 passed============================");
                throw new NoSuchPlayerException("IDs passed for players 2 is not valid");
            }
            else
            {
                game = new TicTacToe(GameType.TTT, player1.get(), player2.get(), "NEW"); // This gives Declaration not allowed here. Why?
                return ResponseEntity.ok(game);
            }
        }
        catch(NumberFormatException nme)
        {
            System.out.println(nme.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        catch(NoSuchPlayerException nse)
        {
            System.out.println(nse.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
