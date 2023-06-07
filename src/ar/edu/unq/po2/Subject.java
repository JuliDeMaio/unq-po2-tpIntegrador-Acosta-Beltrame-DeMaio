package ar.edu.unq.po2;

public interface Subject {
	
	public void addObserver(ZonaDeCoberturaListener listener);
	public void removeObserver(ZonaDeCoberturaListener listener);
	public void notificarMuestraSubida(Muestra muestra);
	public void notificarMuestraValidada(Muestra muestra);
}
