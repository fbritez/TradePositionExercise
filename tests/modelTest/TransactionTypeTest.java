package modelTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import model.Inventory;
import model.PurchaseType;
import model.SaleType;
import model.Transaction;
import model.TransactionType;

class TransactionTypeTest {

	
	@Test
	void testUpdateInventoryForPurchaseType() {
		
		Inventory inventory = mock(Inventory.class);
		Transaction transaction = mock(Transaction.class);

		TransactionType purchaseType = new PurchaseType();
		
		purchaseType.updateInventoryWith(inventory, transaction);
		
		verify(inventory).registerPurchase(transaction);
		
	}
	 
	@Test
	void testUpdateInventoryForSAleType() {
		
		Inventory inventory = mock(Inventory.class);
		Transaction transaction = mock(Transaction.class);

		TransactionType saleType = new SaleType();
		
		saleType.updateInventoryWith(inventory, transaction);
		
		verify(inventory).registerSale(transaction); 
		
	}


}
