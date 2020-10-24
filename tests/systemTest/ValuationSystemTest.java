package systemTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Account;
import model.Instrument;
import model.OpenPosition;
import system.InventorySystem;
import system.IQuoteSystem;
import system.ValuationSystem;

class ValuationSystemTest {

	@Test
	void testValuateSomeHoldinginSomeReferenceInstrument() {
		IQuoteSystem quoteSystem = mock(IQuoteSystem.class);
		InventorySystem inventorySystem = mock(InventorySystem.class);
		
		ValuationSystem valuationSystem = new ValuationSystem(quoteSystem, inventorySystem); 
		
		Account account = mock(Account.class); 
		Double quote = new Double(1.5);
		Integer amount = 14000;
		Instrument dolar = mock(Instrument.class); 
		Instrument bond = mock(Instrument.class);
		
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(any(), any(), any())).thenReturn(quote);
		when(inventorySystem.getPositionForInstrumentFromAccount(eq(bond), eq(account))).thenReturn(amount);
		
		Double valuatedAmount = amount * quote;
		
		assertEquals(valuatedAmount, valuationSystem.valauatedPosition(account, bond,  new Date(), dolar));
	}

	@Test
	void testValuatePortfolioForSomeAccountInDolar() {
		
		IQuoteSystem quoteSystem = mock(IQuoteSystem.class);
		InventorySystem InventorySystem = mock(InventorySystem.class);
		
		ValuationSystem valuationSystem = new ValuationSystem(quoteSystem, InventorySystem); 
		
		OpenPosition openPosition = mock(OpenPosition.class);
		OpenPosition anotherOpenPosition = mock(OpenPosition.class);
		Instrument bond = mock(Instrument.class);
		Instrument dolar = mock(Instrument.class);
		Account account = mock(Account.class);
		Double bondQuote = new Double(1.5);
		Double euroQuote = new Double(2.5);
		Integer amount = 14000;
		Integer anotherAmount = 30000; 
		List<OpenPosition> openPositions = new ArrayList<OpenPosition>();
		openPositions.add(openPosition);
		openPositions.add(anotherOpenPosition);
		
		when(InventorySystem.getAllOpenPositionFor(account)).thenReturn(openPositions);
		
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(eq(bond), any(), eq(dolar))).thenReturn(bondQuote);
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(eq(dolar), any(), eq(dolar))).thenReturn(euroQuote);
		
		when(openPosition.getRemainder()).thenReturn(amount);
		when(openPosition.getInstrument()).thenReturn(bond);
		
		when(anotherOpenPosition.getRemainder()).thenReturn(anotherAmount);
		when(anotherOpenPosition.getInstrument()).thenReturn(dolar);
		
		Double valuatedAmount = ((amount * bondQuote ) +  (anotherAmount * euroQuote));
		
		assertEquals(valuatedAmount, valuationSystem.valautePortfolioForAccountInReferenceInstrument(account, new Date(), dolar));
	}
		
	@Test
	void testValuatePortfolioForSomeAccountInEuro() {
		
		IQuoteSystem quoteSystem = mock(IQuoteSystem.class);
		InventorySystem InventorySystem = mock(InventorySystem.class);
		
		ValuationSystem valuationSystem = new ValuationSystem(quoteSystem, InventorySystem); 
		
		OpenPosition openPosition = mock(OpenPosition.class);
		OpenPosition anotherOpenPosition = mock(OpenPosition.class);
		Instrument bond = mock(Instrument.class);
		Instrument dolar = mock(Instrument.class);
		Account account = mock(Account.class);
		Double bondQuote = new Double(1.5);
		Double euroQuote = new Double(4.76);
		Integer amount = 14000; 
		Integer anotherAmount = 30000; 
		List<OpenPosition> openPositions = new ArrayList<OpenPosition>();
		openPositions.add(openPosition);
		openPositions.add(anotherOpenPosition);
		
		when(InventorySystem.getAllOpenPositionFor(account)).thenReturn(openPositions);
		
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(eq(bond), any(), eq(dolar))).thenReturn(bondQuote);
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(eq(dolar), any(), eq(dolar))).thenReturn(euroQuote);
		
		when(openPosition.getRemainder()).thenReturn(amount);
		when(openPosition.getInstrument()).thenReturn(bond);
		
		when(anotherOpenPosition.getRemainder()).thenReturn(anotherAmount);
		when(anotherOpenPosition.getInstrument()).thenReturn(dolar);
		
		Double valuatedAmount = ((amount * bondQuote ) +  (anotherAmount * euroQuote));
		
		assertEquals(valuatedAmount, valuationSystem.valautePortfolioForAccountInReferenceInstrument(account, new Date(), dolar));
	}
	 
	@Test
	void testGetProfitValueForInvestmentInSomeInstrument() {
		
		IQuoteSystem quoteSystem = mock(IQuoteSystem.class);
		InventorySystem InventorySystem = mock(InventorySystem.class);
		
		ValuationSystem valuationSystem = new ValuationSystem(quoteSystem, InventorySystem); 
		
		OpenPosition openPosition = mock(OpenPosition.class);
		OpenPosition anotherOpenPosition = mock(OpenPosition.class);
		Instrument bond = mock(Instrument.class);
		Instrument dolar = mock(Instrument.class);
		Account account = mock(Account.class);
		Double bondQuote = new Double(1.5);
		Integer amount = 14000; 
		Integer anotherAmount = 30000; 
		
		List<OpenPosition> openPositions = new ArrayList<OpenPosition>();
		openPositions.add(openPosition);
		openPositions.add(anotherOpenPosition);
		
		when(InventorySystem.getAllOpenPositionFor(account, bond)).thenReturn(openPositions);
		
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(eq(bond), any(), any())).thenReturn(bondQuote);
		
		when(openPosition.getRemainder()).thenReturn(amount);
		when(openPosition.getInstrument()).thenReturn(bond);
		when(openPosition.getPurchasePrice()).thenReturn(1.0);
		
		when(anotherOpenPosition.getRemainder()).thenReturn(anotherAmount);
		when(anotherOpenPosition.getInstrument()).thenReturn(bond);
		when(anotherOpenPosition.getPurchasePrice()).thenReturn(1.0);
		
		
		Double valuatedAmount = ((amount + anotherAmount) * (bondQuote - 1.0));
		
		assertTrue(valuationSystem.getProfitAndLossFor(bond,account, new Date(), dolar) > 0 );
		assertEquals(valuatedAmount, valuationSystem.getProfitAndLossFor(bond,account, new Date(), dolar));
	}
	
	@Test
	void testGetLostValueForInvestmentInSomeInstrument() {
		
		IQuoteSystem quoteSystem = mock(IQuoteSystem.class);
		InventorySystem InventorySystem = mock(InventorySystem.class);
		
		ValuationSystem valuationSystem = new ValuationSystem(quoteSystem, InventorySystem); 
		
		OpenPosition openPosition = mock(OpenPosition.class);
		OpenPosition anotherOpenPosition = mock(OpenPosition.class);
		Instrument bond = mock(Instrument.class);
		Instrument dolar = mock(Instrument.class);
		Account account = mock(Account.class);
		Double bondQuote = new Double(0.5);
		Integer amount = 14000; 
		Integer anotherAmount = 30000; 
		
		List<OpenPosition> openPositions = new ArrayList<OpenPosition>();
		openPositions.add(openPosition);
		openPositions.add(anotherOpenPosition);
		
		when(InventorySystem.getAllOpenPositionFor(account, bond)).thenReturn(openPositions);
		
		when(quoteSystem.getPriceForInstrumentAtDateForReferenceInstrument(eq(bond), any(), any())).thenReturn(bondQuote);
		
		when(openPosition.getRemainder()).thenReturn(amount);
		when(openPosition.getInstrument()).thenReturn(bond);
		when(openPosition.getPurchasePrice()).thenReturn(1.0);
		
		when(anotherOpenPosition.getRemainder()).thenReturn(anotherAmount);
		when(anotherOpenPosition.getInstrument()).thenReturn(bond);
		when(anotherOpenPosition.getPurchasePrice()).thenReturn(1.0);
		
		
		Double valuatedAmount = ((amount + anotherAmount) * (bondQuote - 1.0));
		
		assertTrue(valuationSystem.getProfitAndLossFor(bond,account, new Date(), dolar) < 0 );
		assertEquals(valuatedAmount, valuationSystem.getProfitAndLossFor(bond,account, new Date(), dolar));
	}
	 
	  
		
}
