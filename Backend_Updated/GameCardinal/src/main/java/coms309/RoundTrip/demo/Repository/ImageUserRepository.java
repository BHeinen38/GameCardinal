package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.ImageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUserRepository extends JpaRepository<ImageUser, Integer>
{
	
	
}

