package ar.edu.unq.po2.filtroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorExpertos;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraVerificada;
import ar.edu.unq.po2.filtros.FiltroDeNivelDeVerificacion;

class FiltroDeNivelDeVerificacionTest {

	private FiltroDeNivelDeVerificacion filtroDeNivelDeVerificacion1;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	
	private EstadoMuestraVerificada estadoMuestraVerificada;
	private EstadoMuestraOpinadaPorBasicos estadoMuestraOpinadaPorBasicos;
	private EstadoMuestraOpinadaPorExpertos estadoMuestraOpinadaPorExpertos;

	@BeforeEach
	void setUp() throws Exception {
		
		// DOC
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		
		estadoMuestraVerificada = mock(EstadoMuestraVerificada.class);
		estadoMuestraOpinadaPorBasicos = mock(EstadoMuestraOpinadaPorBasicos.class);
		estadoMuestraOpinadaPorExpertos = mock(EstadoMuestraOpinadaPorExpertos.class);
		
		// SUT
		filtroDeNivelDeVerificacion1 = new FiltroDeNivelDeVerificacion(estadoMuestraVerificada);
	}

	@Test
	void testInicializacionDeUnFiltroDeNivelDeVerificacion() {
		// Setup
		EstadoMuestraVerificada filtroEspecificadoEsperado = estadoMuestraVerificada;
		
		// Verify
		assertEquals(filtroEspecificadoEsperado, filtroDeNivelDeVerificacion1.getFiltroEspecificado());
	}
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasVaciaYDevuelveUnSetDeMuestrasVacia() {
		
		// Setup
		Set<Muestra> setDeMuestrasAFiltrar = Set.of();
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeNivelDeVerificacion1.filtrarMuestras(setDeMuestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
	
	@Test
	void testUnFiltroFiltraUnSetDeMuestrasYDevuelveElSetEsperadoDeMuestras() {
		// Setup
		int cantidadDeMuestrasEsperada = 2;
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2, muestra3, muestra4);
		when(muestra1.getState()).thenReturn(estadoMuestraVerificada);
		when(muestra2.getState()).thenReturn(estadoMuestraVerificada);
		when(muestra3.getState()).thenReturn(estadoMuestraOpinadaPorBasicos);
		when(muestra4.getState()).thenReturn(estadoMuestraOpinadaPorExpertos);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeNivelDeVerificacion1.filtrarMuestras(muestrasAFiltrar);
		
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
		when(muestra1.getState()).thenReturn(estadoMuestraVerificada);
		when(muestra2.getState()).thenReturn(estadoMuestraVerificada);
		when(muestra3.getState()).thenReturn(estadoMuestraVerificada);
		when(muestra4.getState()).thenReturn(estadoMuestraVerificada);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeNivelDeVerificacion1.filtrarMuestras(muestrasAFiltrar);
		
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
		when(muestra1.getState()).thenReturn(estadoMuestraOpinadaPorBasicos);
		when(muestra2.getState()).thenReturn(estadoMuestraOpinadaPorBasicos);
		when(muestra3.getState()).thenReturn(estadoMuestraOpinadaPorBasicos);
		when(muestra4.getState()).thenReturn(estadoMuestraOpinadaPorExpertos);
		
		// Exercise
		Set<Muestra> setDeMuestrasResultante = filtroDeNivelDeVerificacion1.filtrarMuestras(muestrasAFiltrar);
		
		// Verify
		assertTrue(setDeMuestrasResultante.isEmpty());
	}
}
