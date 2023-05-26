package ar.edu.unq.po2.estadosDeUsuario;

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

}
