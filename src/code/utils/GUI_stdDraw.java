package code.utils;

import code.algorithms.Graph_Algo;
import code.dataStructure.DGraph;
import code.dataStructure.Node;
import code.dataStructure.edge_data;
import code.dataStructure.node_data;
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
            StdDraw.setXscale(minX-2,maxX+2);
            StdDraw.setYscale(minY-2,maxY+1);
         it = d.getV().iterator();
         StdDraw.setPenRadius(0.004);
        for (int i = 1; i <d.allnode.size(); i++) {
            StdDraw.setPenRadius(0.004);
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(d.allnode.get(i).getLocation().x(),d.allnode.get(i).getLocation().y(),0.05);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setFont(new Font("Arial",Font.BOLD,20));
            StdDraw.text(d.allnode.get(i).getLocation().x()+0.06,d.allnode.get(i).getLocation().y()+0.06,""+i);
            Collection<edge_data> Ni= d.getE(i);
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
                StdDraw.setPenColor(Color.darkGray);
                StdDraw.text((sr.getLocation().x()+ des.getLocation().x())/2,(sr.getLocation().y()+ des.getLocation().y())/2,
                        ""+temp.getWeight());
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.filledCircle(0.1*des.getLocation().x()+0.9*sr.getLocation().x(),0.1*des.getLocation().y()+0.9*sr.getLocation().y(),0.04);

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
        GUI_stdDraw G = new  GUI_stdDraw();

        Node v1 = new Node(new Point3D(1,4,0),8,"hi",0);
        Node v2 = new Node(new Point3D(2,5,0),80,"or",0);
        Node v3 = new Node(new Point3D(4,3,0),100,"oghgh",0);
        Node v4 = new Node(new Point3D(4,5,0),80,"or",0);
        Node v5 = new Node(new Point3D(2.5,5,0),80,"or",0);
        Node v6 = new Node(new Point3D(1.8,5,0),80,"or",0);
      //  Node v4 = new Node(new Point3D(1,1,0),100,"oghgh",0);
        DGraph u = new DGraph();
        u.addNode(v1);
        u.addNode(v2);
        u.addNode(v3);
        u.addNode(v4);
        u.addNode(v5);
        u.addNode(v6);
     //   u.addNode(v4);
        u.connect(v1.getKey(),v2.getKey(),100);
        u.connect(v3.getKey(),v2.getKey(),20);
        u.connect(v3.getKey(),v1.getKey(),3);
        u.connect(v5.getKey(),v1.getKey(),20.3);
        u.connect(v2.getKey(),v1.getKey(),2.6);
        u.connect(v3.getKey(),v2.getKey(),8.2);
        u.connect(v6.getKey(),v5.getKey(),10);
       // u.connect(v3.getKey(),v1.getKey(),8);
       // u.connect(v4.getKey(),v1.getKey(),8);

        G.GUIgraph(u);

    }
}