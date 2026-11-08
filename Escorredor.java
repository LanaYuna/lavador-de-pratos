import java.util.logging.Logger;

public class Escorredor {

//	private static Logger logger = Logger.getLogger(Class.class.getSimpleName());
	
	public Escorredor(){
		
	}
	
	FilaCircular fila = new FilaCircular(20);
	
	public synchronized void colocarPrato(Prato prato) {
		
		while(fila.isFull()) {
			wait();
		}
		
		if(fila.isFull()) {
//			logger.info("Fila está cheia");
			throw new IllegalStateException("Fila está cheia"); // log com quant pratos
		}
		
		fila.enqueue(prato);
	}
	
	public synchronized void tirarPrato() {
		
		while(fila.isEmpty()) {
			wait();
		}
		
		if(fila.isEmpty()) {
//			logger.info("Fila está vazia");
			throw new IllegalStateException("Fila está vazia"); // log com quant pratos
		}
		
		fila.dequeue();
	}
	
}
