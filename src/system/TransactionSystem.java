package system;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionSystem {

	private InventorySystem inventorySystem;
	private List<Transaction> transactions;
	
	public TransactionSystem(InventorySystem anInventorySystem) {
		inventorySystem = anInventorySystem;
		transactions = new ArrayList<Transaction>();
	}

	public InventorySystem getInventorySystem() {
		return inventorySystem;
	} 

	public List<Transaction> transactions() {
		return transactions;
	}

	public void register(Transaction transaction) {
		inventorySystem.register(transaction);
		this.save(transaction);
	}

	private void save(Transaction transaction) {
		transactions.add(transaction);
	}

}
