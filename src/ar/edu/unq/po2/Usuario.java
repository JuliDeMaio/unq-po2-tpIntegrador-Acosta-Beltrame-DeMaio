package ar.edu.unq.po2;

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

	private EstadoUsuario estadoActual;
	
	public Usuario(EstadoUsuario estadoDeUsuario) {
		super();
		this.setState(estadoDeUsuario);
	}

	public Set<Muestra> obtenerMuestrasRegistradas() {
		return AppWeb.getInstance().muestrasDeUsuario(this);
	}

	public EstadoUsuario getState() {
		return estadoActual;
	}

	public void setState(EstadoUsuario estadoDeUsuario) {
		this.estadoActual = estadoDeUsuario;
	}

	public int cantidadMuestras() {
		return this.obtenerMuestrasRegistradas().size();
	}

	public int cantidadOpiniones() {
		return this.obtenerOpiniones().size();
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
		
		Opinion opinionAEmitir = new Opinion(tipoDeOpinion, this);
		
		this.getState().realizarVerificacionesPara(muestra, opinionAEmitir);
		this.emitirOpinionVerificadaDe(muestra, opinionAEmitir);
	}
	
	public void emitirOpinionVerificadaDe(Muestra muestra, Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
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
		return this.obtenerOpiniones()
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
	
	public List<Opinion> obtenerOpiniones() {
		return AppWeb.getInstance().opinionesDeUsuario(this);
	}
}