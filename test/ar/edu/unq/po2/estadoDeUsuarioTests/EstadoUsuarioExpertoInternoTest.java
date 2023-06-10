package ar.edu.unq.po2.estadoDeUsuarioTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorUnExperto;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoInterno;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class EstadoUsuarioExpertoInternoTest {

	private EstadoUsuarioExpertoInterno estadoUsuarioExpertoInterno;
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	private EstadoMuestraOpinadaPorUnExperto estadoMuestraOpinadaPorUnExperto;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		estadoUsuarioExpertoInterno = new EstadoUsuarioExpertoInterno();
		
		// Dummy
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
		estadoMuestraOpinadaPorUnExperto = mock(EstadoMuestraOpinadaPorUnExperto.class);
	}

	@Test
	void testInicializacionEstadoDeUsuarioExpertoInterno() {
		// Setup
		EstadoUsuarioExpertoInterno estadoEsperado = new EstadoUsuarioExpertoInterno();
		
		// Verify
		assertEquals(estadoUsuarioExpertoInterno.getClass(), estadoEsperado.getClass());
	}
	
	@Test
	void testEstadoDeUsuarioExpertoInternoRealizaLaVerificacionYNoArrojaExcepciones() {
		// Setup
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(false);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		when(muestra1.obtenerNivelDeVerificacion()).thenReturn(NivelDeVerificacion.VOTADA);
				
		// Verify
		assertDoesNotThrow(() -> estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1));	
	}
	
	@Test
	void testEstadoUsuarioExpertoInternoRealizaLaVerificacionYArrojaUsuarioEsDueñoDeLaMuestraException() {
		// Setup
		String errorEsperado = "Usted es el creador de la muestra, no puede opinar sobre ella.";
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioEsDueñoDeLaMuestraException.class, () -> {
			estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testEstadoUsuarioExpertoInternoRealizaLaVerificacionYArrojaUsuarioNoEsOpinionUnicaException() {
		// Setup
		String errorEsperado = "Usted ya ha emitido una opinión sobre esta muestra.";
		when(muestra1.opinoElUsuario(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}

	@Test
	void testEstadoUsuarioExpertoInternoRealizaLaVerificacionYArrojaUsuarioEsMuestraVerificadaException() {
		// Setup
		String errorEsperado = "La muestra ya está verificada, nadie puede opinar sobre la misma.";
		when(muestra1.obtenerNivelDeVerificacion()).thenReturn(NivelDeVerificacion.VERIFICADA);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Exercise-Verify
		Exception error = assertThrows(UsuarioEsMuestraVerificadaException.class, () -> {
			estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1);
        });
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoBasico() {
		// Verify
		assertFalse(estadoUsuarioExpertoInterno.esEstadoBasico());
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoExpertoInterno() {
		// Verify
		assertTrue(estadoUsuarioExpertoInterno.esEstadoExpertoInterno());
	}
	
	@Test
	void testUnEstadoConoceSiEsEstadoExpertoExterno() {
		// Verify
		assertFalse(estadoUsuarioExpertoInterno.esEstadoExpertoExterno());
	}

	@Test
	void testUnEstadoActualizaLaCategoriaDeUnUsuarioQueNoCumpleLosRequisitosParaSeguirSiendoUsuarioExpertoInterno() {
		// Setup
		when(usuario1.cumpleConLosRequisitosDeUsuarioExperto()).thenReturn(false);
		// Exercise
		estadoUsuarioExpertoInterno.actualizarCategoria(usuario1);
		// Verify
		verify(usuario1, times(1)).setState(any());
	}
	
	@Test
	void testUnEstadoNoActualizaLaCategoriaDeUnUsuarioQueCumpleLosRequisitosParaSeguirSiendoUsuarioExpertoInterno() {
		// Setup
		when(usuario1.cumpleConLosRequisitosDeUsuarioExperto()).thenReturn(true);
		// Exercise
		estadoUsuarioExpertoInterno.actualizarCategoria(usuario1);
		// Verify
		verify(usuario1, never()).setState(any());
	}
	
	@Test
	void testUnUsuarioExpertoInternoRecibeLaDelegacionDeLaVerificacionDeLaMuestra() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Exercise
		estadoUsuarioExpertoInterno.gestionarEstadoMuestraPara(estadoMuestraOpinadaPorUnExperto, muestra1);
		
		// Verify
		verify(estadoMuestraOpinadaPorUnExperto, times(1)).realizarVerificacionParaUsuarioExperto(muestra1);
	}
	
	@Test
	void unEstadoDeUsuarioBasicoSabeQueNoEsUnEstadoExperto() {
		assertTrue(estadoUsuarioExpertoInterno.esEstadoExperto());
	}
}
