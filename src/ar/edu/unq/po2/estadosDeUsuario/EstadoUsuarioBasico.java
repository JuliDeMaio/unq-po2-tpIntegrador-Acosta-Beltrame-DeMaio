package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.Usuario;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;
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

	@Override
	public void gestionarEstadoMuestraPara(IEstadoMuestra estadoMuestra, Muestra muestra) {
		estadoMuestra.realizarVerificacionParaUsuarioBasico(muestra);
	}
}
