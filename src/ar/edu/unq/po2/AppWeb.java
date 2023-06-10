package ar.edu.unq.po2;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.filtros.Filtro;

public class AppWeb {
	
	private Set<Muestra> muestras;
	private Set<Usuario> usuariosRegistrados;

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
}