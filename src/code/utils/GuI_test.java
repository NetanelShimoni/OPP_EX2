package code.utils;
import code.algorithms.Graph_Algo;
import code.dataStructure.DGraph;
import code.dataStructure.Node;
import code.utils.GUI_stdDraw;
import code.dataStructure.graph;

import javax.swing.JFrame;
public class GuI_test {
    private static  graph gr;
    public static void main(String[] args) {
        GUI_graph a =new GUI_graph(gr);
        DGraph p =new DGraph();
        Node v1 = new Node(new Point3D(2,3,0),8,"hi",0);
        Node v2 = new Node(new Point3D(20,30,0),80,"or",0);
        Node v3 = new Node(new Point3D(30,40,0),100,"oghgh",0);
        Node v4 = new Node(new Point3D(50,40,0),100,"oghgh",0);
        System.out.println(v1.getKey());
        System.out.println(v2.getKey());
        System.out.println(v3.getKey());
        p.addNode(v1);
        p.addNode(v2);
        p.addNode(v3);
       // p.addNode(v4);
        p.connect(0,1,8);
        p.connect(1,2,8);
        p.connect(2,0,8);
       // p.connect(3,5,8);
        Graph_Algo o =new Graph_Algo();
        o.init(p);

        gr.addNode(v1);
        gr.addNode(v2);
        gr.addNode(v3);
        gr.connect(0,1,8);
        gr.connect(1,2,8);
        gr.connect(2,0,8);
        //System.out.println(p.toString());
        System.out.println(o.isConnected());
        System.out.println(a.gr.nodeSize());

        a.setVisible(true);
        a.repaint();



    }
}
