package unitTest;

import static org.junit.Assert.*;
import model.Letter;
import model.User;

import org.junit.Test;

public class SendLetterTest {
	@Test
	public void testSendLetter() {
		User u1 = new User();
		User u2 = new User();
		
		Letter letter = new Letter();
		u1.sendLetter(u2, letter);
		
		assertEquals(u1, letter.getOwner());
		assertEquals(u2, letter.getReceiver());
	}
}
