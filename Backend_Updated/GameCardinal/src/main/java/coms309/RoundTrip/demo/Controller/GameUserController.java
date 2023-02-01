package coms309.RoundTrip.demo.Controller;

import coms309.RoundTrip.demo.Model.GameUser;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Repository.GameRepository;
import coms309.RoundTrip.demo.Repository.GameUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author ranais
 * Controller that helps manage GameUsers
 */

@RestController
public class GameUserController
{
    @Autowired
    GameUserRepository gur;

    /**
     * REST endpoint that gets a User using their ID
     * @param ID integer value that is the id of the user to get
     * @return a HTTP ResponseEntity object that has the value of the User you requested or null if he isn't present.
     * The method returns code 404 if that is the case.
     */
    @GetMapping("/gameUser/{id}")
    @ApiOperation(value = "REST endpoint that gets a User using their ID", response = ResponseEntity.class)
    public ResponseEntity<GameUser> getGameUser(@PathVariable("id") int ID)
    {
        Optional<GameUser> ogu = gur.findById(ID);

        if(ogu.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status((HttpStatus.OK)).body(ogu.get());
    }

    /**
     * REST Endpoint that takes in a GameUser and saves it in the database
     * @param passedUser A GameUser Object
     * @return returns an HTTP ResponseEntity that has code 200 if the save was successful
     */
    @PostMapping(value = "/gameUser")
    @ApiOperation(value = "REST Endpoint  that takes in a GameUser and saves im in the database", response = ResponseEntity.class)
    public ResponseEntity<GameUser> saveGameUser(@RequestBody GameUser passedUser)
    {
        passedUser.setGameList(new ArrayList<>());
        gur.save(passedUser);

        return ResponseEntity.ok(passedUser);
    }

    /**
     * REST endpoint that deletes a GameUser from the database
     * @param ID the ID of the user to be deleted
     * @return returns an HTTP ResponseEntity that contains a String if it is successfully deleted
     */
    @DeleteMapping(value = "/gameUser")
    @ApiOperation(value = "REST endpoint that deletes a GameUser from the database and returns a String saying delete successful", response = ResponseEntity.class)
    public ResponseEntity<String> deleteGameUser(@RequestBody int ID)
    {
        gur.deleteById(ID);

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
    }


}
