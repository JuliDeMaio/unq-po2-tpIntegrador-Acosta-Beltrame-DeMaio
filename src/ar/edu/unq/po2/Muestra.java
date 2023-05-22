package ar.edu.unq.po2;

	/**
	 * @author Acosta, Federico
	 * @see Usuario, Opinion, TipoDeOpinion, Ubicacion
	 * 
	 */
public class Muestra {

		public boolean esVerificada() {
			/*
			this.getEsVerificada();
			*/
		}

		public boolean opinoAlMenosUnExperto() {
			/*
		     getOpiniones()
			.stream()
			.anySatisfy(o -> o.usuarioDueño.esExperto())) 
			*/
		}

		public boolean opinoElUsuario(Usuario usuario) {
			/*
			getOpiniones()
		    .stream()
		    .anySatisfy(o -> o.usuarioDueño.equals(usuario))
		    */
		}
		
		public boolean esDueñoDeLaMuestra(Usuario usuario) {
			/*
			this.getUsuarioDueño().equals(usuario);
			*/
		}

		public void publicarOpinion(Opinion opinion) {
			/*
			 this.getOpiniones.add(opinion)
			 */
			
		}
}
