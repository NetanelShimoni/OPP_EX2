package code.algorithms

/**
 * This interface represents the "regular" Graph Theory algorithms including:
 * 0. clone();
 * 1. init(String file_name);
 * 2. save(String file_name);
 * 3. isConnected();
 * 5. double shortestPathDist(int src, int dest);
 * 6. List<Node> shortestPath(int src, int dest);
 *
 * @author boaz.benmoshe
</Node> */

import java.io.FileNotFoundException
import java.io.IOException

import code.dataStructure.graph
import code.dataStructure.node_data


interface graph_algorithms {
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
     * @return
     */
    val isConnected: Boolean

    /**
     * Init this set of algorithms on the parameter - graph.
     * @param g
     */
    fun init(g: graph)

    /**
     * Compute a deep copy of this graph.
     * @return
     */
    fun copy(): graph

    /**
     * Init a graph from file
     * @param file_name
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    fun init(file_name: String)

    /** Saves the graph to a file.
     *
     * @param file_name
     */
    @Throws(IOException::class)
    fun save(file_name: String)

    /**
     * returns the length of the shortest path between src to dest
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    fun shortestPathDist(src: Int, dest: Int): Double

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    fun shortestPath(src: Int, dest: Int): List<node_data>

    /**
     * computes a relatively short path which visit each node in the targets List.
     * Note: this is NOT the classical traveling salesman problem,
     * as you can visit a node more than once, and there is no need to return to source node -
     * just a simple path going over all nodes in the list.
     * @param targets
     * @return
     */
    fun TSP(targets: List<Integer>): List<node_data>
}
