package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.enums.IResultadoMuestra;
import ar.edu.unq.po2.enums.Vinchuca;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;

class MuestraTest {
	
	private Muestra muestra;
	private Usuario usuario;
	private Opinion opinion1;
	private Ubicacion ubicacion;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// DOC
		usuario = mock(Usuario.class);
		opinion1 = mock(Opinion.class);
		
		// SUT
		muestra = new Muestra("Vinchuca.jpg", usuario, opinion1, ubicacion);
	}

	@Test
	void testInicializacionDeUnaMuestraSubidaPorUnUsuarioBasico() {
		// Setup
		
		
		String fotoEsperada = "Vinchuca.jpg";
		IResultadoMuestra resultadoEsperado = Vinchuca.VINCHUCAINFESTANS;
		Usuario usuarioEsperado = usuario;
		int cantidadDeMuestrasEsperadas = 1;
		Ubicacion ubicacionEsperada = ubicacion;
		
		// Verify
		assertEquals(fotoEsperada, muestra.getFoto());
		assertEquals(EstadoMuestraOpinadaPorBasicos.class, muestra.getState());
		assertEquals(resultadoEsperado, muestra.getResultadoActual());
		assertEquals(usuarioEsperado, muestra.getUsuarioDueño());
		assertTrue(muestra.getOpiniones().contains(opinion1));
		assertEquals(cantidadDeMuestrasEsperadas, muestra.getOpiniones().size());
		assertEquals(ubicacionEsperada, muestra.getUbicacion());
	}
}
