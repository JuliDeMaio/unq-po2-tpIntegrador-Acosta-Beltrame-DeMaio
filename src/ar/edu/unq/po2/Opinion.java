package ar.edu.unq.po2;

import java.time.LocalDate;

/**
	 * @author Acosta, Federico
	 * @see Usuario, TipoDeOpinion, Ubicacion
	 * 
	 */
public class Opinion {
	
	private LocalDate fechaDeEmision;

	public Opinion(Usuario usuario, TipoDeOpinion tipoDeOpinion) {

	}

	public Usuario getUsuarioDueño() {
	return null;
	}

	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	public void setFechaDeEmision(LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public boolean seEmitioEnLosUltimos30Dias() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
