package ar.edu.unq.po2;

	/**
	 * @author Beltrame, Franco
	 * @see TipoDeOrganizacion, Ubicacion
	 *
	 */

public class Organizacion implements ZonaDeCoberturaListener{
	
	private TipoDeOrganizacion tipoDeOrganizacion;
	private int cantidadDeTrabajadores;
	private Ubicacion ubicacion;
	private FuncionalidadExterna funcionalidadExternaSubida;
	private FuncionalidadExterna funcionalidadExternaValidada;
	
	public Organizacion(TipoDeOrganizacion tipoDeOrganizacion, int cantidadDeTrabajadores, Ubicacion ubicacion, 
						FuncionalidadExterna funcionalidadSubida, FuncionalidadExterna funcionalidadValidada) {
		super();
		this.setTipoDeOrganizacion(tipoDeOrganizacion);
		this.setCantidadDeTrabajadores(cantidadDeTrabajadores);
		this.setUbicacion(ubicacion);
		this.setFuncionalidadExternaSubida(funcionalidadSubida);
		this.setFuncionalidadExternaValidada(funcionalidadValidada);
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

	public FuncionalidadExterna getFuncionalidadExternaSubida() {
		return funcionalidadExternaSubida;
	}
	
	public FuncionalidadExterna getFuncionalidadExternaValidada() {
		return funcionalidadExternaValidada;
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

	private void setFuncionalidadExternaSubida(FuncionalidadExterna funcionalidadExternaSubida) {
		this.funcionalidadExternaSubida = funcionalidadExternaSubida;
	}

	private void setFuncionalidadExternaValidada(FuncionalidadExterna funcionalidadExternaValidada) {
		this.funcionalidadExternaValidada = funcionalidadExternaValidada;
	}

	@Override
	public void muestraSubida(ZonaDeCobertura zona, Muestra muestra) {
		this.getFuncionalidadExternaSubida().nuevoEvento(this, zona, muestra);
	}
	
	@Override
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		this.getFuncionalidadExternaValidada().nuevoEvento(this, zona, muestra);
	}

}
