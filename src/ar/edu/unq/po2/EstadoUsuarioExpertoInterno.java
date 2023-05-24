package ar.edu.unq.po2;

import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoExterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioExpertoInterno extends EstadoUsuario {

	@Override
	public void realizarVerificacionesPara(Muestra muestra, Opinion opinion) throws UsuarioException {
		realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(muestra, opinion);
		realizarVerificacionDeQueEsOpinionUnica(muestra, opinion);
		realizarVerificacionDeQueNoEsMuestraVerificada(muestra);
		
	}
}
