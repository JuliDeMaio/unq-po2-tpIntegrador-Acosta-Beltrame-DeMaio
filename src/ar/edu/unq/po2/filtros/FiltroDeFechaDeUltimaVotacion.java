package ar.edu.unq.po2.filtros;

import java.time.LocalDate;

import ar.edu.unq.po2.Muestra;

	/**	
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar el Filtro por Fecha de Ultima Votacion, siendo un Leaf del Patron Composite.
	 * @see Filtro, IBusqueda, And, Or
	 * @DesignPattern Composite <<Leaf>>
	 */
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
	
	/**
	 * @DesignPattern Template Method - Primitive Operation
	 */
	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.obtenerFechaDeUltimaVotacion().isEqual(filtroEspecificado);
	}
	
}
