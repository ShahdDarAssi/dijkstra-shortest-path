
public class MyArrayList<T> {

	private Object[] data;
    private int size;
	
    public MyArrayList() {
    	 this.data = (T[]) new Object[2];
    	 this.size = 0;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	 public void clear(){
	        size = 0;
	    }
	 
    public void add(T item) {
        if (size == data.length) {
        	resize(); 
        }
        data[size] = item;
		size++;
    }

    private void resize() {
        T[] newArr = (T[]) new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newArr[i] = (T) data[i];
        }
        data = newArr;
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }
}
