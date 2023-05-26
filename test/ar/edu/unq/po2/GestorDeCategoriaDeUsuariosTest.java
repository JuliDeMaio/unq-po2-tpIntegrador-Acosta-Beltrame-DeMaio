package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoInterno;

class GestorDeCategoriaDeUsuariosTest {

	private GestorDeCategoriaDeUsuarios gestorDeCategoria;
	
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Usuario usuario4;
	private Usuario usuario5;
	
	@BeforeEach
	void setUp() throws Exception {
		// SUT
		gestorDeCategoria = new GestorDeCategoriaDeUsuarios();
				
		// Dummy
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		usuario3 = mock(Usuario.class);
		usuario4 = mock(Usuario.class);
		usuario5 = mock(Usuario.class);
	}

	@Test
	void testInicializacionDeUnGestorDeCategoriaDeUsuarios() {
		
		// Verify
		assertTrue(gestorDeCategoria.getUsuariosRegistrados().isEmpty());
	}
	
	@Test
	void testVerificacionCuandoUnGestorAgregaUnUsuario() {
		// Setup
		int cantidadDeUsuariosEsperada = 1;
		
		// Exercise
		gestorDeCategoria.agregarUsuario(usuario1);
		
		// Verify
		assertEquals(cantidadDeUsuariosEsperada, gestorDeCategoria.cantidadDeUsuariosRegistrados());
		assertTrue(gestorDeCategoria.getUsuariosRegistrados().contains(usuario1));
	}
	
	@Test
	void testVerificacionCuandoUnGestorEliminaUnUsuario() {
		// Setup
		gestorDeCategoria.agregarUsuario(usuario1);
		
		// Exercise
		gestorDeCategoria.eliminarUsuario(usuario1);
		
		// Verify
		assertTrue(gestorDeCategoria.getUsuariosRegistrados().isEmpty());
		assertFalse(gestorDeCategoria.getUsuariosRegistrados().contains(usuario1));
	}
	
	@Test
	void testSeVerificanCuandoUnUsuarioCumpleConLosRequisitosParaSerExperto() {
		// Setup
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(15);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(22);
		
		// Verify
		assertTrue(gestorDeCategoria.cumpleConLosRequisitosDeUsuarioExperto(usuario1));
	}
	
	@Test
	void testSeVerificaCuandoUnUsuarioNoCumpleConLosRequisitosParaSerExperto() {
		// Setup
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(3);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(4);
		
		// Verify
		assertFalse(gestorDeCategoria.cumpleConLosRequisitosDeUsuarioExperto(usuario1));
	}
	
	@Test
	void testSeActualizaLaCategoriaDeUnUsuarioBasicoQueCumpleConLosRequisitos() {
		// Setup
		when(usuario1.esUsuarioBasico()).thenReturn(true);
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(15);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(22);
		
		// Exercise
		gestorDeCategoria.actualizarCategoriaDeUsuario(usuario1);
		
		// Verify
		verify(usuario1, times(1)).setState(Mockito.any(EstadoUsuarioExpertoInterno.class));
		
	}
	
	@Test
	void testSeMantieneLaCategoriaDeUnUsuarioBasicoQueNoCumpleConLosRequisitos() {
		// Setup
		when(usuario1.esUsuarioBasico()).thenReturn(true);
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(9);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(5);
		
		gestorDeCategoria.actualizarCategoriaDeUsuario(usuario1);
		
		// Verify
		verify(usuario1, times(0)).setState(Mockito.any(EstadoUsuario.class));
	}
	
	@Test
	void testSeMantieneLaCategoriaDeUnUsuarioExpertoInternoQueCumpleConLosRequisitos() {
		// Setup
		when(usuario1.esUsuarioBasico()).thenReturn(false);
		when(usuario1.esUsuarioExpertoInterno()).thenReturn(true);
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(15);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(22);
				
		gestorDeCategoria.actualizarCategoriaDeUsuario(usuario1);
				
		// Verify
		verify(usuario1, times(0)).setState(Mockito.any(EstadoUsuario.class));
	}
	
	@Test
	void testSeActualizaLaCategoriaDeUnUsuarioExpertoInternoQueNoCumpleConLosRequisitos() {
		// Setup
		when(usuario1.esUsuarioBasico()).thenReturn(false);
		when(usuario1.esUsuarioExpertoInterno()).thenReturn(true);
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(9);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(5);
						
		gestorDeCategoria.actualizarCategoriaDeUsuario(usuario1);
						
		// Verify
		verify(usuario1, times(1)).setState(Mockito.any(EstadoUsuarioBasico.class));	
	}
	
	@Test
	void testSeMantieneLaCategoriaDeUnUsuarioExpertoExternoSinImportarLosRequisitos() {
		// Setup
		when(usuario1.esUsuarioBasico()).thenReturn(false);
		when(usuario1.esUsuarioExpertoInterno()).thenReturn(false);
		
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(9);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(5);
								
		gestorDeCategoria.actualizarCategoriaDeUsuario(usuario1);
								
		// Verify
		verify(usuario1, times(0)).setState(Mockito.any(EstadoUsuario.class));	
	}
	
	@Test
	void testVerificacionDeActualizacionDeCategoriaDeTodosLosUsuarios() {
	    // Setup
		gestorDeCategoria.agregarUsuario(usuario1);
		when(usuario1.esUsuarioBasico()).thenReturn(true);
		when(usuario1.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(15);
		when(usuario1.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(22);
		
		gestorDeCategoria.agregarUsuario(usuario2);
		when(usuario2.esUsuarioBasico()).thenReturn(true);
		when(usuario2.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(9);
		when(usuario2.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(5);
		
		gestorDeCategoria.agregarUsuario(usuario3);
		when(usuario3.esUsuarioBasico()).thenReturn(false);
		when(usuario3.esUsuarioExpertoInterno()).thenReturn(true);
		when(usuario3.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(15);
		when(usuario3.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(22);		
		
		gestorDeCategoria.agregarUsuario(usuario4);
		when(usuario4.esUsuarioBasico()).thenReturn(false);
		when(usuario4.esUsuarioExpertoInterno()).thenReturn(true);
		when(usuario4.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(9);
		when(usuario4.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(5);
		
		gestorDeCategoria.agregarUsuario(usuario5);
		when(usuario5.esUsuarioBasico()).thenReturn(false);
		when(usuario5.esUsuarioExpertoInterno()).thenReturn(false);
		when(usuario5.cantidadDeMuestrasEmitidasEnUltimos30Dias()).thenReturn(9);
		when(usuario5.cantidadDeOpinionesEmitidasEnUltimos30Dias()).thenReturn(5);
								
		// Exercise
		gestorDeCategoria.actualizarCategoriasDeUsuarios();
		
		// Verify
		verify(usuario1, times(1)).setState(Mockito.any(EstadoUsuarioExpertoInterno.class));	
		verify(usuario2, times(0)).setState(Mockito.any(EstadoUsuario.class));
		verify(usuario3, times(0)).setState(Mockito.any(EstadoUsuario.class));
		verify(usuario4, times(1)).setState(Mockito.any(EstadoUsuarioBasico.class));
		verify(usuario5, times(0)).setState(Mockito.any(EstadoUsuario.class));
	}
}
