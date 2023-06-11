package ar.edu.unq.po2.zonaCoberturaObserver;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Ubicacion;
import ar.edu.unq.po2.enums.TipoDeOrganizacion;

/**
	 * @author Beltrame, Franco
	 * @note Esta clase tiene como objetivo modelar la Organizacion, como parte del Patron Observer.
	 * @see TipoDeOrganizacion, IZonaDeCoberturaListener
	 * @DesignPattern Observer <<ConcreteListener>>
	 */

public class Organizacion implements IZonaDeCoberturaListener{
	
	private TipoDeOrganizacion tipoDeOrganizacion;
	private int cantidadDeTrabajadores;
	private Ubicacion ubicacion;
	private IFuncionalidadExterna funcionalidadExternaSubida;
	private IFuncionalidadExterna funcionalidadExternaValidada;
	private ZonaDeCobertura miZonaDeCobertura;
	
	public Organizacion(TipoDeOrganizacion tipoDeOrganizacion, int cantidadDeTrabajadores, Ubicacion ubicacion, 
						IFuncionalidadExterna funcionalidadSubida, IFuncionalidadExterna funcionalidadValidada,
						ZonaDeCobertura zonaDeCobertura) {
		super();
		this.setTipoDeOrganizacion(tipoDeOrganizacion);
		this.setCantidadDeTrabajadores(cantidadDeTrabajadores);
		this.setUbicacion(ubicacion);
		this.setFuncionalidadExternaSubida(funcionalidadSubida);
		this.setFuncionalidadExternaValidada(funcionalidadValidada);
		this.setZonaDeCobertura(zonaDeCobertura);
	}

	public TipoDeOrganizacion getTipoDeOrganizacion() {
		return tipoDeOrganizacion;
	}

	public int getCantidadDeTrabajadores() {
		return cantidadDeTrabajadores;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public IFuncionalidadExterna getFuncionalidadExternaSubida() {
		return funcionalidadExternaSubida;
	}
	
	public IFuncionalidadExterna getFuncionalidadExternaValidada() {
		return funcionalidadExternaValidada;
	}
	
	public ZonaDeCobertura getZonaDeCobertura() {
		return this.miZonaDeCobertura;
	}
	
	private void setZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.miZonaDeCobertura = zonaDeCobertura;
		zonaDeCobertura.addObserver(this);
	}
	
	private void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
		this.tipoDeOrganizacion = tipoDeOrganizacion;
	}

	private void setCantidadDeTrabajadores(int cantidadDeTrabajadores) {
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	private void setFuncionalidadExternaSubida(IFuncionalidadExterna funcionalidadExternaSubida) {
		this.funcionalidadExternaSubida = funcionalidadExternaSubida;
	}

	private void setFuncionalidadExternaValidada(IFuncionalidadExterna funcionalidadExternaValidada) {
		this.funcionalidadExternaValidada = funcionalidadExternaValidada;
	}

	/**
	 * @note mensaje parte de la interfaz IZonaDeCoberturaListener
	 */
	@Override
	public void muestraSubida(ZonaDeCobertura zona, Muestra muestra) {
		this.getFuncionalidadExternaSubida().nuevoEvento(this, zona, muestra);
	}
	
	/**
	 * @note mensaje parte de la interfaz IZonaDeCoberturaListener
	 */
	@Override
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		this.getFuncionalidadExternaValidada().nuevoEvento(this, zona, muestra);
	}
}