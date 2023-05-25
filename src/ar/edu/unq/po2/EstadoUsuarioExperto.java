package ar.edu.unq.po2;

import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

public abstract class EstadoUsuarioExperto extends EstadoUsuario {

	@Override
	public void realizarVerificacionesPara(Muestra muestra, Opinion opinion) throws UsuarioException {
		realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(muestra, opinion);
		realizarVerificacionDeQueEsOpinionUnica(muestra, opinion);
		realizarVerificacionDeQueNoEsMuestraVerificada(muestra);		
	}

}
