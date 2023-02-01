package coms309.RoundTrip.demo.Controller;

import coms309.RoundTrip.demo.Model.ProfilePicture;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Repository.ProfilePictureRepository;
import coms309.RoundTrip.demo.Repository.UserRepository;
import coms309.RoundTrip.demo.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

/**
 * @author ranais
 * Class that deals with API calls for the ProfilePicture class
 */
@EnableSwagger2
@RestController
public class PictureController
{
    /**
     * equaling repositories
     */
    public PictureController(UserRepository ur)
    {
        userRepository = ur;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfilePictureRepository picRepository;

    @Autowired
    private UserService uService;

    /**
     * REST END POINT that will get user ID tied to picture ID
     * @param userID Takes in user id to map to userID and make sure that we know that the user belongs to this picture
     * @return http status founnd if the picture is empty.
     * return error 404 if this controller does not work
     */
    @GetMapping("/picture")
    public ResponseEntity<ProfilePicture> getPic(@RequestBody int userID)
    {
        Optional<ProfilePicture> op = picRepository.findById(userID);

        if(op.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(op.get());
    }
    /**
     * REST END POINT that will take in a profile picture
     * @param pp takes in a picture to save
     * @return returns http status okay that you have saved pic to repositroy
     * tthis mapping will throw a 404 error if it is not found
     */
    @PostMapping("/picture")
    @ApiOperation(value = "REST Endpoint that saves the users picture and returns a HTTP ResponseEntity with the saved pic", response = ResponseEntity.class )
    public ResponseEntity<ProfilePicture> savePic (@RequestBody ProfilePicture pp)
    {
        if(userRepository.findByUsername(pp.getUser().getUsername()) == null)
            picRepository.save(pp);
        else
        {
            pp.getUser().setPicture(pp);
            userRepository.save(pp.getUser());
            picRepository.save(pp);
        }

        return ResponseEntity.status(HttpStatus.OK).body(pp);
    }

//    @PostMapping("/picture/user")
//    public ResponseEntity<ProfilePicture> savePicWithUser (@RequestBody ProfilePicture pp, @PathVariable String UserID)
//    {
//        pp.getUser().setPicture(pp);
//        picRepository.save(pp);
//
//        return ResponseEntity.status(HttpStatus.OK).body(pp);
//    }

//    @PutMapping("/picture")
//    public ResponseEntity<ProfilePicture> updatePic (@RequestBody String userID, @RequestBody String picLocation)
//    {
//        deletePic(userID);
//        return savePic(userID, picLocation);
//    }
    /**
     * REST END POINT that takes in userID and will delete from the repositroy
     * @param userID String int user id to map to picture
     * @return https status okay that you have deleted the pic
     * this mapping will throw a 404 error if it is not found
     */
    @DeleteMapping("/picture/{userID}")
    @ApiOperation(value = "takes in the ID of the picture that needs to be deleted", response = ResponseEntity.class)
    public ResponseEntity<String> deletePic (@PathVariable String userID)
    {
        ProfilePicture p = picRepository.getById(Integer.parseInt(userID));
        picRepository.delete(p);

        return ResponseEntity.status(HttpStatus.OK).body("Successffuullyy Deleted");
    }
}
