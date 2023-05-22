package ar.edu.unq.po2;

	/**
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * @see Usuario, EstadoUsuarioExpertoExterno, EstadoUsuarioExpertoInterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioBasico implements IEstadoUsuario {

	@Override
	public void gestionarOpinionPara(Muestra muestra, Opinion opinion) {
		opinion.getUsuarioDueño().emitirOpinionDeSiendoUsuarioBasico(muestra, opinion);
	}
}
