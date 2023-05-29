package ar.edu.unq.po2;

import java.time.LocalDate;

/**
	 * @author Acosta, Federico
	 * 		   Beltrame, Franco
	 * @see Usuario, TipoDeOpinion, Ubicacion
	 * 
	 */
public class Opinion {
	
	private TipoDeOpinion tipoDeOpinion;
	private Usuario usuarioDueño;
	private LocalDate fechaDeEmision;

	public Opinion(TipoDeOpinion tipoDeOpinion, Usuario usuarioDueño, LocalDate fechaDeEmision) {
		super();
		this.setTipoDeOpinion(tipoDeOpinion);
		this.setUsuarioDueño(usuarioDueño);
		this.setFechaDeEmision(fechaDeEmision);
	}

	public TipoDeOpinion getTipoDeOpinion() {
		return tipoDeOpinion;
	}

	public Usuario getUsuarioDueño() {
		return usuarioDueño;
	}
	
	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	private void setTipoDeOpinion(TipoDeOpinion tipoDeOpinion) {
		this.tipoDeOpinion = tipoDeOpinion;
	}

	private void setUsuarioDueño(Usuario usuarioDueño) {
		this.usuarioDueño = usuarioDueño;
	}

	public void setFechaDeEmision(LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public boolean seEmitioEnLosUltimos30Dias() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaActualRestada = fechaActual.minusDays(30);
		
		return(!(this.getFechaDeEmision().isBefore(fechaActualRestada)));
	}
}