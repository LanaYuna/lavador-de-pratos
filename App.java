public class App {
	static int max = 20;
	
	Escorredor escorredor;
	Enxugador enxugador;
	Lavador lavador;
	
	int quantEnxugadores;
	int quantLavadores;
	
	public App() {
		this.escorredor = new Escorredor(max);
	}

    public static void main(String[] args){
    	
    	App app = new App();

    	app.iniciar(2, 3);
    	app.parar();
    	
    }
    
    private void iniciar(int q1, int q2) {
    	quantEnxugadores = q1;
    	quantLavadores = q2;
    	
    	for(int i=0; i < quantEnxugadores; i++) {
    		this.enxugador = new Enxugador(escorredor);
    		new Thread(enxugador).start();
    	}
    	
    	for(int i=0; i < quantLavadores; i++) {
    		this.lavador = new Lavador(escorredor);
    		new Thread(lavador).start();
    	}
    }
    
    private void parar() {
    	enxugador.parar();
		lavador.parar();
    }
}
 