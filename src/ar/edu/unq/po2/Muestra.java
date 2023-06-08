package ar.edu.unq.po2;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.po2.estadosDeMuestra.EstadoMuestraOpinadaPorUnExperto;
import ar.edu.unq.po2.estadosDeMuestra.IEstadoMuestra;

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

		public LocalDate getFechaDeEmision() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean seEmitioEnLosUltimos30Dias() {
			// TODO Auto-generated method stub
			return null;
		}

		public Ubicacion getUbicacion() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getResultadoActual() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean seTomoDentroDeZonaDeCobertura(ZonaDeCobertura zonaDeCobertura1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getState() {
			// TODO Auto-generated method stub
			return null;
		}

		public LocalDate obtenerFechaDeUltimaVotacion() {
			// Hacer con Streams de Min, obtener la fecha mas reciente y no hacer SortedCollection.
			return null;
		}

		public void actualizarResultadoActual(TipoDeOpinion opinionMasFrecuente) {
			// TODO Auto-generated method stub
			
		}

		public TipoDeOpinion obtenerTipoDeOpinionMayoritariaDeExpertos() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setState(IEstadoMuestra estadoMuestra) {
			// TODO Auto-generated method stub
			
		}

		public void solicitarVerificacionDeResultadoActual() {
			// TODO Auto-generated method stub
			
		}

		public TipoDeOpinion obtenerTipoDeOpinionMayoritaria() {
			// TODO Auto-generated method stub
			return null;
		}

		public List<Opinion> getOpinionesDeExperto() {
			// TODO Auto-generated method stub
			return null;
		}
}
