package ar.edu.unq.po2.estadosDeMuestraTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.ResultadoMuestra;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorExpertos;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

class EstadoMuestraOpinadaPorExpertosTest {

	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	private EstadoMuestraOpinadaPorExpertos estadoMuestraOpinadaPorExpertos;

	@BeforeEach
	void setUp() {
		
		// DOC
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
		
		// SUT
		estadoMuestraOpinadaPorExpertos = new EstadoMuestraOpinadaPorExpertos();
	}

	@Test
	void testUnEstadoMuestraOpinadaPorExpertosDelegaLaVerificacionAUnUsuarioYEsteLaRecibe() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Setup
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise
		estadoMuestraOpinadaPorExpertos.verificarMuestra(muestra1, opinion1);
		
		// Verify
		verify(usuario1, times(1)).gestionarEstadoMuestraPara(estadoMuestraOpinadaPorExpertos, muestra1);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorExpertosRealizaLaVerificacionParaUnUsuarioBasico() throws MuestraEstaVotadaPorExpertosException {
		// Setup
		String errorEsperado = "Esta muestra ya fue votada por expertos.";
		
		// Exercise-Verify
		Exception error = assertThrows(MuestraEstaVotadaPorExpertosException.class, () -> {
			estadoMuestraOpinadaPorExpertos.realizarVerificacionParaUsuarioBasico(muestra1);
	    });
	        
	    assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorExpertosRealizaLaVerificacionParaUnUsuarioExpertoYCambiaSuEstado() {
		// Setup
		when(muestra1.hay2OpinionesQueCoinciden()).thenReturn(true);
		
		// Exercise
		estadoMuestraOpinadaPorExpertos.realizarVerificacionParaUsuarioExperto(muestra1);
		
		// Verify
		verify(muestra1, times(1)).setState(any());
		verify(muestra1, times(1)).solicitarVerificacionDeResultadoActual();
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorExpertosRealizaLaVerificacionParaUnUsuarioExpertoYNoCambiaSuEstado() {
		// Setup
		when(muestra1.hay2OpinionesQueCoinciden()).thenReturn(false);
		
		// Exercise
		estadoMuestraOpinadaPorExpertos.realizarVerificacionParaUsuarioExperto(muestra1);
		
		// Verify
		verify(muestra1, never()).setState(any());
		verify(muestra1, times(1)).actualizarResultadoActual(ResultadoMuestra.NODEFINIDA);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorExpertosRealizaLaVerificacionDelResultadoActualDeLaMuestra() {
		// Exercise
		estadoMuestraOpinadaPorExpertos.verificarResultadoActualDeMuestra(muestra1);
		
		// Verify
		verify(muestra1, times(1)).actualizarResultadoActual(ResultadoMuestra.NODEFINIDA);
	}

}
