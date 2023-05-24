package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
	void testUnUsuarioBasicoNoPuedeOpinarDosVeces() {
		
		// Setup
		if (muestra.opinoElUsuario(opinion.getUsuarioDueño())) {
			throw new UsuarioNoEsOpinionUnicaException();
		}
		
		// Exercise
		julianBasico.emitirOpinionDeSiendoUsuarioBasico(muestra1, opinion1);
		julianBasico.emitirOpinionDeSiendoUsuarioBasico(muestra1, opinion1);
				
		// Verify
		verify(this.julianBasico.getState(), times(0)).gestionarOpinionPara(Mockito.any(Muestra.class), Mockito.any(Opinion.class));
		verify(this.julianBasico.getState(), Mockito.
	}

}
