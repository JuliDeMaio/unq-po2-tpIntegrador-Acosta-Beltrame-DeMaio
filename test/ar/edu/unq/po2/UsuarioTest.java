package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.enums.TipoDeOpinion;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoInterno;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

class UsuarioTest {

	private Usuario julianBasico;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	private Muestra muestra5;
	private Muestra muestra6;
	private Muestra muestra7;
	private Muestra muestra8;
	private Muestra muestra9;
	private Muestra muestra10;
	private Muestra muestra11;
	
	private Opinion opinion1;
	private Opinion opinion2;
	private Opinion opinion3;
	private Opinion opinion4;
	private Opinion opinion5;
	private Opinion opinion6;
	private Opinion opinion7;
	private Opinion opinion8;
	private Opinion opinion9;
	private Opinion opinion10;
	private Opinion opinion11;
	private Opinion opinion12;
	private Opinion opinion13;
	private Opinion opinion14;
	private Opinion opinion15;
	private Opinion opinion16;
	private Opinion opinion17;
	private Opinion opinion18;
	private Opinion opinion19;
	private Opinion opinion20;
	private Opinion opinion21;
	
	private EstadoUsuario estadoUsuarioBasico;
	private EstadoUsuario estadoUsuarioExpertoInterno;
	
	private EstadoMuestraOpinadaPorBasicos estadoMuestraOpinadaPorBasicos;
	
	private AppWeb appWeb;
	
	@BeforeEach
	void setUp() {
		
		// Dummy
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
		muestra6 = mock(Muestra.class);
		muestra7 = mock(Muestra.class);
		muestra8 = mock(Muestra.class);
		muestra9 = mock(Muestra.class);
		muestra10 = mock(Muestra.class);
		muestra11 = mock(Muestra.class);
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		opinion5 = mock(Opinion.class);
		opinion6 = mock(Opinion.class);
		opinion7 = mock(Opinion.class);
		opinion8 = mock(Opinion.class);
		opinion9 = mock(Opinion.class);
		opinion10 = mock(Opinion.class);
		opinion11 = mock(Opinion.class);
		opinion12 = mock(Opinion.class);
		opinion13 = mock(Opinion.class);
		opinion14 = mock(Opinion.class);
		opinion15 = mock(Opinion.class);
		opinion16 = mock(Opinion.class);
		opinion17 = mock(Opinion.class);
		opinion18 = mock(Opinion.class);
		opinion19 = mock(Opinion.class);
		opinion20 = mock(Opinion.class);
		opinion21 = mock(Opinion.class);
	
		estadoUsuarioBasico = mock(EstadoUsuarioBasico.class);
		estadoUsuarioExpertoInterno = mock(EstadoUsuarioExpertoInterno.class);
		
		estadoMuestraOpinadaPorBasicos = mock(EstadoMuestraOpinadaPorBasicos.class);
		
		appWeb = AppWeb.getInstance();
		
		// SUT
		julianBasico = new Usuario(estadoUsuarioBasico);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		appWeb.reiniciarSistema();
	}

	@Test
	void testInicializacionDeUnUsuario() {
		
		// Setup
		EstadoUsuario estadoUsuarioEsperado = estadoUsuarioBasico;
		
		// Exercise - Verify
		assertTrue(julianBasico.obtenerMuestrasRegistradas().isEmpty());
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
		appWeb.guardarMuestra(muestra1);
		appWeb.guardarMuestra(muestra2);
		when(muestra1.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra2.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
				
		// Verify
		assertEquals(cantidadMuestrasEsperadas, julianBasico.cantidadMuestras());
	}
	
	@Test
	void testUnUsuarioDelegaLaVerificacionDeLaEmisionDeLaOpinion() throws UsuarioException, MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		
		// Exercise
		julianBasico.emitirOpinionDe(muestra1, Chinche.PHTIACHINCHE);
		
		// Verify
		verify(julianBasico.getState(), times(1)).realizarVerificacionesPara(Mockito.any(Muestra.class), (Mockito.any(Opinion.class)));
	}

	@Test
    void testUnUsuarioDelegaLaVerificacionDeLaEmisionDeLaOpinionQueLanzaUnaExcepcion() throws UsuarioException, MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException { 
		
		// Exercise
		julianBasico.emitirOpinionDe(muestra1, TipoDeOpinion.IMAGENPOCOCLARA);
		
		// Verify
		verify(estadoUsuarioBasico, times(1)).realizarVerificacionesPara(Mockito.any(Muestra.class), any(Opinion.class));
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
		
		appWeb.guardarMuestra(muestra1);
		appWeb.guardarMuestra(muestra2);
		appWeb.guardarMuestra(muestra3);
		appWeb.guardarMuestra(muestra4);
		appWeb.guardarMuestra(muestra5);
		
		when(muestra1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra2.seEmitioEnLosUltimos30Dias()).thenReturn(false);
		when(muestra3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra4.seEmitioEnLosUltimos30Dias()).thenReturn(false);
		when(muestra5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		when(muestra1.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra2.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra3.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra4.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra5.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		
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
	
	@Test
	void testUnUsuarioDelegaLaVerificacionDeUnCategoria() {
		
		// Exercise
		julianBasico.actualizarCategoria();
		
		// Verify
		verify(estadoUsuarioBasico, times(1)).actualizarCategoria(julianBasico);
	}
	
	@Test
	/**
	 * @Note: Este test prueba el caso en el que el usuario cumple todos los requisitos de muestras y opiniones. 
	 */
	void testUnUsuarioSabeQueCumpleConLosRequisitosParaSerUsuarioExperto() {
		// Setup
		appWeb.guardarMuestra(muestra1);
		appWeb.guardarMuestra(muestra2);
		appWeb.guardarMuestra(muestra3);
		appWeb.guardarMuestra(muestra4);
		appWeb.guardarMuestra(muestra5);
		appWeb.guardarMuestra(muestra6);
		appWeb.guardarMuestra(muestra7);
		appWeb.guardarMuestra(muestra8);
		appWeb.guardarMuestra(muestra9);
		appWeb.guardarMuestra(muestra10);
		appWeb.guardarMuestra(muestra11);
		
		when(opinion1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion2.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion4.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion6.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion7.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion8.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion9.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion10.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion11.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion12.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion13.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion14.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion15.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion16.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion17.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion18.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion19.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion20.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion21.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		julianBasico.guardarOpinion(opinion1);
		julianBasico.guardarOpinion(opinion2);
		julianBasico.guardarOpinion(opinion3);
		julianBasico.guardarOpinion(opinion4);
		julianBasico.guardarOpinion(opinion5);
		julianBasico.guardarOpinion(opinion6);
		julianBasico.guardarOpinion(opinion7);
		julianBasico.guardarOpinion(opinion8);
		julianBasico.guardarOpinion(opinion9);
		julianBasico.guardarOpinion(opinion10);
		julianBasico.guardarOpinion(opinion11);
		julianBasico.guardarOpinion(opinion12);
		julianBasico.guardarOpinion(opinion13);
		julianBasico.guardarOpinion(opinion14);
		julianBasico.guardarOpinion(opinion15);
		julianBasico.guardarOpinion(opinion16);
		julianBasico.guardarOpinion(opinion17);
		julianBasico.guardarOpinion(opinion18);
		julianBasico.guardarOpinion(opinion19);
		julianBasico.guardarOpinion(opinion20);
		julianBasico.guardarOpinion(opinion21);
		
		when(muestra1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra2.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra4.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra6.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra7.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra8.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra9.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra10.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra11.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		when(muestra1.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra2.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra3.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra4.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra5.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra6.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra7.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra8.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra9.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra10.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		when(muestra11.esDueñoDeLaMuestra(julianBasico)).thenReturn(true);
		
		// Verify
		assertTrue(julianBasico.cumpleConLosRequisitosDeUsuarioExperto());
	}
	
	@Test
	void testUnUsuarioSabeQueNoCumpleConLosRequisitosParaSerUsuarioExperto() {
		// Verify
		assertFalse(julianBasico.cumpleConLosRequisitosDeUsuarioExperto());
	}
	
	@Test
	// Este test prueba el caso en el que el usuario cumple el requisito de muestras en los ultimos 30 dias pero no así el de las opiniones.
	void testUnUsuarioSabeQueNoCumpleConLosRequisitosParaSerUsuarioExperto2() {
		// Setup
		
		when(muestra1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra2.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra4.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra6.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra7.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra8.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra9.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra10.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(muestra11.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		// Verify
		assertFalse(julianBasico.cumpleConLosRequisitosDeUsuarioExperto());
	}
	
	@Test
	// Este test prueba el caso en el que el usuario cumple el requisito de opiniones en los ultimos 30 dias pero no así el de las muestras.
	void testUnUsuarioSabeQueNoCumpleConLosRequisitosParaSerUsuarioExperto3() {
		// SetUp
		when(opinion1.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion2.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion3.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion4.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion5.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion6.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion7.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion8.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion9.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion10.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion11.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion12.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion13.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion14.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion15.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion16.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion17.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion18.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion19.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion20.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		when(opinion21.seEmitioEnLosUltimos30Dias()).thenReturn(true);
		
		julianBasico.guardarOpinion(opinion1);
		julianBasico.guardarOpinion(opinion2);
		julianBasico.guardarOpinion(opinion3);
		julianBasico.guardarOpinion(opinion4);
		julianBasico.guardarOpinion(opinion5);
		julianBasico.guardarOpinion(opinion6);
		julianBasico.guardarOpinion(opinion7);
		julianBasico.guardarOpinion(opinion8);
		julianBasico.guardarOpinion(opinion9);
		julianBasico.guardarOpinion(opinion10);
		julianBasico.guardarOpinion(opinion11);
		julianBasico.guardarOpinion(opinion12);
		julianBasico.guardarOpinion(opinion13);
		julianBasico.guardarOpinion(opinion14);
		julianBasico.guardarOpinion(opinion15);
		julianBasico.guardarOpinion(opinion16);
		julianBasico.guardarOpinion(opinion17);
		julianBasico.guardarOpinion(opinion18);
		julianBasico.guardarOpinion(opinion19);
		julianBasico.guardarOpinion(opinion20);
		julianBasico.guardarOpinion(opinion21);
		
		// Verify
		assertFalse(julianBasico.cumpleConLosRequisitosDeUsuarioExperto());
	}
	
	@Test
	void testUnUsuarioRecibeLaDelegacionDelEstadoDeUnaMuestraYEsteLaDelegaASuEstadoDeCategoria() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Exercise
		julianBasico.gestionarEstadoMuestraPara(estadoMuestraOpinadaPorBasicos, muestra1);
		// Verify
		verify(estadoUsuarioBasico, times(1)).gestionarEstadoMuestraPara(estadoMuestraOpinadaPorBasicos, muestra1);
	}	
	
	@Test
	void testUnUsuarioBasicoConoceSiEsUsuarioExperto() {
		// Setup
		when(estadoUsuarioBasico.esEstadoExperto()).thenReturn(false);
		
		// Verify
		assertFalse(julianBasico.esUsuarioExperto());
	}
}