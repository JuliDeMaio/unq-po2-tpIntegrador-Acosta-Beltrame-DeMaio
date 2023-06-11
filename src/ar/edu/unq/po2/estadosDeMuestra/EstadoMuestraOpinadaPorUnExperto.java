package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;


	/** 
	 * 
	 * @author Beltrame, Franco
	 * 		   De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar un ConcreteState de Muestra.
	 * @see Muestra, IEstadoMuestra, EstadoMuestraOpinadaPorBasicos, EstadoMuestraOpinadaPorExpertos, EstadoMuestraVerificada
	 * @DesignPattern State <ConcreteStateB>
	 */
public class EstadoMuestraOpinadaPorUnExperto implements IEstadoMuestra {

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
		Opinion opinion = muestra.obtenerOpinionesDeExpertos().get(0);
		muestra.actualizarResultadoActual(opinion.getTipoDeOpinion());
	}

	/**
	 * @note Retorno del double-dispatch en caso de que el Usuario sea basico.
	 */
	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) {
		System.out.println("Error: No deberia llegar aca, hay una excepcion previa");
		
	}

	/**
	 * @note Retorno del double-dispatch en caso de que el Usuario sea experto.
	 */
	@Override
	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) {
		muestra.setState(new EstadoMuestraOpinadaPorExpertos());
		muestra.solicitarVerificacionDeResultadoActual();
	}
	
	@Override
	public NivelDeVerificacion obtenerNivelDeVerificacion() {
		return NivelDeVerificacion.VOTADA;
	}
}
