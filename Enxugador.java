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
			Prato prato = escorredor.tirarPrato();
			
			int random = new Random().nextInt();
			
			
			notify(); // ao tirar prato, notify() p/ colocar prato novo (escorredor cheio)
		}
	}

	public void parar() {
		this.done = true;
	}
	
}
