package ar.edu.unq.po2;

public interface ISubject {
	
	public void addObserver(IZonaDeCoberturaListener listener);
	public void removeObserver(IZonaDeCoberturaListener listener);
	public void notificarMuestraSubida(Muestra muestra);
	public void notificarMuestraValidada(Muestra muestra);
}
