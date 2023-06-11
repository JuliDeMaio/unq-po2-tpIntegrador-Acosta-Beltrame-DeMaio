package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.usuarioExceptions.*;

/**
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar un estado concreto, el de usuario basico.
	 * @see Usuario, EstadoUsuarioExpertoExterno, EstadoUsuarioExpertoInterno, EstadoUsuario, IEstadoMuestra
	 * @DesignPattern State <<ConcreteStateA>>
	 */
public class EstadoUsuarioBasico extends EstadoUsuario {

	/**
	 * @note mensaje que realiza las verificaciones para emitir una opinion.
	 */
	@Override
	public void realizarVerificacionesPara(Muestra muestra, Opinion opinion) throws UsuarioException {
		realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(muestra, opinion);
		realizarVerificacionDeQueEsOpinionUnica(muestra, opinion);
		realizarVerificacionDeQueNoVotoUnExperto(muestra);
		realizarVerificacionDeQueNoEsMuestraVerificada(muestra);
		
	}

	private void realizarVerificacionDeQueNoVotoUnExperto(Muestra muestra) throws UsuarioYaVotoUnExpertoException {
		if  (muestra.opinoAlMenosUnExperto()) {
			throw new UsuarioYaVotoUnExpertoException();
		}
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		if(usuario.cumpleConLosRequisitosDeUsuarioExperto()) {
			usuario.setState(new EstadoUsuarioExpertoInterno());
		}
	}
	
	@Override
	public boolean esEstadoBasico() {
		return true;
	}

	@Override
	public boolean esEstadoExpertoInterno() {
		return false;
	}

	@Override
	public boolean esEstadoExpertoExterno() {
		return false;
	}

	/**
	 * @note mensaje parte del double-dispatch, realiza la gestion siendo usuario basico.
	 */
	@Override
	public void gestionarEstadoMuestraPara(IEstadoMuestra estadoMuestra, Muestra muestra) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		estadoMuestra.realizarVerificacionParaUsuarioBasico(muestra);
	}

	@Override
	public boolean esEstadoExperto() {
		return false;
	}
}