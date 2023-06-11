package ar.edu.unq.po2.estadoDeUsuarioTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.AppWeb;
import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioYaVotoUnExpertoException;

class EstadoUsuarioBasicoTest {

	private EstadoUsuarioBasico estadoUsuarioBasico;
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	private EstadoMuestraOpinadaPorBasicos estadoMuestraOpinadaPorBasicos;
	private AppWeb appWeb;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		estadoUsuarioBasico = new EstadoUsuarioBasico();
		
		// DOC
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
		estadoMuestraOpinadaPorBasicos = mock(EstadoMuestraOpinadaPorBasicos.class);
		appWeb = AppWeb.getInstance();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		appWeb.reiniciarSistema();
	}

	@Test
	void testInicializacionEstadoDeUsuarioBasico() {
		// Setup
		EstadoUsuarioBasico estadoEsperado = new EstadoUsuarioBasico();
		
		// Verify
		assertEquals(estadoUsuarioBasico.getClass(), estadoEsperado.getClass());
	}
	
	@Test
	void testEstadoDeUsuarioBasicoRealizaLaVerificacionYNoArrojaExcepciones() {
		// Setup
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(false);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		when(muestra1.obtenerNivelDeVerificacion()).thenReturn(NivelDeVerificacion.VOTADA);
		
		// Verify
		assertDoesNotThrow(() -> estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1));	
	}
	
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioEsDueñoDeLaMuestraException() {
		// Setup
		String errorEsperado = "Usted es el creador de la muestra, no puede opinar sobre ella.";
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioEsDueñoDeLaMuestraException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });

		assertEquals(error.getMessage(), errorEsperado);
	}			        
			
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioNoEsOpinionUnicaException() {
		// Setup
		String errorEsperado = "Usted ya ha emitido una opinión sobre esta muestra.";
		when(muestra1.opinoElUsuario(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioYaVotoUnExpertoException() {
		// Setup
		String errorEsperado = "La muestra cuenta con la opinion de un experto, usted no cuenta con la categoria necesaria para opinar sobre ella.";
		when(muestra1.opinoAlMenosUnExperto()).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioYaVotoUnExpertoException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioEsMuestraVerificadaException() {
		// Setup
		String errorEsperado = "La muestra ya está verificada, nadie puede opinar sobre la misma.";
		when(muestra1.obtenerNivelDeVerificacion()).thenReturn(NivelDeVerificacion.VERIFICADA);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		Exception error = assertThrows(UsuarioEsMuestraVerificadaException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoBasico() {
		// Verify
		assertTrue(estadoUsuarioBasico.esEstadoBasico());
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoExpertoInterno() {
		// Verify
		assertFalse(estadoUsuarioBasico.esEstadoExpertoInterno());
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoExpertoExterno() {
		// Verify
		assertFalse(estadoUsuarioBasico.esEstadoExpertoExterno());
	}
	
	@Test
	void testUnEstadoActualizaLaCategoriaDeUnUsuarioQueCumpleLosRequisitosParaSerUsuarioExpertoInterno() {
		// Setup
		when(usuario1.cumpleConLosRequisitosDeUsuarioExperto()).thenReturn(true);
		// Exercise
		estadoUsuarioBasico.actualizarCategoria(usuario1);
		// Verify
		verify(usuario1, times(1)).setState(any());
	}
	
	@Test
	void testUnEstadoNoActualizaLaCategoriaDeUnUsuarioQueNoCumpleLosRequisitosParaSerUsuarioExpertoInterno() {
		// Setup
		when(usuario1.cumpleConLosRequisitosDeUsuarioExperto()).thenReturn(false);
		// Exercise
		estadoUsuarioBasico.actualizarCategoria(usuario1);
		// Verify
		verify(usuario1, never()).setState(any());
	}
	
	@Test
	void unEstadoDeUsuarioBasicoRecibeLaDelegacionDeLaVerificacionDeLaMuestra() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Exercise
		estadoUsuarioBasico.gestionarEstadoMuestraPara(estadoMuestraOpinadaPorBasicos, muestra1);
		
		// Verify
		verify(estadoMuestraOpinadaPorBasicos, times(1)).realizarVerificacionParaUsuarioBasico(muestra1);
	}
	
	@Test
	void unEstadoDeUsuarioBasicoSabeQueNoEsUnEstadoExperto() {
		assertFalse(estadoUsuarioBasico.esEstadoExperto());
	}
}
