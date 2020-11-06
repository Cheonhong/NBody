public class Linkedlist<T> implements MyList<T>{
	private Node<T> head;
	private int size;
	
	@SuppressWarnings("hiding")
	public class Node<T>{
		T data;
		Node<T> next;
		
		public Node(T value) {
			data = value;
			next = null;
		}
	}

	public Linkedlist() {
		head = null;
		size = 0;
	}
	public int size() {
		return size;
	}
	public T get (int pos) {
		Node<T> curr = head;
		for (int i = 0; i < pos; i++) {
			curr = curr.next;
		}
		return curr.data;
	}
	public boolean add (T item) {
		if (head == null) {
			head = new Node<T>(item);
			++size;
			return true;
		}
		Node<T> prev = head;
		for(int i = 0; i < size-1; i++)
			prev = prev.next;
		Node<T> node = new Node<T> (item);
		prev.next = node;
		++size;
		return true;
	}
	public void add(T item, int pos) {
		if (pos == 0) {
			Node<T> node = new Node<T>(item);
			node.next = head;
			head = node;
			++size;
		} else {
			Node<T> prev = head;
			for (int i = 0; i < pos-1; i++)
				prev = prev.next;
			Node<T> node = new Node<T>(item);
			node.next = prev.next;
			prev.next = node;
			++size;
		}
	}
	public T remove(int pos) {
		if(pos == 0) {
			Node<T> node = head;
			head = head.next;
			--size;
			return node.data;
		} else {
			Node<T> prev = head;
			for (int i = 0; i < pos -1; i++)
				prev = prev.next;
			Node<T> node = prev.next;
			prev.next = node.next;
			--size;
			return node.data;
		}
	}
	public void printArray() {
		Node<T> curr = head;
		for (int i = 0; i < size; i++) {
			System.out.print(curr.data + " ");
		}
		System.out.println();
	}
}
