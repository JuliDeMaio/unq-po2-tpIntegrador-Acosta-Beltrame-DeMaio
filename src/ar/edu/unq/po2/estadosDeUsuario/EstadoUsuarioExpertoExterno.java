package ar.edu.unq.po2.estadosDeUsuario;

import ar.edu.unq.po2.Usuario;

/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoInterno, IEstadoUsuario
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
		System.out.println("El usuario es Experto Externo, esto no deberia ocurrir.");
	}
}