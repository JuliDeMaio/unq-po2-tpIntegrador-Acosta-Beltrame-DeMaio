package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsDueñoDeLaMuestraException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioEsMuestraVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioNoEsOpinionUnicaException;

	/**
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar y tipar los distintos States que puede tomar el Usuario.
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoInterno, EstadoUsuarioExpertoExterno, IEstadoMuestra
	 * @DesignPattern State <<State>>
	 * 
	 */
public abstract class EstadoUsuario {
	
	/**
	 * @note mensaje que realiza las verificaciones para emitir una opinion.
	 * @param muestra
	 * @param opinion
	 * @throws UsuarioException
	 */
	public abstract void realizarVerificacionesPara(Muestra muestra, Opinion opinion) throws UsuarioException;

	protected void realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(Muestra muestra, Opinion opinion) throws UsuarioEsDueñoDeLaMuestraException {
		if (muestra.esDueñoDeLaMuestra(opinion.getUsuarioDueño())) {
			throw new UsuarioEsDueñoDeLaMuestraException();
		}
	}
	
	protected void realizarVerificacionDeQueEsOpinionUnica(Muestra muestra, Opinion opinion) throws UsuarioNoEsOpinionUnicaException {
		if (muestra.opinoElUsuario(opinion.getUsuarioDueño())) {
			throw new UsuarioNoEsOpinionUnicaException();
		}
	}
	
	protected void realizarVerificacionDeQueNoEsMuestraVerificada(Muestra muestra) throws UsuarioEsMuestraVerificadaException {
		if (muestra.obtenerNivelDeVerificacion().equals(NivelDeVerificacion.VERIFICADA)) {
			throw new UsuarioEsMuestraVerificadaException();
		}
	}

	public abstract boolean esEstadoBasico();

	public abstract boolean esEstadoExperto();
	
	public abstract boolean esEstadoExpertoInterno();
	
	public abstract boolean esEstadoExpertoExterno();
	
	public abstract void actualizarCategoria(Usuario usuario);

	/**
	 * @note Este mensaje es parte del double-dispatch 
	 * @param estadoMuestra : estadoMuestra para el que gestiona el double-dispatch
	 * @param muestra
	 * @throws MuestraEstaVotadaPorExpertosException
	 * @throws MuestraEstaVerificadaException
	 */
	public abstract void gestionarEstadoMuestraPara(IEstadoMuestra estadoMuestra, Muestra muestra) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException;
}