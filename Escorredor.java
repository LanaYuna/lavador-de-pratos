import java.util.logging.Logger;

public class Escorredor {

//	private static Logger logger = Logger.getLogger(Class.class.getSimpleName());
	private FilaCircular fila;
	
	public Escorredor(int capacidade){
		fila = new FilaCircular(capacidade);
	}
	
	public synchronized void colocarPrato(Prato prato) throws InterruptedException{
		
		while(fila.isFull()) {
			wait();
		}
		
		if(fila.isFull()) {
//			logger.info("Fila está cheia");
			throw new IllegalStateException("Fila está cheia"); // log com quant pratos
		}

		fila.enqueue(prato);
		notify();   // notify() p/ enxugador
	}
	
	public synchronized Prato tirarPrato() throws InterruptedException {
		
		while(fila.isEmpty()) {
			wait();
		}
		
		if(fila.isEmpty()) {
//			logger.info("Fila está vazia");
			throw new IllegalStateException("Fila está vazia"); // log com quant pratos
		}
		
		Prato prato = fila.dequeue();
		notify();   // notify() p/ colocar prato novo (escorredor cheio)
		
		return prato;
	}
	
}
