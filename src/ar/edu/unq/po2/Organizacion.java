package ar.edu.unq.po2;

	/**
	 * @author Beltrame, Franco
	 * @see TipoDeOrganizacion, Ubicacion
	 *
	 */

public class Organizacion {
	
	private TipoDeOrganizacion tipoDeOrganizacion;
	private int cantidadDeTrabajadores;
	private Ubicacion ubicacion;
	
	public Organizacion(TipoDeOrganizacion tipoDeOrganizacion, int cantidadDeTrabajadores, Ubicacion ubicacion) {
		
		this.setTipoDeOrganizacion(tipoDeOrganizacion);
		this.setCantidadDeTrabajadores(cantidadDeTrabajadores);
		this.setUbicacion(ubicacion);
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

	private void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
		this.tipoDeOrganizacion = tipoDeOrganizacion;
	}

	private void setCantidadDeTrabajadores(int cantidadDeTrabajadores) {
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

}
