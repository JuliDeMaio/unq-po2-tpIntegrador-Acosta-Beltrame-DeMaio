package ar.edu.unq.po2.estadoDeUsuarioTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoExterno;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

class EstadoUsuarioExpertoExternoTest {

	private EstadoUsuarioExpertoExterno estadoUsuarioExpertoExterno;
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		estadoUsuarioExpertoExterno = new EstadoUsuarioExpertoExterno();
		
		// Dummy
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
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
		// Verify
		assertDoesNotThrow(() -> estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1));	
	}
	
	@Test
	void testEstadoUsuarioExpertoExternoRealizaLaVerificacionYArrojaUsuarioEsDueñoDeLaMuestraException() {
		// Setup
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioEsDueñoDeLaMuestraException.class, () -> {
			estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1);
        });
	}
	
	@Test
	void testEstadoUsuarioExpertoExternoRealizaLaVerificacionYArrojaUsuarioNoEsOpinionUnicaException() {
		// Setup
		when(muestra1.opinoElUsuario(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1);
        });
	}

	@Test
	void testEstadoUsuarioExpertoExternoRealizaLaVerificacionYArrojaUsuarioEsMuestraVerificadaException() {
		// Setup
		when(muestra1.esVerificada()).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioEsMuestraVerificadaException.class, () -> {
			estadoUsuarioExpertoExterno.realizarVerificacionesPara(muestra1, opinion1);
        });
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
}
	
	