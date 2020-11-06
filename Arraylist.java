@SuppressWarnings("unchecked")
public class Arraylist<T> implements MyList<T> {
	private int size;
	private T[] arr;
	
	public Arraylist() {
		arr = ((T[]) new Object[10]);
	}
	public int size() {
		return size;
	}
	public T get(int pos) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("Invalid position");
		}
		return arr[pos];
	}
	public boolean add(T item) {
		if (size == arr.length)
			grow_array();
		arr[size++] = item;
		return true;
	}
	public void grow_array() {
		T [] new_arr = (T[]) new Object [arr.length*2];
		for(int i = 0; i < arr.length; i++) {
			new_arr[i] = arr[i];
		}
		arr = new_arr;
	}
	public void add(T item, int pos) {
		if (size == arr.length)
			grow_array();
		for (int i = size;i > pos; i--) {
			arr[i] = arr[i-1];
		}
		arr[pos] = item;
		++size;
	}
	public T remove(int pos) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("List index out of bounds");
		}
		T item = arr[pos];

		for(int i = pos + 1; i < size; i++) {
			arr[i-1] = arr[i];
		}
		--size;

		return item;
	}
	public void printArray() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}