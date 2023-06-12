package ar.edu.unq.po2;

import java.time.LocalDate;

import ar.edu.unq.po2.enums.ITipoDeOpinion;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;

	/**
	 * @author Acosta, Federico
	 * 		   Beltrame, Franco
	 * 		   De Maio, Julian
	 * @see Usuario, TipoDeOpinion, Muestra
	 * @note Esta clase tiene como objetivo modelar una Opinion del sistema.
	 */
public class Opinion {
	
	private ITipoDeOpinion tipoDeOpinion;
	private Usuario usuarioDueño;
	private LocalDate fechaDeEmision;
	private EstadoUsuario categoriaDeEmision;

	public Opinion(ITipoDeOpinion tipoDeOpinion, Usuario usuarioDueño) {
		super();
		this.setTipoDeOpinion(tipoDeOpinion);
		this.setUsuarioDueño(usuarioDueño);
		this.setFechaDeEmision(LocalDate.now());
		this.setCategoriaDeEmision(usuarioDueño.getState());
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
	
	public EstadoUsuario getCategoriaDeEmision() {
		return categoriaDeEmision;
	}
	
	private void setTipoDeOpinion(ITipoDeOpinion tipoDeOpinion) {
		this.tipoDeOpinion = tipoDeOpinion;
	}

	private void setUsuarioDueño(Usuario usuarioDueño) {
		this.usuarioDueño = usuarioDueño;
	}

	private void setFechaDeEmision(LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	private void setCategoriaDeEmision(EstadoUsuario categoriaDeEmision) {
		this.categoriaDeEmision = categoriaDeEmision;
	}

	public boolean seEmitioEnLosUltimos30Dias() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaActualRestada = fechaActual.minusDays(30);
		
		return(!(this.getFechaDeEmision().isBefore(fechaActualRestada)));
	}

	public boolean fueEmitidaPorUnExperto() {
		return this.getCategoriaDeEmision().esEstadoExperto();
	}

	public boolean fueEmitidaPorUsuario(Usuario usuario) {
		return this.getUsuarioDueño().equals(usuario);
	}
}