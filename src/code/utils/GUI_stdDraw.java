package code.utils;

import code.algorithms.Graph_Algo;
import code.dataStructure.*;
import code.utils.Point3D;
import code.utils.StdDraw;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

public class GUI_stdDraw {

//    private DGraph d = null;
//
//    public GUI(){
//        GUIgraph(this.d);
//    }

    public void GUIgraph(DGraph d) {
        double minX=0, minY=0,maxX=0,maxY=0;
        Iterator it = d.getV().iterator();
            while(it.hasNext()){
                node_data node = (node_data) it.next();
                minX=Math.min(minX,node.getLocation().x());
                maxX=Math.max(maxX,node.getLocation().x());
                maxY=Math.max(maxY,node.getLocation().y());
                minY=Math.min(minY,node.getLocation().y());
        }
            StdDraw.setCanvasSize(1000,1000);
            StdDraw.setXscale(minX-750,maxX+750);
            StdDraw.setYscale(minY-750,maxY+750);
         it = d.getV().iterator();
         StdDraw.setPenRadius(0.004);
        while (it.hasNext()){
            node_data a = (node_data) it.next();
            StdDraw.setPenRadius(0.004);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledCircle(a.getLocation().x(),a.getLocation().y(),0.05);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setFont(new Font("Arial",Font.BOLD,20));
            StdDraw.text(a.getLocation().x()+0.06,a.getLocation().y()+0.06,""+(a.getKey()));
            Collection<edge_data> Ni= d.getE(a.getKey());
            if(Ni!=null){
                Iterator <edge_data> itn= Ni.iterator();
                while (itn.hasNext()){
                edge_data temp = (edge_data) itn.next();
                int src=temp.getSrc();
                int dest = temp.getDest();
                node_data sr=d.getNode(src);
                node_data des= d.getNode(dest);
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(sr.getLocation().x() ,sr.getLocation().y(),des.getLocation().x(),des.getLocation().y());
                    StdDraw.setPenColor(Color.YELLOW);
                    StdDraw.filledCircle(0.9*des.getLocation().x()+0.1*sr.getLocation().x(),0.9*des.getLocation().y()+0.1*sr.getLocation().y(),0.04);
                    StdDraw.setPenColor(Color.darkGray);
                StdDraw.text((sr.getLocation().x()+ des.getLocation().x())/2,(sr.getLocation().y()+ des.getLocation().y())/2,
                        ""+temp.getWeight());

                }
            }


        }


//        MenuBar menuBar = new MenuBar();
//        Menu menu = new Menu("Menu");
//        menuBar.add(menu);
//        this.setMenuBar(menuBar);
//
//        MenuItem item1 = new MenuItem("Item 1");
//        item1.addActionListener(this);
//
//        MenuItem item2 = new MenuItem("Item 2");
//        item2.addActionListener(this);
//
//        menu.add(item1);
//        menu.add(item2);
//
//        this.addMouseListener(this);
    }







    public static void main(String[] args) {
        Graph_Algo ga;
        graph g=new DGraph();
        GUI_stdDraw G = new  GUI_stdDraw();
        g = new DGraph();
        ga = new Graph_Algo();
        g.addNode(new Node(new Point3D(30, 500)));
        g.addNode(new Node(new Point3D(270, 80)));
        g.addNode(new Node(new Point3D(50, 100)));
        g.addNode(new Node(new Point3D(250, 250)));
        g.addNode(new Node(new Point3D(500, 250)));
        g.addNode(new Node(new Point3D(450, 550)));
        g.addNode(new Node(new Point3D(2000, 2000)));
        g.connect(7,5,2);
        g.connect(1, 3, 14);
        g.connect(1, 4, 9);
        g.connect(1, 6, 7);
        g.connect(3, 2, 9);
        g.connect(3, 4, 2);
        g.connect(4, 1, 2);
        g.connect(4, 3, 2);
        g.connect(4, 5, 11);
        g.connect(4, 6, 10);
        g.connect(5, 2, 6);
        g.connect(6, 5, 15);
        ga.init(g);
        G.GUIgraph((DGraph) g);

//        Node v1 = new Node(new Point3D(1,4,0),8,"hi",0);
//        Node v2 = new Node(new Point3D(2,5,0),80,"or",0);
//        Node v3 = new Node(new Point3D(4,3,0),100,"oghgh",0);
//        Node v4 = new Node(new Point3D(4,5,0),80,"or",0);
////        Node v5 = new Node(new Point3D(2.5,5,0),80,"or",0);
////        Node v6 = new Node(new Point3D(1.8,5,0),80,"or",0);
//      //  Node v4 = new Node(new Point3D(1,1,0),100,"oghgh",0);
//        DGraph u = new DGraph();
//        u.addNode(v1);
//        u.addNode(v2);
//        u.addNode(v3);
////        u.addNode(v6);
//        u.addNode(v4);
//        u.connect(v1.getKey(),v2.getKey(),0.5);
//       // u.connect(v2.getKey(),v3.getKey(),0.5);
//        u.connect(v3.getKey(),v1.getKey(),2);
//        u.connect(v3.getKey(),v4.getKey(),30);
//       u.connect(v1.getKey(),v3.getKey(),2);
//       u.connect(v3.getKey(),v2.getKey(),60);
//       u.connect(v2.getKey(),v4.getKey(),0.25);
//      // u.connect(v3.getKey(),v4.getKey(),2);
//       u.connect(v4.getKey(),v3.getKey(),2);
//     //   System.out.println(u.removeEdge(v2.getKey(), v1.getKey()));
//
//        // u.removeNode(v1.getKey());
//        // u.removeNode(v3.getKey());
//        // u.removeNode(v2.getKey());
//         //u.removeNode(v4.getKey());
//       System.out.println(u.edgeSize());
//       u.removeEdge(v2.getKey(),v4.getKey());
//        System.out.println(u.edgeSize());
//
//
//        Graph_Algo temp = new Graph_Algo();
//        temp.init(u);
//       // System.out.println(temp.shortestPathDist(2,3));
////        graph t;
////        t= temp.copy();
//
//        G.GUIgraph(u);
//        Graph_Algo y = new Graph_Algo();
////        y.init(t);
//       // temp.shortestPath(0,1);
//        System.out.println(temp.isConnected());
//        List <Integer> iinteger = new LinkedList<Integer>();
//        //iinteger.add(0);
////        iinteger.add(1);
////      iinteger.add(2);
//       iinteger.add(3);
//        iinteger.add(1);
//    //  System.out.println(temp.TSP(iinteger));
//


    }
}