package coms309.RoundTrip.demo;

import coms309.RoundTrip.demo.Controller.GameUserController;
import coms309.RoundTrip.demo.Model.GameUser;
import coms309.RoundTrip.demo.Model.ProfilePicture;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Model.UserType;
import coms309.RoundTrip.demo.Repository.GameUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestGameUser
{
//
//    @Mock
//    GameUserController guControl;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGameUserCreate()
    {

        GameUserController guControl = mock(GameUserController.class);

        GameUser builder = new GameUser(1, new User("BuilderBob", "CanWeDoIt", "bob@builder.com", "Bob", UserType.GMUSR, new ProfilePicture(), new GameUser()), new ArrayList<>());
        GameUser winston = new GameUser(2, new User("Winston", "YesWeCan", "winston@builder.com", "Winston", UserType.MSGUSR, new ProfilePicture(1, "Jupiter", new User()), new GameUser()), new ArrayList<>());

        ArrayList<GameUser> gamers = new ArrayList<>();
        gamers.add(builder);
        gamers.add(winston);

        when(guControl.getGameUser(1)).thenReturn(ResponseEntity.ok(gamers.get(0)));
        when(guControl.getGameUser(2)).thenReturn(ResponseEntity.ok(gamers.get(1)));

        assertEquals(guControl.getGameUser(1), ResponseEntity.ok(builder));
        assertEquals(guControl.getGameUser(2), ResponseEntity.ok(winston));
    }

    @Test
    public void testDeleteUser()
    {
        GameUserController guControl = mock(GameUserController.class);

        GameUser builder = new GameUser(1, new User("BuilderBob", "CanWeDoIt", "bob@builder.com", "Bob", UserType.GMUSR, new ProfilePicture(), new GameUser()), new ArrayList<>());
        GameUser winston = new GameUser(2, new User("Winston", "YesWeCan", "winston@builder.com", "Winston", UserType.MSGUSR, new ProfilePicture(1, "Jupiter", new User()), new GameUser()), new ArrayList<>());

        ArrayList<GameUser> gamers = new ArrayList<>();
        gamers.add(builder);
        gamers.add(winston);

        when(guControl.deleteGameUser(1)).thenReturn(ResponseEntity.ok("Deleted 1"));
        when(guControl.deleteGameUser(2)).thenReturn(ResponseEntity.ok("Deleted 2"));

        assertEquals(guControl.deleteGameUser(1), ResponseEntity.ok("Deleted 1"));
        assertEquals(guControl.deleteGameUser(2), ResponseEntity.ok("Deleted 2"));
    }





}
