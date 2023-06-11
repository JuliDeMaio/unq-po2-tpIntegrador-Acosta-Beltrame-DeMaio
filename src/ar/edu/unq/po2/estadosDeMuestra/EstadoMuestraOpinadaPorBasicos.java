package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.IResultadoMuestra;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;
	
	/** 
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar un ConcreteState de Muestra.
	 * @see Muestra, IEstadoMuestra, EstadoMuestraOpinadaPorUnExperto, EstadoMuestraOpinadaPorExpertos, EstadoMuestraVerificada
	 * @DesignPattern State <ConcreteStateA>
	 */
public class EstadoMuestraOpinadaPorBasicos implements IEstadoMuestra {

	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) {
		this.verificarResultadoActualDeMuestra(muestra);
	}
	
	/**
	 * @note Mensaje que inicial el double-dispatch que solicita al Usuario la indicacion de su categoria,
	 * 			para poder realizar la verificacion correspondiente.
	 */
	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		opinion.getUsuarioDueño().gestionarEstadoMuestraPara(this, muestra);
	}
	
	/**
	 * @note Retorno del double-dispatch en caso de que el Usuario sea basico.
	 */
	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		IResultadoMuestra opinionMasFrecuente = muestra.obtenerTipoDeOpinionMayoritaria();
		muestra.actualizarResultadoActual(opinionMasFrecuente);
		
	}

	/**
	 * @note Retorno del double-dispatch en caso de que el Usuario sea experto.
	 */
	@Override
	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) {
		muestra.setState(new EstadoMuestraOpinadaPorUnExperto());
		muestra.solicitarVerificacionDeResultadoActual();
		
	}

	@Override
	public NivelDeVerificacion obtenerNivelDeVerificacion() {
		return NivelDeVerificacion.VOTADA;
	}
}
