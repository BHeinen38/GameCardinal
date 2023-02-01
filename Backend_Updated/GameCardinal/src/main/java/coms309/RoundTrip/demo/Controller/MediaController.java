package coms309.RoundTrip.demo.Controller;

import java.util.List;


import coms309.RoundTrip.demo.Model.Media;
import coms309.RoundTrip.demo.Repository.MediaRepository;
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
 * This is our media controller that will work with our image user 
 */
@RestController
public class MediaController {
	@Autowired
	MediaRepository mR;

	
	private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
	/**
	 * REST END point that will save the media 
	 * @param media takes in media to be saved that will be saved to our repository
	 * @return RESPONSE ok and that you have sucessfully saved 
	 * throw 404 error
	 */
    @PostMapping(value = "/media")
    public ResponseEntity<Media> saveMedia(@RequestBody Media media)
    {
        //System.out.println("CHICKEN: " + passedUser.toString());

      mR.save(media);
       
        return  ResponseEntity.ok(media);
    }

	/**
	 * REST END POINT that will show all of our media in our ImageUserRepository 
	 * @return list of medias medias that is saved in our ImageUserRepository 
	 * throws 404 if this get mapping fails
	 */
	@GetMapping(value = "/media")
    public List<Media> getAllMedia()
    {
        return mR.findAll();
    }
	
	
	/**
	 * REST END POINT that will get a media by id 
	 * @param id takes in id to know what medai we would like to return from the repository 
	 * @return the media object with the correct id associated with it 
	 * throws a 404 error if this get mapping fails 
	 */
	 @GetMapping(path = "/getMediaById")
	 public Media getMediaById(@PathVariable int id){
	        return mR.getById(id);
	        
	    }
	 
	 /**
	  * REST end point that will update the media be id in the repository 
	  * @param id takes in id to know what media object to be updated  
	  * @param media media that will be updated that will be updated 
	  * @return new media
	  * throws 404 error
	  */
	  @PutMapping("/media/Update")
	    public Media updateMedia(@PathVariable int id, @RequestBody Media media){
	        Media media1 = this.mR.getById(id);
	        if(media1 == null)
	            return null;
	        //need to actually update the medai still and i will get back to this 
	        return this.mR.getById(id);
	    } 
	  /**
	   * REST end point that will delete media by id 
	   * @param id takes in id to know what media to delete
	   * @return that you have succesfully deleted the media
	   * this delete mapping will throw a 404 error if not found 
	   */
	  @DeleteMapping(value = "/medai/{id}")
	  String deleteMedia(@PathVariable int id) {
		  mR.deleteById(id);
		  return success;
		  
	  }


	
}