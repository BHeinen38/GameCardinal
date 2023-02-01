package coms309.RoundTrip.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coms309.RoundTrip.demo.Model.Message2;

@Repository
public interface Message2Repository extends JpaRepository<Message2, Integer >
{
	

}
