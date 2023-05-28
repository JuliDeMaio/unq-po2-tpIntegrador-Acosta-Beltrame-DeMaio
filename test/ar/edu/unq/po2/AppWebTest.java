package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppWebTest {
	
	private AppWeb appWeb;
	
	private Muestra muestra1;

	@BeforeEach
	void setUp() throws Exception {
		
		// SUT
		appWeb = AppWeb.getInstance();
		
		// DOC
		muestra1 = mock(Muestra.class);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		appWeb.reiniciarApp();
	}
	
	@Test
	void testInicializacionDeUnaAppWeb() {
		// Verify
		assertTrue(appWeb.getMuestras().isEmpty());
	}

	@Test
	void testSeSubeUnaMuestraALaAppWebYQuedaRegistrada() {
		// Exercise
		appWeb.guardarMuestra(muestra1);
		
		// Verify
		assertEquals(appWeb.getMuestras().size(), 1);
		assertTrue(appWeb.getMuestras().contains(muestra1));
	}
}
