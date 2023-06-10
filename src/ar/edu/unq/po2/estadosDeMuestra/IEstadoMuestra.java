package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;
import ar.edu.unq.po2.enums.NivelDeVerificacion;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVerificadaException;
import ar.edu.unq.po2.muestraExceptions.MuestraEstaVotadaPorExpertosException;

public interface IEstadoMuestra {

	public void verificarMuestra(Muestra muestra, Opinion opinion) throws MuestraEstaVerificadaException, MuestraEstaVotadaPorExpertosException;
	
	public void verificarResultadoActualDeMuestra(Muestra muestra);
	
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra) throws MuestraEstaVotadaPorExpertosException, MuestraEstaVerificadaException;

	public void realizarVerificacionParaUsuarioExperto(Muestra muestra) throws MuestraEstaVerificadaException;

	public NivelDeVerificacion obtenerNivelDeVerificacion();
}
