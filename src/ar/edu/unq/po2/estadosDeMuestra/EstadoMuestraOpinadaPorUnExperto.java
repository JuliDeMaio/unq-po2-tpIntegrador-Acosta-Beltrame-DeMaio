package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

public class EstadoMuestraOpinadaPorUnExperto implements IEstadoMuestra {

	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException {
		opinion.getUsuarioDueño().gestionarEstadoMuestraPara(this, muestra);
		
	}

	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		Opinion opinion = muestra.obtenerOpinionesDeExpertos().get(0);
		muestra.actualizarResultadoActual(opinion.getTipoDeOpinion());
	}

	@Override
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) {
		System.out.println("Error: No deberia llegar aca, hay una excepcion previa");
		
	}

	@Override
	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) {
		muestra.setState(new EstadoMuestraOpinadaPorExpertos());
		muestra.solicitarVerificacionDeResultadoActual();
	}

	@Override
	public boolean esMuestraVerificada() {
		return false;
	}
}
