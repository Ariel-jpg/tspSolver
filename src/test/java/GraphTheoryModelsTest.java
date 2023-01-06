import GraphTheoryModels.DirectedRelation;
import GraphTheoryModels.Vertex;
import GraphTheoryModels.WeightedRelation;
import org.junit.Test;

import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphTheoryModelsTest {
    @Test public void generalTestsModels(){
        Vertex v = new Vertex("v"), w = new Vertex("w"), t = new Vertex("t"), h = new Vertex("h");

        WeightedRelation wr = new WeightedRelation(v.getId(), w.getId(), 200);
        WeightedRelation wr1 = new WeightedRelation(v.getId(), t.getId(), 100);
        WeightedRelation wr2 = new WeightedRelation(v.getId(), h.getId(), 150);

        v.addRelation(wr);
        v.addRelation(wr1);
        v.addRelation(wr2);

        assertThat(wr.containsVertex(v)).isTrue();
        assertThat(wr.containsVertex(w)).isTrue();
        assertThat(wr.containsVertex(t)).isFalse();
        assertThat(wr.getWeight()).isEqualTo(200);

        PriorityQueue<WeightedRelation> copy = v.getOrderedRelation();

        // Immutability test
        assertThat(copy.remove().getWeight()).isEqualTo(100);
        assertThat(v.getMinRelation().getWeight()).isEqualTo(100);
        assertThat(copy.remove().getWeight()).isEqualTo(150);
        assertThat(v.getMinRelation().getWeight()).isEqualTo(100);
        assertThat(copy.remove().getWeight()).isEqualTo(200);
        assertThat(v.getMinRelation().getWeight()).isEqualTo(100);

        assertThat(copy.isEmpty()).isTrue();

        assertThat(v.getRelationWith(t)).isEqualTo(wr1);
        assertThat(v.getRelationWith(h)).isEqualTo(wr2);


        List<DirectedRelation> orderedRelationsV = v.getMinRelation().generateOrderedPairs();
        DirectedRelation vt = orderedRelationsV.get(0), tv = orderedRelationsV.get(1);

        assertThat(vt.getNextVertexId()).isEqualTo(t.getId());
        assertThat(tv.isNextEqualsTo(v)).isTrue();
        assertThat(tv.isNextEqualsTo(t)).isFalse();
        assertThat(tv.getOriginVertexId()).isEqualTo(t.getId());
        assertThat(tv.getWeight()).isEqualTo(v.getMinRelation().getWeight());
        assertThat(vt.getWeight()).isEqualTo(v.getMinRelation().getWeight());
    }
}
