package ar.edu.unq.po2;

	/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioBasico, EstadoUsuarioExpertoInterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioExpertoExterno implements IEstadoUsuario {

	@Override
	public void gestionarOpinionPara(Muestra muestra, Opinion opinion) {
		opinion.getUsuarioDueño().emitirOpinionDeSiendoUsuarioExperto(muestra, opinion);
	}

}
