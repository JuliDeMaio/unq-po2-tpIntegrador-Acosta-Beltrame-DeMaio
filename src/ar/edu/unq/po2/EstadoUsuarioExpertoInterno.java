package ar.edu.unq.po2;

	/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoExterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioExpertoInterno implements IEstadoUsuario {

	@Override
	public void gestionarOpinionPara(Muestra muestra, Opinion opinion) {
		opinion.getUsuarioDueño().emitirOpinionDeSiendoUsuarioExperto(muestra, opinion);
	}
	
}
