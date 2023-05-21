package ar.edu.unq.po2;

	/**
	 * @author Acosta, Federico
	 * @see Usuario, EstadoUsuarioExpertoExterno, EstadoUsuarioExpertoInterno, IEstadoUsuario
	 * 
	 */
public class EstadoUsuarioBasico implements IEstadoUsuario {

	@Override
	public void emitirOpinionDe(Muestra muestra, TipoDeOpinion opinion, ) {
		
		this.realizarVerificacionesPara(muestra, usuario);
		
		Opinion opinionAEmitir = new Opinion(usuario, opinion);
	}
	
	public void realizarVerificacionesPara(Muestra muestra, Usuario usuario) {
		try {
			usuario.emitirOpinionDe
		} catch (ExcepcionNoEsOpinionUnica e1, 
				ExcepcionEsDueñoDeLaMuestra e2, 
				ExcepcionEsMuestraVerificada e3) {
			// TODO: handle exception
			System.out.println(e1.getMessage());
			System.out.println(e2.getMessage());
			System.out.println(e3.getMessage());
		}
	}
	
	public void verificarQueSeaOpinionUnica() {
		try {
			emitirOpinionDe
			
		} catch () {
			// TODO: handle exception
		}
	}
	
	public void verificarQueNoSeaElDueñoDeLaMuestra() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void verificarNivelDeVerificacion() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
