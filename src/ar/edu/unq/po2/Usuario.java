package ar.edu.unq.po2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.usuarioExceptions.UsuarioException;

	/**
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * 
	 * @see IEstadoUsuario, VerificadorDeEmisionDeOpinion, 
	 * 	    UsuarioException
	 **/

public class Usuario {

	private List<Opinion> opinionesRegistradas;
	private List<Muestra> muestrasRegistradas;
	private IEstadoUsuario estadoActual;
	
	public Usuario(IEstadoUsuario estadoDeUsuario) {
		super();
		this.setOpinionesRegistradas(new ArrayList<Opinion>());
		this.setMuestrasRegistradas(new ArrayList<Muestra>());
		this.setState(estadoDeUsuario);
	}

	public List<Opinion> getOpinionesRegistradas() {
		return opinionesRegistradas;
	}

	public List<Muestra> getMuestrasRegistradas() {
		return muestrasRegistradas;
	}

	public IEstadoUsuario getState() {
		return estadoActual;
	}

	private void setOpinionesRegistradas(List<Opinion> opinionesRegistradas) {
		this.opinionesRegistradas = opinionesRegistradas;
	}

	private void setMuestrasRegistradas(List<Muestra> muestrasRegistradas) {
		this.muestrasRegistradas = muestrasRegistradas;
	}

	private void setState(IEstadoUsuario estadoDeUsuario) {
		this.estadoActual = estadoDeUsuario;
	}
	

	public int cantidadOpiniones() {
		return this.getOpinionesRegistradas().size();
	}

	public void emitirOpinionDe(Muestra muestra, TipoDeOpinion tipoDeOpinion) {
		Opinion opinionAEmitir = new Opinion(this, tipoDeOpinion);
		this.getState().gestionarOpinionPara(muestra, opinionAEmitir);
	}
	
	public void emitirOpinionDeSiendoUsuarioBasico(Muestra muestra, Opinion opinion) {
		try {
			new VerificadorDeEmisionDeOpinion().realizarVerificacionesParaUsuarioBasico(muestra, opinion);
			this.emitirOpinionVerificadaDe(muestra, opinion);
		} catch (UsuarioException e) {
				System.out.println(e.getMessage());
		}
	}
	
	public void emitirOpinionDeSiendoUsuarioExperto(Muestra muestra, Opinion opinion) {
		try {
			new VerificadorDeEmisionDeOpinion().realizarVerificacionesParaUsuarioExperto(muestra, opinion);
			this.emitirOpinionVerificadaDe(muestra, opinion);
		} catch (UsuarioException e) {
				System.out.println(e.getMessage());
		}
	}
		
	private void emitirOpinionVerificadaDe(Muestra muestra, Opinion opinion) {
		muestra.publicarOpinion(opinion);
		this.guardarOpinion(opinion);
	}

	private void guardarOpinion(Opinion opinion) {
		getOpinionesRegistradas().add(opinion);
	}
}
