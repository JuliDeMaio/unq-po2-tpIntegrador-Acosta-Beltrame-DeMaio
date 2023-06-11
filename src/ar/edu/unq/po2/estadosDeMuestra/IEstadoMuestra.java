package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

	/**
	 * 
	 * @author Acosta, Federico
	 * @note Esta interfaz tiene como objetivo modelar la interfaz del State de Muestra, para todos los
	 * 			estados posibles que pueda tener la muestra.
	 * @see Muestra, EstadoMuestraOpinadaPorBasicos, EstadoMuestraOpinadaPorUnExperto, EstadoMuestraOpinadaPorExpertos, EstadoMuestraVerificada
	 * @DesignPattern State <State>
	 */
public interface IEstadoMuestra {

	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException;
	
	public void verificarResultadoActualDeMuestra(Muestra muestra);
	
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException;

	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) throws MuestraEstaVerificadaException;

	public NivelDeVerificacion obtenerNivelDeVerificacion();
}
