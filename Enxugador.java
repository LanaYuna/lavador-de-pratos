import java.util.Random;
import java.util.logging.Logger;

public class Enxugador implements Runnable{
	
    private static final Logger logger = Logger.getLogger(Enxugador.class.getName());
	
	private Escorredor escorredor;
	private volatile boolean done = false;
	
	public Enxugador(Escorredor escorredor) {
		this.escorredor = escorredor;
	}

	@Override
	public void run() {
		
		while(!done && !Thread.currentThread().isInterrupted()) {
			
			try {
				Prato prato = escorredor.tirarPrato();
				
				logger.fine("Enxugador começou a enxugar o prato " + prato.getSerie());
				
				int random = (new Random().nextInt(8) + 3) * 10_000_000;
				
				for(int i=0; i < random; i++) {
					// ENXUGANDO PRATO
				}
				
				logger.fine("Enxugador terminou de enxugar o prato " + prato.getSerie());

                escorredor.pratoEnxugado();
			
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		
		logger.fine("Enxugador encerrado.");
	}

	public void parar() {
		this.done = true;
	}
		
}
