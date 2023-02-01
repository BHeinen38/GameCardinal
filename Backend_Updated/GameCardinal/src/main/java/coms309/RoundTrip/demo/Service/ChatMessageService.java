// package coms309.RoundTrip.demo.Service;

// import org.springframework.beans.factory.annotation.Autowired;


// import org.springframework.stereotype.Service;

// import coms309.RoundTrip.demo.Exception.ResourceNotFoundException;
// import coms309.RoundTrip.demo.Model.ChatMessage;
// import coms309.RoundTrip.demo.Model.MessageStatus;
// import coms309.RoundTrip.demo.Repository.ChatMessageRepository;

// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class ChatMessageService {
//     @Autowired private ChatMessageRepository repository;
//     @Autowired private ChatRoomService chatRoomService;
//    // @Autowired private MongoOperations mongoOperations;

//     public ChatMessage save(ChatMessage chatMessage) {
//         chatMessage.setStatus(MessageStatus.RECEIVED);
//         repository.save(chatMessage);
//         return chatMessage;
//     }

//     public long countNewMessages(String senderId, String recipientId) {
//         return repository.countBySenderIdAndRecipientIdAndStatus(
//                 senderId, recipientId, MessageStatus.RECEIVED);
//     }

//     public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
//         var chatId = chatRoomService.getChatId(senderId, recipientId, false);

//         var messages =
//                 chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

//         // if(messages.size() > 0) {
//         //     updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
//         // }

//         return messages;
//     }

//     public ChatMessage findById(String id) {
//         return repository
//                 .findById(id)
//                 .map(chatMessage -> {
//                     chatMessage.setStatus(MessageStatus.DELIVERED);
//                     return repository.save(chatMessage);
//                 })
//                 .orElseThrow(() ->
//                         new ResourceNotFoundException("can't find message (" + id + ")"));
//     }

//     // public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
//     //     Query query = new Query(
//     //             Criteria
//     //                     .where("senderId").is(senderId)
//     //                     .and("recipientId").is(recipientId));
//     //     Update update = Update.update("status", status);
//     //     mongoOperations.updateMulti(query, update, ChatMessage.class);
//     // }
// }
