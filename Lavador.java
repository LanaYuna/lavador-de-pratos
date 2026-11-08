public class Lavador implements Runnable{
	
	private Escorredor escorredor;
	private volatile boolean done = false; // variável compartilhada
	
	public Lavador(Escorredor escorredor) {
		this.escorredor = escorredor;
	}
	
    public void run(){
    	
    	while(!done) {
    		
    		Prato prato = PratoFactory.criarPrato();
    		
    		if(prato.getSujeira() == Sujeira.BAIXO) {
    			for(int i=0; i < 30000000; i++) {
    				
    			}
    		} else if(prato.getSujeira() == Sujeira.MEDIO) {
    			for(int i=0; i < 50000000; i++) {
    				
    			}
    		} else {
    			for(int i=0; i < 100000000; i++) {
    				
    			}
    		}
    		
    		escorredor.colocarPrato(prato);
    		notify();   // colocar prato, notify() p/ enxugador
    	}
    
    }
    
    public void parar() {
    	this.done = true;
    }
    
}
