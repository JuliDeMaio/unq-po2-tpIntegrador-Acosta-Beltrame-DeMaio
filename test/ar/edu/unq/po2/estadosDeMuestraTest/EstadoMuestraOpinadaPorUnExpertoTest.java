package ar.edu.unq.po2.estadosDeMuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.enums.Vinchuca;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorUnExperto;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

class EstadoMuestraOpinadaPorUnExpertoTest {

	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	private EstadoMuestraOpinadaPorUnExperto estadoMuestraOpinadaPorUnExperto;

	@BeforeEach
	void setUp() {
		
		// DOC
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
		
		// SUT
		estadoMuestraOpinadaPorUnExperto = new EstadoMuestraOpinadaPorUnExperto();
	}

	@Test
	void testUnEstadoMuestraOpinadaPorUnExpertoDelegaLaVerificacionAUnUsuarioYEsteLaRecibe() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Setup
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		// Exercise
		estadoMuestraOpinadaPorUnExperto.verificarMuestra(muestra1, opinion1);
		// Verify
		verify(usuario1, times(1)).gestionarEstadoMuestraPara(estadoMuestraOpinadaPorUnExperto, muestra1);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorUnExpertoRealizaLaVerificacionParaUnUsuarioBasico() {
		// Setup
		when(muestra1.obtenerTipoDeOpinionMayoritaria()).thenReturn(Chinche.CHINCHEFOLIADA);
		// Exercise
		estadoMuestraOpinadaPorUnExperto.realizarVerificacionParaUsuarioBasico(muestra1);
		// Verify
		verifyNoInteractions(muestra1);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorUnExpertoRealizaLaVerificacionParaUnUsuarioExperto() {
		// Exercise
		estadoMuestraOpinadaPorUnExperto.realizarVerificacionParaUsuarioExperto(muestra1);
		// Verify
		verify(muestra1, times(1)).setState(any());
		verify(muestra1, times(1)).solicitarVerificacionDeResultadoActual();
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorUnExpertoRealizaLaVerificacionDelResultadoActualDeLaMuestra() {
		// Setup
		when(opinion1.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAINFESTANS);
		when(muestra1.obtenerOpinionesDeExpertos()).thenReturn(Arrays.asList(opinion1));
		// Exercise
		estadoMuestraOpinadaPorUnExperto.verificarResultadoActualDeMuestra(muestra1);
		// Verify
		verify(muestra1, times(1)).actualizarResultadoActual(Vinchuca.VINCHUCAINFESTANS);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorBasicosSabeQueNoEstaVerificada() {
		// Setup
		NivelDeVerificacion nivelDeVerificacionEsperado = NivelDeVerificacion.VOTADA;
		
		// Verify
		assertEquals(nivelDeVerificacionEsperado, estadoMuestraOpinadaPorUnExperto.obtenerNivelDeVerificacion());
	}
}
