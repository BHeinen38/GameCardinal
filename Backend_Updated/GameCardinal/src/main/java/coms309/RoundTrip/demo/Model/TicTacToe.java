package coms309.RoundTrip.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TicTacToe extends Game
{
    @Column(name = "CurrentState")
    String currentBoard = "";

    public TicTacToe(String currentBoard)
    {
        this.currentBoard = currentBoard;
    }

    public TicTacToe(GameType gametype, GameUser player2, GameUser player1, String currentBoard)
    {
        super(gametype, player2, player1);
        this.currentBoard = currentBoard;
    }

    public String getCurrentBoard()
    {
        return currentBoard;
    }

    public void setCurrentBoard(String currentBoard)
    {
        this.currentBoard = currentBoard;
    }


}
