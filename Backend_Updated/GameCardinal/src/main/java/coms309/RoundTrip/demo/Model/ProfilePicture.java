package coms309.RoundTrip.demo.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * @author ranais
 *
 * BROKEN: https://stackoverflow.com/questions/39432871/hibernate-attempted-to-assign-id-from-null-one-to-one-property/39490626
 */
@Entity
@Table(name = "ProfilePicture")
public class ProfilePicture
{
    @Id
    private int id;

    @Column(name = "location")
    private String location;
    /**
     * one user can only have have one profile picture 
     */
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    @MapsId
    @JoinColumn(name = "picture")
    private User user;

    public ProfilePicture(){}
    /**
     * this is how we can construct a profile picture 
     * @param id takes in a int and makes it the profile picture id 
     * @param location takes in a location and makes it the profile pictures location
     * @param user takes in a user and assigns it to this profile picture 
     */
    public ProfilePicture(int id, String location, User user)
    {
        this.id = id;
        this.location = location;
        this.user = user;
    }

    /**
     * 
     * @return this will return our profile pictures id
     */
    public int getId()
    {
        return id;
    }
/**
 * 
 * @param id this takes in an int and makes it our profile picture id 
 */
    public void setId(int id)
    {
        this.id = id;
    }
/**
 * 
 * @return this will return our profile pictures user
 */
    public User getUser()
    {
        return user;
    }
/**
 * 
 * @param user this is how we can set a user for for our profile picture class 
 */
    public void setUser(User user)
    {
        this.user = user;
    }
    /**
     * 
     * @return this will return our profile pictures id 
     */
    public String getLocation()
    {
        return location;
    }
/**
 * 
 * @param picLocation this takes in a string and makes it our profiles pictures location we can set our location 
 */
    public void setLocation(String picLocation)
    {
        this.location = picLocation;
    }


}
