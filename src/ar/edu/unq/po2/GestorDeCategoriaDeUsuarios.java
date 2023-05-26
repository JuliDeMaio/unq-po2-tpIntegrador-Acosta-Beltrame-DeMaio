package ar.edu.unq.po2;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioBasico;
import ar.edu.unq.po2.estadosDeUsuario.EstadoUsuarioExpertoInterno;

public class GestorDeCategoriaDeUsuarios {
	
	private Set<Usuario> usuariosRegistrados;

	public GestorDeCategoriaDeUsuarios() {
		super();
		this.setUsuariosRegistrados(new HashSet<Usuario>());
	}

	public Set<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}

	private void setUsuariosRegistrados(Set<Usuario> usuariosRegistrados) {
		this.usuariosRegistrados = usuariosRegistrados;
	}

	public void agregarUsuario(Usuario usuario) {
		this.getUsuariosRegistrados().add(usuario);
	}

	public int cantidadDeUsuariosRegistrados() {
		return this.getUsuariosRegistrados().size();
	}

	public void eliminarUsuario(Usuario usuario) {
		this.getUsuariosRegistrados().remove(usuario);
	}

	public boolean cumpleConLosRequisitosDeUsuarioExperto(Usuario usuario) {
		return((usuario.cantidadDeMuestrasEmitidasEnUltimos30Dias() > 10) 
			   &&
			   (usuario.cantidadDeOpinionesEmitidasEnUltimos30Dias() > 20));
	}

	public void actualizarCategoriaDeUsuario(Usuario usuario) {
		if (usuario.esUsuarioBasico()) {
			verificarSiEsAptoParaCategoriaExperto(usuario);
		} 
		else if (usuario.esUsuarioExpertoInterno()) {
			verificarSiEsAptoParaSeguirComoCategoriaExperto(usuario);
		}
	}
	
	public void verificarSiEsAptoParaCategoriaExperto(Usuario usuario) {
		if(cumpleConLosRequisitosDeUsuarioExperto(usuario)) {
			usuario.setState(new EstadoUsuarioExpertoInterno());
		}
	}

	public void verificarSiEsAptoParaSeguirComoCategoriaExperto(Usuario usuario) {
		if (!(cumpleConLosRequisitosDeUsuarioExperto(usuario))) {
			usuario.setState(new EstadoUsuarioBasico());
		}
	}

	public void actualizarCategoriasDeUsuarios() {
		this.getUsuariosRegistrados()
		.stream()
		.forEach(u -> actualizarCategoriaDeUsuario(u));
	}
}
