package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.filtros.FiltroDeTipoDeInsecto;

class AppWebTest {
	
	private AppWeb appWeb;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	
	private FiltroDeTipoDeInsecto filtroPorInsecto1;
	
	private ZonaDeCobertura zonaDeCobertura1;

	@BeforeEach
	void setUp() throws Exception {
		
		// SUT
		appWeb = new AppWeb();
		
		// DOC
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		
		filtroPorInsecto1 = mock(FiltroDeTipoDeInsecto.class);
		
		zonaDeCobertura1 = mock(ZonaDeCobertura.class);
	}
	
	@Test
	void testInicializacionDeUnaAppWeb() {
		
		// Verify
		assertTrue(appWeb.getMuestras().isEmpty());
	}

	@Test
	void testSeSubeUnaMuestraALaAppWebYQuedaRegistrada() {
		// Setup
		TipoDeVinchucaAlSubirMuestra resultadoDeMuestraEsperado = TipoDeVinchucaAlSubirMuestra.VINCHUCASORDIDA;
		when(muestra1.getResultadoActual()).thenReturn(resultadoDeMuestraEsperado);
		
		// Exercise
		appWeb.guardarMuestra(muestra1);
		
		// Verify
		assertEquals(appWeb.getMuestras().size(), 1);
		assertTrue(appWeb.getMuestras().contains(muestra1));
		assertEquals(resultadoDeMuestraEsperado, muestra1.getResultadoActual());
	}
	
	@Test
	void testSeEliminaUnaMuestraDeLaAppWebYQuedaRegistrada() {
		// Setup
		appWeb.guardarMuestra(muestra1);
		
		// Exercise
		appWeb.eliminarMuestra(muestra1);
		
		// Verify
		assertEquals(appWeb.getMuestras().size(), 0);
		assertFalse(appWeb.getMuestras().contains(muestra1));
	}
	
	@Test
	void testLaAppWebFiltraPorTipoDeInsectoUnaListaDeMuestrasYSeDevuelveUnaListaDeMuestrasFiltrada() {
		// Setup
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2);
		Set<Muestra> listaDeMuestrasEsperada = Set.of(muestra1);
		int cantidadDeMuestrasEsperada = 1;
		when(filtroPorInsecto1.filtrarMuestras(muestrasAFiltrar)).thenReturn(listaDeMuestrasEsperada);
		
		// Exercise
		Set<Muestra> listaDeMuestrasResultante = appWeb.filtrarMuestras(muestrasAFiltrar, filtroPorInsecto1); 
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, listaDeMuestrasResultante.size());
		assertTrue(listaDeMuestrasResultante.contains(muestra1));
	}
	
	@Test
	void testLaAppWebConoceLasMuestrasDeUnaZonaDeCoberturaYSeDevuelveUnaListaDeMuestrasDeDichaZona() {
		
		// Setup
		int cantidadDeMuestrasEsperada = 2;
		Set<Muestra> muestrasAVerificar = Set.of(muestra1, muestra2, muestra3);
		
		when(muestra1.seTomoDentroDeZonaDeCobertura(zonaDeCobertura1)).thenReturn(true);
		when(muestra2.seTomoDentroDeZonaDeCobertura(zonaDeCobertura1)).thenReturn(true);
		when(muestra3.seTomoDentroDeZonaDeCobertura(zonaDeCobertura1)).thenReturn(false);
		
		// Exercise
		Set<Muestra> listaDeMuestrasResultante = appWeb.muestrasDeZonaDeCobertura(muestrasAVerificar, zonaDeCobertura1);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperada, listaDeMuestrasResultante.size());
		assertTrue(listaDeMuestrasResultante.contains(muestra1));
		assertTrue(listaDeMuestrasResultante.contains(muestra2));	
	}
}
