package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.MessagingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagingUserRepository extends JpaRepository<MessagingUser, Integer>
{
	
	
}
