package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.enums.TipoDeOrganizacion;
import ar.edu.unq.po2.zonaCoberturaObserver.IFuncionalidadExterna;
import ar.edu.unq.po2.zonaCoberturaObserver.Organizacion;
import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

class OrganizacionTest {
	
	private Organizacion organizacion1;
	private Ubicacion ubicacion1;
	
	private IFuncionalidadExterna funcionalidadExternaSubida;
	private IFuncionalidadExterna funcionalidadExternaValidada;
	
	private ZonaDeCobertura zonaDeCobertura1;
	private Muestra muestra1;
	
	@BeforeEach
	void setUp() {
		
		// DOC
		ubicacion1 = mock(Ubicacion.class);
		zonaDeCobertura1 = mock(ZonaDeCobertura.class);
		muestra1 = mock(Muestra.class);
		
		funcionalidadExternaSubida = mock(IFuncionalidadExterna.class);
		funcionalidadExternaValidada = mock(IFuncionalidadExterna.class);
		
		// SUT
		organizacion1 = new Organizacion(TipoDeOrganizacion.SALUD, 5, ubicacion1, funcionalidadExternaSubida, 
				funcionalidadExternaValidada, zonaDeCobertura1);
	}

	@Test
	void verificacionDeInicializacionDeUnaOrganizacion() {
		
		// Setup
		int cantidadDeTrabajadoresEsperados = 5;
		
		// Exercise - Verify
		assertEquals(TipoDeOrganizacion.SALUD, organizacion1.getTipoDeOrganizacion());
		assertEquals(cantidadDeTrabajadoresEsperados, organizacion1.getCantidadDeTrabajadores());
		assertEquals(zonaDeCobertura1, organizacion1.getZonaDeCobertura());
		assertTrue(ubicacion1.equals(organizacion1.getUbicacion()));
	}
	
	@Test
	void testUnaOrganizacionRecibeUnaNotificacionDeUnaMuestraSubida() {
		
		// Exercise
		organizacion1.muestraSubida(zonaDeCobertura1, muestra1);
		
		// Verify
		verify(funcionalidadExternaSubida, times(1)).nuevoEvento(organizacion1, zonaDeCobertura1, muestra1);
	}
	
	@Test
	void testUnaOrganizacionRecibeUnaNotificacionDeUnaMuestraValidada() {
		
		// Exercise
		organizacion1.muestraValidada(zonaDeCobertura1, muestra1);
		
		// Verify
		verify(funcionalidadExternaValidada, times(1)).nuevoEvento(organizacion1, zonaDeCobertura1, muestra1);
	}

}