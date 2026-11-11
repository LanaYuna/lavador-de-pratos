import java.util.logging.Logger;

public class Escorredor {

    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());

    private final FilaCircular fila;

    private final Object monitorLavador = new Object();
    private final Object monitorEnxugador = new Object();
    private final Object monitorConclusao = new Object();

    private int emProcessamento = 0; 

    public Escorredor(int capacidade) {
        fila = new FilaCircular(capacidade);
        logger.info("Escorredor vazio. Quantidade: 0");
    }

    private synchronized boolean estaCheio() {
        return fila.isFull();
    }

    private synchronized boolean estaVazio() {
        return fila.isEmpty();
    }

    public void colocarPrato(Prato prato) throws InterruptedException {

        synchronized (monitorLavador) {

            while (estaCheio()) {
                logger.fine("Lavador aguardando espaço no escorredor.");
                monitorLavador.wait();
            }

            boolean estavaVazio;

            synchronized (this) {
                estavaVazio = fila.isEmpty();

                fila.enqueue(prato);

                logger.fine("Prato " + prato.getSerie() + " colocado. Quantidade: " + fila.size());

                if (fila.isFull()) {
                    logger.info("Escorredor cheio. Quantidade: " + fila.size());
                }
            }

            if (estavaVazio) {
                synchronized (monitorEnxugador) {
                    monitorEnxugador.notify();
                }
            }
        }
    }

    public Prato tirarPrato() throws InterruptedException {

        synchronized (monitorEnxugador) {

            while (estaVazio()) {
                logger.fine("Enxugador aguardando prato.");
                monitorEnxugador.wait();
            }

            Prato prato;
            boolean estavaCheio;

            synchronized (this) {
                estavaCheio = fila.isFull();

                prato = fila.dequeue();
                emProcessamento++;

                logger.fine("Prato " + prato.getSerie()+ " retirado. Quantidade: " + fila.size());

                if (fila.isEmpty()) {
                    logger.info("Escorredor vazio. Quantidade: " + fila.size());
                }
            }

            if (estavaCheio) {
                synchronized (monitorLavador) {
                    monitorLavador.notify();
                }
            }

            return prato;
        }
    }
    
    public void aguardarEspaco() throws InterruptedException {
        synchronized (monitorLavador) {
            while (estaCheio()) {
                logger.fine("Lavador aguardando espaço.");
                monitorLavador.wait();
            }
        }
    }

    public void pratoEnxugado() {
        synchronized (monitorConclusao) {

            boolean terminou;

            synchronized (this) {
                emProcessamento--;
                terminou = fila.isEmpty() && emProcessamento == 0;
            }

            if (terminou) {
                monitorConclusao.notify();
            }
        }
    }

    public void aguardarConclusao() throws InterruptedException {

        synchronized (monitorConclusao) {

            while (true) {

                synchronized (this) {
                    if (fila.isEmpty() && emProcessamento == 0) {
                        return;
                    }
                }

                monitorConclusao.wait();
            }
        }
    }
 
}
