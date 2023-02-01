// package coms309.RoundTrip.demo.Service;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import coms309.RoundTrip.demo.Model.ChatRoom;
// import coms309.RoundTrip.demo.Repository.ChatRoomRepository;

// import java.util.Optional;

// @Service
// public class ChatRoomService {

//     @Autowired private ChatRoomRepository chatRoomRepository;

//     public Optional<String> getChatId(
//             String senderId, String recipientId, boolean createIfNotExist) {

//          return chatRoomRepository
//                 .findBySenderIdAndRecipientId(senderId, recipientId)
//                 .map(ChatRoom::getChatId)
//                  .or(() -> {
//                     if(!createIfNotExist) {
//                         return  Optional.empty();
//                     }
//                      var chatId =
//                             String.format("%s_%s", senderId, recipientId);

//                     ChatRoom senderRecipient = ChatRoom
//                             .builder()
//                             .chatId(chatId)
//                             .senderId(senderId)
//                             .recipientId(recipientId)
//                             .build();

//                     ChatRoom recipientSender = ChatRoom
//                             .builder()
//                             .chatId(chatId)
//                             .senderId(recipientId)
//                             .recipientId(senderId)
//                             .build();
//                     chatRoomRepository.save(senderRecipient);
//                     chatRoomRepository.save(recipientSender);

//                     return Optional.of(chatId);
//                 });
//     }
// }