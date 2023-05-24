package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import ar.edu.unq.po2.usuarioExceptions.UsuarioException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class UsuarioTest {

	private Usuario julianBasico;
	private Usuario federicoExpertoInterno;
	private Usuario francoExpertoExterno;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Opinion opinion1;
	private Opinion opinion2;
	
	private EstadoUsuario estadoUsuarioBasico;
	private EstadoUsuario estadoUsuarioExpertoInterno;
	private EstadoUsuario estadoUsuarioExpertoExterno;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@BeforeEach
	void setUp() {
		
		// Dummys
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		
		// Mock
		estadoUsuarioBasico = mock(EstadoUsuarioBasico.class);
		estadoUsuarioExpertoInterno = mock(EstadoUsuarioExpertoInterno.class);
		estadoUsuarioExpertoExterno = mock(EstadoUsuarioExpertoExterno.class);
		
		// SUT
		julianBasico = new Usuario(estadoUsuarioBasico);
		federicoExpertoInterno = new Usuario(estadoUsuarioExpertoInterno);
		francoExpertoExterno = new Usuario(estadoUsuarioExpertoExterno);

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
		
		//Exercise
		julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.PHTIACHINCHE);
		
		//Verify
		verify(julianBasico.getState(), times(1)).realizarVerificacionesPara(Mockito.any(Muestra.class), (Mockito.any(Opinion.class)));
	}

	@Test
    public void testUnUsuarioDelegaLaVerificacionDeLaEmisionDeLaOpinionQueLanzaUnaExcepcion() { 
        
		//Setup
		when(muestra1.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1)).thenReturn(null);
				/*
				thenReturn(
				if (muestra1.esDueñoDeLaMuestra(julianBasico)) {
					throw new UsuarioNoEsOpinionUnicaException();
			});
		*/
		//Excercise-Verify
		Assertions.assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.IMAGENPOCOCLARA);
        });
    }
}

