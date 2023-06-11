package ar.edu.unq.po2.filtroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.filtros.FiltroDeFechaDeCreacion;
import ar.edu.unq.po2.filtros.FiltroDeFechaDeUltimaVotacion;
import ar.edu.unq.po2.filtros.FiltroDeNivelDeVerificacion;
import ar.edu.unq.po2.filtros.FiltroDeTipoDeInsecto;
import ar.edu.unq.po2.filtros.Or;

class OrTest {

	private Or or1;
	private Or or2; 
	private Or orCompuesto;
	private Or orDobleCompuesto;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	
	private FiltroDeTipoDeInsecto filtroDeTipoDeInsecto1;
	private FiltroDeNivelDeVerificacion filtroDeNivelDeVerificacion1;
	private FiltroDeFechaDeUltimaVotacion filtroDeFechaDeUltimaVotacion1;
	private FiltroDeFechaDeCreacion filtroDeFechaDeCreacion1;

	@BeforeEach
	void setUp() throws Exception {
				
		// DOC
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		
		filtroDeTipoDeInsecto1 = mock(FiltroDeTipoDeInsecto.class);
		filtroDeNivelDeVerificacion1 = mock(FiltroDeNivelDeVerificacion.class);
		filtroDeFechaDeUltimaVotacion1 = mock(FiltroDeFechaDeUltimaVotacion.class);	
		filtroDeFechaDeCreacion1 = mock(FiltroDeFechaDeCreacion.class);
		
		// SUT
		or1 = new Or(filtroDeTipoDeInsecto1, filtroDeNivelDeVerificacion1);
		or2 = new Or(filtroDeFechaDeUltimaVotacion1, filtroDeFechaDeCreacion1);
		orCompuesto = new Or(or1, filtroDeFechaDeUltimaVotacion1);
		orDobleCompuesto = new Or(or1, or2);
	}

	@Test
	void testInicializacionDeUnOr() {
		// Setup
		FiltroDeTipoDeInsecto filtroEspecificadoEsperado1 = filtroDeTipoDeInsecto1;
		FiltroDeNivelDeVerificacion filtroEspecificadoEsperado2 = filtroDeNivelDeVerificacion1;
		
		// Verify
		assertEquals(filtroEspecificadoEsperado1, or1.getFiltro1());
		assertEquals(filtroEspecificadoEsperado2, or1.getFiltro2());
	}
	
	@Test
	void testUnOrFiltraUnSetDeMuestrasVaciaYDevuelveUnSetDeMuestrasVacia() {
		
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		Set<Muestra> setDeMuestrasEsperado = Set.of();
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = or1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnOrFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras() {
		// Setup
		int cantidadDeMuestrasEsperada = 2;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of();
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = or1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
	}
	
	@Test
	void testUnOrFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperada = 3;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra3);
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = or1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
	}
	
	@Test
	void testUnOrFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras3() {
		// Setup
		int cantidadDeMuestrasEsperada = 4;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra3, muestra4);
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = or1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
	}
	
	@Test
	void testUnOrConOtroOrDentroFiltraUnSetDeMuestrasVacioYDevuelveUnSetDeMuestrasVacio() {
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		Set<Muestra> setDeMuestrasEsperado1 = Set.of();
		Set<Muestra> setDeMuestrasEsperado2 = Set.of();
		Set<Muestra> setDeMuestrasEsperado3 = Set.of();
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}

	@Test
	void testUnOrConOtroOrDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras1() {
		// Setup
		int cantidadDeMuestrasEsperadas = 2;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of();
		Set<Muestra> setDeMuestrasEsperado3 = Set.of();
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnOrConOtroOrDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperadas = 3;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra3);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1);
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnOrConOtroOrDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras3() {
		// Setup
		int cantidadDeMuestrasEsperadas = 4;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1);
		
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnOrConDosOrDentroFiltraUnSetDeMuestrasVacioYDevuelveUnSetDeMuestrasVacio() {
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		Set<Muestra> setDeMuestrasEsperado1 = Set.of();
		Set<Muestra> setDeMuestrasEsperado2 = Set.of();
		Set<Muestra> setDeMuestrasEsperado3 = Set.of();
		Set<Muestra> setDeMuestrasEsperado4 = Set.of();
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeCreacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado4);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orDobleCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnOrConDosOrDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras1() {
		// Setup
		int cantidadDeMuestrasEsperadas = 3;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra2);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra3);
		Set<Muestra> setDeMuestrasEsperado4 = Set.of();
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeCreacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado4);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orDobleCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
				
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnOrConDosOrDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperadas = 4;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra2);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra3);
		Set<Muestra> setDeMuestrasEsperado4 = Set.of(muestra4);
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeCreacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado4);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = orDobleCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
				
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
}
