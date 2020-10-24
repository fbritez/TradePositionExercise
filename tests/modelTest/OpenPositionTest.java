package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.OpenPosition;
import model.Transaction;
import static org.mockito.Mockito.*;

class OpenPositionTest {

	@Test
	void testCreation() {
		Transaction transaction = mock(Transaction.class);
		OpenPosition openPosition = new OpenPosition(transaction);
		
		assertEquals(transaction, openPosition.getTransaction());
		assertEquals(transaction.getAmount(), openPosition.getRemainder());
	}

	@Test
	void testUpdateRemainder() {
		Transaction transaction = mock(Transaction.class);
		Transaction anotherTransaction = mock(Transaction.class);	
		Integer amount = 13000;
		Integer anotherAmount = 5000; 
		
		when(transaction.getAmount()).thenReturn(amount);
		when(anotherTransaction.getAmount()).thenReturn(anotherAmount);
		
		OpenPosition openPosition = new OpenPosition(transaction);
		
		assertEquals(transaction, openPosition.getTransaction());
		assertEquals(transaction.getAmount(), openPosition.getRemainder());
		
		openPosition.updateRemaniderFrom(anotherTransaction);
		
		Integer remainder = (amount - anotherAmount);
		assertEquals(remainder, openPosition.getRemainder());
	}
	
	@Test
	void testClosePosition() {
		Transaction transaction = mock(Transaction.class);
		OpenPosition openPosition = new OpenPosition(transaction);
		
		assertEquals(transaction, openPosition.getTransaction());
		assertEquals(transaction.getAmount(), openPosition.getRemainder());
		
		openPosition.closePosition();
		assertEquals(0, openPosition.getRemainder(), 0.1);
		
	}
	
	@Test
	void testGetPurchasePrice() {
		Transaction transaction = mock(Transaction.class);
		OpenPosition openPosition = new OpenPosition(transaction);
		Double amount = 1000.0;
		
		when(transaction.getPrice()).thenReturn(amount);

		assertEquals(amount, openPosition.getPurchasePrice(), 0.1);
		
	}

}
