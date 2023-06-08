package ar.edu.unq.po2.filtros;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;

	/**
	 * @author Acosta, Federico
	 * 
	 * @see
	 * 
	 * Rol: <<Leaf>>
	 */

public class FiltroDeNivelDeVerificacion extends Filtro {

	private IEstadoMuestra filtroEspecificado;
	
	public FiltroDeNivelDeVerificacion(IEstadoMuestra nivelDeVerificacion) {
		super();
		this.setFiltroEspecificado(nivelDeVerificacion);
	}

	public IEstadoMuestra getFiltroEspecificado() {
		return filtroEspecificado;
	}

	public void setFiltroEspecificado(IEstadoMuestra filtroEspecificado) {
		this.filtroEspecificado = filtroEspecificado;
	}

	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.getState().equals(filtroEspecificado);
	}
}