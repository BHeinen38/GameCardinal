//package coms309.RoundTrip.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import coms309.RoundTrip.demo.Model.MessagingConversation;
//import coms309.RoundTrip.demo.Repository.MessagingConversationRepository;
//
//
//public class TestMessageConversation {
//	
//	@Mock
//	MessagingConversationRepository mCR;
//	
//	@Test
//	public void getMessagingConversationById() {
//		when(mCR.getById((long) 1).thenReturn(new MessagingConversation(1, "this is so much fun"));
//		
//		MessagingConversation mc = mCR.getById((long) 1);
//		
//		assertEquals("this is so much fun", mc.getLastSentMessage());
//		
//	}
//}
