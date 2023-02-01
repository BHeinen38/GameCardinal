package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.MessagingConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagingConversationRepository extends JpaRepository<MessagingConversation, Integer>
{
	

}

