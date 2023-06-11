package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.enums.Vinchuca;
import ar.edu.unq.po2.filtros.FiltroDeTipoDeInsecto;
import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

class AppWebTest {
	
	private AppWeb appWeb;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	
	private FiltroDeTipoDeInsecto filtroPorInsecto1;
	
	private ZonaDeCobertura zonaDeCobertura1;

	@BeforeEach
	void setUp() throws Exception {
		
		// SUT
		appWeb = AppWeb.getInstance();
		
		// DOC
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		
		filtroPorInsecto1 = mock(FiltroDeTipoDeInsecto.class);
		
		zonaDeCobertura1 = mock(ZonaDeCobertura.class);
		
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		usuario3 = mock(Usuario.class);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		appWeb.reiniciarSistema();
	}
	
	@Test
	void testInicializacionDeUnaAppWeb() {
		
		// Verify
		assertTrue(appWeb.getMuestras().isEmpty());
		assertTrue(appWeb.getUsuariosRegistrados().isEmpty());
	}

	@Test
	void testSeSubeUnaMuestraALaAppWebYQuedaRegistrada() {
		
		// Setup
		when(muestra1.getResultadoActual()).thenReturn(Vinchuca.VINCHUCASORDIDA);
		
		// Exercise
		appWeb.guardarMuestra(muestra1);
		
		// Verify
		assertEquals(appWeb.getMuestras().size(), 1);
		assertTrue(appWeb.getMuestras().contains(muestra1));
		assertEquals(Vinchuca.VINCHUCASORDIDA, muestra1.getResultadoActual());
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
	
	@Test
	void testVerificacionCuandoUnaAppWebAgregaUnUsuario() {
		
		// Setup
		int cantidadDeUsuariosEsperada = 1;
		
		// Exercise
		appWeb.agregarUsuario(usuario1);
		
		// Verify
		assertEquals(cantidadDeUsuariosEsperada, appWeb.cantidadDeUsuariosRegistrados());
		assertTrue(appWeb.getUsuariosRegistrados().contains(usuario1));
	}
	
	@Test
	void testVerificacionCuandoUnaAppWebEliminaUnUsuario() {
		
		// Setup
		appWeb.agregarUsuario(usuario1);
		
		// Exercise
		appWeb.eliminarUsuario(usuario1);
		
		// Verify
		assertTrue(appWeb.getUsuariosRegistrados().isEmpty());
		assertFalse(appWeb.getUsuariosRegistrados().contains(usuario1));
	}

	@Test
	void testUnaAppWebActualizaLaCategoriaDeUnUsuarioYElUsuarioRecibeLaDelegacion() {
		
		// Setup
		appWeb.agregarUsuario(usuario1);
		
		// Exercise
		appWeb.actualizarCategoriaDeUsuario(usuario1);
		
		// Verify
		verify(usuario1, times(1)).actualizarCategoria();
	}
	
	@Test
	void testUnaAppWebActualizaLasCategoriasDeVariosUsuarioYLosUsuariosRecibenLaDelegacion() {
		
		// Setup
		appWeb.agregarUsuario(usuario1);
		appWeb.agregarUsuario(usuario2);
		appWeb.agregarUsuario(usuario3);
		
		// Exercise
		appWeb.actualizarCategoriasDeUsuarios();
		
		// Verify
		verify(usuario1, times(1)).actualizarCategoria();
		verify(usuario2, times(1)).actualizarCategoria();
		verify(usuario3, times(1)).actualizarCategoria();
	}
	
	@Test
	void testUnaAppWebConoceLasMuestrasDeUnUsuarioDado() {
		
		// Setup
		appWeb.guardarMuestra(muestra1);
		appWeb.guardarMuestra(muestra2);
		appWeb.guardarMuestra(muestra3);
		int cantidadDeMuestrasEsperadas = 3;
		when(muestra1.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(muestra2.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		when(muestra3.esDueñoDeLaMuestra(usuario1)).thenReturn(true);
		
		// Exercise
		Set<Muestra> muestrasObtenidas = appWeb.muestrasDeUsuario(usuario1);
		
		// Verify
		assertTrue(muestrasObtenidas.contains(muestra1));
		assertTrue(muestrasObtenidas.contains(muestra2));
		assertTrue(muestrasObtenidas.contains(muestra3));
		assertEquals(cantidadDeMuestrasEsperadas, muestrasObtenidas.size());
	}
	
}
