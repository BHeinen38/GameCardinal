package coms309.RoundTrip.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author ranais
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "GameType", discriminatorType = DiscriminatorType.STRING)
public class Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gameID", nullable = false, unique = true)
    private int gameID;

    @Column(name = "game")
    @Enumerated
    private GameType gametype;

    @OneToOne
    @JoinColumn(name = "winner")
    private GameUser winner;


    @OneToOne
    @JoinColumn(name = "other_player")
    private GameUser player2;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private GameUser player1;

    public Game(){}
/**
 * thee created a game 
 * @param gametype create a game using this gametype
 * @param player2 create a gamme using this player
 * @param player1 create a gamme using this player 
 */
    public Game(GameType gametype, GameUser player2, GameUser player1)
    {
        this.gametype = gametype;
        this.player2 = player2;
        this.player1 = player1;
    }
    /**
     * 
     * @return this will return the GameId
     */
    public Integer getGameID()
    {
        return gameID;
    }
    /**
     * 
     * @param id this is how  we can set a game id 
     */
    public void setGameID(Integer id)
    {
        this.gameID = id;
    }

    /**
     * 
     * @return this will return a the winner 
     */
    public GameUser getWinner()
    {
        return winner;
    }
    /**
     * 
     * @param winner this is how we can set a winner 
     */
    public void setWinner(GameUser winner)
    {
        this.winner = winner;
    }
    /**
     * 
     * @return This is how we are able to acces our game type
     */
    public GameType getGametype()
    {
        return gametype;
    }
    /**
     * 
     * @param type this is how we are able to set our game type 
     */
    public void setGametype(GameType type)
    {
        this.gametype = type;
    }
    /**
     * 
     * @return this is how we are able to access our opponent player 
     */
    public GameUser getPlayer2()
    {
        return player2;
    }
    /**
     * 
     * @param otherPlayer this  is how we able to ser the opponent 
     */
    public void setPlayer2(GameUser otherPlayer)
    {
        this.player2 = otherPlayer;
    }
    /**
     * 
     * @return this is how we are able to get player one 
     */
    public GameUser getPlayer1()
    {
        return player1;
    }
    /**
     * 
     * @param player1 this is how we are able to set our player one 
     */
    public void setPlayer1(GameUser player1)
    {
        this.player1 = player1;
    }
}
