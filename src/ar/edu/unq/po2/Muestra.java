package ar.edu.unq.po2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	 * 		   Beltrame, Franco
	 * 		   De Maio, Julian Daniel
	 * @see Usuario, Opinion, TipoDeOpinion, Ubicacion
	 * @note Esta clase tiene como objetivo modelar una Muestra del sistema.
	 */
public class Muestra {
	
	private String foto;
	private IEstadoMuestra estadoActual;
	private IResultadoMuestra resultadoActual;
	private Usuario usuarioDueño;
	private List<Opinion> opiniones;
	private Ubicacion ubicacion;
	private LocalDate fechaDeEmision;
	
	/**
	 * @note La Muestra se inicializa sin opiniones y con un resultado no definido, en un estado inicial. 
	 * 			A partir del cual se le indica que reciba una Opinion (la del Usuario dueño), lo cual desencadena 
	 * 			la asignacion de IEstadoMuestra y ResultadoMuestra.
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
		this.setResultadoActual(opinionDeInicio.getTipoDeOpinion());
		this.setUsuarioDueño(usuarioDueño);
		this.setOpiniones(new ArrayList<Opinion>());
		this.setUbicacion(ubicacion);
		this.setFechaDeEmision(LocalDate.now());
		this.recibirOpinion(opinionDeInicio);
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

	public List<Opinion> getOpiniones() {
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

	private void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	private void setFechaDeEmision(LocalDate fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public void guardarOpinion(Opinion opinion) {
		this.getOpiniones().add(opinion);
	}
	
	public void eliminarOpinion(Opinion opinion) {
		this.getOpiniones().remove(opinion);
	}
	
	public void recibirOpinion(Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException {
		guardarOpinion(opinion);
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
		return getOpiniones().stream()
			    	.map(o -> o.getFechaDeEmision())
			    	.max(LocalDate::compareTo)
			    	.orElse(getOpiniones().isEmpty() ? null : getOpiniones().get(0).getFechaDeEmision());
	}
	
	public List<Opinion> obtenerOpinionesDeExpertos() {
		return (this.getOpiniones().stream().filter(o -> o.fueEmitidaPorUnExperto()).toList());
	}
	
	public IResultadoMuestra obtenerTipoDeOpinionMayoritaria() {
		return obtenerTipoDeOpinionMayoritariaDe(obtenerTiposDeOpiniones(getOpiniones()));
	}
	
	public IResultadoMuestra obtenerTipoDeOpinionMayoritariaDeExpertos() {
		return obtenerTipoDeOpinionMayoritariaDe(obtenerTiposDeOpiniones(obtenerOpinionesDeExpertos()));
	}
	
	public List<IResultadoMuestra> obtenerTiposDeOpiniones(List<Opinion> opiniones){
		return opiniones.stream() // Convierte las Opiniones a TiposDeOpinion
                .map(o -> o.getTipoDeOpinion())
                .collect(Collectors.toList());
	}
	
	public IResultadoMuestra obtenerTipoDeOpinionMayoritariaDe(List<IResultadoMuestra> listaTiposDeOpiniones) {
		Map<IResultadoMuestra, Long> ocurrenciasTiposDeOpinion = listaTiposDeOpiniones.stream() // Compara las ocurrencias y las introduce a un Map<TipoDeOpinion, Ocurrencia>.
                .collect(Collectors.groupingBy(to -> to, Collectors.counting()));

        Optional<Map.Entry<IResultadoMuestra, Long>> maxEntry = ocurrenciasTiposDeOpinion.entrySet() // Busca el maximo, en caso de que este vacia la lista es Optional.
                .stream()
                .max(Map.Entry.comparingByValue());

        IResultadoMuestra opinionMasFrecuente = getOpiniones().get(0).getTipoDeOpinion(); // Por defecto, la primera es la maxima.
         
        Map.Entry<IResultadoMuestra, Long> entry = maxEntry.get();
        long maxCount = entry.getValue();

        List<Map.Entry<IResultadoMuestra, Long>> empates = ocurrenciasTiposDeOpinion.entrySet() // Busca si hay empate con el resto de TiposDeOpinion.
        				.stream()
        				.filter(e -> e.getValue() == maxCount)
        				.collect(Collectors.toList());

        if (empates.size() > 1) { // Si hay Empate, asigna NODEFINIDA. Si no, asigna el maximo.
        	opinionMasFrecuente = ResultadoMuestra.NODEFINIDA; // En caso de Empate, retornar NoDefinida.
        } else {
        	opinionMasFrecuente = entry.getKey(); // En caso de haber maximo, retornarlo.
        }

		return opinionMasFrecuente;
	}
	
	public boolean existeOpinionEnExpertos(ITipoDeOpinion tipoDeOpinion) {
		return this.obtenerOpinionesDeExpertos()
				   .stream()
				   .anyMatch(o -> o.getTipoDeOpinion() == tipoDeOpinion);
	}
	
	public boolean hay2OpinionesDeExpertosQueCoinciden() {
		return this.obtenerOpinionesDeExpertos().stream()
				   .collect(Collectors.groupingBy(o -> o.getTipoDeOpinion()))
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
	
	public List<Opinion> obtenerOpinionesDeUsuario(Usuario usuario) {
		return getOpiniones().stream().filter(o -> o.fueEmitidaPorUsuario(usuario)).toList();
	}
} 