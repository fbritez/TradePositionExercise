package model;

public class Transaction {

	private TransactionType type;
	private Integer amount;
	private Instrument instrument;
	private Account account;
	private Double price; 

	public Transaction(TransactionType aType, Integer anAmount, Instrument anInstrumnet, Double aPrice, Account anAccount) {
		type = aType;
		amount = anAmount;
		instrument = anInstrumnet;
		account = anAccount;
		price = aPrice;
	}
	 

	public void updateInventory(Inventory inventory) {
		this.getType().updateInventoryWith(inventory, this);
	}


 
	public Integer getAmount() {
		return amount;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public Account getAccount() {
		return account;
	}

	public TransactionType getType() {
		return type;
	}
	public Double getPrice() {
		return price;
	}

}
