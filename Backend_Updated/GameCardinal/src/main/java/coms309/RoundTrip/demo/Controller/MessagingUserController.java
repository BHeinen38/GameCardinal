package coms309.RoundTrip.demo.Controller;

import java.util.List;


import coms309.RoundTrip.demo.Model.MessagingUser;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Repository.MessagingUserRepository;
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
 * This is our first level user that will allow us to send messages from user to user 
 */
@RestController
public class MessagingUserController {
	@Autowired
	MessagingUserRepository messagingUserRepository;

	
	private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
	/**
	 * REST END POINT that takes in passed user 
	 * @param passedUser passed user that will be save to the repository 
	 * @return REsponeEntitiy ok that we have saved the user 
	 * this mapping will throw a 404 error if it is not found 
	 */
    @PostMapping(value = "/msgUser")
    public ResponseEntity<MessagingUser> saveUser(@RequestBody MessagingUser passedUser)
    {
        //System.out.println("CHICKEN: " + passedUser.toString());

       messagingUserRepository.save(passedUser);
       
        return  ResponseEntity.ok(passedUser);
    }

	/**
	 * REST END  point that will list all of the possible messaging users that is saved in our repository.
	 * @return all of our messagingUser that is saved in our local repository
	 * this mapping will throw a 404 error if it is not found 
	 */
	@GetMapping(value = "/listAllMsgUsers")
    public List<MessagingUser> getAllMessengingUsers()
    {
        return messagingUserRepository.findAll();
    }
	/**
	 * This is really a useless controller but another option for creating a new messagingUser from a regular user 
	 * @param user takes in user to be saved to the MessagingUserRepository 
	 * @return the newly created messaging user 
	 * this mapping will throw a 404 error if it is not found 
	 */
	@PostMapping("/newMessageUser")
	public MessagingUser Messaging(@RequestBody User user)
	 {
		MessagingUser messagingUser = new MessagingUser(user);
	    messagingUser.setUser(user);
	        
	    messagingUserRepository.save(messagingUser);
	        
	    return messagingUser;    
	 }
	/**
	 * REST END POINT THAT gets messaging user by id 
	 * @param id INTEGER int the returns messaging user by id 
	 * @return returns the messaging user with the correct id 
	 * this mapping will throw a 404 error if it is not found 
	 */
	 @GetMapping(path = "/messaginguser/{id}")
	 MessagingUser getUserById(@PathVariable int id){
	        return messagingUserRepository.getById(id);
	    }
	 
	 /**
	  * REST END point that will update messaging user by id a messaging user 
	  * @param id takes in a int to know what messagingUserthat we are trying to update 
	  * @param request messaging user and will save the updated messaginguser
	  * @return the new updated messaging user by id 
	  * this mapping will throw a 404 error if it is not found 
	  */
	  @PutMapping("/users/update/{id}")
	    MessagingUser updateMessageUser(@PathVariable int id, @RequestBody MessagingUser request){
	        MessagingUser user = messagingUserRepository.getById(id);
	        if(user == null)
	            return null;
	        messagingUserRepository.save(request);
	        return messagingUserRepository.getById(id);
	    } 
	  /**
	   * REST END point that will delete by ID 
	   * @param id Id to know what messaging user to delete 
	   * @return sucess showing that you successfully deleted that messaginguser
	   * this mapping will throw a 404 error if it is not found  
	   */
	  @DeleteMapping(value = "/messagingUser/{id}")
	  String deleteMessagingUser(@PathVariable int id) {
		  messagingUserRepository.deleteById(id);
		  return success;
		  
	  }


	
}
