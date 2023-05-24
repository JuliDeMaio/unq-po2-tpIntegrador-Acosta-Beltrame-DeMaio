package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.usuarioExceptions.UsuarioException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class UsuarioTest {

	private Usuario julianBasico;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Opinion opinion1;
	private Opinion opinion2;
	
	private EstadoUsuario estadoUsuarioBasico;
	
	@BeforeEach
	void setUp() {
		
		// Dummy
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		
		// Mock
		estadoUsuarioBasico = mock(EstadoUsuarioBasico.class);
		
		// SUT
		julianBasico = new Usuario(estadoUsuarioBasico);
	}

	@Test
	void testInicializacionDeUnUsuario() {
		
		// Setup
		EstadoUsuario estadoUsuarioEsperado = estadoUsuarioBasico;
		
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
		julianBasico.guardarOpinion(opinion1);
		julianBasico.guardarOpinion(opinion2);
		
		// Verify
		assertEquals(cantidadOpinionesEsperadas, julianBasico.cantidadOpiniones());
	}
	
	@Test
	void testCantidadDeMuestrasDeUnUsuario() {
		
		// Setup
		int cantidadMuestrasEsperadas = 2;
				
		// Exercise
		julianBasico.guardarMuestra(muestra1);
		julianBasico.guardarMuestra(muestra2);
				
		// Verify
		assertEquals(cantidadMuestrasEsperadas, julianBasico.cantidadMuestras());
	}
	
	@Test
	void testUnUsuarioDelegaLaVerificacionDeLaEmisionDeLaOpinion() throws UsuarioException {
		
		// Exercise
		julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.PHTIACHINCHE);
		
		// Verify
		verify(julianBasico.getState(), times(1)).realizarVerificacionesPara(Mockito.any(Muestra.class), (Mockito.any(Opinion.class)));
	}

	@Test
    public void testUnUsuarioDelegaLaVerificacionDeLaEmisionDeLaOpinionQueLanzaUnaExcepcion() throws UsuarioException { 
		
		
		// Setup
		Mockito.doThrow(UsuarioNoEsOpinionUnicaException.class).when(estadoUsuarioBasico)
			.realizarVerificacionesPara(Mockito.any(Muestra.class), Mockito.any(Opinion.class));
		
		// Exercise-Verify
		assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.IMAGENPOCOCLARA);
        });
    }
}



