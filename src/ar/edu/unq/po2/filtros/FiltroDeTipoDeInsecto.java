package ar.edu.unq.po2.filtros;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.enums.ITipoDeInsecto;

	/**
	 * 
	 * @author Acosta, Federico
	 *
	 * @see
	 * 
	 * Rol: <<Leaf>>
	 */

public class FiltroDeTipoDeInsecto extends Filtro {
	
	private ITipoDeInsecto filtroEspecificado;
	
	public FiltroDeTipoDeInsecto(ITipoDeInsecto tipoDeInsecto) {
		super();
		this.setFiltroEspecificado(tipoDeInsecto);
	}

	public ITipoDeInsecto getFiltroEspecificado() {
		return filtroEspecificado;
	}

	public void setFiltroEspecificado(ITipoDeInsecto filtroEspecificado) {
		this.filtroEspecificado = filtroEspecificado;
	}

	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.getResultadoActual().equals(this.getFiltroEspecificado());
	}
}
