import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Prato {
    private static final AtomicInteger contadorSerie = new AtomicInteger(0);
    private static final Random random = new Random();
	
    private final int serie;
    private final Sujeira sujeira;
    
    public Prato(){
        int sorteio = random.nextInt(100)+1;
        
        if(sorteio <= 30) {
        	sujeira = Sujeira.BAIXO;
        } else if(sorteio <= 90) {
        	sujeira = Sujeira.MEDIO;
        } else {
        	sujeira = Sujeira.ENGORDURADO;
        }
        
        serie = contadorSerie.incrementAndGet();
    }

    public int getSerie(){
        return this.serie;
    }
    
    public Sujeira getSujeira(){
        return this.sujeira;
    }
}
