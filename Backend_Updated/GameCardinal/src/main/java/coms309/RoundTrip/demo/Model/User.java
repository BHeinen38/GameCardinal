package coms309.RoundTrip.demo.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import coms309.RoundTrip.demo.Utility;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * @author ranais
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserInterface
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "user_id")
    private int ID;

    @Column(name = "username", unique = true)
    private String username = "";

    @Column(name = "password")
    private String password = "";

    @Email
    @Column(name = "email")
    private String email = "";

    @Column(name = "name")
    private String name = "";

    @Column(name = "UserType")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private ProfilePicture picture;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private GameUser gamer;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "User", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@PrimaryKeyJoinColumn
    private MessagingUser mUser;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "user1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @PrimaryKeyJoinColumn
    private ImageUser imageUser;


    public User(String username, String password, String email, String name, UserType type, ProfilePicture picture, GameUser gamer)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.type = type;
//        System.out.println("========================================"+picture+"=======================================");
        this.picture = picture;
        this.gamer = gamer;


    }

    public User()
    {
    }

//    @OneToOne(mappedBy = "", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private ProfilePicture picture;

//    @OneToOne(mappedBy = "", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private ProfilePicture picture;

    public MessagingUser getmUser() {
		return mUser;
	}

	public void setmUser(MessagingUser mUser) {
		this.mUser = mUser;
	}

	public ImageUser getImageUser() {
		return imageUser;
	}

	public void setImageUser(ImageUser imageUser) {
		this.imageUser = imageUser;
	}

	public GameUser getGamer()
    {
        return gamer;
    }

    public void setGamer(GameUser gameUser)
    {
        this.gamer = gameUser;
    }

    public ProfilePicture getPicture()
    {
        return picture;
    }

    public void setPicture(ProfilePicture picture)
    {
        if (picture == null)
        {
            if (this.picture != null)
            {
                this.picture.setUser(null);
            }
        }
        else
        {
            picture.setUser(this);
        }
        this.picture = picture;
    }

    @Override
    public int getID()
    {
        return ID;
    }

    public void setID(int ID) {this.ID = ID;}

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String fName)
    {
        this.name = fName;
    }

    @Override
    public UserType getType()
    {
        return type;
    }

    public void setType(UserType type)
    {
        this.type = type;
    }

    public String toString()
    {
        return "User " + getID() + ": " + this.name;
    }
}

