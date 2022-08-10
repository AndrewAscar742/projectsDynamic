import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrdenarNumTest {

	@Test
	void OrdenarNumeros() {
		assertEquals(OrdenarNum.ordenarVet("12;10;2;3;6;5"), OrdenarNum.getVetOrdenado());
	}

}
