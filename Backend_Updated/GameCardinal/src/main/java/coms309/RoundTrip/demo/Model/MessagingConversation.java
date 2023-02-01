package coms309.RoundTrip.demo.Model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import coms309.RoundTrip.demo.Model.Message;
import coms309.RoundTrip.demo.Model.MessagingUser;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "MessagingUserConversation")
public class MessagingConversation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MessageId")
	private int conversationId;
	
	
	
	
	@Column(name = "lastSentMessage")
	private String lastSentMessage;
	
	
	/**
	 * many messaging users can have many conversations 
	 */
	@ManyToMany
	//@JoinColumn(nullable = false)
	@JsonIgnore
	List <MessagingUser> messagingUser;


	/**
	 * one messaging user can have many messaging conversations 
	 */
	@OneToMany(mappedBy = "messagingConversation")
	 @JsonIgnore
	private List<Message> listOfMessages;
	
	
	
/**
 * contructs new messaging conversation
 * @param conversationId id
 * @param lastSentMessage last sent message 
 */
	public MessagingConversation(int conversationId, String lastSentMessage) {
		super();
		this.conversationId = conversationId;
		this.lastSentMessage = lastSentMessage;
	}
/**
 * alot of the same stuff
 * @param conversationId
 * @param lastSentMessage
 * @param messagingUser
 */
	public MessagingConversation(int conversationId, String lastSentMessage, List<MessagingUser> messagingUser) {
		super();
		this.conversationId = conversationId;
		this.lastSentMessage = lastSentMessage;
		this.messagingUser = messagingUser;
	}
	/**
	 * creates a list of messaging users and list of media 
	 */
	public MessagingConversation() {
		messagingUser = new ArrayList<>();
		listOfMessages = new ArrayList<>();
	}
/**
 * 
 * @return this will return the conversations id
 */
	public long getConversationId() {
		return conversationId;
	}
/**
 * 
 * @param conversationId takes in a int and makes in messagingconversations id
 */
	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}
/**
 * 
 * @return will return the last sent message 
 */
	public String getLastSentMessage() {
		return lastSentMessage;
	}
/**
 * d
 * @param lastSentMessage takes in a string and makes it our lastSentMessages string 
 */
	public void setLastSentMessage(String lastSentMessage) {
		this.lastSentMessage = lastSentMessage;
	}
/**
 * this will return list of messaging user for our many to many since 
 * @return
 */
	public List<MessagingUser> getMessagingUser() {
		return messagingUser;
	}
/**
 * this is how we can set a new list of messaging user 
 * @param messagingUser
 */
	public void setMessagingUser(List<MessagingUser> messagingUser) {
		this.messagingUser = messagingUser;
	}
	/**
	 * this is how we can add a new messaging user 
	 * @param messagingUser
	 */
	public void addMessageUser(MessagingUser messagingUser) {
		this.messagingUser.add(messagingUser);
	}
/**
 * 
 * @return a list of messages 
 */
	public List<Message> getListOfMessages() {
		return listOfMessages;
	}
/**
 * 
 * @param listOfMessages how we can set a new list of Messages 
 */
	public void setListOfMessages(List<Message> listOfMessages) {
		this.listOfMessages = listOfMessages;
	}


	/**
	 * how we can add a a new message to our  list of messages
	 * @param message
	 */
	public void addMessage(Message message) {
		this.listOfMessages.add(message);
	}


}
