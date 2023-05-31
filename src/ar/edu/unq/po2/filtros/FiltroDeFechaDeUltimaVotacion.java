package ar.edu.unq.po2.filtros;

import java.time.LocalDate;

import ar.edu.unq.po2.Muestra;

public class FiltroDeFechaDeUltimaVotacion extends Filtro {

	private LocalDate filtroEspecificado;
	
	public FiltroDeFechaDeUltimaVotacion(LocalDate fechaEspecificada) {
		super();
		this.setFiltroEspecificado(fechaEspecificada);
	}
	
	public LocalDate getFiltroEspecificado() {
		return filtroEspecificado;
	}

	public void setFiltroEspecificado(LocalDate filtroEspecificado) {
		this.filtroEspecificado = filtroEspecificado;
	}
	
	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.obtenerFechaDeUltimaVotacion().isEqual(filtroEspecificado);
	}
	
}
