package ar.edu.unq.po2;

public class GestorDeOpiniones {

	public void verificarOpinionPara(Opinion opinionAEmitir, Usuario usuario) {
		
		verificarQueSeaOpinionUnica(opinionAEmitir, usuario);
		verificarQueNoSeaElDueñoDeLaMuestra(opinionAEmitir, usuario);
		verificarNivelDeVerificacion(opinionAEmitir, usuario);
	}
}
