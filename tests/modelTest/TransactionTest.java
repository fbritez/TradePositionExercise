package modelTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Account;

import model.Instrument;
import model.Inventory;
import model.Transaction;
import model.TransactionType;

import static org.mockito.Mockito.*;

class TransactionTest {

	@Test
	void testCreation() {
		
		Integer amount = 10000;
		Instrument instrument = mock(Instrument.class);
		Account account = mock(Account.class); 
		Double price = new Double(1.3);
		TransactionType type = mock(TransactionType.class);
		
		Transaction transaction = new Transaction(type, amount, instrument , price , account);
		
		assertEquals(transaction.getType(), type);
		assertEquals(price, transaction.getPrice());
		assertEquals(transaction.getAmount(), amount);
		assertEquals(transaction.getInstrument(), instrument);
		assertEquals(transaction.getAccount(), account);
	}
	
	@Test
	void testUpdateSomeAccountBalance() {
		 
		Integer amount = 10000;
		Instrument instrument = mock(Instrument.class);
		Account account = mock(Account.class); 
		TransactionType type = mock(TransactionType.class);
		Double price = new Double(1.3);
		Inventory inventory = mock(Inventory.class);
		
		Transaction transaction = new Transaction(type, amount, instrument , price , account);
		
		transaction.updateInventory(inventory);
		
		verify(type).updateInventoryWith(inventory, transaction);
	}
	

}
