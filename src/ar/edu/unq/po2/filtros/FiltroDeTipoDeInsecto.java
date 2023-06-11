package ar.edu.unq.po2.filtros;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.enums.ITipoDeInsecto;

	/**	
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar el Filtro por Tipo de Insecto, siendo un Leaf del Patron Composite.
	 * @see Filtro, IBusqueda, And, Or, ITipoDeInsecto
	 * @DesignPattern Composite <<Leaf>>
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

	/**
	 * @DesignPattern Template Method - Primitive Operation
	 */
	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.getResultadoActual().equals(this.getFiltroEspecificado());
	}
}
