package ar.edu.unq.po2.estadoDeUsuarioTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoInterno;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class EstadoUsuarioExpertoInternoTest {

	private EstadoUsuarioExpertoInterno estadoUsuarioExpertoInterno;
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		estadoUsuarioExpertoInterno = new EstadoUsuarioExpertoInterno();
		
		// Dummy
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
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
		// Verify
		assertDoesNotThrow(() -> estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1));	
	}
	
	@Test
	void testEstadoUsuarioExpertoInternoRealizaLaVerificacionYArrojaUsuarioEsDueñoDeLaMuestraException() {
		// Setup
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioEsDueñoDeLaMuestraException.class, () -> {
			estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1);
        });
	}
	
	@Test
	void testEstadoUsuarioExpertoInternoRealizaLaVerificacionYArrojaUsuarioNoEsOpinionUnicaException() {
		// Setup
		when(muestra1.opinoElUsuario(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1);
        });
	}

	@Test
	void testEstadoUsuarioExpertoInternoRealizaLaVerificacionYArrojaUsuarioEsMuestraVerificadaException() {
		// Setup
		when(muestra1.esVerificada()).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioEsMuestraVerificadaException.class, () -> {
			estadoUsuarioExpertoInterno.realizarVerificacionesPara(muestra1, opinion1);
        });
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
	
}
