package ar.edu.unq.po2.filtros;

import java.time.LocalDate;

import ar.edu.unq.po2.Muestra;

/**	
	 * 
	 * @author Acosta, Federico
	 * 
	 * @see
	 * 
	 * Rol: <<Leaf>>
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

	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.getFechaDeEmision().isEqual(filtroEspecificado);
	}
}