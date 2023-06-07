package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZonaDeCoberturaTest {
	
	private ZonaDeCobertura zonaDeCobertura1;
	private ZonaDeCobertura zonaDeCobertura2;
	private ZonaDeCobertura zonaDeCobertura3;
	private ZonaDeCobertura zonaDeCobertura4;

	
	private ZonaDeCoberturaListener listener1;
	private ZonaDeCoberturaListener listener2;
	
	private Ubicacion ubicacion1;
	private Ubicacion ubicacion2;
	private Ubicacion ubicacion3;
	private Ubicacion ubicacion4;

	
	private Muestra muestra1;

	@BeforeEach
	void setUp() throws Exception {

		// DOC
		listener1 = mock(Organizacion.class);
		listener2 = mock(Organizacion.class);
		
		ubicacion1 = mock(Ubicacion.class);
		ubicacion2 = mock(Ubicacion.class);
		ubicacion3 = mock(Ubicacion.class);
		
		muestra1 = mock(Muestra.class);
		
		// SUT
		zonaDeCobertura1 = new ZonaDeCobertura("Bernal", 30d, ubicacion1);
		zonaDeCobertura2 = new ZonaDeCobertura("Quilmes", 10d, ubicacion2);
		zonaDeCobertura3 = new ZonaDeCobertura("Ezpeleta", 2d, ubicacion3);
		zonaDeCobertura4 = new ZonaDeCobertura("Ezpeleta", 20d, ubicacion4);
	}

	@Test
	void testDeInicializacionDeUnaZonaDeCobertura() {
		
		// Setup
		String nombreEsperado = "Bernal";
		double radioEsperado = 30d;
		Ubicacion epicentroEsperado = ubicacion1;
		
		// Verify
		assertEquals(nombreEsperado, zonaDeCobertura1.getNombre());
		assertEquals(radioEsperado, zonaDeCobertura1.getRadioKm());
		assertEquals(epicentroEsperado, zonaDeCobertura1.getEpicentro());
		assertTrue(zonaDeCobertura1.getListeners().isEmpty());
	}
	
	@Test
	void testUnaZonaDeCoberturaAgregaDosListener() {
				
		// Setup
		int cantidadDeListenersEsperados = 2;
		
		// Exercise
		zonaDeCobertura1.addObserver(listener1);
		zonaDeCobertura1.addObserver(listener2);
		
		// Verify
		assertEquals(cantidadDeListenersEsperados, zonaDeCobertura1.getListeners().size());
		assertTrue(zonaDeCobertura1.getListeners().contains(listener1));
		assertTrue(zonaDeCobertura1.getListeners().contains(listener2));
	}
	
	@Test
	void testUnaZonaDeCoberturaEliminaListener() {
		
		// Setup
		zonaDeCobertura1.addObserver(listener1);
		zonaDeCobertura1.addObserver(listener2);
		int cantidadDeListenersEsperados = 1;
		
		// Exercise
		zonaDeCobertura1.removeObserver(listener2);
		
		// Verify
		assertEquals(cantidadDeListenersEsperados, zonaDeCobertura1.getListeners().size());
		assertTrue(zonaDeCobertura1.getListeners().contains(listener1));
		assertFalse(zonaDeCobertura1.getListeners().contains(listener2));
	}
	
	@Test
	void testUnaZonaDeCoberturaNotificaASusListenersDeUnaMuestraSubida() {
		
		// Setup
		zonaDeCobertura1.addObserver(listener1);
		zonaDeCobertura1.addObserver(listener2);
		
		// Exercise
		zonaDeCobertura1.notificarMuestraSubida(muestra1);
		
		// Verify
		verify(listener1, times(1)).muestraSubida(zonaDeCobertura1, muestra1);
		verify(listener2, times(1)).muestraSubida(zonaDeCobertura1, muestra1);
	}
	
	@Test
	void testUnaZonaDeCoberturaNotificaASusListenersDeUnaMuestraValidada() {
		
		// Setup
		zonaDeCobertura1.addObserver(listener1);
		zonaDeCobertura1.addObserver(listener2);
		
		// Exercise
		zonaDeCobertura1.notificarMuestraValidada(muestra1);
		
		// Verify
		verify(listener1, times(1)).muestraValidada(zonaDeCobertura1, muestra1);
		verify(listener2, times(1)).muestraValidada(zonaDeCobertura1, muestra1);
	}
	
	@Test
	void testUnaZonaDeCoberturaConoceLasZonasQueLaIntersecan() {
		
		// Setup
		when(ubicacion1.distanciaEntre(ubicacion2)).thenReturn(25d);
		when(ubicacion1.distanciaEntre(ubicacion3)).thenReturn(40d);
		when(ubicacion1.distanciaEntre(ubicacion4)).thenReturn(30d);

		int cantidadDeZonasEsperadas = 2;
		Set<ZonaDeCobertura> zonasAFiltrar = Set.of(zonaDeCobertura2, zonaDeCobertura3, zonaDeCobertura4);
		
		// Exercise
		Set<ZonaDeCobertura> zonasObtenidas = zonaDeCobertura1.zonasQueIntersecan(zonasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeZonasEsperadas, zonaDeCobertura1.zonasQueIntersecan(zonasAFiltrar).size());
		assertTrue(zonasObtenidas.contains(zonaDeCobertura2));
		assertTrue(zonasObtenidas.contains(zonaDeCobertura4));
	}

}
