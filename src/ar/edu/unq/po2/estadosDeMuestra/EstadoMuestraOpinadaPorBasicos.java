package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.IResultadoMuestra;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

public class EstadoMuestraOpinadaPorBasicos implements IEstadoMuestra {

	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) {
		this.verificarResultadoActualDeMuestra(muestra);
		
	}
	
	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		opinion.getUsuarioDueño().gestionarEstadoMuestraPara(this, muestra);
	}
	
		
	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		IResultadoMuestra opinionMasFrecuente = muestra.obtenerTipoDeOpinionMayoritaria();
		muestra.actualizarResultadoActual(opinionMasFrecuente);
		
	}

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
