package ar.edu.unq.po2.filtros;

import java.time.LocalDate;

import ar.edu.unq.po2.Muestra;

	/**	
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar el Filtro por Fecha de Creacion, siendo un Leaf del Patron Composite.
	 * @see Filtro, IBusqueda, And, Or
	 * @DesignPattern Composite <<Leaf>>
	 */
public class FiltroDeFechaDeCreacion extends Filtro {

	private LocalDate filtroEspecificado;
	
	public FiltroDeFechaDeCreacion(LocalDate fecha) {
		super();
		this.setFiltroEspecificado(fecha);
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
		return muestra.getFechaDeEmision().isEqual(filtroEspecificado);
	}
}