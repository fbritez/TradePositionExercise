package model;

public class SaleType implements TransactionType {

	@Override
	public void updateInventoryWith(Inventory inventory, Transaction transaction) {
		inventory.registerSale(transaction);	
	}


}
