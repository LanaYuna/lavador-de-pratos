public class Escorredor {
	
	FilaCircular fila = new FilaCircular(20);
	
	public synchronized void colocarPrato(Prato prato) {
		
		while(fila.isFull()) {
			wait();
		}
		
		if(fila.isFull()) {
			throw new IllegalStateException("Fila está cheia"); // log com quant pratos
		}
		
		fila.enqueue(prato);
	}
	
	public synchronized void tirarPrato() {
		
		while(fila.isEmpty()) {
			wait();
		}
		
		if(fila.isEmpty()) {
			throw new IllegalStateException("Fila está vazia"); // log com quant pratos
		}
		
		fila.dequeue();
	}
	
}
