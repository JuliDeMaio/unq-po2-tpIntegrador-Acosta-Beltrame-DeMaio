package ar.edu.unq.po2.filtroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.enums.ITipoDeInsecto;
import ar.edu.unq.po2.enums.Vinchuca;
import ar.edu.unq.po2.filtros.FiltroDeTipoDeInsecto;

class FiltroDeTipoDeInsectoTest {
	
	private FiltroDeTipoDeInsecto filtroDeTipoDeInsecto1;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;

	@BeforeEach
	void setUp() throws Exception {
		
		// SUT
		filtroDeTipoDeInsecto1 = new FiltroDeTipoDeInsecto(Chinche.PHTIACHINCHE);
		
		// DOC
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
	}

	@Test
	void testInicializacionDeUnFiltroDeTipoDeInsecto() {
		// Setup
		ITipoDeInsecto filtroEspecificadoEsperado = (Chinche.PHTIACHINCHE);
		
		// Verify
		assertEquals(filtroEspecificadoEsperado, filtroDeTipoDeInsecto1.getFiltroEspecificado());
	}	
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasVaciaYDevuelveUnSetDeMuestrasVacia() {
		
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras() {
		// Setup
		int cantidadDeMuestrasEsperada = 2;
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		when(muestra1.getResultadoActual()).thenReturn(Chinche.PHTIACHINCHE);
		when(muestra2.getResultadoActual()).thenReturn(Chinche.PHTIACHINCHE);
		when(muestra3.getResultadoActual()).thenReturn(Vinchuca.VINCHUCAINFESTANS);
		when(muestra4.getResultadoActual()).thenReturn(Chinche.CHINCHEFOLIADA);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeTipoDeInsecto1.filtrarMuestras(muestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
	}
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperada = 4;
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		when(muestra1.getResultadoActual()).thenReturn(Chinche.PHTIACHINCHE);
		when(muestra2.getResultadoActual()).thenReturn(Chinche.PHTIACHINCHE);
		when(muestra3.getResultadoActual()).thenReturn(Chinche.PHTIACHINCHE);
		when(muestra4.getResultadoActual()).thenReturn(Chinche.PHTIACHINCHE);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeTipoDeInsecto1.filtrarMuestras(muestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
	}
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras3() {
		// Setup
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		
		when(muestra1.getResultadoActual()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(muestra2.getResultadoActual()).thenReturn(Vinchuca.VINCHUCASORDIDA);
		when(muestra3.getResultadoActual()).thenReturn(Vinchuca.VINCHUCASORDIDA);
		when(muestra4.getResultadoActual()).thenReturn(Chinche.CHINCHEFOLIADA);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeTipoDeInsecto1.filtrarMuestras(muestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
}
