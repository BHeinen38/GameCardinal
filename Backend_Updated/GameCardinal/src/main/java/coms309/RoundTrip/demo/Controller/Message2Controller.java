package coms309.RoundTrip.demo.Controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import coms309.RoundTrip.demo.Model.Message2;
import coms309.RoundTrip.demo.Repository.Message2Repository;


@Controller      
@ServerEndpoint(value = "/chat/{username}")  // the websocket url is ws://localhost/chat/{username}
public class Message2Controller {

  
	private static Message2Repository msgRepo; 

	//we have a static variable that will make it work .
	@Autowired
	public void setMessageRepository(Message2Repository repo) {
		msgRepo = repo;  // we are setting the static variable
	}

	//this is needed to store information on session and user
	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(Message2Controller.class);


	@OnOpen
	public void OpeningTheMessagingFeature(Session session, @PathParam("username") String username) 
      throws IOException {
		//telling the logger that we have opened a chat
		logger.info("Entered into Open");

		//putting the username and session into our personal hashtable to keep data organized and useful
		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		//getting the chat history for the specific user
		sendMessageToYouSpecificUser(username, getChatHistory());
		
		//letting the users know that we have joined the chat
		String message = "User:" + username + " has Joined the Chat";
		sendMessageToWholeChatRoom(message);
	}


	@OnMessage
	public void Messaging(Session session, String message){

		//putting the new information into our log 
		logger.info("Entered into Message: Got Message:" + message);
		String username = sessionUsernameMap.get(session);

		//this is  the most basic way to have direct messaging and this is the way that discord does it so we are too.
		if (message.startsWith("@")) {
			String directMessagePart = message.split(" ")[0].substring(1); 

      // send the message to the sender and receiver
	  		sendMessageToYouSpecificUser(directMessagePart, "[Direct] " + username + ": " + message);
			  sendMessageToYouSpecificUser(username, "[Direct] " + username + ": " + message);

		} 
    else { // broadcast
		sendMessageToWholeChatRoom(username + ": " + message);
		}
		//saving the message to our history
		msgRepo.save(new Message2(username, message));

	}


	@OnClose
	public void ClosingTheMessagingFeature(Session session) throws IOException {
		//letting the log know that we are closing out of messaging
		logger.info("Entered into Close");

		//removing the session and username from our data.
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

    	// broadcase that the user disconnected
		String message = username + " disconnected";
		sendMessageToWholeChatRoom(message);
	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessageToYouSpecificUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} 
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}


	private void sendMessageToWholeChatRoom(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} 
      catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}
	

  // Gets the Chat history from the repository
	private String getChatHistory() {
		List<Message2> messages = msgRepo.findAll();
    
    // convert the list to a string
		StringBuilder sb = new StringBuilder();
		if(messages != null && messages.size() != 0) {
			for (Message2 message : messages) {
				sb.append(message.getUserName() + ": " + message.getContent() + "\n");
			}
		}
		return sb.toString();
	}

} // end of Class

