package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.enums.ResultadoMuestra;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

public class EstadoMuestraOpinadaPorExpertos implements IEstadoMuestra {

	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		opinion.getUsuarioDueño().gestionarEstadoMuestraPara(this, muestra);
	}

	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		muestra.actualizarResultadoActual(ResultadoMuestra.NODEFINIDA);
	}

	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) throws MuestraEstaVotadaPorExpertosException {
		throw new MuestraEstaVotadaPorExpertosException();
	}

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
