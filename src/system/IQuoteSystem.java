package system;

import java.util.Date;

import model.Instrument;

public interface IQuoteSystem {

	Double getPriceForInstrumentAtDateForReferenceInstrument(Instrument instrument, Date date, Instrument referenceInstrument);
	/*This method get the instrument price from quote valauted in the reference instrument*/
}

