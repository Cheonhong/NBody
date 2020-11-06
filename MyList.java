public interface MyList<T> {
	public abstract T get(int pos) throws Exception;
	public abstract boolean add(T item);
	public abstract void add(T item, int pos);
	public abstract T remove(int pos) throws Exception;
	public abstract int size();
}
