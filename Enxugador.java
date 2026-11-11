import java.util.Random;

public class Enxugador implements Runnable{
	
	private Escorredor escorredor;
	private volatile boolean done = false;
	
	public Enxugador(Escorredor escorredor) {
		this.escorredor = escorredor;
	}

	@Override
	public void run() {
		
		while(!done) {
			
			try {
				Prato prato = escorredor.tirarPrato();
				
				int random = (new Random().nextInt(8) + 3) * 10_000_000;
				
				for(int i=0; i < random; i++) {
					// ENXUGANDO PRATO
				}
			
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}

	public void parar() {
		this.done = true;
	}
	
	// USAR INTERRUPT PARA ENCERRAMENTO
	
}
