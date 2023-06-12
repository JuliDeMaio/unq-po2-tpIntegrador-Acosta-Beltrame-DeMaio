package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.enums.Chinche;
import ar.edu.unq.po2.enums.IResultadoMuestra;
import ar.edu.unq.po2.enums.ResultadoMuestra;
import ar.edu.unq.po2.enums.TipoDeOpinion;
import ar.edu.unq.po2.enums.Vinchuca;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

class MuestraTest {
	
	private Muestra muestra;
	
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Usuario usuario4;
	private Usuario usuario5;
	
	private Opinion opinion1;
	private Opinion opinion2;
	private Opinion opinion3;
	private Opinion opinion4;
	private Opinion opinion5;
	
	private Ubicacion ubicacion;
	
	private ZonaDeCobertura zonaDeCobertura;
	
	private EstadoMuestraOpinadaPorBasicos estadoMuestraOpinadaPorBasicos;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// DOC
		ubicacion = mock(Ubicacion.class);
		
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		usuario3 = mock(Usuario.class);
		usuario4 = mock(Usuario.class);
		usuario5 = mock(Usuario.class);
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		opinion5 = mock(Opinion.class);
		
		estadoMuestraOpinadaPorBasicos = mock(EstadoMuestraOpinadaPorBasicos.class);
		
		// Setup
		when(opinion1.getUsuarioDueño()).thenReturn(usuario1);
		when(opinion1.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAINFESTANS);
		
		when(opinion2.getUsuarioDueño()).thenReturn(usuario2);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGENPOCOCLARA);
		
		when(opinion3.getUsuarioDueño()).thenReturn(usuario3);
		when(opinion3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.NINGUNA);
		
		when(opinion4.getUsuarioDueño()).thenReturn(usuario4);
		when(opinion4.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAINFESTANS);
		
		when(opinion5.getUsuarioDueño()).thenReturn(usuario5);
		when(opinion5.getTipoDeOpinion()).thenReturn(TipoDeOpinion.NINGUNA);
				
		// SUT
		muestra = new Muestra("Vinchuca.jpg", Vinchuca.VINCHUCAGUASAYANA, usuario1, opinion1, ubicacion);
	}

	@Test
	void testInicializacionDeUnaMuestraSubidaPorUnUsuario() throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		// Setup
		String fotoEsperada = "Vinchuca.jpg";
		Vinchuca tipoDeVinchucaEsperado = Vinchuca.VINCHUCAGUASAYANA;
		IResultadoMuestra resultadoEsperado = Vinchuca.VINCHUCAINFESTANS;
		Usuario usuarioEsperado = usuario1;
		int cantidadDeMuestrasEsperadas = 1;
		Ubicacion ubicacionEsperada = ubicacion;
		LocalDate fechaEmisionEsperada = LocalDate.now();
		
		// Verify
		assertEquals(fotoEsperada, muestra.getFoto());
		assertEquals(tipoDeVinchucaEsperado, muestra.getTipoDeVinchucaFotografiada());
		assertEquals(EstadoMuestraOpinadaPorBasicos.class, muestra.getState().getClass());
		assertEquals(resultadoEsperado, muestra.getResultadoActual());
		assertEquals(usuarioEsperado, muestra.getUsuarioDueño());
		assertTrue(muestra.getOpiniones().contains(opinion1));
		assertEquals(cantidadDeMuestrasEsperadas, muestra.getOpiniones().size());
		assertEquals(ubicacionEsperada, muestra.getUbicacion());
		assertEquals(fechaEmisionEsperada, muestra.getFechaDeEmision());
		verify(usuario1, times(1)).gestionarEstadoMuestraPara(any(EstadoMuestraOpinadaPorBasicos.class), any(Muestra.class));
	}
	
	@Test
	void testUnaMuestraGuardaUnaOpinion() {
		// Setup
		int cantidadDeMuestrasEsperadas = 1;
		
		// Verify
		assertTrue(muestra.getOpiniones().contains(opinion1));
		assertEquals(cantidadDeMuestrasEsperadas, muestra.getOpiniones().size());
	}
	
	@Test
	void testUnaMuestraEliminaUnaOpinion() {
		// Setup
		int cantidadDeMuestrasEsperadas = 0;
		
		// Exercise
		muestra.eliminarOpinion(opinion1);
		
		// Verify
		assertFalse(muestra.getOpiniones().contains(opinion1));
		assertEquals(cantidadDeMuestrasEsperadas, muestra.getOpiniones().size());
	}
	
	@Test
	void testUnaMuestraSabeCuandoLaVotoUnExperto() throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		// Setup
		when(usuario1.esUsuarioExperto()).thenReturn(true);
		
		// Exercise-Verify
		assertTrue(muestra.opinoAlMenosUnExperto());
	}
	
	@Test
	void testUnaMuestraSabeCuandoNoLaVotoUnExperto() throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		// Setup
		when(usuario1.esUsuarioExperto()).thenReturn(false);
			
		// Exercise-Verify
		assertFalse(muestra.opinoAlMenosUnExperto());
	} 
	
	@Test
	void testUnaMuestraSabeCuandoOpinoUnUsuario() {
		
		// Verify
		assertTrue(muestra.opinoElUsuario(usuario1));
	}
	
	@Test
	void testUnaMuestraSabeCuandoNoOpinoUnUsuario() {
		// Verify
		assertFalse(muestra.opinoElUsuario(usuario2));
	}
	
	@Test
	void testUnaMuestraSabeCuandoUnUsuarioEsDueñoDeElla() {	
		// Verify
		assertTrue(muestra.esDueñoDeLaMuestra(usuario1));
	}
	
	@Test
	void testUnaMuestraSabeCuandoUnUsuarioNoEsDueñoDeElla() {
		// Verify
		assertFalse(muestra.esDueñoDeLaMuestra(usuario2));
	}
	
	@Test
	void testUnaMuestraSabeCuandoSeEmitioEnLosUltimos30Dias() {
		// Verify
		assertTrue(muestra.seEmitioEnLosUltimos30Dias());
	}
	
	@Test
	void testUnaMuestraDelegaLaResponsabilidadDeSaberSiSeEmitioEnUnaZonaDeCobertura() {
		// Exercise
		muestra.seTomoDentroDeZonaDeCobertura(zonaDeCobertura);
		
		// Verify
		verify(ubicacion, only()).estaDentroDeLaZonaDeCobertura(zonaDeCobertura, muestra);
	}
	
	@Test
	void testUnaMuestraConoceLaFechaDeLaUltimaVotacion() {
		// Setup
		LocalDate fechaEsperada = LocalDate.of(2022, Month.JANUARY, 5);
		when(opinion1.getFechaDeEmision()).thenReturn(LocalDate.of(2002, Month.AUGUST, 9));
		when(opinion2.getFechaDeEmision()).thenReturn(LocalDate.of(2022, Month.JANUARY, 5));
		when(opinion3.getFechaDeEmision()).thenReturn(LocalDate.of(2002, Month.MAY, 3));
		
		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertEquals(fechaEsperada, muestra.obtenerFechaDeUltimaVotacion());
	}
	
	@Test
	void testUnaMuestraConoceLaFechaDeLaUltimaVotacionCuandoNoHay() {
		
		muestra.eliminarOpinion(opinion1);
		
		// Verify
		assertEquals(null, muestra.obtenerFechaDeUltimaVotacion());
	}
	
	@Test
	void testUnaMuestraConoceElTipoDeOpinionMayoritariaEntreSusOpinionesCuandoHay1() {
		// Setup
		IResultadoMuestra tipoDeOpinionEsperado = Vinchuca.VINCHUCAINFESTANS;
		
		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		muestra.guardarOpinion(opinion4);
		
		// Verify
		assertEquals(tipoDeOpinionEsperado, muestra.obtenerTipoDeOpinionMayoritaria());
	}
	
	@Test
	void testUnaMuestraConoceElTipoDeOpinionMayoritariaEntreSusOpinionesCuandoHay2() {
		// Setup
		IResultadoMuestra tipoDeOpinionEsperado = TipoDeOpinion.NINGUNA;

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		muestra.guardarOpinion(opinion5);
		
		// Verify
		assertEquals(tipoDeOpinionEsperado, muestra.obtenerTipoDeOpinionMayoritaria());
	}
	
	@Test
	void testUnaMuestraConoceElTipoDeOpinionMayoritariaEntreSusOpinionesCuandoHayEmpate() {
		// Setup
		IResultadoMuestra tipoDeOpinionEsperado = ResultadoMuestra.NODEFINIDA;
		
		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertEquals(tipoDeOpinionEsperado, muestra.obtenerTipoDeOpinionMayoritaria());
	}
	
	@Test
	void testUnaMuestraConoceLasOpinionesQueFueronEmitidasPorExpertos() {
		// Setup
		int cantidadDeOpinionesEsperadas = 2;
	
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(true);

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Exercise
		List<Opinion> opinionesEmitidasPorExpertos = muestra.obtenerOpinionesDeExpertos();
		
		//Verify
		assertEquals(cantidadDeOpinionesEsperadas, opinionesEmitidasPorExpertos.size());
		assertTrue(opinionesEmitidasPorExpertos.contains(opinion2));
		assertTrue(opinionesEmitidasPorExpertos.contains(opinion3));
		assertFalse(opinionesEmitidasPorExpertos.contains(opinion1));
	}
	
	@Test
	void testUnaMuestraConoceElTipoDeOpinionMayoritariaEntreSusOpinionesDeExpertosCuandoHay() {
		// Setup
		IResultadoMuestra tipoDeOpinionEsperado = Vinchuca.VINCHUCAGUASAYANA;
		
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(false);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertEquals(tipoDeOpinionEsperado, muestra.obtenerTipoDeOpinionMayoritariaDeExpertos());
	}
	
	@Test
	void testUnaMuestraConoceElTipoDeOpinionMayoritariaEntreSusOpinionesDeExpertosCuandoHayEmpate() {
		// Setup
		IResultadoMuestra tipoDeOpinionEsperado = ResultadoMuestra.NODEFINIDA;
		
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Chinche.CHINCHEFOLIADA);

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertEquals(tipoDeOpinionEsperado, muestra.obtenerTipoDeOpinionMayoritariaDeExpertos());
	}
	
	@Test
	void testUnaMuestraConoceSiExisteUnDeterminadoTipoDeOpinionEntreLasOpinionesDeExpertos() {
		// Setup		
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Chinche.CHINCHEFOLIADA);
		
		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertTrue(muestra.existeOpinionEnExpertos(Chinche.CHINCHEFOLIADA));
	}
	
	@Test
	void testUnaMuestraConoceQueNoExisteUnDeterminadoTipoDeOpinionEntreLasOpinionesDeExpertos() {
		// Setup		
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Chinche.CHINCHEFOLIADA);
		
		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertFalse(muestra.existeOpinionEnExpertos(Chinche.PHTIACHINCHE));
	}
	
	@Test
	void testUnaMuestraConoceSiHay2OpinionesDeExpertosQueCoinciden() {
		// Setup
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertTrue(muestra.hay2OpinionesDeExpertosQueCoinciden());
	}
	
	@Test
	void testUnaMuestraConoceQueNoHay2OpinionesDeExpertosQueCoinciden() {
		// Setup
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(false);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(false);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertFalse(muestra.hay2OpinionesDeExpertosQueCoinciden());
	}
	
	@Test
	void testUnaMuestraConoceQueNoHay2OpinionesDeExpertosQueCoinciden2() {
		// Setup
		
		when(opinion2.fueEmitidaPorUnExperto()).thenReturn(true);
		when(opinion3.fueEmitidaPorUnExperto()).thenReturn(false);
		when(opinion2.getTipoDeOpinion()).thenReturn(Vinchuca.VINCHUCAGUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(Chinche.CHINCHEFOLIADA);

		muestra.guardarOpinion(opinion2);
		muestra.guardarOpinion(opinion3);
		
		// Verify
		assertFalse(muestra.hay2OpinionesDeExpertosQueCoinciden());
	}
	
	@Test
	void testUnaMuestraDelegaASuEstadoLaVerificacionDeSuResultadoActual() {
		// Setup
		muestra.setState(estadoMuestraOpinadaPorBasicos);
		
		// Exercise
		muestra.solicitarVerificacionDeResultadoActual();
		
		// Verify
		verify(estadoMuestraOpinadaPorBasicos, only()).verificarResultadoActualDeMuestra(muestra);
	}
	
	@Test
	void testUnaMuestraActualizaSuEstadoActual() {
		// Setup
		Chinche resultadoMuestraEsperado = Chinche.CHINCHEFOLIADA;
		
		// Exercise
		muestra.actualizarResultadoActual(Chinche.CHINCHEFOLIADA);
		
		// Verify
		assertEquals(resultadoMuestraEsperado, muestra.getResultadoActual());
	}
	
	@Test
	void testUnaMuestraDelegaASuEstadoLaObtencionDeSuNivelDeVerificacion() {
		// Setup
		muestra.setState(estadoMuestraOpinadaPorBasicos);
		
		// Exercise
		muestra.obtenerNivelDeVerificacion();
		
		// Verify
		verify(estadoMuestraOpinadaPorBasicos, only()).obtenerNivelDeVerificacion();
	}
	
	@Test
	void testUnaMuestraConoceLasOpinionesDeUnUsuario() {
		// Setup
		when(opinion1.fueEmitidaPorUsuario(usuario1)).thenReturn(true);
		when(opinion2.fueEmitidaPorUsuario(usuario1)).thenReturn(true);
		when(opinion3.fueEmitidaPorUsuario(usuario1)).thenReturn(false);
		
		muestra.guardarOpinion(opinion2);
		
		int cantidadDeOpinionesEsperadas = 2;
		
		// Exercise
		List<Opinion> opinionesObtenidas = muestra.obtenerOpinionesDeUsuario(usuario1);
		
		// Verify
		assertEquals(cantidadDeOpinionesEsperadas, opinionesObtenidas.size());
		assertTrue(opinionesObtenidas.contains(opinion1));
		assertTrue(opinionesObtenidas.contains(opinion2));
	}
}







