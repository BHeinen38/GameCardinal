package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>
{
	Message findByMessage(String message);

}
