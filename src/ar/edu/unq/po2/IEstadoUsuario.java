package ar.edu.unq.po2;

	/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoInterno, EstadoUsuarioExpertoExterno
	 * 
	 */
public interface IEstadoUsuario {

	public void emitirOpinionDe(Muestra muestra, TipoDeOpinion opinion);
}
