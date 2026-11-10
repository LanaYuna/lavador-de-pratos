public class App {

    public static void main(String[] args){
    	Escorredor escorredor = new Escorredor(20);
    	Enxugador enxugador = new Enxugador(escorredor);
    	Lavador lavador = new Lavador(escorredor);
    	
    	int quantidadeEnxugadores = 2;
    	int quantidadeLavadores = 2;
    	
    	for(int i=0; i < quantidadeEnxugadores; i++) {
    		
    		new Thread(enxugador);
    		
//    		TrabalhadorFactory.criarEnxugador(escorredor).start();
    	}
    	
    	for(int i=0; i < quantidadeLavadores; i++) {
    		new Thread(lavador);
//    		TrabalhadorFactory.criarLavador(escorredor).start();
    	}
    	
    	enxugador.parar();
		lavador.parar();
    	
    }
    
}
 