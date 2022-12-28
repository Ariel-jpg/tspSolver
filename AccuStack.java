package TspResolver;

import dijkstra.v2.DijkstraAlgorithm;

import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;

public class AccuStack<T> {
    private int value = 0;
    private Stack<T> stack = new Stack<>();
    private Hashtable<Integer, Integer> indexes = new Hashtable<>();

    public Hashtable<Integer, Integer> getIndexes() { return indexes; }
    public int getValue() { return value; }
    public Stack<T> getStack() { return stack; }
    private int duplicatedKeys = 0;

    AccuStack(){};
    AccuStack(int value){ this.value = value; }

    public int push(T e, int acc){
        stack.push(e);
        value += acc;
        indexes.put(e.hashCode(), acc);

        return value;
    }

    public T pop() {
        T e = stack.pop();
        value -= indexes.remove(e.hashCode());

        return e;
    }

    public T peek() { return stack.peek(); }

    public boolean isEmpty(){ return stack.isEmpty(); }
}