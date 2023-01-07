package DataStructures;

import java.util.Hashtable;
import java.util.Stack;

public class AccuStack<E> extends Stack<E> {
    int stackValue = 0;
    Hashtable<Integer, Integer> itemValues = new Hashtable<>();

    public E push(E item, int itemValue) {
        stackValue += itemValue;
        itemValues.put(item.hashCode(), itemValue);

        return super.push(item);
    }

    @Override
    public synchronized E pop() {
        E element = super.pop();

        // Only if there are duplicate keys
        if(!itemValues.isEmpty())
            stackValue -= itemValues.remove(element.hashCode());


        return element;
    }

    public int getValue() { return stackValue; }
}
