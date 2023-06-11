package ar.edu.unq.po2.filtros;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.enums.NivelDeVerificacion;

	/**	
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar el Filtro por Nivel de Verificacion, siendo un Leaf del Patron Composite.
	 * @see Filtro, IBusqueda, And, Or, NivelDeVerificacion
	 * @DesignPattern Composite <<Leaf>>
	 */
public class FiltroDeNivelDeVerificacion extends Filtro {

	private NivelDeVerificacion filtroEspecificado;
	
	public FiltroDeNivelDeVerificacion(NivelDeVerificacion nivelDeVerificacion) {
		super();
		this.setFiltroEspecificado(nivelDeVerificacion);
	}

	public NivelDeVerificacion getFiltroEspecificado() {
		return filtroEspecificado;
	}

	public void setFiltroEspecificado(NivelDeVerificacion filtroEspecificado) {
		this.filtroEspecificado = filtroEspecificado;
	}

	/**
	 * @DesignPattern Template Method - Primitive Operation
	 */
	@Override
	public boolean pasaElFiltro(Muestra muestra) {
		return muestra.obtenerNivelDeVerificacion().equals(this.getFiltroEspecificado());
	}
}