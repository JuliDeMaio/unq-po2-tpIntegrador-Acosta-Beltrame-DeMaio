package ar.edu.unq.po2;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.filtros.Filtro;
import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

	/**
	 * 
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 *
	 * @note Esta clase tiene como objetivo modelar la AppWeb del sistema, utilizando el patron Singleton. 
	 * @DesignPattern Singleton
	 */
public class AppWeb {
	
	private Set<Muestra> muestras;
	private Set<Usuario> usuariosRegistrados;
	public static AppWeb instance;

	public AppWeb() {
	    this.setMuestras(new HashSet<Muestra>());
	    this.setUsuariosRegistrados(new HashSet<Usuario>());
	}
	
	public Set<Muestra> getMuestras() {
		return this.muestras;
	}
	
	public Set<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}
	
	public static AppWeb getInstance() {
        if (instance == null) {
            instance = new AppWeb();
        }
        return instance;
    }

	private void setUsuariosRegistrados(Set<Usuario> usuariosRegistrados) {
		this.usuariosRegistrados = usuariosRegistrados;
	}
	
	private void setMuestras(Set<Muestra> muestras) {
		this.muestras = muestras;
	}

	public void guardarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);
	}
	
	public void eliminarMuestra(Muestra muestra) {
		this.getMuestras().remove(muestra);
	}

	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras, Filtro filtro) {
		return (filtro.filtrarMuestras(muestras));
	}

	public Set<Muestra> muestrasDeZonaDeCobertura(Set<Muestra> muestras, ZonaDeCobertura zonaDeCobertura) {
		return Set.copyOf(muestras.stream()
								  .filter(m -> m.seTomoDentroDeZonaDeCobertura(zonaDeCobertura))
								  .toList());
	}
	
	public void agregarUsuario(Usuario usuario) {
		this.getUsuariosRegistrados().add(usuario);
	}

	public void eliminarUsuario(Usuario usuario) {
		this.getUsuariosRegistrados().remove(usuario);
	}
	
	public int cantidadDeUsuariosRegistrados() {
		return this.getUsuariosRegistrados().size();
	}
	
	public void actualizarCategoriasDeUsuarios() {
		this.getUsuariosRegistrados()
		.stream()
		.forEach(u -> actualizarCategoriaDeUsuario(u));
	}
	
	public void actualizarCategoriaDeUsuario(Usuario usuario) {
		usuario.actualizarCategoria();
	}

	public Set<Muestra> muestrasDeUsuario(Usuario usuario) {
		return Set.copyOf(
				this.getMuestras()
				.stream()
				.filter(m -> m.esDueñoDeLaMuestra(usuario))
				.toList());
	}
	
	/**
	 * @note mensaje para reiniciar el sistema a 0, util en los Tests. 
	 */
	public void reiniciarSistema() {
		getMuestras().clear();
		getUsuariosRegistrados().clear();
	}
}