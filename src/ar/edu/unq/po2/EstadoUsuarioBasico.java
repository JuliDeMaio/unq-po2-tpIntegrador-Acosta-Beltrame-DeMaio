package ar.edu.unq.po2;

import ar.edu.unq.po2.usuarioExceptions.*;

/**
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * @see Usuario, EstadoUsuarioExpertoExterno, EstadoUsuarioExpertoInterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioBasico extends EstadoUsuario {

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
}
