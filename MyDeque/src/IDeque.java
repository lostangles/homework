public interface IDeque<E> {
    public void addFirst(E e); 
    public void addLast(E e);
    public E removeFirst();
    public E getFirst();
    public boolean isEmpty();
}