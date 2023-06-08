package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;

public class EstadoMuestraOpinadaPorUnExperto implements IEstadoMuestra {

	@Override
	public void verificarMuestra(Muestra muestra, Opinion opinion) {
		opinion.getUsuarioDueño().gestionarEstadoMuestraPara(this, muestra);
		
	}

	@Override
	public void verificarResultadoActualDeMuestra(Muestra muestra) {
		Opinion opinion = muestra.getOpinionesDeExperto().get(0);
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

	
}
