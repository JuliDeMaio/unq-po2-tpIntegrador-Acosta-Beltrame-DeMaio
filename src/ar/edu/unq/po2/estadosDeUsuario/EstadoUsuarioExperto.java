package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

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
	
}
