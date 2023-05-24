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
	private EstadoUsuario estadoActual;
	
	public Usuario(EstadoUsuario estadoDeUsuario) {
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

	public EstadoUsuario getState() {
		return estadoActual;
	}

	private void setOpinionesRegistradas(List<Opinion> opinionesRegistradas) {
		this.opinionesRegistradas = opinionesRegistradas;
	}

	private void setMuestrasRegistradas(List<Muestra> muestrasRegistradas) {
		this.muestrasRegistradas = muestrasRegistradas;
	}

	private void setState(EstadoUsuario estadoDeUsuario) {
		this.estadoActual = estadoDeUsuario;
	}
	
	public void guardarMuestra(Muestra muestra) {
		this.getMuestrasRegistradas().add(muestra);
	}

	public int cantidadMuestras() {
		return this.getMuestrasRegistradas().size();
	}

	public int cantidadOpiniones() {
		return this.getOpinionesRegistradas().size();
	}

	public void emitirOpinionDe(Muestra muestra, TipoDeOpinion tipoDeOpinion) {
		Opinion opinionAEmitir = new Opinion(this, tipoDeOpinion);
		try {
			this.getState().realizarVerificacionesPara(muestra, opinionAEmitir);
		} catch (UsuarioException e) {
				System.out.println(e.getMessage());
		}
		this.emitirOpinionVerificadaDe(muestra, opinionAEmitir);
	}
	
	public void emitirOpinionVerificadaDe(Muestra muestra, Opinion opinion) {
		this.guardarOpinion(opinion);
		muestra.publicarOpinion(opinion);
	}

	public void guardarOpinion(Opinion opinion) {
		this.getOpinionesRegistradas().add(opinion);
	}
}
