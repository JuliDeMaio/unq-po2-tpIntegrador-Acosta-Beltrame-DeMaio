package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;

	/** 
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar un ConcreteState de Muestra. Al ser al Verificada,
	 * 			casi no tiene comportamiento, ya que no es modificable.
	 * @see Muestra, IEstadoMuestra, EstadoMuestraOpinadaPorBasicos, EstadoMuestraOpinadaPorExpertos, EstadoMuestraOpinadaPorUnExperto
	 * @DesignPattern State <ConcreteStateD>
	 */
public class EstadoMuestraVerificada implements IEstadoMuestra {

	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVerificadaException {
		throw new MuestraEstaVerificadaException();
	}

	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		muestra.actualizarResultadoActual(muestra.obtenerTipoDeOpinionMayoritariaDeExpertos());
	}

	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) throws MuestraEstaVerificadaException {
		throw new MuestraEstaVerificadaException();
	}

	@Override
	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) throws MuestraEstaVerificadaException {
		throw new MuestraEstaVerificadaException();
	}
	
	@Override
	public NivelDeVerificacion obtenerNivelDeVerificacion() {
		return NivelDeVerificacion.VERIFICADA;
	}
}