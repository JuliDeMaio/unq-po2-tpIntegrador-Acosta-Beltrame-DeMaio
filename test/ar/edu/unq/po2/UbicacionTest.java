package ar.edu.unq.po2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

class UbicacionTest {
	
	private Ubicacion ubicacion1;
	private Ubicacion ubicacion2;
	private Ubicacion ubicacion3;
	private Ubicacion ubicacion4;
	
	private ZonaDeCobertura zonaDeCobertura1;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;

	@BeforeEach
	void setUp() throws Exception {
		
		// SUT
		ubicacion1 = new Ubicacion(6.4d, 10.8d);
		ubicacion2 = new Ubicacion(4.2d, -9.3d);
		ubicacion3 = new Ubicacion(5.7d, 7.1d);
		ubicacion4 = new Ubicacion(15.3d, 34.0d);
		
		// DOC
		zonaDeCobertura1 = mock(ZonaDeCobertura.class);
		
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		
	}

	@Test
	void testInicializacionDeUbicacion() {
		// Setup
		double latitudEsperada = 6.4d;
		double longitudEsperada = 10.8d;
		
		// Verify
		assertEquals(latitudEsperada, ubicacion1.getLatitud());
		assertEquals(longitudEsperada, ubicacion1.getLongitud());

	}
	
	@Test
	void testUnaUbicacionConoceLaDistanciaQueTieneRespectoAOtraUbicacion() {
		// Setup
		double distanciaEsperada = 22.3;
		
		// Verify
		assertEquals(distanciaEsperada, ubicacion1.distanciaEntre(ubicacion2));
		assertEquals(distanciaEsperada, ubicacion2.distanciaEntre(ubicacion1));

	}
	
	@Test
	void testUnaUbicacionSabeQueEstaDentroDeUnaZonaDeCobertura() {
		// Setup
		when(zonaDeCobertura1.getRadioKm()).thenReturn(20d);
		when(zonaDeCobertura1.getEpicentro()).thenReturn(ubicacion3);
		when(muestra1.getUbicacion()).thenReturn(ubicacion2);

		// Verify
		assertTrue(ubicacion1.estaDentroDeLaZonaDeCobertura(zonaDeCobertura1, muestra1));
	}
	
	@Test
	void testUnaUbicacionSabeQueNoEstaDentroDeUnaZonaDeCobertura() {
		// Setup
		when(zonaDeCobertura1.getRadioKm()).thenReturn(10d);
		when(zonaDeCobertura1.getEpicentro()).thenReturn(ubicacion3);
		when(muestra1.getUbicacion()).thenReturn(ubicacion2);

		// Verify
		assertFalse(ubicacion1.estaDentroDeLaZonaDeCobertura(zonaDeCobertura1, muestra1));
	}
	
	@Test
	void testUnaUbicacionConoceLasUbicacionesQueEstanAMenosDe25Kilometros() {
		// Setup
		int cantidadDeUbicacionesEsperadas = 2;
		List<Ubicacion> ubicacionesAFiltrar = Arrays.asList(ubicacion2, ubicacion3, ubicacion4);
		
		// Exercise
		List<Ubicacion> ubicacionesFiltradas = ubicacion1.ubicacionesAMenosDeKilometros(ubicacionesAFiltrar, 25d);
		
		// Verify
		assertEquals(cantidadDeUbicacionesEsperadas, ubicacionesFiltradas.size());
		assertTrue(ubicacionesFiltradas.contains(ubicacion2));
		assertTrue(ubicacionesFiltradas.contains(ubicacion3));
	}
	
	@Test
	void testUnaUbicacionConoceLasMuestrasQueEstanAMenosDe15Kilometros() {
		
		// Setup
		when(muestra1.getUbicacion()).thenReturn(ubicacion2);
		when(muestra2.getUbicacion()).thenReturn(ubicacion3);
		when(muestra3.getUbicacion()).thenReturn(ubicacion4);
		
		int cantidadDeMuestrasEsperadas = 1;
		Set<Muestra> muestrasAFiltrar = Set.of(muestra1, muestra2, muestra3);
		
		// Exercise
		Set<Muestra> muestrasFiltradas = ubicacion1.muestrasAMenosDeKilometros(muestrasAFiltrar, 15d);
		
		// Verify
		assertEquals(cantidadDeMuestrasEsperadas, muestrasFiltradas.size());
		assertTrue(muestrasFiltradas.contains(muestra2));
	}
}
