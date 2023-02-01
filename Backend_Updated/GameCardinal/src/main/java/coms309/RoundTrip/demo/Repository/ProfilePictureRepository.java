package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.ProfilePicture;
import coms309.RoundTrip.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Integer>
{
}
