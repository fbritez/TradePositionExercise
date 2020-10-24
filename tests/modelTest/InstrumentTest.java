package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Instrument;

class InstrumentTest {

	@Test
	void testCreation() {
		String instrumenName = "Dolar";
		Instrument instrument = new Instrument(instrumenName);
		
		assertEquals(instrumenName, instrument.getName());
	}

}
