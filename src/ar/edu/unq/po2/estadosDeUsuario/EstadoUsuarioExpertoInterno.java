package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Usuario;

	/**
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar una clase concreta del ConcreteStateB del State.
	 * 			Mas concretamente, el estado UsuarioExpertoInterno.
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoExterno, EstadoUsuario
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
