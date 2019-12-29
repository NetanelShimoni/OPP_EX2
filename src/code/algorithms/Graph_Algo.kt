package code.algorithms

import java.io.*
import java.util.ArrayList
import java.util.LinkedList
import code.dataStructure.node_data
import code.dataStruct
import code.dataStructure.*

import code.dataStructure.DGraph
import code.dataStructure.graph

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 */
class Graph_Algo : graph_algorithms {

    //private LinkedList<graph> a =new LinkedList<>();
    private var our_graph: graph = DGraph()

    val isConnected: Boolean
        @Override
        get() {
            for (i in 0 until (our_graph as DGraph).alledges.size()) {
                for (j in 0 until (our_graph as DGraph).alledges.get(i).size()) {
                    (our_graph as DGraph).alledges.get(i).get(j).setTag(0)

                }
            }
            var count_src = 0
            var count_node = 0
            for (i in 0 until (our_graph as DGraph).alledges.size()) {
                for (j in 0 until (our_graph as DGraph).alledges.get(i).size()) {
                    count_src = (our_graph as DGraph).alledges.get(i).get(j).getTag()
                    count_node = (our_graph as DGraph).allnode.get(i).getTag()
                    count_src++
                    count_node++
                    (our_graph as DGraph).alledges.get(i).get(j).setTag(count_src)
                    (our_graph as DGraph).allnode.get(i).setTag(count_node)


                }
            }
            return false
        }


    @Override
    fun init(g: graph) {
        this.our_graph = g
    }

    @Override
    @Throws(IOException::class, ClassNotFoundException::class)
    fun init(file_name: String) {
        //graph g=null;
        val file = FileInputStream(file_name)
        val `in` = ObjectInputStream(file)
        this.our_graph = `in`.readObject() as graph
        `in`.close()
        file.close()

    }

    @Override
    @Throws(IOException::class)
    fun save(file_name: String) {
        val file = FileOutputStream(file_name)
        val out = ObjectOutputStream(file)
        out.writeObject(this.our_graph)
        out.close()
        file.close()


    }

    @Override
    fun shortestPathDist(src: Int, dest: Int): Double {
        for (i in 0 until our_graph.nodeSize()) {
            for (j in 0 until (our_graph as DGraph).alledges.get(i).size()) {
                (our_graph as DGraph).alledges.get(i).get(j).setTag(0)
                (our_graph as DGraph).alledges.get(i).get(j).setInfo("INF,@")
            }
        }

        //shortestPathDist_rec(src,dest);

        return 0.0
    }


    @Override
    fun shortestPath(src: Int, dest: Int): List<node_data>? {
        // TODO Auto-generated method stub
        return null
    }

    @Override
    fun TSP(targets: List<Integer>): List<node_data>? {
        // TODO Auto-generated method stub
        return null
    }

    @Override
    fun copy(): graph {
        val copy = DGraph()
        (copy as DGraph).alledges = (our_graph as DGraph).alledges
        (copy as DGraph).allnode = (our_graph as DGraph).allnode
        return copy
    }

}
