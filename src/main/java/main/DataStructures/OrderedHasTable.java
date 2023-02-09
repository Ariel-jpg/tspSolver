package main.DataStructures;

import java.util.*;

public class OrderedHasTable<K, E> implements Cloneable {
    Hashtable<K, E> hashtable = new Hashtable<>();
    PriorityQueue<E> queue;

    public OrderedHasTable(Comparator<E> comparator){ queue = new PriorityQueue<E>(comparator); }

    public Collection<E> values() { return hashtable.values(); }

    public E get(K key){ return hashtable.get(key); }
    public boolean containsKey(K key) { return hashtable.get(key) != null; }

    public E getMin() { return queue.peek(); }
    public PriorityQueue<E> getOrderedValues(){ return new PriorityQueue<E>(queue); }

    public void put(K key, E element){
        queue.add(element);
        hashtable.put(key, element);
    }

    public E remove(K key){
        E element = hashtable.remove(key);
        queue.remove(element);

        return element;
    }
}
