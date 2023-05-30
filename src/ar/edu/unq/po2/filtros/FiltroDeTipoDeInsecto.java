package ar.edu.unq.po2.filtros;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.ResultadoMuestra;

	/**
	 * 
	 * @author Acosta, Federico
	 *
	 * @see
	 * 
	 * Rol: <<Leaf>>
	 */

public class FiltroDeTipoDeInsecto extends Filtro {
	
	private ResultadoMuestra filtroEspecificado;
	
	public FiltroDeTipoDeInsecto(ResultadoMuestra tipoDeInsecto) {
		super();
		this.setFiltroEspecificado(tipoDeInsecto);
	}

	public ResultadoMuestra getFiltroEspecificado() {
		return filtroEspecificado;
	}

	public void setFiltroEspecificado(ResultadoMuestra filtroEspecificado) {
		this.filtroEspecificado = filtroEspecificado;
	}

	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.getResultadoActual().equals(filtroEspecificado);
	}
}
