package coms309.RoundTrip.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class ImageUser
{
	@Id
	@Column(name = "imageUserId")
	private int id;
	
	/**
	 * each messaging user can only correspond to one user 
	 */
	@JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    @MapsId
    @JoinColumn(name = "imageUser")
    private User user1;
	
	@OneToMany(mappedBy = "imageUser")
	 @JsonIgnore
	private List<Media> listOfMedia;
	/**
	 * this is how we are able set one to one mapping with user 
	 * @param user creates an image user using a user 
	 */
	public ImageUser(User user) {
		super();
		user1 = user;
	}

	/**
	 * this will construct a ner list of media for a one to many mapping 
	 */
	public ImageUser() {
		listOfMedia = new ArrayList<>();
	}

	
	/**
	 * this is how we are able get our image user id 
	 * @return it will return the id for our image user 
	 */
	public int getId() {
		return id;
	}
/**
 * this is how we are able to set our id 
 * @param id takes in id and makes it our id 
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * this is our one to one mapping with user 
 * @return returns the user 
 */
	public User getUser() {
		return user1;
	}
/**
 * this is how we are able to set our one to one mapping user
 * @param user takes in user and  makes it the image user user 
 */
	public void setUser(User user) {
		user1 = user;
	}
/**
 * this will get all of our lists of media 
 * @return will return the list of media
 */
	public List<Media> getListOfMedia() {
		return listOfMedia;
	}
/**
 * this is how we could set a list of media 
 * @param listOfMedia takes in list of media and will make it the image user list of media
 */
	public void setListOfMedia(List<Media> listOfMedia) {
		this.listOfMedia = listOfMedia;
	}
	/**
	 * this is how we are able to add to our list of  media
	 * @param listOfMedia takes in media and adds it to our list
	 */
	public void addMedia(Media listOfMedia) {
		this.listOfMedia.add(listOfMedia);
	}
	

}
