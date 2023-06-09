package ar.edu.unq.po2.estadosDeMuestraTest;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

class EstadoMuestraOpinadaPorBasicosTest {
	
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	private EstadoMuestraOpinadaPorBasicos estadoMuestraOpinadaPorBasicos;

	@BeforeEach
	void setUp() {
		
		// DOC
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
		
		// SUT
		estadoMuestraOpinadaPorBasicos = new EstadoMuestraOpinadaPorBasicos();
	}

	@Test
	void testUnEstadoMuestraOpinadaPorBasicosDelegaLaVerificacionAUnUsuarioYEsteLaRecibe() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Setup
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		// Exercise
		estadoMuestraOpinadaPorBasicos.verificarMuestra(muestra1, opinion1);
		// Verify
		verify(usuario1, times(1)).gestionarEstadoMuestraPara(estadoMuestraOpinadaPorBasicos, muestra1);
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorBasicosRealizaLaVerificacionParaUnUsuarioBasico() {
		// Setup
		when(muestra1.obtenerTipoDeOpinionMayoritaria()).thenReturn(Chinche.CHINCHEFOLIADA);
		// Exercise
		estadoMuestraOpinadaPorBasicos.realizarVerificacionParaUsuarioBasico(muestra1);
		// Verify
		verify(muestra1, times(1)).actualizarResultadoActual(Chinche.CHINCHEFOLIADA);
		verify(muestra1, times(1)).obtenerTipoDeOpinionMayoritaria();
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorBasicosRealizaLaVerificacionParaUnUsuarioExperto() {
		// Exercise
		estadoMuestraOpinadaPorBasicos.realizarVerificacionParaUsuarioExperto(muestra1);
		// Verify
		verify(muestra1, times(1)).setState(any());
		verify(muestra1, times(1)).solicitarVerificacionDeResultadoActual();
	}
	
	@Test
	void testUnEstadoMuestraOpinadaPorBasicosRealizaLaVerificacionDelResultadoActualDeLaMuestra() {
		// Setup
		when(muestra1.obtenerTipoDeOpinionMayoritaria()).thenReturn(Chinche.CHINCHEFOLIADA);
		// Exercise
		estadoMuestraOpinadaPorBasicos.verificarResultadoActualDeMuestra(muestra1);
		// Verify
		verify(muestra1, times(1)).obtenerTipoDeOpinionMayoritaria();
		verify(muestra1, times(1)).actualizarResultadoActual(Chinche.CHINCHEFOLIADA);
	}

}
