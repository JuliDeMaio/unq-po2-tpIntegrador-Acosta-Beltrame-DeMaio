package ar.edu.unq.po2.estadosDeMuestraTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraVerificada;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

class EstadoMuestraVerificadaTest {

	private Muestra muestra1;
	private Opinion opinion1;
	private EstadoMuestraVerificada estadoMuestraVerificada;

	@BeforeEach
	void setUp() {
		
		// DOC
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		
		// SUT
		estadoMuestraVerificada = new EstadoMuestraVerificada();
	}

	@Test
	void testUnEstadoMuestraVerificadaDelegaLaVerificacionAUnUsuarioExpertoYArrojaUnaExcepcion() {
		// Setup
		String errorEsperado = "Esta muestra ya esta verificada, no puede ser modificada.";
				
		// Exercise-Verify
		Exception error = assertThrows(MuestraEstaVerificadaException.class, () -> {
			estadoMuestraVerificada.verificarMuestra(muestra1, opinion1);
		});
			        
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testUnEstadoMuestraVerificadaRealizaLaVerificacionParaUnUsuarioBasicoYArrojaUnaExcepcion() throws MuestraEstaVotadaPorExpertosException {
		// Setup
		String errorEsperado = "Esta muestra ya esta verificada, no puede ser modificada.";
						
		// Exercise-Verify
		Exception error = assertThrows(MuestraEstaVerificadaException.class, () -> {
			estadoMuestraVerificada.realizarVerificacionParaUsuarioBasico(muestra1);
		});
					        
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testUnEstadoMuestraVerificadaRealizaLaVerificacionParaUnUsuarioExpertoYArrojaUnaExcepcion() throws MuestraEstaVerificadaException {
		// Setup
		String errorEsperado = "Esta muestra ya esta verificada, no puede ser modificada.";
						
		// Exercise-Verify
		Exception error = assertThrows(MuestraEstaVerificadaException.class, () -> {
			estadoMuestraVerificada.realizarVerificacionParaUsuarioExperto(muestra1);
		});
					        
		assertEquals(error.getMessage(), errorEsperado);
	}

	@Test
	void testUnEstadoMuestraVerificadaRealizaLaVerificacionDelResultadoActualDeLaMuestra() {
		// Setup
		when(muestra1.obtenerTipoDeOpinionMayoritariaDeExpertos()).thenReturn(Chinche.PHTIACHINCHE);
		
		// Exercise
		estadoMuestraVerificada.verificarResultadoActualDeMuestra(muestra1);
		
		// Verify
		verify(muestra1, times(1)).obtenerTipoDeOpinionMayoritariaDeExpertos();
		verify(muestra1, times(1)).actualizarResultadoActual(Chinche.PHTIACHINCHE);
	}

}
