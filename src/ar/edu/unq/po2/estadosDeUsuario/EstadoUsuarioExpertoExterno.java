package ar.edu.unq.po2.estadosDeUsuario;

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

}
