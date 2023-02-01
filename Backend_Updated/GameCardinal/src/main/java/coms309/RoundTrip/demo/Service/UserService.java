package coms309.RoundTrip.demo.Service;

import coms309.RoundTrip.demo.Model.ProfilePicture;
import coms309.RoundTrip.demo.Model.User;
import coms309.RoundTrip.demo.Repository.ProfilePictureRepository;
import coms309.RoundTrip.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    ProfilePictureRepository picRepository;

    @Autowired
    UserRepository userRepository;

    public UserService()
    {

    }


    public ResponseEntity<User> setUserPic(int userID)
    {
        Optional<User> tempU = userRepository.findById(userID);
        if (tempU.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Optional<ProfilePicture> tempP = picRepository.findById(userID);
        if (tempP.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        try
        {
            User u = tempU.get();
            ProfilePicture p = tempP.get();

            u.setPicture(p);
            p.setUser(u);

            userRepository.save(u);
            picRepository.save(p);
            return ResponseEntity.status(HttpStatus.OK).body(u);
        } catch (NoSuchElementException | NullPointerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Custom Header","So the code thought it got a user but when I tried to \n" +
                    "get information from the object, it failed..... \n" + e.getMessage()).body(null);
        }
    }

}