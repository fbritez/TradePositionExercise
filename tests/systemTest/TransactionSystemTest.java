package systemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Transaction;
import system.InventorySystem;
import system.TransactionSystem;

import static org.mockito.Mockito.*;

class TransactionSystemTest {
	
	
	@Test
	void testCreation() {
		
		InventorySystem inventorySystem = mock(InventorySystem.class);
		
		TransactionSystem transactionSystem = new TransactionSystem(inventorySystem);
		
		assertEquals(inventorySystem, transactionSystem.getInventorySystem());
	}

	@Test
	void testRegisterTransaction() {
		
		InventorySystem inventorySystem = mock(InventorySystem.class);
		Transaction transaction = mock(Transaction.class);
		
		TransactionSystem transactionSystem = new TransactionSystem(inventorySystem);
		
		assertEquals(0,transactionSystem.transactions().size() );
		
		transactionSystem.register(transaction);
		
		assertEquals(1,transactionSystem.transactions().size() );
		verify(inventorySystem).register(transaction);
		
		
	}

}
