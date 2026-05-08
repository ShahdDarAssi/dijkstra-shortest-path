
public class PriorityQueue<T extends Comparable<T>> {

	private PQNode<T> front;
	private PQNode<T> rear;
	private int size;

	public PriorityQueue() {
		this.front = null;
		this.rear = null;
		this.size = 0;
	}

	public PQNode<T> getFront() {
		return front;
	}

	public void setFront(PQNode<T> front) {
		this.front = front;
	}

	public PQNode<T> getRear() {
		return rear;
	}

	public void setRear(PQNode<T> rear) {
		this.rear = rear;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T peek() {

		if (isEmpty()) {
			return null;
		}
		return front.getData();
	}

	public void enQueue(T data) {

		PQNode<T> newNode = new PQNode<>(data);

		if (isEmpty()) {
			front = rear = newNode;
			size++;
			return;
		}

		PQNode<T> current = front;
		PQNode<T> previous = null;

		while (current != null && current.getData().compareTo(data) < 0) {

			previous = current;
			current = current.getNext();
		}

		if (previous == null) {

			newNode.setNext(front);
			front = newNode;

		} else if (current == null) {

			rear.setNext(newNode);
			rear = newNode;

		} else {
			previous.setNext(newNode);
			newNode.setNext(current);
		}

		size++;
	}

	public T deQueue() {

		if (isEmpty()) {
			return null;
		}

		T data = front.getData();
		front = front.getNext();

		if (front == null) {
			rear = null;
		}

		size--;
		return data;
	}

}
