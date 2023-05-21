package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	
	private Usuario julianBasico;
	
	private Muestra muestra1;
	private Muestra muestra2;
	
	private IEstadoUsuario estadoUsuarioBasico;

	@BeforeEach
	void setUp() {
		
		// Dummys
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		
		// Mock
		estadoUsuarioBasico = mock(EstadoUsuarioBasico.class);
		
		julianBasico = new Usuario(estadoUsuarioBasico);
	}

	@Test
	void testInicializacionDeUnUsuario() {
		
		// Setup
		IEstadoUsuario estadoUsuarioEsperado = estadoUsuarioBasico;
		
		// Exercise - Verify
		assertTrue(julianBasico.getMuestrasRegistradas().isEmpty());
		assertTrue(julianBasico.getOpinionesRegistradas().isEmpty());
		assertTrue(julianBasico.getState().equals(estadoUsuarioEsperado));
	}
	
	@Test
	void testCantidadDeOpinionesDeUnUsuario() {
		
		// Setup
		int cantidadOpinionesEsperadas = 2;
		
		// Exercise
		julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.CHINCHEFOLIADA);
		julianBasico.emitirOpinionDe(muestra2, TipoDeOpinion.VINCHUCASORDIDA);
		
		// Verify
		assertEquals(cantidadOpinionesEsperadas, julianBasico.cantidadOpiniones());
	}
	
	@Test
	void testCantidadDeMuestrasDeUnUsuario() {
		
	}

}
