package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Usuario;

/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoExterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioExpertoInterno extends EstadoUsuarioExperto {

	@Override
	public boolean esEstadoExpertoInterno() {
		return true;
	}

	@Override
	public boolean esEstadoExpertoExterno() {
		return false;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		if(!usuario.cumpleConLosRequisitosDeUsuarioExperto()) {
			usuario.setState(new EstadoUsuarioBasico());
		}
	}
}
