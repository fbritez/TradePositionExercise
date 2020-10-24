package system;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import model.Account;
import model.Instrument;
import model.Inventory;
import model.OpenPosition;
import model.Transaction;

public class InventorySystem {
	
	private List<Inventory> inventories;
	
	public InventorySystem() {
		inventories = new ArrayList<Inventory>();
	}
	
	public List<Inventory> getAllInventories() {
		return inventories;
	}

	public void register(Transaction transaction) {
			Inventory inventory = this.getInventoryFor(transaction.getAccount());
			transaction.updateInventory(inventory);
			
	}

	public Inventory getInventoryFor(Account account) {
		return getAllInventories().stream().filter(inventory -> inventory.getAccount() == account)
				.findFirst() 
				.orElse(this.newInventoryFor(account));
	}

	private Inventory newInventoryFor(Account account) {
		Inventory inventory = new Inventory(account);
		this.save(inventory);
		return inventory;
	} 

	private void save(Inventory inventory) {
		this.getAllInventories().add(inventory);
		
	}

	public Integer getPositionForInstrumentFromAccount(Instrument instrument, Account account) {
		
		return this.getInventoryFor(account).getPositionForInstrument(instrument);
	}

	public List<OpenPosition> getAllOpenPositionFor(Account account) {
		
		return this.getInventoryFor(account).getOpenPositions();
	}

	public List<OpenPosition> getAllOpenPositionFor(Account account, Instrument instrument) {
		
		return this.getAllOpenPositionFor(account).stream().filter((openPosition) -> openPosition.getInstrument() == instrument).collect(Collectors.toList());
	}
}
