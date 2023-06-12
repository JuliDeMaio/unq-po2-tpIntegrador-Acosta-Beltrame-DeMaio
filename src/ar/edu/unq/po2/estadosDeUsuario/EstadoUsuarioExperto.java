package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

	/**
	 * 
	 * @author feder
	 * @see Usuario, EstadoUsuarioExpertoExterno, EstadoUsuarioExpertoInterno, EstadoUsuario, IEstadoMuestra
	 * @note esta clase tiene como objetivo modelar un estado concreto del State, el de usuario experto.
	 * @DesignPattern State <ConcreteStateB>>
	 */
public abstract class EstadoUsuarioExperto extends EstadoUsuario {

	@Override
	public void realizarVerificacionesPara(Muestra muestra, Opinion opinion) throws UsuarioException {
		realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(muestra, opinion);
		realizarVerificacionDeQueEsOpinionUnica(muestra, opinion);
		realizarVerificacionDeQueNoEsMuestraVerificada(muestra);		
	}
	
	@Override
	public boolean esEstadoBasico() {
		return false;
	}
	
	@Override
	public boolean esEstadoExperto() {
		return true;
	}
	
	/**
	 * @note mensaje parte del double-dispatch, realiza la gestion siendo usuario experto.
	 */
	@Override
	public void gestionarEstadoMuestraPara(IEstadoMuestra estadoMuestra, Muestra muestra) throws MuestraEstaVerificadaException {
		estadoMuestra.realizarVerificacionParaUsuarioExperto(muestra);
	}
	
}
