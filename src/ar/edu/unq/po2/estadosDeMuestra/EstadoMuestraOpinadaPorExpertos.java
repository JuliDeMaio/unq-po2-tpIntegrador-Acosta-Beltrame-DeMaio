package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.enums.ResultadoMuestra;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;


	/** 
	 * 
	 * @author De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar un ConcreteState de Muestra.
	 * @see Muestra, IEstadoMuestra, EstadoMuestraOpinadaPorBasicos, EstadoEstadoMuestraOpinadaPorUnExperto, EstadoMuestraVerificada
	 * @DesignPattern State <ConcreteStateC>
	 */
public class EstadoMuestraOpinadaPorExpertos implements IEstadoMuestra {

	/**
	 * @note Mensaje que inicial el double-dispatch que solicita al Usuario la indicacion de su categoria,
	 * 			para poder realizar la verificacion correspondiente.
	 */
	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		opinion.getUsuarioDueño().gestionarEstadoMuestraPara(this, muestra);
	}

	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		muestra.actualizarResultadoActual(ResultadoMuestra.NODEFINIDA);
	}

	/**
	 * @note Retorno del double-dispatch en caso de que el Usuario sea basico.
	 */
	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) throws MuestraEstaVotadaPorExpertosException {
		throw new MuestraEstaVotadaPorExpertosException();
	}

	/**
	 * @note Retorno del double-dispatch en caso de que el Usuario sea experto.
	 */
	@Override
	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) {
		if(muestra.hay2OpinionesQueCoinciden()) {
		muestra.setState(new EstadoMuestraVerificada());
		muestra.solicitarVerificacionDeResultadoActual();
		}
		muestra.actualizarResultadoActual(ResultadoMuestra.NODEFINIDA);
	}
	
	@Override
	public NivelDeVerificacion obtenerNivelDeVerificacion() {
		return NivelDeVerificacion.VOTADA;
	}
}
