package coms309.RoundTrip.demo.Controller;

import coms309.RoundTrip.demo.Configuration.WebSocketConfigForMessage2;
import coms309.RoundTrip.demo.Model.Game;
import coms309.RoundTrip.demo.Model.GameUser;
import coms309.RoundTrip.demo.Model.TicTacToe;
import coms309.RoundTrip.demo.Repository.GameRepository;
import coms309.RoundTrip.demo.Repository.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

@ServerEndpoint(value = "/TTT/{playerID}/{gameID}/{updateString}")
@Controller
public class TicTacToeWebsocket
{
    private static GameUserRepository gameUserRepository;
    private static GameRepository gameRepository;

    @Autowired
    public void setGameUserRepository(GameUserRepository gameUserRepository) {TicTacToeWebsocket.gameUserRepository = gameUserRepository;}

    @Autowired
    public void setGameRepository(GameRepository gameRepository)
    {
        TicTacToeWebsocket.gameRepository = gameRepository;
    }

    private static Map<Session, GameUser> sessionGameUserMap = new Hashtable<>();
    private static Map<GameUser, Session> gameUserSessionMap = new Hashtable<>();

    private static Map<Session, TicTacToe> sessionGameMap = new Hashtable<>();
    private static Map<TicTacToe, Session> gameSessionMap = new Hashtable<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("playerID") int playerID, @PathParam("gameID") int gameID)
    {
        Optional<GameUser> player = gameUserRepository.findById(playerID);

        if(player.isPresent())
        {
            sessionGameUserMap.put(session, player.get());
            gameUserSessionMap.put(player.get(), session);

            Optional<Game> currentGame = gameRepository.findById(gameID);
            if(currentGame.isPresent())
            {
                sessionGameMap.put(session, (TicTacToe) currentGame.get());
                gameSessionMap.put((TicTacToe) currentGame.get(), session);
            }
            else
            {
                OnError(new Throwable("No Such Game Found"));
            }
            sendData("Connected To Game");
        }
        else
        {
            OnError(new Throwable("No Such User Found"));
        }
    }

    @OnClose
    public void OnClose(Session session)
    {
        sendData("Ending Game");
        GameUser player = sessionGameUserMap.get(session);
        sessionGameUserMap.remove(session);
        gameUserSessionMap.remove(player);

        sendData("Game Quit");
    }

    @OnMessage
    @MessageMapping(value = "/TTT/{playerID}/{gameID}/{updateString}")
    public void playMove(Session session, @DestinationVariable("updateString") String updateString)
    {
        TicTacToe game = sessionGameMap.get(session);

        char status = updateString.charAt(0);
        int playerTurn = updateString.charAt(10);
        String boardStatus = updateString.substring(1,10);

        if(status == 'G')
        {
            sendData(sessionGameMap.get(session).getCurrentBoard());
        }
        else if(status == 'U')
        {
            game.setCurrentBoard(updateString);
            gameRepository.save(game);

            sessionGameMap.remove(session);
            sessionGameMap.put(session, game);

            sendData(game.getCurrentBoard());
        }
        else if(status == 'V')
        {
            if(playerTurn == 1)
                game.setWinner(game.getPlayer1());
            else if(playerTurn == 2)
                game.setWinner(game.getPlayer2());
            else
                throw new IllegalStateException("Incorrect playerNumber passed");

            game.setCurrentBoard(updateString);
            gameRepository.save(game);

            sessionGameMap.remove(session);
            sessionGameMap.put(session, game);

            sendData(game.getCurrentBoard());
        }
        else
        {
            throw new IllegalStateException("Incorrect status code");
        }
    }

    @OnError
    public void OnError(Throwable throwable)
    {
        System.out.println("Errored Out " + Arrays.toString(throwable.getStackTrace()));
    }

    private void sendData(String message)
    {
        sessionGameUserMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage().toString());
                e.printStackTrace();
                }
        });

    }
}
