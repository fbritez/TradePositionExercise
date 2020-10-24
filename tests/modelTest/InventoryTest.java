package modelTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import model.Account;
import model.Instrument;
import model.Inventory;
import model.Transaction;

class InventoryTest {

	@Test
	void testCreation() {
		Account account = mock(Account.class);
		Inventory inventory = new Inventory(account);
		
		assertTrue(inventory.getOpenPositions().isEmpty());
		assertEquals(account, inventory.getAccount());
	}
	 
	@Test
	void testRegisterPurchaseTransactionAndCreateNewOpenPosition() {
		Account account = mock(Account.class);
		Inventory inventory = new Inventory(account);
		Transaction transaction = mock(Transaction.class);
		Integer amount = 123000;
		
		when(transaction.getAccount()).thenReturn(account);
		when(transaction.getAmount()).thenReturn(amount);
		
		
		assertTrue(inventory.getOpenPositions().isEmpty());

		inventory.registerPurchase(transaction);
		
		assertFalse(inventory.getOpenPositions().isEmpty());
		assertEquals(amount, inventory.getPosition());
	}
	
	@Test
	void testRegisterSaleTransactionAndReducePosition() {
		Account account = mock(Account.class);
		Inventory inventory = new Inventory(account);
		Transaction transaction = mock(Transaction.class);
		Transaction anotherTransaction = mock(Transaction.class);
		Instrument instrument = mock(Instrument.class);
		Integer amount = 123000;
		Integer anotherAmount = 3400;
		
		Integer remainder = amount - anotherAmount;
		
		when(transaction.getAccount()).thenReturn(account);
		when(transaction.getAmount()).thenReturn(amount);
		when(transaction.getInstrument()).thenReturn(instrument);

		when(anotherTransaction.getAccount()).thenReturn(account);
		when(anotherTransaction.getAmount()).thenReturn(anotherAmount);
		when(anotherTransaction.getInstrument()).thenReturn(instrument);
				
		assertTrue(inventory.getOpenPositions().isEmpty());
		inventory.registerPurchase(transaction);
		
		inventory.registerSale(anotherTransaction);
		
		assertFalse(inventory.getOpenPositions().isEmpty());
		assertEquals(remainder, inventory.getPosition());
	}

	@Test
	void testRegisterSaleTransactionWithAmountGreaterThanFirstOpenPositionAmount() {
		Account account = mock(Account.class);
		Inventory inventory = new Inventory(account);
		Transaction transaction = mock(Transaction.class);
		Transaction thirdTransaction = mock(Transaction.class);
		Transaction anotherTransaction = mock(Transaction.class);
		Instrument instrument = mock(Instrument.class);
		Integer amount = 1000;
		Integer thirdAmount = 124000; 
		Integer anotherAmount = 3400;
		
		Integer remainder = (amount + thirdAmount) - anotherAmount;
		
		when(transaction.getAccount()).thenReturn(account);
		when(transaction.getAmount()).thenReturn(amount);
		when(transaction.getInstrument()).thenReturn(instrument);

		
		when(anotherTransaction.getAccount()).thenReturn(account);
		when(anotherTransaction.getAmount()).thenReturn(anotherAmount);
		when(anotherTransaction.getInstrument()).thenReturn(instrument);
		
		when(thirdTransaction.getAccount()).thenReturn(account);
		when(thirdTransaction.getAmount()).thenReturn(thirdAmount);
		when(thirdTransaction.getInstrument()).thenReturn(instrument);
		
		
		assertTrue(inventory.getOpenPositions().isEmpty());
		inventory.registerPurchase(transaction);
		inventory.registerPurchase(thirdTransaction);
		
		inventory.registerSale(anotherTransaction);
		
		assertFalse(inventory.getOpenPositions().isEmpty());
		assertEquals(remainder, inventory.getPosition());
	}
	
	@Test
	void testGetPositionForInstrumnet() {
		Account account = mock(Account.class);
		Inventory inventory = new Inventory(account);
		Transaction transaction = mock(Transaction.class);
		Transaction thirdTransaction = mock(Transaction.class);
		Transaction anotherTransaction = mock(Transaction.class);
		Instrument instrument = mock(Instrument.class);
		Instrument anotherInstrument = mock(Instrument.class);
		
		Integer amount = 10000;
		Integer thirdAmount = 124000; 
		Integer anotherAmount = 3400;
		
		Integer remainder = amount - anotherAmount;
		
		when(transaction.getAccount()).thenReturn(account);
		when(transaction.getAmount()).thenReturn(amount);
		when(transaction.getInstrument()).thenReturn(instrument);

		when(anotherTransaction.getAccount()).thenReturn(account);
		when(anotherTransaction.getAmount()).thenReturn(anotherAmount);
		when(anotherTransaction.getInstrument()).thenReturn(instrument);
		
		when(thirdTransaction.getAccount()).thenReturn(account);
		when(thirdTransaction.getAmount()).thenReturn(thirdAmount);
		when(thirdTransaction.getInstrument()).thenReturn(anotherInstrument);
		
		
		inventory.registerPurchase(transaction);
		inventory.registerPurchase(thirdTransaction);	
		inventory.registerSale(anotherTransaction);
		
		assertFalse(inventory.getOpenPositions().isEmpty());
		assertEquals(remainder, inventory.getPositionForInstrument(instrument));
		assertEquals(thirdAmount, inventory.getPositionForInstrument(anotherInstrument));
	}
}
