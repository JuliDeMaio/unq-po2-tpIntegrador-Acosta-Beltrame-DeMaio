package ar.edu.unq.po2.estadosDeMuestra;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Opinion;

public interface IEstadoMuestra {

	public void verificarMuestra(Muestra muestra, Opinion opinion);
	
	public void verificarResultadoActualDeMuestra(Muestra muestra);
	
	public void realizarVerificacionParaUsuarioBasico(Muestra muestra);

	public void realizarVerificacionParaUsuarioExperto(Muestra muestra);

}
