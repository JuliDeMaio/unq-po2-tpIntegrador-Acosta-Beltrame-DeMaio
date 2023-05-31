package ar.edu.unq.po2.filtros;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.Muestra;

public class And implements IBusqueda {
	
	private IBusqueda filtro1;
	private IBusqueda filtro2;

	public And(IBusqueda filtro1, IBusqueda filtro2) {
		super();
		this.setFiltro1(filtro1);
		this.setFiltro2(filtro2);
	}

	public IBusqueda getFiltro1() {
		return filtro1;
	}

	public IBusqueda getFiltro2() {
		return filtro2;
	}

	private void setFiltro1(IBusqueda filtro1) {
		this.filtro1 = filtro1;
	}

	private void setFiltro2(IBusqueda filtro2) {
		this.filtro2 = filtro2;
	}

	@Override
	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras) {
		Set<Muestra> resultadosFiltro1 = filtro1.filtrarMuestras(muestras);
		Set<Muestra> resultadosFiltro2 = filtro2.filtrarMuestras(muestras);
		Set<Muestra> setResultante = new HashSet<>(resultadosFiltro1);
		setResultante.retainAll(resultadosFiltro2);
		return(setResultante);
	}
}
