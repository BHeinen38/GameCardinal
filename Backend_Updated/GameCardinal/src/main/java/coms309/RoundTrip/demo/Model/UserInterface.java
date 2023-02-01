package coms309.RoundTrip.demo.Model;

/**
 * @author ranais
 */
public interface UserInterface
{
    int getID();

    String getUsername();
    void setUsername(String username);

    String getPassword();
    void setPassword(String password);

    String getEmail();
    void setEmail(String email);

    String getName();
    void setName(String fName);


    UserType getType();
    void setType(UserType type);


}

