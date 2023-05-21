package ar.edu.unq.po2;

import java.util.ArrayList;
import java.util.List;

	/*
	 * @author Acosta, Federico
	 * @see IEstadoUsuario, EstadoUsuarioBasico, EstadoUsuarioExpertoExterno, EstadoUsuarioExpertoInterno
	 * 
	 */
public class Usuario {

	private List<Opinion> opinionesRegistradas;
	private List<Muestra> muestrasRegistradas;
	private IEstadoUsuario estadoActual;
	private GestorDeOpiniones gestorDeOpiniones;
	
	public Usuario(IEstadoUsuario estadoDeUsuario) {
		super();
		this.setOpinionesRegistradas(new ArrayList<Opinion>());
		this.setMuestrasRegistradas(new ArrayList<Muestra>());
		this.setState(estadoDeUsuario);
		this.setGestorDeOpiniones(new GestorDeOpiniones());
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

	public GestorDeOpiniones getGestorDeOpiniones() {
		return gestorDeOpiniones;
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
	
	public void setGestorDeOpiniones(GestorDeOpiniones gestorDeOpiniones) {
		this.gestorDeOpiniones = gestorDeOpiniones;
	}

	public int cantidadOpiniones() {
		return this.getOpinionesRegistradas().size();
	}

	public void emitirOpinionDe(Muestra muestra, TipoDeOpinion tipoDeOpinion) {
		this.getState().emitirOpinionDe(muestra, tipoDeOpinion);
	}
}
