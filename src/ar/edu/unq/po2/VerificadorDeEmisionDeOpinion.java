package ar.edu.unq.po2;

import ar.edu.unq.po2.usuarioExceptions.*;

	/** @author Acosta, Federico
	 * 		    De Maio, Julian
	 *  @see UsuarioException
	 **/

public class VerificadorDeEmisionDeOpinion {
	
	public VerificadorDeEmisionDeOpinion(){
		super();
	}
	
	public void realizarVerificacionesParaUsuarioBasico(Muestra muestra, Opinion opinion) throws UsuarioException{
		realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(muestra, opinion);
		realizarVerificacionDeQueEsOpinionUnica(muestra, opinion);
		realizarVerificacionDeQueNoVotoUnExperto(muestra);
		realizarVerificacionDeQueNoEsMuestraVerificada(muestra);
	}

	private void realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(Muestra muestra, Opinion opinion) throws UsuarioEsDueñoDeLaMuestraException {
		if (muestra.esDueñoDeLaMuestra(opinion.getUsuarioDueño())) {
			throw new UsuarioEsDueñoDeLaMuestraException();
		}
	}

	private void realizarVerificacionDeQueEsOpinionUnica(Muestra muestra, Opinion opinion) throws UsuarioNoEsOpinionUnicaException {
		if (muestra.opinoElUsuario(opinion.getUsuarioDueño())) {
			throw new UsuarioNoEsOpinionUnicaException();
		}
	}

	private void realizarVerificacionDeQueNoVotoUnExperto(Muestra muestra) throws UsuarioYaVotoUnExpertoException {
		if  (muestra.opinoAlMenosUnExperto()) {
			throw new UsuarioYaVotoUnExpertoException();
		}
	}

	private void realizarVerificacionDeQueNoEsMuestraVerificada(Muestra muestra) throws UsuarioEsMuestraVerificadaException {
		if (muestra.esVerificada()) {
			throw new UsuarioEsMuestraVerificadaException();
		}
	}

	public void realizarVerificacionesParaUsuarioExperto(Muestra muestra, Opinion opinion) throws UsuarioException {
		realizarVerificacionDeQueNoEsMuestraVerificada(muestra);
		realizarVerificacionDeQueNoEsElDueñoDeLaMuestra(muestra, opinion);
		realizarVerificacionDeQueEsOpinionUnica(muestra, opinion);
	}



}
