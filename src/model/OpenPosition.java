package model;

public class OpenPosition {
	
	private Transaction transaction;
	private Integer remainder;

	public OpenPosition(Transaction aTransaction) {
		transaction = aTransaction;
		remainder = transaction.getAmount();
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Integer getRemainder() {
		return remainder;
	}

	public void updateRemaniderFrom(Transaction aTransaction) {
		remainder = remainder - aTransaction.getAmount();
	} 

	public void closePosition() {
		remainder = 0;
	}

	public Instrument getInstrument() {
		
		return this.getTransaction().getInstrument();
	}
	
	public Double getPurchasePrice() {
		
		return this.getTransaction().getPrice();
	}

}
