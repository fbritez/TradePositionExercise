package model;

public class PurchaseType implements TransactionType {


	@Override
	public void updateInventoryWith(Inventory inventory, Transaction transaction) {
		inventory.registerPurchase(transaction);
		
	}


}
