import java.util.Random;

public class Prato {
    private int serie;
    private Sujeira sujeira;
    private int contadorSerie = 0;
    
    public void prato(){
        int random = new Random().nextInt(100)+1;
        
        if(random <= 30) {
        	sujeira = Sujeira.BAIXO;
        } else if(random <= 60) {
        	sujeira = Sujeira.MEDIO;
        } else {
        	sujeira = Sujeira.ENGORDURADO;
        }
        
        serie = this.contadorSerie++;
    }

    public int getSerie(){
        return this.serie;
    }
    
    public Sujeira getSujeira(){
        return this.sujeira;
    }
}
