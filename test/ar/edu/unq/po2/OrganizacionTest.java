package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrganizacionTest {
	
	private Organizacion organizacion1;
	private Ubicacion ubicacion1;

	@BeforeEach
	void setUp() {
		
		// Dummy
		ubicacion1 = mock(Ubicacion.class);
		
		organizacion1 = new Organizacion(TipoDeOrganizacion.SALUD, 5, ubicacion1);
	}

	@Test
	void verificacionDeInicializacionDeUnaOrganizacion() {
		
		// Setup
		int cantidadDeTrabajadoresEsperados = 5;
		
		// Exercise - Verify
		assertEquals(TipoDeOrganizacion.SALUD, organizacion1.getTipoDeOrganizacion());
		assertEquals(cantidadDeTrabajadoresEsperados, organizacion1.getCantidadDeTrabajadores());
		assertTrue(ubicacion1.equals(organizacion1.getUbicacion()));
	}

}