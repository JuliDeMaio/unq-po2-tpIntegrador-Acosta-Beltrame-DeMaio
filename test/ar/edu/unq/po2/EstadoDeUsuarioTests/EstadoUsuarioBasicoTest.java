package ar.edu.unq.po2.EstadoDeUsuarioTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioYaVotoUnExpertoException;

class EstadoUsuarioBasicoTest {

	private EstadoUsuarioBasico estadoUsuarioBasico;
	private Muestra muestra1;
	private Opinion opinion1;
	private Usuario usuario1;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		estadoUsuarioBasico = new EstadoUsuarioBasico();
		
		// Dummy
		muestra1 = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		usuario1 = mock(Usuario.class);
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
		// Verify
		assertDoesNotThrow(() -> estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1));	
	}
	
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioEsDueñoDeLaMuestraException() {
		// Setup
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioEsDueñoDeLaMuestraException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
	}
	
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioNoEsOpinionUnicaException() {
		// Setup
		when(muestra1.opinoElUsuario(usuario1)).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioNoEsOpinionUnicaException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
	}
	
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioYaVotoUnExpertoException() {
		// Setup
		when(muestra1.opinoAlMenosUnExperto()).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioYaVotoUnExpertoException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
	}
	
	// LA DEBERIA CONOCER USUARIO BASICO????
	@Test
	void testEstadoUsuarioBasicoRealizaLaVerificacionYArrojaUsuarioEsMuestraVerificadaException() {
		// Setup
		when(muestra1.esVerificada()).thenReturn(true);
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		
		// Verify
		assertThrows(UsuarioEsMuestraVerificadaException.class, () -> {
			estadoUsuarioBasico.realizarVerificacionesPara(muestra1, opinion1);
        });
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
	
}
