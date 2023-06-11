package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Usuario;

	/**
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar una clase concreta del ConcreteStateB del State.
	 * 			Mas concretamente, el estado UsuarioExpertoExterno.
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoInterno, EstadoUsuario
	 * 
	 */
public class EstadoUsuarioExpertoExterno extends EstadoUsuarioExperto {

	@Override
	public boolean esEstadoExpertoInterno() {
		return false;
	}

	@Override
	public boolean esEstadoExpertoExterno() {
		return true;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		System.out.println("El usuario es Experto Externo, no es posible cambiar la categoria");
	}
}