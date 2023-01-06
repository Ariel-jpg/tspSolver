# tspSolver
<p align="center"> --- V. 0.1 ---- </p>
The algorithm consists of Finding the minimum Hamiltonian cycles of each vertex. For each vertex the following sequence is followed: \

- Let G = {V, A} be a graph where V is the set of vertices and A the set of edges that join those vertices, where each a ∈ A has the form: a = {v, w}. v, w ∈ V
- Let S = {CH, A_s} be the final set of Hamiltonian circuits, CH the set of all Hamiltonian circuits such that if ch ∈ CH then ch = (v_1, ..., v_n) (for all v ∈ V) that general the algorithm and A_s the set of edges of each Hamiltonian path contained in CH

----------------------------------------------------------

<p align="center"> Algorithm per iteration </p>

0 -
Any vertex is selected and the edge with the least weight is chosen (represented with the "Relation" class). \
All relations that the other nodes have with v are eliminated and the relation of v with any other node (note the unidirectional description and its non-correlation) that is minimal is found. In other words, find the minimum edge

v, w ∈ V, a_m = {v, w} ∈ A / ∀a ∈ v : w(a_m) < w(a), then A_s1 = {a_m} ^ ch_1 = (v, w).

1 - 
w becomes the evaluated one (v = w). Exactly the same is done with the exception that now w has n-1 relations (remember that the relations with v of all nodes were removed). \
As in the previous step, all relationships with v (w) are removed and the minimum relationship is chosen using the same criteria.

2 -
The process is repeated until v has no more relations to evaluate (that is, n-1 vertices have been visited), at which time the relations removed with the starting vertex are added and the edge that directly relates them is chosen. \
For this point, ch_1 and A_s1 remain of the form: ch_1 = (v, v_1, v_2, ... , v_n, v), A_s1 = {a_m, a_m1, ... , a_mn}

3 -
Finally, all deleted relations are added

----------------------------------------------------------

<p align="center"> --- Others --- </p>
This is done n times, until all vertices have a Hamiltonian circuit starting with them.


For the choice of the minimum path, first a sum of all the edges in A_sn that belong to ch_n is made, secondly the sum of the total weight of each vertex is made and finally the subtraction between the second sum and the first is made. Then, the shortest path is the one such that the subtraction leaves the largest number \
It should be noted that this is done differently in the implementation. Here many implementation and flow details are left out. That is why set notation is used.

-----------------------------------------------------------

<p align="center"> --- Conclusions ---- </p>
The experimental order of the implementation is O(n⁵) \
This algorithm, as mentioned in the commit, was tested with graphs of up to 7 nodes and 21 edges. In all controlled experiments it gave the minimal route. \
Subsequently, in controlled tests of up to 20 nodes and 190 edges, the result was good but not minimal (the contrast was made with the Held-Karp method. For more information: https://en.wikipedia.org/wiki/Held%E2%80%93Karp_algorithm). Also in uncontrolled experiments (the edges were randomly generated) with 7 nodes and 21 edges, the algorithm gave good paths but not the minimum. Transforming this method into a heuristic method.
