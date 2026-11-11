import java.util.logging.Logger;

public class Lavador implements Runnable{
	
	private static final Logger logger = Logger.getLogger(Lavador.class.getName());
	
	private Escorredor escorredor;
	private volatile boolean done = false; 
	
	public Lavador(Escorredor escorredor) {
		this.escorredor = escorredor;
	}
	
    public void run(){
    	
    	   while (!done && !Thread.currentThread().isInterrupted()) {

               try {
                   escorredor.aguardarEspaco();

                   if (done || Thread.currentThread().isInterrupted()) {
                       break;
                   }

                   Prato prato = PratoFactory.criarPrato();

                   logger.fine("Lavador criou o prato " + prato.getSerie()+ " - sujeira: " + prato.getSujeira());

                   int limite;

                   switch (prato.getSujeira()) {
                       case BAIXO:
                           limite = 30_000_000;
                           break;

                       case MEDIO:
                           limite = 50_000_000;
                           break;

                       case ENGORDURADO:
                           limite = 100_000_000;
                           break;

                       default:
                           throw new IllegalStateException("Nível de sujeira desconhecido");
                   }

                   for (long i = 0; i < limite && !done; i++) {
                       // LAVANDO PRATO
                   }

                   if (done || Thread.currentThread().isInterrupted()) {
                       break;
                   }

                   logger.fine("Lavador terminou de lavar o prato "
                           + prato.getSerie());

                   escorredor.colocarPrato(prato);

               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   break;
               }
           }

           logger.fine("Lavador encerrado.");
    }
    
    public void parar() {
    	this.done = true;
    }

}
