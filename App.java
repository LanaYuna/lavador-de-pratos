import java.util.logging.Logger;

public class App {
	
	private static final Logger logger = Logger.getLogger(Escorredor.class.getName());

    private static final int CAPACIDADE = 20;

    private final Escorredor escorredor;

    private Lavador[] lavadores;
    private Enxugador[] enxugadores;

    private Thread[] threadsLavadores;
    private Thread[] threadsEnxugadores;

    public App() {
        escorredor = new Escorredor(CAPACIDADE);
    }

    public static void main(String[] args) {

        App app = new App();

        try {
            app.iniciar(2, 3);

            Thread.sleep(10_000);

            app.parar();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void iniciar(int quantidadeEnxugadores, int quantidadeLavadores) {

        if (quantidadeEnxugadores < 1 || quantidadeLavadores < 1) {
            throw new IllegalArgumentException("É necessário ter pelo menos um lavador e um enxugador.");
        }

        lavadores = new Lavador[quantidadeLavadores];
        enxugadores = new Enxugador[quantidadeEnxugadores];

        threadsLavadores = new Thread[quantidadeLavadores];
        threadsEnxugadores = new Thread[quantidadeEnxugadores];

        for (int i = 0; i < quantidadeEnxugadores; i++) {

            enxugadores[i] = new Enxugador(escorredor);

            threadsEnxugadores[i] = new Thread(enxugadores[i], "Enxugador-" + (i + 1));

            threadsEnxugadores[i].start();
        }

        for (int i = 0; i < quantidadeLavadores; i++) {

            lavadores[i] = new Lavador(escorredor);

            threadsLavadores[i] = new Thread(lavadores[i], "Lavador-" + (i + 1));

            threadsLavadores[i].start();
        }
    }

    public void parar() throws InterruptedException {

        for (int i = 0; i < lavadores.length; i++) {
            lavadores[i].parar();
            threadsLavadores[i].interrupt(); // Interrompe uma espera bloqueante, como wait().
        }

        for (Thread thread : threadsLavadores) {
            thread.join();
        }

        escorredor.aguardarConclusao();

        for (int i = 0; i < enxugadores.length; i++) {
            enxugadores[i].parar();
            threadsEnxugadores[i].interrupt();
        }

        for (Thread thread : threadsEnxugadores) {
            thread.join();
        }
        
        logger.info("Fim do programa");
    }
}