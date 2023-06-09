package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;

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

}
