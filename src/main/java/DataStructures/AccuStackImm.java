package DataStructures;

import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

public final class AccuStackImm<E> {
    final Object[] elementData;
    final int size;
    final int stackValue;
    final Hashtable<Integer, Integer> itemValues;

    public AccuStackImm(){
        size = 0;
        elementData = new Object[size];
        stackValue = 0;
        itemValues = new Hashtable<>(size);
    };

    private AccuStackImm(E item, int size, int stackValue, int itemValue, Hashtable<Integer, Integer> itemValues, Object[] elementData){
        this.size = size;
        this.elementData = new Object[size];
        this.itemValues = new Hashtable<>(size);
        this.stackValue = stackValue;

        for(Integer key: itemValues.keySet())
            this.itemValues.put(key, itemValues.get(key));

        if (size - 1 >= 0) System.arraycopy(elementData, 0, this.elementData, 0, size - 1);

        this.itemValues.put(item.hashCode(), itemValue);
        this.elementData[size - 1] = item;
    };

    public AccuStackImm<E> push(E item, int itemValue){
        return new AccuStackImm<E>(item, size + 1, this.stackValue + itemValue, itemValue, itemValues, elementData);
    }

    public E peek(){ return (E) elementData[size-1]; }

    public boolean containsItem(E item) { return itemValues.containsKey(item.hashCode()); }

    public int getStackValue(){ return stackValue; }
    public int getSize(){ return size; }

}
