package ar.edu.unq.po2.estadoDeUsuarioTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorUnExperto;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoExterno;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class EstadoUsuarioExpertoExternoTest {

	private EstadoUsuarioExpertoExterno estadoUsuarioExpertoExterno;
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	private EstadoMuestraOpinadaPorUnExperto estadoMuestraOpinadaPorUnExperto;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		estadoUsuarioExpertoExterno = new EstadoUsuarioExpertoExterno();
		
		// Dummy
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
		estadoMuestraOpinadaPorUnExperto = mock(EstadoMuestraOpinadaPorUnExperto.class);
	}

	@Test
	void testInicializacionEstadoDeUsuarioExpertoExterno() {
		// Setup
		EstadoUsuarioExpertoExterno estadoEsperado = new EstadoUsuarioExpertoExterno();
		
		// Verify
		assertEquals(estadoUsuarioExpertoExterno.getClass(), estadoEsperado.getClass());
	}
	
	@Test
	void testEstadoDeUsuarioExpertoExternoRealizaLaVerificacionYNoArrojaExcepciones() {
		// Setup
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(false);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		when(muestra1.obtenerNivelDeVerificacion()).thenReturn(NivelDeVerificacion.VOTADA);
				
		// Verify
		assertDoesNotThrow(() -> estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1));	
	}
	
	@Test
	void testEstadoUsuarioExpertoExternoRealizaLaVerificacionYArrojaUsuarioEsDueñoDeLaMuestraException() {
		// Setup
		String errorEsperado = "Usted es el creador de la muestra, no puede opinar sobre ella.";
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioEsDueñoDeLaMuestraException.class, () -> {
			estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testEstadoUsuarioExpertoExternoRealizaLaVerificacionYArrojaUsuarioNoEsOpinionUnicaException() {
		// Setup
		String errorEsperado = "Usted ya ha emitido una opinión sobre esta muestra.";
		when(muestra1.opinoElUsuario(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		
		Exception error = assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}

	@Test
	void testEstadoUsuarioExpertoExternoRealizaLaVerificacionYArrojaUsuarioEsMuestraVerificadaException() {
		// Setup
		String errorEsperado = "La muestra ya está verificada, nadie puede opinar sobre la misma.";
		when(muestra1.obtenerNivelDeVerificacion()).thenReturn(NivelDeVerificacion.VERIFICADA);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioEsMuestraVerificadaException.class, () -> {
			estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}

	@Test
	void testUnEstadoConoceSiEsEstadoBasico() {
		// Verify
		assertFalse(estadoUsuarioExpertoExterno.esEstadoBasico());
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoExpertoInterno() {
		// Verify
		assertFalse(estadoUsuarioExpertoExterno.esEstadoExpertoInterno());
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoExpertoExterno() {
		// Verify
		assertTrue(estadoUsuarioExpertoExterno.esEstadoExpertoExterno());
	}

	@Test
	void testUnEstadoNuncaActualizaLaCategoriaDeUnUsuarioQueYaEsExpertoExterno() {
		// Exercise
		estadoUsuarioExpertoExterno.actualizarCategoria(usuario1);
		// Verify
		verify(usuario1, never()).setState(any());
	}
	
	@Test
	void testUnUsuarioExpertoExternoRecibeLaDelegacionDeLaVerificacionDeLaMuestra() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Exercise
		estadoUsuarioExpertoExterno.gestionarEstadoMuestraPara(estadoMuestraOpinadaPorUnExperto, muestra1);
		
		// Verify
		verify(estadoMuestraOpinadaPorUnExperto, times(1)).realizarVerificacionParaUsuarioExperto(muestra1);
	}
	
	@Test
	void unEstadoDeUsuarioBasicoSabeQueNoEsUnEstadoExperto() {
		assertTrue(estadoUsuarioExpertoExterno.esEstadoExperto());
	}
}
	
	