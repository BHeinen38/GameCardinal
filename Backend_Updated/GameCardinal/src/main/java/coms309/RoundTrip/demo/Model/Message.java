package coms309.RoundTrip.demo.Model;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MessageId")
	private int messageId;
	
	@Column(name = "SenderId")
	private int senderId;
	
	@Column(name = "recipientId")
	private int recipientId;
	
	@Column(name = "message")
	private String message;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timeStamp")
	private Date timeStamp = new Date();
	
	/**
	 * there are many messages that can correspond to one messaging conversation 
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	private MessagingConversation messagingConversation;
	
	

/**
 * Creates a new message 
 * @param senderId get the  sender id 
 * @param recipientId gets the recipients id 
 * @param message also takes in the message that is wanting to send 
 */
	public Message(int senderId, int recipientId, String message) {
		super();
		
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.message = message;
		
	}
	/**
	 * emtpy constructor 
	 */
	public Message() {
		
	}

/**
 * this is how we get our message id 
 * @return returns the  messages id
 */
	public int getMessageId() {
		return messageId;
	}

/**
 * this is how we can set the message id
 * @param messageId takes in a int and makes it the messages id 
 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

/**
 * this is how we get the sender id 
 * @return returns messages sender id 
 */
	public long getSenderId() {
		return senderId;
	}

/**
 * this is how we can set the senders id 
 * @param senderId takes in senders id and makes it messages sender id 
 */
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

/**
 * gets recipitnes id 
 * @return returns messages reciepient id
 */
	public long getRecipientId() {
		return recipientId;
	}

/**
 * setting recipients id 
 * @param recipientId takes in int and makes it messages recipients id 
 */
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

/**
 * the local message
 * @return messages message
 */
	public String getMessage() {
		return message;
	}

/**
 * sets the local message
 * @param message takes in message and makes it message 
 */
	public void setMessage(String message) {
		this.message = message;
	}

/**
 * 
 * @return messages timestamp
 */
	public Date getTimeStamp() {
		return timeStamp;
	}

/**
 * 
 * @param timeStamp takes in a time stamp and makes it messages timestamp 
 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

/**
 * 
 * @return messaging conversation 
 */
	public MessagingConversation getMessagingConversation() {
		return messagingConversation;
	}

/**
 * 
 * @param messagingConversation sets this messagingconversation and makes it our messaging conversation
 */
	public void setMessagingConversation(MessagingConversation messagingConversation) {
		this.messagingConversation = messagingConversation;
	}
	
	
	

}
