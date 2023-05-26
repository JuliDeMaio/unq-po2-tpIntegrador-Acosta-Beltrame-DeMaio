package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoInterno;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class UsuarioTest {

	private Usuario julianBasico;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	private Muestra muestra5;
	
	private Opinion opinion1;
	private Opinion opinion2;
	private Opinion opinion3;
	private Opinion opinion4;
	private Opinion opinion5;
	
	private EstadoUsuario estadoUsuarioBasico;
	private EstadoUsuario estadoUsuarioExpertoInterno;
	
	@BeforeEach
	void setUp() {
		
		// Dummy
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		opinion5 = mock(Opinion.class);
		
		// Mock
		estadoUsuarioBasico = mock(EstadoUsuarioBasico.class);
		estadoUsuarioExpertoInterno = mock(EstadoUsuarioExpertoInterno.class);
		
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
	void testVerificacionCambioDeStateDeUnUsuario() {
		
		// Setup
		EstadoUsuario estadoEsperado = estadoUsuarioExpertoInterno;
		
		// Exercise
		julianBasico.setState(estadoEsperado);
		
		// Verify
		assertEquals(estadoEsperado, julianBasico.getState());
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
    void testUnUsuarioDelegaLaVerificacionDeLaEmisionDeLaOpinionQueLanzaUnaExcepcion() throws UsuarioException { 
		
		
		// Setup
		Mockito.doThrow(UsuarioNoEsOpinionUnicaException.class).when(estadoUsuarioBasico)
			.realizarVerificacionesPara(Mockito.any(Muestra.class), Mockito.any(Opinion.class));
		
		// Exercise-Verify
		assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.IMAGENPOCOCLARA);
        });
    }
	
	@Test
    void testUnUsuarioConoceLaCantidadDeOpinionesQueEmitioEnLosUltimos30Dias() {
		// Setup
		int cantidadDeOpinionesEsperadas = 3;
		
		when(opinion1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion2.seEmitioEnLosUltimos30Dias()).thenReturn(false);
		when(opinion3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion4.seEmitioEnLosUltimos30Dias()).thenReturn(false);
		when(opinion5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		julianBasico.guardarOpinion(opinion1);
		julianBasico.guardarOpinion(opinion2);
		julianBasico.guardarOpinion(opinion3);
		julianBasico.guardarOpinion(opinion4);
		julianBasico.guardarOpinion(opinion5);
		
		// Verify
		assertEquals(cantidadDeOpinionesEsperadas, julianBasico.cantidadDeOpinionesEmitidasEnUltimos30Dias());		
	}
	
	@Test
    void testUnUsuarioConoceLaCantidadDeMuestrasQueEmitioEnLosUltimos30Dias() {
		// Setup
		int cantidadDeMuestrasEsperadas = 3;
		
		when(muestra1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra2.seEmitioEnLosUltimos30Dias()).thenReturn(false);
		when(muestra3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra4.seEmitioEnLosUltimos30Dias()).thenReturn(false);
		when(muestra5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		julianBasico.guardarMuestra(muestra1);
		julianBasico.guardarMuestra(muestra2);
		julianBasico.guardarMuestra(muestra3);
		julianBasico.guardarMuestra(muestra4);
		julianBasico.guardarMuestra(muestra5);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperadas, julianBasico.cantidadDeMuestrasEmitidasEnUltimos30Dias());
	}
	
	@Test
    void testUnUsuarioBasicoConoceSiEsUsuarioBasico() {
		// Setup
		when(estadoUsuarioBasico.esEstadoBasico()).thenReturn(true);
		
		// Verify
		assertTrue(julianBasico.esUsuarioBasico());
	}
	
	@Test
    void testUnUsuarioBasicoConoceSiEsUsuarioExpertoInterno() {
		// Setup
		when(estadoUsuarioBasico.esEstadoExpertoInterno()).thenReturn(false);
		
		// Verify
		assertFalse(julianBasico.esUsuarioExpertoInterno());
	}
	
	@Test
    void testUnUsuarioBasicoConoceSiEsUsuarioExpertoExterno() {
		// Setup
		when(estadoUsuarioBasico.esEstadoExpertoExterno()).thenReturn(false);
		
		// Verify
		assertFalse(julianBasico.esUsuarioExpertoExterno());
	}
	
}



