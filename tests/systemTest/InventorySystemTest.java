package systemTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import model.Account;
import model.Instrument;
import model.PurchaseType;
import model.Transaction;
import system.InventorySystem;

class InventorySystemTest {
	
	
	@Test
	void testCreation() {
		InventorySystem inventorySystem = new InventorySystem();

		assertTrue(inventorySystem.getAllInventories().isEmpty());
		
	}

	@Test
	void testCreateInventory() {
		Transaction transaction = mock(Transaction.class); 
		Account account = mock(Account.class); 
		InventorySystem inventorySystem = new InventorySystem();
		
		when(transaction.getAccount()).thenReturn(account);
		
		assertTrue(inventorySystem.getAllInventories().isEmpty());
		
		inventorySystem.register(transaction);
		
		assertFalse(inventorySystem.getAllInventories().isEmpty());
		assertEquals(account, inventorySystem.getInventoryFor(transaction.getAccount()).getAccount());
		
	}
	
	@Test
	void testGetPositionForSomeInstrumnetAndSOmeAccount() {

		Account account = mock(Account.class); 
		Integer amount = 534000;
		Instrument instrument = mock(Instrument.class);
		Transaction transaction = new Transaction(new PurchaseType(), amount, instrument, 1500.6, account );
				
		InventorySystem inventorySystem = new InventorySystem();
		
		inventorySystem.register(transaction);
		
		assertEquals(amount, inventorySystem.getPositionForInstrumentFromAccount(instrument, account));
		
	}
	
	@Test
	void testGetAllOpenPositionForSomeAccount() {

		Account account = mock(Account.class); 
		Integer amount = 534000;
		Instrument instrument = mock(Instrument.class);
		Transaction transaction = new Transaction(new PurchaseType(), amount, instrument, 1500.6, account );
				
		InventorySystem inventorySystem = new InventorySystem();
		
		assertTrue(inventorySystem.getAllOpenPositionFor(account).isEmpty());
		
		inventorySystem.register(transaction);
		
		assertFalse(inventorySystem.getAllOpenPositionFor(account).isEmpty());
		assertEquals(1, inventorySystem.getAllOpenPositionFor(account).size());
		
		inventorySystem.register(transaction);
		assertEquals(2, inventorySystem.getAllOpenPositionFor(account).size());
		
	}

	@Test
	void testGetAllOpenPositionForSomeAccountAndSomeInstrument() {

		Account account = mock(Account.class); 
		Integer amount = 534000;
		Instrument instrument = mock(Instrument.class);
		Instrument anotherInstrument = mock(Instrument.class);
		Transaction transaction = new Transaction(new PurchaseType(), amount, instrument, 1500.6, account );
		Transaction anotherTransaction = new Transaction(new PurchaseType(), amount, anotherInstrument, 1500.6, account );
				
		InventorySystem inventorySystem = new InventorySystem();
		
		assertTrue(inventorySystem.getAllOpenPositionFor(account, instrument).isEmpty());
		
		inventorySystem.register(transaction);
		
		assertFalse(inventorySystem.getAllOpenPositionFor(account, instrument).isEmpty());
		assertEquals(1, inventorySystem.getAllOpenPositionFor(account , instrument).size());
		 
		inventorySystem.register(transaction);
		
		assertEquals(2, inventorySystem.getAllOpenPositionFor(account, instrument).size());
		
		inventorySystem.register(anotherTransaction);
		
		assertEquals(2, inventorySystem.getAllOpenPositionFor(account, instrument).size());
		
	}

}
