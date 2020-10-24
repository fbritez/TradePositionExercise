package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
	
	private Account account;
	private List<OpenPosition> openPositions;

	public Inventory(Account anAccount) {
		account = anAccount;
		openPositions = new ArrayList<OpenPosition>();
	}

	public Account getAccount() {
		return account;
	}

	public List<OpenPosition> getOpenPositions() {
		return openPositions;
	}

	public void registerPurchase(Transaction transaction) {
		this.getOpenPositions().add(new OpenPosition(transaction));
		
	}
	
	private Integer calculatePositionFrom(List<OpenPosition> anOpenPositions) {
		return anOpenPositions.stream().mapToInt((openPosition) -> openPosition.getRemainder()).sum();
	}

	public Integer getPosition() {
		
		return this.calculatePositionFrom(this.getOpenPositions());
	}

	public void registerSale(Transaction transaction) {
		OpenPosition firstOpenPosition = this.getOpenPositionsForInstrument(transaction.getInstrument()).get(0);
		
		if(firstOpenPosition.getRemainder() >= transaction.getAmount()) {
			firstOpenPosition.updateRemaniderFrom(transaction);
		} 
		else {
			Integer difference = transaction.getAmount() - firstOpenPosition.getRemainder();
			firstOpenPosition.closePosition();
			
			Transaction remainderAmountTransaction = new Transaction(transaction.getType(), difference, transaction.getInstrument(), transaction.getPrice(), transaction.getAccount());
			this.registerSale(remainderAmountTransaction);
				
		}
		
	}

	private List<OpenPosition> getOpenPositionsForInstrument(Instrument instrument) {
		
		return this.getOpenPositions().stream().filter((openPosition) -> openPosition.getInstrument() == instrument && openPosition.getRemainder() > 0).collect(Collectors.toList());
	}

	public Integer getPositionForInstrument(Instrument instrument) {
		return this.calculatePositionFrom(this.getOpenPositionsForInstrument(instrument));
	}

}
