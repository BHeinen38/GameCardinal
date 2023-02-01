package coms309.RoundTrip.demo.Controller;

import java.util.List;

import coms309.RoundTrip.demo.Model.MessagingConversation;
import coms309.RoundTrip.demo.Repository.MessagingConversationRepository;
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
 * this is the controller that will control our messaging conversations 
 */
@RestController
public class MessagingConversationController {
	
	
	@Autowired
    MessagingConversationRepository mCR;
	
	
	private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    /**
     * this controller will save a messagingConversation to our local repositroy 
     * @param convo takes in a Messaging conversation that we will be able to save to our repository 
     * @return the saved messaging conversation 
     * this post mapping will throw a 404 error if this controller is not found 
     */
    @PostMapping(value = "/saveConvo")
    public ResponseEntity<MessagingConversation> saveMessageConvo(@RequestBody MessagingConversation convo)
    {
        //System.out.println("CHICKEN: " + passedUser.toString());

        mCR.save(convo);
        

        return  ResponseEntity.ok(convo);
    }

    /**
     * this will return all of the saved conversations that is saved in our repository 
     * @return s all of our conversations that is saved in our repository 
     * this controller will throw a 404 error if this method is not found 
     */
    @GetMapping(path = "/messagingConversations")
    List<MessagingConversation> getAllMessagingConversations(){
        return mCR.findAll();
        
    }
    /**
     * Searches throw our messaging conversations and returns the one corresponding to the correct id 
     * @param id takes in a integer id that we will be able to getById
     * @return the messagingConversation that corresponds with the correct id 
     * this will throw a 404 error if it is not able to be found 
     */
    @GetMapping(path = "messagingConversation/{id}")
    MessagingConversation getMessagingConvoById(@PathVariable int id) {
    	return mCR.getById(id);
    	
    }
    /**
     * creates a new conv with a constructor 
     * @param message takes in a messaging conversation that we would like to save to our repository 
     * @return that your have succefully created the messaging conversation 
     * this mapping will throw a 404 error if it is not found 
     */
    @PostMapping(path = "/messagingConversations")
    String createConvo(MessagingConversation message) {
    	if(message == null) {
    		return failure;
    	}
    	mCR.save(message);
    	return success;
    }
    /**
     * this is how we can update a messaging connversation by id and save it to the local repository 
     * @param id takes in Integer to know what messaging conversation that we are trying to update
     * @param request takes in messaging conversation to update
     * @return the messaging conversation by id 
     * this mapping will throw a 404 error if it is not found 
     */
    @PutMapping("/messagingConversation/{id}")
    MessagingConversation updateConvo(@PathVariable int id, @RequestBody MessagingConversation request) {
    	MessagingConversation message = mCR.getById(id);
    	if(message == null) {
    		return null;
    	}
        //maybe have a set id method that would allow us to do this?
    	mCR.save(request);
    	return mCR.getById(id);
    	
    }
    /**
     * This is how we can delete a messagingConversation by id 
     * @param id takes in Integer to know what messagingConversation to deleteBy
     * @return that you have sucessfully deleted the messaging Conversation 
     * this mapping will throw a 404 error if it is not found 
     */
    @DeleteMapping(value = "/messageConversation/{id}")
	  String deleteMessageConversation(@PathVariable int id) {
		  mCR.deleteById(id);
		  return success;
		  
	  }

}
