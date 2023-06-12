package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.enums.ITipoDeOpinion;
import ar.edu.unq.po2.enums.TipoDeOpinion;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExperto;

class OpinionTest {
	
	private Opinion opinion1;
	private Opinion opinion2;
	
	private Usuario usuario1;
	private Usuario usuario2;
	
	private LocalDate fecha1;
	
	private EstadoUsuarioBasico estadoDeUsuarioBasico;
	private EstadoUsuarioExperto estadoDeUsuarioExperto;

	@BeforeEach
	void setUp() throws Exception {
				
		// DOC
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		fecha1 = LocalDate.now();
		estadoDeUsuarioBasico = mock(EstadoUsuarioBasico.class);
		estadoDeUsuarioExperto = mock(EstadoUsuarioExperto.class);
		
		when(usuario1.getState()).thenReturn(estadoDeUsuarioBasico);
		when(usuario2.getState()).thenReturn(estadoDeUsuarioExperto);
		
		// SUT
		opinion1 = new Opinion(Chinche.CHINCHEFOLIADA, usuario1);
		opinion2 = new Opinion(TipoDeOpinion.IMAGENPOCOCLARA, usuario2);
	}

	@Test
	void testInicializacionDeUnaOpinion() {
		
		// Setup
		ITipoDeOpinion tipoDeOpinionEsperada = Chinche.CHINCHEFOLIADA;
		Usuario usuarioEsperado = usuario1;
		LocalDate fechaEsperada = fecha1;
		EstadoUsuario estadoDeUsuarioEsperado = estadoDeUsuarioBasico;
		
		
		// Verify
		assertEquals(tipoDeOpinionEsperada, opinion1.getTipoDeOpinion());
		assertEquals(usuarioEsperado, opinion1.getUsuarioDueño());
		assertEquals(fechaEsperada, opinion1.getFechaDeEmision());
		assertEquals(estadoDeUsuarioEsperado, opinion1.getCategoriaDeEmision());
	}
	
	@Test
	void testUnaOpinionSabeQueSeEmitioEnLosUltimos30Dias() {
		
		// Verify
		assertTrue(opinion2.seEmitioEnLosUltimos30Dias());
	}
	
	@Test
	void testUnaOpinionSabeSiFueEmitidaPorUnUsuarioExperto() {
		// Setup
		when(estadoDeUsuarioBasico.esEstadoExperto()).thenReturn(false);
		// Verify
		assertFalse(opinion1.fueEmitidaPorUnExperto());
	}
	
	@Test
	void testUnaOpinionSabeSiFueEmitidaPorUnUsuarioExperto2() {
		// Setup
		when(estadoDeUsuarioExperto.esEstadoExperto()).thenReturn(true);
		// Verify
		assertTrue(opinion2.fueEmitidaPorUnExperto());
	}
	
	@Test
	void testUnaOpinionSabeSiFueEmitidaPorUnUsuarioEspecifico() {
		// Verify
		assertTrue(opinion1.fueEmitidaPorUsuario(usuario1));
	}
	
	@Test
	void testUnaOpinionSabeCualEraLaCategoriaDelUsuarioDueñoAlMomentoDeEmitirlaSinImportarSiLaCambiaLuego() {
		// Setup
		EstadoUsuario estadoDeUsuarioEsperado = estadoDeUsuarioBasico;
		when(usuario1.getState()).thenReturn(estadoDeUsuarioExperto);
		
		// Verify
		assertEquals(estadoDeUsuarioEsperado, opinion1.getCategoriaDeEmision());
	}
	
}
