
public class FilaCircular {
	private Prato[] fila;
	private int front;
	private int rear;
	private int quantidade;

	public FilaCircular(int tamanho) {
		this.fila = new Prato[tamanho];
		this.front = 0;
		this.rear = 0;
		this.quantidade = 0;
	}
	
	public void enqueue(Prato prato) {
		
		if(fila.length == quantidade) {
			throw new IllegalStateException("Fila cheia");
		}
		
		fila[rear] = prato;
		rear = (rear + 1) % fila.length;
		quantidade++;
		
	}
	
	public Prato dequeue() {
		
		if(quantidade == 0) {
			throw new IllegalStateException("Fila vazia");
		}
		
		Prato elemento = fila[front];
		front = (front + 1) % fila.length;
		quantidade --;
		
		return elemento;
	}
	
	public boolean isEmpty() {
		return quantidade == 0;
	}
	
	public boolean isFull() {
		return fila.length == quantidade;
	}
	
	public int size() {
		return quantidade;
	}
	
}
