// package coms309.RoundTrip.demo.Repository;



// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import coms309.RoundTrip.demo.Model.ChatMessage;
// import coms309.RoundTrip.demo.Model.MessageStatus;
// import java.util.List;

// @Repository
// public interface ChatMessageRepository
//         extends JpaRepository<ChatMessage, String> {

//     long countBySenderIdAndRecipientIdAndStatus(
//             String senderId, String recipientId, MessageStatus status);

//     List<ChatMessage> findByChatId(String chatId);
// }
