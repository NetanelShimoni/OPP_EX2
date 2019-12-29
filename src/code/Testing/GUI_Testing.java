package code.Testing;

import code.dataStructure.DGraph;
import code.dataStructure.Node;
import code.utils.GUI_graph;
import code.utils.Point3D;

import java.io.Serializable;

public class GUI_Testing implements Serializable {

public static void main(String[] args) throws InterruptedException {
        DGraph g = new DGraph();
        g.addNode(new Node(new Point3D(30, 500)));
        g.addNode(new Node(new Point3D(270, 80)));
        g.addNode(new Node(new Point3D(50, 100)));
      g.addNode(new Node(new Point3D(250, 250)));
//       g.addNode(new Node(new Point3D(500, 250)));
//        g.addNode(new Node(new Point3D(450, 550)));
        g.connect(1  , 2, 14);
       //g.connect(1, 4, 9);
//        g.connect(1, 6, 7);
       g.connect(3, 2, 9);
//        g.connect(3, 4, 2);
//        g.connect(4, 1, 2);
//        g.connect(4, 3, 2);
//        g.connect(4, 5, 11);
//        g.connect(4, 6, 10);
//        g.connect(5, 2, 6);
//        g.connect(6, 5, 15);
        GUI_graph gui = new GUI_graph( g);
        }}