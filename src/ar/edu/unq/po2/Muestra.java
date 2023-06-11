package ar.edu.unq.po2;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.enums.IResultadoMuestra;
import ar.edu.unq.po2.enums.ITipoDeOpinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.enums.ResultadoMuestra;
import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorBasicos;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

/**
	 * @author Acosta, Federico
	 * @see Usuario, Opinion, TipoDeOpinion, Ubicacion
	 * @note Esta clase tiene como objetivo modelar una Muestra del sistema.
	 */
public class Muestra {
	
	private String foto;
	private IEstadoMuestra estadoActual;
	private IResultadoMuestra resultadoActual;
	private Usuario usuarioDueño;
	private Set<Opinion> opiniones;
	private Ubicacion ubicacion;
	private LocalDate fechaDeEmision;
	
	/**
	 * @note TODO
	 * @param foto
	 * @param usuarioDueño
	 * @param opinionDeInicio
	 * @param ubicacion
	 * @throws MuestraEstaVerificadaException
	 * @throws MuestraEstaVotadaPorExpertosException
	 */
	public Muestra(String foto, Usuario usuarioDueño, Opinion opinionDeInicio, Ubicacion ubicacion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		super();
		this.setFoto(foto);
		this.setState(new EstadoMuestraOpinadaPorBasicos());
		this.setResultadoActual(ResultadoMuestra.NODEFINIDA);
		this.setUsuarioDueño(usuarioDueño);
		this.setOpiniones(new HashSet<Opinion>());
		this.setUbicacion(ubicacion);
		this.recibirOpinion(opinionDeInicio);
		this.setFechaDeEmision(LocalDate.now());
	}

	public String getFoto() {
		return foto;
	}

	public IEstadoMuestra getState() {
		return estadoActual;
	}
	
	public IResultadoMuestra getResultadoActual() {
		return resultadoActual;
	}

	public Usuario getUsuarioDueño() {
		return usuarioDueño;
	}

	public Set<Opinion> getOpiniones() {
		return opiniones;
	}
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	private void setFoto(String foto) {
		this.foto = foto;
	}

	public void setState(IEstadoMuestra estadoActual) {
		this.estadoActual = estadoActual;
	}

	private void setResultadoActual(IResultadoMuestra resultadoActual) {
		this.resultadoActual = resultadoActual;
	}

	private void setUsuarioDueño(Usuario usuarioDueño) {
		this.usuarioDueño = usuarioDueño;
	}

	private void setOpiniones(Set<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	private void setFechaDeEmision(LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public void recibirOpinion(Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		this.getOpiniones().add(opinion);
		solicitarVerificacionDeMuestra(opinion);
	}
	
	public boolean opinoAlMenosUnExperto() {
		return this.getOpiniones()
				   .stream()
				   .anyMatch(o -> o.getUsuarioDueño().esUsuarioExperto()); 
	}
	
	public boolean opinoElUsuario(Usuario usuario) {
		return this.getOpiniones()
				   .stream()
				   .anyMatch(o -> o.getUsuarioDueño().equals(usuario));
	}
	
	public boolean esDueñoDeLaMuestra(Usuario usuario) {
		return this.getUsuarioDueño().equals(usuario);
	}
	
	public boolean seEmitioEnLosUltimos30Dias() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaActualRestada = fechaActual.minusDays(30);
		
		return(!(this.getFechaDeEmision().isBefore(fechaActualRestada)));
	}	
	
	public boolean seTomoDentroDeZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		return this.getUbicacion().estaDentroDeLaZonaDeCobertura(zonaDeCobertura, this);
	}
	
	public LocalDate obtenerFechaDeUltimaVotacion() {
		return (this.getOpiniones()
				   .stream()
				   .min((Opinion o1, Opinion o2)->o1.getFechaDeEmision()
				   .compareTo(o2.getFechaDeEmision()))
				   .get()).getFechaDeEmision();
	}
	
	public IResultadoMuestra obtenerTipoDeOpinionMayoritaria() {
		List<IResultadoMuestra> listaTiposDeOpiniones = getOpiniones().stream()
                .map(o -> o.getTipoDeOpinion())
                .collect(Collectors.toList());
		
		IResultadoMuestra opinionMasFrecuente = listaTiposDeOpiniones.stream()
                .max(Comparator.comparingInt(to -> Collections.frequency(listaTiposDeOpiniones, to)))
                .orElse(ResultadoMuestra.NODEFINIDA);
		
		return opinionMasFrecuente;
	}
	
	public IResultadoMuestra obtenerTipoDeOpinionMayoritariaDeExpertos() {
		List<IResultadoMuestra> listaTiposDeOpiniones = obtenerOpinionesDeExpertos().stream()
                .map(o -> o.getTipoDeOpinion())
                .collect(Collectors.toList());
		
		IResultadoMuestra opinionMasFrecuente = listaTiposDeOpiniones.stream()
                .max(Comparator.comparingInt(to -> Collections.frequency(listaTiposDeOpiniones, to)))
                .orElse(ResultadoMuestra.NODEFINIDA);
		
		return opinionMasFrecuente;
	}
	
	public List<Opinion> obtenerOpinionesDeExpertos() {
		return (this.getOpiniones().stream().filter(o -> o.fueEmitidaPorUnExperto()).toList());
	}
	
	public boolean existeOpinionEnExpertos(ITipoDeOpinion tipoDeOpinion) {
		return this.obtenerOpinionesDeExpertos()
				   .stream()
				   .anyMatch(o -> o.getTipoDeOpinion() == tipoDeOpinion);
	}
	
	public boolean hay2OpinionesQueCoinciden() {
		return this.getOpiniones().stream()
				   .collect(Collectors.groupingBy(Opinion::getTipoDeOpinion))
				   .values().stream().anyMatch(g->g.size() > 1);
	}
	
	public void solicitarVerificacionDeMuestra(Opinion opinionDeInicio) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		solicitarVerificacionDeEstadoActual(opinionDeInicio);
	}
	
	public void solicitarVerificacionDeEstadoActual(Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		this.getState().verificarMuestra(this, opinion);
	}
	
	public void solicitarVerificacionDeResultadoActual() {
		this.getState().verificarResultadoActualDeMuestra(this);
	}
	
	public void actualizarResultadoActual(IResultadoMuestra resultadoMuestra) {
		this.setResultadoActual(resultadoMuestra);
	}

	public NivelDeVerificacion obtenerNivelDeVerificacion() {
		return this.getState().obtenerNivelDeVerificacion();
	}
} 