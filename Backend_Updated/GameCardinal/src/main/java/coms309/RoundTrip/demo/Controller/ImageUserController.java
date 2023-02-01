package coms309.RoundTrip.demo.Controller;

import java.util.List;


import coms309.RoundTrip.demo.Model.ImageUser;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Repository.ImageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is our level 2 user that will allow a user to be able to send mmessages and images but is unable to send games
 */
@RestController
public class ImageUserController {
	@Autowired
	ImageUserRepository imageRepository;

	/**
	 * sucess message that the controller has worked perfectly
	 */
	private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
	/**
	 * REST ENd point that will add a Image user
	 * @param image Takes in a image user that that will be saved to our repository 
	 * @return returns an okay ResponseEntitiy that the image user has been saved to out repository
	 * throws a 404 is the image post mapping fails
	 */
    @PostMapping(value = "/image")
    public ResponseEntity<ImageUser> saveImageUser(@RequestBody ImageUser image)
    {
        //System.out.println("CHICKEN: " + passedUser.toString());

      imageRepository.save(image);
       
        return  ResponseEntity.ok(image);
    }

	/**
	 * REST End point that will list all image user in our ImagingUserRepository  
	 * @return a all of the image User that is stored in our repository 
	 * throws a 404 error if the get mapping does not work
	 */
	@GetMapping(value = "/image")
    public List<ImageUser> getAllImageUsers()
    {
        return imageRepository.findAll();
    }
	/**
	 * this is really useless because we already have a controller that will save a imageUser 
	 * @param u takes in a user that we can then convert into a messaging user 
	 * @return the new image user 
	 * throws a 404 error if this post mapping does not work
	 */
	@PostMapping("/send/new/imageUser")
	public ImageUser newImageUser(@RequestBody User u)
	 {
		ImageUser newImage = new ImageUser(u);
		
	        
	    return newImage;    
	 }
	
	
	 
	 /**
	  * updates the image user 
	  * @param id takes in id to know what to update by 
	  * @param image takes in image to update 
	  * @return returns the new image user 
	  */
	  @PutMapping("/image/Update")
	    public ImageUser updateImage(@PathVariable int id, @RequestBody ImageUser image){
	        ImageUser iu = imageRepository.getById(id);
	        
	        if(iu == null)
	            return null;
	        imageRepository.save(image);
	        return this.imageRepository.getById(id);
	    } 
	  /**
	   * rest end point that deletes an image user by id 
	   * @param id takes in a int Integer to know what image user to delete 
	   * @return that you have sucessfully deleted a image user 
	   * This Deletemapping will throw a 404 error if this fails 
	   */
	  @DeleteMapping(value = "/image/{id}")
	  String deleteImageUser(@PathVariable int id) {
		 imageRepository.deleteById(id);
		  return success;
	  }


	
}