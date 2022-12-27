package TspResolver;

import java.util.ArrayList;
import java.util.List;

public class SNode<K, E> {
    E value;
    SNode<K, E> next;
    List<K> hashCodesList = new ArrayList<>();

    SNode(K hashCode, E value) { this.value = value; hashCodesList.add(hashCode); }

    public void addHashCode(K hashCode){ hashCodesList.add(hashCode); }
    public boolean hasNext(){ return next != null; }
    public boolean hasMultipleHashCodes() { return hashCodesList.size() > 1; }
    public boolean containsHashCode(K hashCode) { return hashCodesList.contains(hashCode); }
}
