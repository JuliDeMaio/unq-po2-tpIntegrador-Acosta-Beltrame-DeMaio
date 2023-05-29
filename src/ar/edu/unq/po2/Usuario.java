package ar.edu.unq.po2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

	/**
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * 		   Beltrame, Franco
	 * 
	 * @see EstadoUsuario, UsuarioException
	 **/

public class Usuario {

	private List<Opinion> opinionesRegistradas;
	private List<Muestra> muestrasRegistradas;
	private EstadoUsuario estadoActual;
	
	public Usuario(EstadoUsuario estadoDeUsuario) {
		super();
		this.setOpinionesRegistradas(new ArrayList<Opinion>());
		this.setMuestrasRegistradas(new ArrayList<Muestra>());
		this.setState(estadoDeUsuario);
	}

	public List<Opinion> getOpinionesRegistradas() {
		return opinionesRegistradas;
	}

	public List<Muestra> getMuestrasRegistradas() {
		return muestrasRegistradas;
	}

	public EstadoUsuario getState() {
		return estadoActual;
	}

	private void setOpinionesRegistradas(List<Opinion> opinionesRegistradas) {
		this.opinionesRegistradas = opinionesRegistradas;
	}

	private void setMuestrasRegistradas(List<Muestra> muestrasRegistradas) {
		this.muestrasRegistradas = muestrasRegistradas;
	}

	public void setState(EstadoUsuario estadoDeUsuario) {
		this.estadoActual = estadoDeUsuario;
	}
	
	public void guardarMuestra(Muestra muestra) {
		this.getMuestrasRegistradas().add(muestra);
	}
	
	public void guardarOpinion(Opinion opinion) {
		this.getOpinionesRegistradas().add(opinion);
	}

	public int cantidadMuestras() {
		return this.getMuestrasRegistradas().size();
	}

	public int cantidadOpiniones() {
		return this.getOpinionesRegistradas().size();
	}

	public void emitirOpinionDe(Muestra muestra, TipoDeOpinion tipoDeOpinion) throws UsuarioException {
		
		Opinion opinionAEmitir = new Opinion(tipoDeOpinion, this, LocalDate.now());
		
		this.getState().realizarVerificacionesPara(muestra, opinionAEmitir);
		this.emitirOpinionVerificadaDe(muestra, opinionAEmitir);
	}
	
	public void publicarMuestra(Muestra muestra) {
		AppWeb.getInstance().guardarMuestra(muestra);
		this.guardarMuestra(muestra);
	}
	
	public void emitirOpinionVerificadaDe(Muestra muestra, Opinion opinion) {
		this.guardarOpinion(opinion);
		muestra.publicarOpinion(opinion);
	}

	public int cantidadDeMuestrasEmitidasEnUltimos30Dias() {
		return this.getMuestrasRegistradas()
				   .stream()
				   .filter(o -> o.seEmitioEnLosUltimos30Dias())
				   .toList()
				   .size();
	}

	public int cantidadDeOpinionesEmitidasEnUltimos30Dias() {
		return this.getOpinionesRegistradas()
				   .stream()
				   .filter(m -> m.seEmitioEnLosUltimos30Dias())
				   .toList()
				   .size();
	}

	public boolean esUsuarioBasico() {
		return this.getState().esEstadoBasico();
	}

	public boolean esUsuarioExpertoInterno() {
		return this.getState().esEstadoExpertoInterno();
	}

	public boolean esUsuarioExpertoExterno() {
		return this.getState().esEstadoExpertoExterno();
	}
}
