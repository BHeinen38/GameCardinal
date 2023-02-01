package coms309.RoundTrip.demo.Controller;

import java.util.List;


import coms309.RoundTrip.demo.Model.Message;
import coms309.RoundTrip.demo.Repository.MessageRepository;
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
 * This is our message controller that will help us send messages between the different type of users 
 */
@RestController
public class MessageController {
	@Autowired
	MessageRepository message;

	
	private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
	/**
	 * REST END point that will save the messages to the local repository 
	 * @param Message takes in a message to be saved to the Message repository 
	 * @return RESPONSE entitiy ok that you have sucessfully saved a message 
	 * fails with error 404 if this post mapping fails 
	 */
    @PostMapping(value = "/msg")
    public ResponseEntity<Message> saveMessage(@RequestBody Message Message)
    {
        //System.out.println("CHICKEN: " + passedUser.toString());

      message.save(Message);
       
        return  ResponseEntity.ok(Message);
    }

	/**
	 * REST END POitn that will tell us all of our current messages that is saved to the repository 
	 * @return all of the messages that is saved in our repository 
	 * fails with 404 error if this get mapping fails and cant be found 
	 */
	@GetMapping(value = "/msg")
    public List<Message> getAllMessages()
    {
        return message.findAll();
    }
	/**
	 * This will create a Message from scratch  and saved to the repository 
	 * @param senderId takes in a senderId that is associated with the message
	 * @param recipientId takes in a recipientId that is associated with the message
	 * @param message takes in a message that will be saved to the message repository 
	 * @return the new message with the contructed fields 
	 * throws a 404 error if this post mapping fails
	 */
	@PostMapping("/send/new")
	public Message newMessage(@RequestBody int senderId, int recipientId, String message)
	 {
		Message newMessage = new Message(senderId, recipientId, message);
		newMessage.setSenderId(senderId);
		newMessage.setRecipientId(recipientId);
		newMessage.setMessage(message);
	        
	    this.message.save(newMessage);
	        
	    return newMessage;    
	 }
	/**
	 * This will return a message that is assiociatted with the corresponding id that is passed into it 
	 * @param id id that will tell us which message that we are wanting to return  
	 * @return the message with the correct id from the local repository 
	 * this get mapping will throw a 404 error if it is not able to be found 
	 */
	 @GetMapping(path = "/getMessageById/{id}")
	 public Message getUserById(@PathVariable int id){
	        return message.getById(id);
	        
	    }
	 
	 /**
	  * update the message by id from the local repository
	  * @param id will let us know what message we are trying to update by the corresponding id that is passed into the method  
	  * @param Message will return the updated message and save it to the repository 
	  * @return will return the updated message 
	  * this method will throw a  404 error if this mapping is not found 
	  */
	  @PutMapping("/msg/update/{id}")
	    public Message updateMessage(@PathVariable int id, @RequestBody Message Message){
	        Message message = this.message.getById(id);
	        if(message == null)
	            return null;
	        //we need to actually update the message that I will get back too
	        return this.message.getById(id);
	    } 
	  /**
	   * delete message by id from the repositroy 
	   * @param id takes in int to know what message that we are trying to delete 
	   * @return that you have sucessfully deleted the message 
	   * throws a 404 error if this delete mapping is not found 
	   */
	  @DeleteMapping(value = "/msg/{id}")
	  String deleteMessage(@PathVariable int id) {
		  message.deleteById(id);
		  return success;
		  
	  }


	
}