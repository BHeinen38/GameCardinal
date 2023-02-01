package coms309.RoundTrip.demo.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import coms309.RoundTrip.demo.Model.ProfilePicture;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Repository.ProfilePictureRepository;
import coms309.RoundTrip.demo.Repository.UserRepository;
import coms309.RoundTrip.demo.Service.FileSystemStorageService;
import coms309.RoundTrip.demo.Service.UserService;

import coms309.RoundTrip.demo.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ranais
 *
 * picID and userID are the same thing
 */
@RestController
public class UserController
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfilePictureRepository picRepository;

    @Autowired
    UserService uService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getParticularUser(@PathVariable("id") String id)
    {
        Optional<User> oUser= userRepository.findById(Integer.parseInt(id));
        User u = new User();

        if(oUser.isPresent())
            u = oUser.get();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @GetMapping(value = "/user/all")
    public List<User> getAllData()
    {
        return userRepository.findAll();
    }
    /**
     * REST endpoint that gets a User using their ID 
     * @param passedUser User user value that is the user of the user to pass 
     * @return a passed user 
     * this method returns a 404 code if this does not work 
     */
    @PostMapping(value = "/user")
    public ResponseEntity<User> saveUser(@RequestBody User passedUser)
    {
        User existing = userRepository.findByUsername(passedUser.getUsername());

        if(existing == null)
        {
            userRepository.save(passedUser);
            return  ResponseEntity.ok(passedUser);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(existing);
        }


    }
    /**
     * REST End point that sets user profile pic with the user.
     * @param userID Integer id that is the id of the user to set the profile picture to
     * @return returns http status that is okay if  the method passes 
     * this mapping will throw a 404 error if it is not found 
     */
    @PostMapping("/setUserToPic/{userID}/{picID}")
    public ResponseEntity<String> setUserPic (@PathVariable int userID)
    {
        return ResponseEntity.status(HttpStatus.OK).body(uService.setUserPic(userID).toString());

    }
    /**
     * REST END point that will check if a user is logged in
     * @param passedUser takes in a user to see if the user has been in the system 
     * @return https status ok 
     * this mapping will throw a 404 error if it is not found 
     */
    @PostMapping("user/login")
    public ResponseEntity<User> checkLogin(@RequestBody User passedUser)
    {
        User storedUser = userRepository.findByUsername(passedUser.getUsername());
            if(storedUser == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        if (storedUser.getPassword().equals(passedUser.getPassword()))
        {
            return ResponseEntity.status(HttpStatus.OK).body(storedUser);
        }

        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
