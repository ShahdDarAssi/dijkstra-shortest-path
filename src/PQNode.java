public class PQNode<T> {

	private PQNode<T> next;
	private T data;

	public PQNode(T data) {
		this.next = null;
		this.data = data;
	}

	public PQNode<T> getNext() {
		return next;
	}

	public void setNext(PQNode<T> next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
