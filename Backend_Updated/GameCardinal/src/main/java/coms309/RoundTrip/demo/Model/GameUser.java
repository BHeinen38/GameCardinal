package coms309.RoundTrip.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ranais
 */
@Entity
@Table
public class GameUser
{
    @Id
    @Column(name = "gameID")
    private int ID;
    /**
     * one user can only by one game user 
     */
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    @MapsId
    @JoinColumn(name = "gameUser")
    private User user;
    /**
     * each Game user can have a list of many games
     */
    @OneToMany
    private List<Game> gameList = new ArrayList<>();

    public GameUser(){}

    public GameUser(int ID, User user, List<Game> gameList)
    {
        this.ID = ID;
        this.user = user;
        this.gameList = gameList;
    }
    /**
     * this is how we are able to get our user for one to one mapping with gameUser
     * @return the user from the local class
     */
    public User getUser()
    {
        return user;
    }
    /**
     * 
     * @param user this is how we can set the user and make it 
     */
    public void setUser(User user)
    {
        this.user = user;
    }
/**
 * this is one to many mapping with GameList
 * @return returns a list of games 
 */
    public List<Game> getGameList()
    {
        return gameList;
    }
    /**
     * this is how we set game list it really should be a add game 
     * @param gameList we need to possible make the add game list here 
     */
    public void setGameList(List<Game> gameList)
    {
        this.gameList = gameList;
    }
    /**
     * this is how we can access our game user id 
     * @return returns the ID
     */
    public int getID()
    {
        return ID;
    }
    /**
     * This is how we can set the Game Id 
     * @param ID takes in Id and makes the game user this id 
     */
    public void setID(int ID)
    {
        this.ID = ID;
    }
    /**
     * this is how  we add games 
     * @param toAdd takes in game  to add
     * @return will return new game with the added game 
     */
    public List<Game> addToGameList(Game toAdd)
    {
        gameList.add(toAdd);

        return gameList;
    }
}
