package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer>
{
	
	
}

