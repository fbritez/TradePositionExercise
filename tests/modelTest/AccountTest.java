package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Account;

class AccountTest {

	@Test
	void testCreation() {
		
		Integer accountNumber = 36;
		Account account = new Account(accountNumber);
		
		assertEquals(account.getNumber(), accountNumber);
	}

}
