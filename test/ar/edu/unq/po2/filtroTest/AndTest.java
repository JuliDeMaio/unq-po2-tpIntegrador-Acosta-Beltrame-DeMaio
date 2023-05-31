package ar.edu.unq.po2.filtroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.filtros.And;
import ar.edu.unq.po2.filtros.FiltroDeFechaDeCreacion;
import ar.edu.unq.po2.filtros.FiltroDeFechaDeUltimaVotacion;
import ar.edu.unq.po2.filtros.FiltroDeNivelDeVerificacion;
import ar.edu.unq.po2.filtros.FiltroDeTipoDeInsecto;

class AndTest {

	private And and1;
	private And and2; 
	private And andCompuesto;
	private And andDobleCompuesto;
	
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
		and1 = new And(filtroDeTipoDeInsecto1, filtroDeNivelDeVerificacion1);
		and2 = new And(filtroDeFechaDeUltimaVotacion1, filtroDeFechaDeCreacion1);
		andCompuesto = new And(and1, filtroDeFechaDeUltimaVotacion1);
		andDobleCompuesto = new And(and1, and2);
	}

	@Test
	void testInicializacionDeUnAnd() {
		// Setup
		 FiltroDeTipoDeInsecto filtroEspecificadoEsperado1 = filtroDeTipoDeInsecto1;
		 FiltroDeNivelDeVerificacion filtroEspecificadoEsperado2 = filtroDeNivelDeVerificacion1;
		 
		
		// Verify
		assertEquals(filtroEspecificadoEsperado1, and1.getFiltro1());
		assertEquals(filtroEspecificadoEsperado2, and1.getFiltro2());
	}
	
	@Test
	void testUnAndFiltraUnSetDeMuestrasVaciaYDevuelveUnSetDeMuestrasVacia() {
		
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		Set<Muestra> setDeMuestrasEsperado = Set.of();
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = and1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnAndFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras() {
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of();
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = and1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnAndFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperada = 1;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1);
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = and1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
	}
	
	@Test
	void testUnAndFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras3() {
		// Setup
		int cantidadDeMuestrasEsperada = 4;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1, muestra2, muestra3, muestra4);
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = and1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
	}
	
	@Test
	void testUnAndConOtroAndDentroFiltraUnSetDeMuestrasVacioYDevuelveUnSetDeMuestrasVacio() {
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		Set<Muestra> setDeMuestrasEsperado1 = Set.of();
		Set<Muestra> setDeMuestrasEsperado2 = Set.of();
		Set<Muestra> setDeMuestrasEsperado3 = Set.of();
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = andCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}

	@Test
	void testUnAndConOtroAndDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras1() {
		// Setup
		int cantidadDeMuestrasEsperadas = 2;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1, muestra2, muestra4);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1, muestra2, muestra3);
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = andCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
	}
	
	@Test
	void testUnAndConOtroAndDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperadas = 3;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1, muestra2, muestra3);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1, muestra2, muestra3);
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = andCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnAndConOtroAndDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras3() {
		// Setup
		int cantidadDeMuestrasEsperadas = 4;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1, muestra2, muestra3, muestra4);
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = andCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnAndConDosAndDentroFiltraUnSetDeMuestrasVacioYDevuelveUnSetDeMuestrasVacio() {
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
		Set<Muestra> setDeMuestrasResultante = andDobleCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnAndConDosAndDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras1() {
		// Setup
		int cantidadDeMuestrasEsperadas = 3;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2, muestra3);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1, muestra2, muestra3);
		Set<Muestra> setDeMuestrasEsperado4 = Set.of(muestra1, muestra2, muestra3, muestra4);
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeCreacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado4);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = andDobleCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
				
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
	
	@Test
	void testUnAndConDosAndDentroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras2() {
		// Setup
		int cantidadDeMuestrasEsperadas = 4;
		Set<Muestra> setDeMuestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado1 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado2 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado3 = Set.of(muestra1, muestra2, muestra3, muestra4);
		Set<Muestra> setDeMuestrasEsperado4 = Set.of(muestra1, muestra2, muestra3, muestra4);
		
		when(filtroDeTipoDeInsecto1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado1);
		when(filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado2);
		when(filtroDeFechaDeCreacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado3);
		when(filtroDeFechaDeUltimaVotacion1.filtrarMuestras(setDeMuestrasAFiltrar)).thenReturn(setDeMuestrasEsperado4);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = andDobleCompuesto.filtrarMuestras(setDeMuestrasAFiltrar);
				
		// Verify
		assertTrue(setDeMuestrasResultante.contains(muestra1));
		assertTrue(setDeMuestrasResultante.contains(muestra2));
		assertTrue(setDeMuestrasResultante.contains(muestra3));
		assertTrue(setDeMuestrasResultante.contains(muestra4));
		assertEquals(cantidadDeMuestrasEsperadas, setDeMuestrasResultante.size());
	}
}
