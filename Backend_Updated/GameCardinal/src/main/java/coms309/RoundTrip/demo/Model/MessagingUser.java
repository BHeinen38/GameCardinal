package coms309.RoundTrip.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class MessagingUser
{
	
	@Id
	@Column(name = "Messaging_ID")
	private int id;

	@JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    @MapsId
    @JoinColumn(name = "messageUser")
    private User User;

	
	/*
	 * This is our onetomany mapping to messaging conversation
	 * 
	 */
	
	 @ManyToMany(mappedBy = "messagingUser")
	 @JsonIgnore
	private List<MessagingConversation> messagingConversation;

	 
	 /**
	  * returns messaging users id 
	  * @return
	  */
	public int getId() {
		return id;
	}
/**
 * this is how we can set a messaging user id 
 * @param id takes in a int and makes it maesssaging users int 
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * construct a messsaging user with a user 
 * @param user takes in user and makes messaging user from it 
 */
	public MessagingUser(User user) {
		this.User = user;
	}
	/**
	 * creates a new messaging conversation list 
	 */
	public MessagingUser() {
		messagingConversation = new ArrayList<>();
	}
	
	
	/**
	 * how we can set user for one to one mapping 
	 * @param user takes in user and makes messaging user's user 
	 */
	public void setUser(User user) {
		this.User = user;
	}
	/**
	 * returns messaging user's user
	 * @return
	 */
	public User getUser() {
		return User;
	}
	/**
	 * 
	 * @return messaging conversation 
	 */
	public List<MessagingConversation> getMessages(){
		return messagingConversation;
	}
/**
 * 
 * @param messagingConversation makes this list messaging user list
 */
	public void setMessages(List<MessagingConversation> messagingConversation) {
		this.messagingConversation = messagingConversation;
		
	}
/**
 * add messaging convo to our current list 
 * @param messagingConversation takes in messagingconversation and adds it to the list 
 */
	public void addMessages(MessagingConversation messagingConversation) {
		this.messagingConversation.add(messagingConversation);
	}
	
}
