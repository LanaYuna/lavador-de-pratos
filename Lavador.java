public class Lavador implements Runnable{
	
	private Escorredor escorredor;
	private volatile boolean done = false; // variável compartilhada
	
	public Lavador(Escorredor escorredor) {
		this.escorredor = escorredor;
	}
	
    public void run(){
    	
    	while(!done) {
    		
    		try {
	    		Prato prato = PratoFactory.criarPrato();
	    		
	    		int limite = 0;
	    		
	    		switch(prato.getSujeira()) {
	    			case BAIXO:
	    				limite = 30_000_000;
	    				break;
	    			case MEDIO:
	    				limite = 50_000_000;
	    				break;
	    			case ENGORDURADO:
	    				limite = 100_000_000;
	    				break;
	    		}
	    		
	    		for(int i=0; i < limite; i++) {
	    			// LIMPANDO PRATO
	    		}
	    		
	    		escorredor.colocarPrato(prato);
	    		
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
