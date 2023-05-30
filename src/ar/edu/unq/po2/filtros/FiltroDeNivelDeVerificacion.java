package ar.edu.unq.po2.filtros;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestra;

	/**
	 * @author Acosta, Federico
	 * 
	 * @see
	 * 
	 * Rol: <<Leaf>>
	 */

public class FiltroDeNivelDeVerificacion extends Filtro {

	private EstadoMuestra filtroEspecificado;
	
	public FiltroDeNivelDeVerificacion(EstadoMuestra nivelDeVerificacion) {
		super();
		this.setFiltroEspecificado(nivelDeVerificacion);
	}

	public EstadoMuestra getFiltroEspecificado() {
		return filtroEspecificado;
	}

	public void setFiltroEspecificado(EstadoMuestra filtroEspecificado) {
		this.filtroEspecificado = filtroEspecificado;
	}

	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.getState().equals(filtroEspecificado);
	}
}