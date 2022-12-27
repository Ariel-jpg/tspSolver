package TspResolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

public class OrderedHasTable<K, E> {
    Hashtable<K, E> hashtable = new Hashtable<>();
    SNode<K, E> head = new SNode<>(null, null);
    Comparator<E> comparator;

    OrderedHasTable(Comparator<E> comparator){ this.comparator = comparator; }

    /**
     * @implNote O(1)
     */
    public E get(K key){ return hashtable.get(key); }

    /**
     * @implNote O(n)
     */
    public E remove(K key) throws Exception {
        if(hashtable.containsKey(key)) {
            removeInList(key, head);

            return hashtable.remove(key);
        } else throw new Exception("The key: " + key + " is not found");
    }

    /**
     * @implNote O(1) | This method represents the removal of the minimum element according to the chosen criteria.
     * For example: If the comparator criteria is to have the numbers from largest to smallest then this method will remove the maximum element that,
     * according to the chosen criteria, is the minimum. In other words, it removes the item with the highest priority.
     * If have more than one minimum, remove all
     */
    public List<E> removeMin(){
        List<E> minElements = new ArrayList<>();

        for (K hashCode : head.next.hashCodesList) minElements.add(hashtable.remove(hashCode));
        head.next = head.next.next;

        return minElements;
    }

    private void removeInList(K hashCode, SNode<K, E> node){
        if(node.next.containsHashCode(hashCode) && node.next.hasMultipleHashCodes()) node.next.hashCodesList.remove(hashCode);
        else if(node.next.containsHashCode(hashCode)) node.next = node.next.next;
        else removeInList(hashCode, node.next);
    }

    public boolean hasMultipleMinRelations() { return head.next.hasMultipleHashCodes(); }

    /**
     * @return A list of all keys that have the least element. If there is only one key, then it returns a list with one element
     */
    public List<K> getMin() { return head.next.hashCodesList; }

    /**
     * @implNote O(n)
     */
    public void put(K key, E element){
        SNode<K, E> newNode = new SNode<>(key, element);
        putInList(head, newNode);

        hashtable.put(key, element);
    }

    private void putInList(SNode<K, E> node, SNode<K, E> newNode){
        if(node.hasNext()){
            if(comparator.compare(newNode.value, node.next.value) < 0) {
                newNode.next = node.next;
                node.next = newNode;
            } else if(comparator.compare(newNode.value, node.next.value) == 0) node.next.addHashCode(newNode.hashCodesList.get(0));
            else putInList(node.next, newNode);
        } else node.next = newNode;
    }
}
