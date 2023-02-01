package coms309.RoundTrip.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table 
public class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MediaId")
	private int mediaId;
	
	
	
	@Column(name = "location")
	private String location;

	
	@Column(name = "Type")
	private String type;


	//this needs to have a onetoone with user 


	/**
	 * each image user can have many images 
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	private ImageUser imageUser;

	public Media(String location, String type) {
		super();
		this.location = location;
		this.type = type;
	}
	
	public Media() {
		
	}
	
	
	/**
	 * returns a media id 
	 * @return
	 */
	public int getMediaId() {
		return mediaId;
	}
/**
 * set medai 
 * @param mediaId takes in  an ind and makes it the media id
 */
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
/**
 * this is the location of our media
 * @return will return the location of this medai
 */
	public String getLocation() {
		return location;
	}
/**
 * this is how we set location 
 * @param location takes in a locatiion and makes it our location 
 */
	public void setLocation(String location) {
		this.location = location;
	}
/**
 * 
 * @return returns  the type of this media
 */
	public String getType() {
		return type;
	}
/**
 * this is how we set type 
 * @param type takes in type and makes it our medias type 
 */
	public void setType(String type) {
		this.type = type;
	}
/**
 * many ot one mapping with the image user 
 * @return each image user can have many medias
 */
	public ImageUser getImageUser() {
		return imageUser;
	}
/**
 * this is how we are able to set our image user 
 * @param imageUser takes in image user and makes it the medias image user 
 */
	public void setImageUser(ImageUser imageUser) {
		this.imageUser = imageUser;
	}
	
	
}
