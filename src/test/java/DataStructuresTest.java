import DataStructures.AccuStack;
import DataStructures.AccuStackImm;
import DataStructures.OrderedHasTable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DataStructuresTest {
    @Test
    public void TestImmutableAccuStack(){
        AccuStackImm<String> l = new AccuStackImm<>();
        l.push("A", 23);
        l.push("B", 23);
        l.push("C", 1233);

        AccuStackImm<String> l1 = l.push("SD", 23);
        AccuStackImm<String> l2 = l1.push("A", 23);
        AccuStackImm<String> l3 = l1.push("B", 1);

        assertThat(l2.containsItem("B")).isFalse();
        assertThat(l1.containsItem("A")).isFalse();

        System.out.println("a");
    }

    @Test
    public void testOrderedHashTable() {
        OrderedHasTable<String, Integer> h = new OrderedHasTable<>((o1, o2) -> {
            if(o1 < o2) return -1;
            else if (o1.equals(o2)) return 0;
            else return 1;
        });

        h.put("AAA", 10);
        h.put("Aa", 5);
        h.put("Asd", 7);
        h.put("A", 6);
        h.put("Accc", 19);
        h.put("Acd", 15);
        h.put("Acdk", 9);
        h.put("BBB", 9);

        assertThat(h.getMin()).isEqualTo(5);
        assertThat(h.remove("Aa")).isEqualTo(5);
        assertThat(h.getMin()).isEqualTo(6);
    }

    @Test
    public void testAccuStack() {
        AccuStack<String> s = new AccuStack<>();
        s.push("A", 20);
        s.push("B", 90);
        s.push("C", 10);
        s.push("D", 80);
        s.push("E", 100);

        assertThat(s.getValue()).isEqualTo(300);
        assertThat(s.pop()).isEqualTo("E");
        assertThat(s.getValue()).isEqualTo(200);
        assertThat(s.pop()).isEqualTo("D");
        assertThat(s.getValue()).isEqualTo(120);
        assertThat(s.pop()).isEqualTo("C");
        assertThat(s.getValue()).isEqualTo(110);
        assertThat(s.pop()).isEqualTo("B");
        assertThat(s.getValue()).isEqualTo(20);
    }
}
