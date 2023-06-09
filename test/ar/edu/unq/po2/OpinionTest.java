package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.enums.ITipoDeOpinion;
import ar.edu.unq.po2.enums.TipoDeOpinion;

class OpinionTest {
	
	private Opinion opinion1;
	private Opinion opinion2;
	
	private Usuario usuario1;
	
	private LocalDate fecha1;
	private LocalDate fecha2;

	@BeforeEach
	void setUp() throws Exception {
				
		// DOC
		usuario1 = mock(Usuario.class);
		fecha1 = LocalDate.of(2015, Month.DECEMBER, 22);
		fecha2 = LocalDate.now();
		
		// SUT
		opinion1 = new Opinion(Chinche.CHINCHEFOLIADA, usuario1, fecha1);
		opinion2 = new Opinion(TipoDeOpinion.IMAGENPOCOCLARA, usuario1, fecha2);
	}

	@Test
	void testInicializacionDeUnaOpinion() {
		
		// Setup
		ITipoDeOpinion tipoDeOpinionEsperada = Chinche.CHINCHEFOLIADA;
		Usuario usuarioEsperado = usuario1;
		LocalDate fechaEsperada = fecha1;
		
		// Verify
		assertEquals(tipoDeOpinionEsperada, opinion1.getTipoDeOpinion());
		assertEquals(usuarioEsperado, opinion1.getUsuarioDueño());
		assertEquals(fechaEsperada, opinion1.getFechaDeEmision());
	}
	
	@Test
	void testUnaOpinionSabeQueSeEmitioEnLosUltimos30Dias() {
		
		// Verify
		assertTrue(opinion2.seEmitioEnLosUltimos30Dias());
	}

	@Test
	void testUnaOpinionSabeQueNoSeEmitioEnLosUltimos30Dias() {
		
		// Verify
		assertFalse(opinion1.seEmitioEnLosUltimos30Dias());
	}
}
