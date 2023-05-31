package ar.edu.unq.po2.filtroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.filtros.FiltroDeFechaDeUltimaVotacion;

class FiltroDeFechaDeUltimaVotacionTest {
	
	private FiltroDeFechaDeUltimaVotacion filtroDeFechaDeUltimaVotacion1;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;

	@BeforeEach
	void setUp() throws Exception {
		
		// SUT
		filtroDeFechaDeUltimaVotacion1 = new FiltroDeFechaDeUltimaVotacion(LocalDate.of(1986, Month.JUNE, 29));
				
		// DOC
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
	}

	@Test
	void testInicializacionDeUnFiltroDeFechaDeUltimaVotacion() {
		// Setup
		LocalDate filtroEspecificadoEsperado = LocalDate.of(1986, Month.JUNE, 29);
		
		// Verify
		assertEquals(filtroEspecificadoEsperado, filtroDeFechaDeUltimaVotacion1.getFiltroEspecificado());
	}

	@Test
	void testUnFiltroFiltraUnSetDeMuestrasVaciaYDevuelveUnSetDeMuestrasVacia() {
		
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras() {
		// Setup
		int cantidadDeMuestrasEsperada = 2;
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		when(muestra1.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1986, Month.JUNE, 29));
		when(muestra2.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1986, Month.JUNE, 29));
		when(muestra3.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(2000, Month.NOVEMBER, 25));
		when(muestra4.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.now());
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeFechaDeUltimaVotacion1.filtrarMuestras(muestrasAFiltrar);
		
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
		when(muestra1.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1986, Month.JUNE, 29));
		when(muestra2.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1986, Month.JUNE, 29));
		when(muestra3.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1986, Month.JUNE, 29));
		when(muestra4.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1986, Month.JUNE, 29));
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeFechaDeUltimaVotacion1.filtrarMuestras(muestrasAFiltrar);
		
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
		
		when(muestra1.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1935, Month.NOVEMBER, 22));
		when(muestra2.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(2005, Month.MARCH, 12));
		when(muestra3.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(1995, Month.JULY, 25));
		when(muestra4.obtenerFechaDeUltimaVotacion()).thenReturn(LocalDate.of(2013, Month.DECEMBER, 7));
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeFechaDeUltimaVotacion1.filtrarMuestras(muestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}

}
