package system;

import java.util.Date;

import model.Account;
import model.Instrument;

public class ValuationSystem {

	private IQuoteSystem quoteSystem;
	private InventorySystem inventorySystem;
 
	public ValuationSystem(IQuoteSystem aQuoteSystem, InventorySystem anInventorySystem) {
		quoteSystem = aQuoteSystem;
		inventorySystem = anInventorySystem; 
	}

	public Double valauatedPosition(Account account, Instrument instrument, Date date, Instrument referenceInstrument) {
		
		return (this.inventorySystem.getPositionForInstrumentFromAccount( instrument, account) * quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(instrument ,date, referenceInstrument));
	}

	public Double valautePortfolioForAccountInReferenceInstrument(Account account, Date date, Instrument referenceInstrument) {
		
		return this.inventorySystem.getAllOpenPositionFor(account)
					.stream() 
					.mapToDouble(
								(openPosition) -> openPosition.getRemainder() 
								* 
								this.quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(openPosition.getInstrument(), date, referenceInstrument))
					.sum() ;
	}

	public Double getProfitAndLossFor(Instrument instrument, Account account, Date date, Instrument referenceInstrument) {
		
		return this.inventorySystem.getAllOpenPositionFor(account, instrument).stream()
				.mapToDouble(
						(openPosition) -> openPosition.getRemainder() 
						* (	this.quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(openPosition.getInstrument(), date, referenceInstrument)
							-
							openPosition.getPurchasePrice()
							)
						).sum(); 

	}

}
