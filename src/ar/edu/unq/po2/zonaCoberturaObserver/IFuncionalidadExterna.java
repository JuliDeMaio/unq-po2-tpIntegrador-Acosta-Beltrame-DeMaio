package ar.edu.unq.po2.zonaCoberturaObserver;

import ar.edu.unq.po2.Muestra;

public interface IFuncionalidadExterna {
	
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zonaDeCobertura, Muestra muestra);
}
