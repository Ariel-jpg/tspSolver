package TspResolver;

import java.util.ArrayList;

public class Queue<T> {
    ArrayList<T> queue = new ArrayList<>();

    Queue(){}

    public void enqueue(T element){ queue.add(element); }
    public T dequeue(){ return queue.remove(0); }
    public int size(){ return queue.size(); }
    public boolean isEmpty(){ return queue.isEmpty(); }
    public T peek(){ return queue.get(0); }
}
