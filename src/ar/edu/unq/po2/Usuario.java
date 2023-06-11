package ar.edu.unq.po2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.enums.ITipoDeOpinion;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuario;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

	/**
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * 		   Beltrame, Franco
	 * 
	 * @note Esta clase tiene como objetivo modelar a un Usuario del sistema.
	 * 
	 * @see EstadoUsuario, UsuarioException, AppWeb, Muestra, Opinion. 
	 * @DesignPattern State <<Context>> (estados de usuario)
	 **/

public class Usuario {

	private List<Opinion> opinionesRegistradas;
	private EstadoUsuario estadoActual;
	
	public Usuario(EstadoUsuario estadoDeUsuario) {
		super();
		this.setOpinionesRegistradas(new ArrayList<Opinion>());
		this.setState(estadoDeUsuario);
	}

	public List<Opinion> getOpinionesRegistradas() {
		return opinionesRegistradas;
	}

	public Set<Muestra> obtenerMuestrasRegistradas() {
		return AppWeb.getInstance().muestrasDeUsuario(this);
	}

	public EstadoUsuario getState() {
		return estadoActual;
	}

	private void setOpinionesRegistradas(List<Opinion> opinionesRegistradas) {
		this.opinionesRegistradas = opinionesRegistradas;
	}

	public void setState(EstadoUsuario estadoDeUsuario) {
		this.estadoActual = estadoDeUsuario;
	}
	
	public void guardarOpinion(Opinion opinion) {
		this.getOpinionesRegistradas().add(opinion);
	}

	public int cantidadMuestras() {
		return this.obtenerMuestrasRegistradas().size();
	}

	public int cantidadOpiniones() {
		return this.getOpinionesRegistradas().size();
	}

	/**
	 * @note mensaje que desencadena la emision de una opinion, puede arrojar excepcion si no cumple los requisitos
	 * 			gestionados por los EstadoUsuario
	 * @param muestra
	 * @param tipoDeOpinion
	 * @throws UsuarioException
	 * @throws MuestraEstaVerificadaException
	 * @throws MuestraEstaVotadaPorExpertosException
	 */
	public void emitirOpinionDe(Muestra muestra, ITipoDeOpinion tipoDeOpinion) throws UsuarioException, MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		
		Opinion opinionAEmitir = new Opinion(tipoDeOpinion, this, LocalDate.now());
		
		this.getState().realizarVerificacionesPara(muestra, opinionAEmitir);
		this.emitirOpinionVerificadaDe(muestra, opinionAEmitir);
	}
	
	public void emitirOpinionVerificadaDe(Muestra muestra, Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		this.guardarOpinion(opinion);
		muestra.recibirOpinion(opinion);
	}

	public int cantidadDeMuestrasEmitidasEnUltimos30Dias() {
		return this.obtenerMuestrasRegistradas()
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
	
	public boolean esUsuarioExperto() {
		return this.getState().esEstadoExperto();
	}

	public boolean esUsuarioExpertoInterno() {
		return this.getState().esEstadoExpertoInterno();
	}

	public boolean esUsuarioExpertoExterno() {
		return this.getState().esEstadoExpertoExterno();
	}

	public void actualizarCategoria() {
		this.getState().actualizarCategoria(this);
	}
	
	public boolean cumpleConLosRequisitosDeUsuarioExperto() {
		return((this.cantidadDeMuestrasEmitidasEnUltimos30Dias() > 10) 
			   &&
			   (this.cantidadDeOpinionesEmitidasEnUltimos30Dias() > 20));
	}

	/**
	 * @note Mensaje intermedio del double-dispatch entre IEstadoMuestra y Usuario
	 * @param estadoMuestra -> estadoMuestra para el que realiza el dispatch.
	 * @param muestra -> muestra sobre la que realiza el dispatch.
	 * @throws MuestraEstaVotadaPorExpertosException
	 * @throws MuestraEstaVerificadaException
	 */
	public void gestionarEstadoMuestraPara(IEstadoMuestra estadoMuestra, Muestra muestra) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		this.getState().gestionarEstadoMuestraPara(estadoMuestra, muestra);
	}
}