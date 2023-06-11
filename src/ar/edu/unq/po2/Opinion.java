package ar.edu.unq.po2;

import java.time.LocalDate;

import ar.edu.unq.po2.enums.ITipoDeOpinion;

	/**
	 * @author Acosta, Federico
	 * 		   Beltrame, Franco
	 * @see Usuario, TipoDeOpinion, Muestra
	 * @note Esta clase tiene como objetivo modelar una Opinion del sistema.
	 */
public class Opinion {
	
	private ITipoDeOpinion tipoDeOpinion;
	private Usuario usuarioDueño;
	private LocalDate fechaDeEmision;

	public Opinion(ITipoDeOpinion tipoDeOpinion, Usuario usuarioDueño, LocalDate fechaDeEmision) {
		super();
		this.setTipoDeOpinion(tipoDeOpinion);
		this.setUsuarioDueño(usuarioDueño);
		this.setFechaDeEmision(fechaDeEmision);
	}

	public ITipoDeOpinion getTipoDeOpinion() {
		return tipoDeOpinion;
	}

	public Usuario getUsuarioDueño() {
		return usuarioDueño;
	}
	
	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	private void setTipoDeOpinion(ITipoDeOpinion tipoDeOpinion) {
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

	public boolean fueEmitidaPorUnExperto() {
		return this.getUsuarioDueño().esUsuarioExperto();
	}

	public boolean fueEmitidaPorUsuario(Usuario usuario) {
		return this.getUsuarioDueño().equals(usuario);
	}
}