package algorithms;

import java.util.*;

//import javax.swing.text.html.HTMLDocument.Iterator;

import dataStructure.*;

import com.sun.org.apache.regexp.internal.recompile;
import gui.GUI_graph;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {

    //	private ArrayList <graph> a =new ArrayList<graph>();
    //	private ArrayList <node_data> b =new ArrayList<node_data>();
    //	 HashMap<Integer, node_data> allnode = new HashMap<Integer, node_data>();
    public DGraph g = new DGraph();

    public Graph_Algo(DGraph copy1) {
        this.g=copy1;
    }

    public Graph_Algo() {
        this.g=null;
    }

    public Graph_Algo(graph graph) {
        this.g=(DGraph)graph;
    }

    public graph getGraph(){
        return this.g;
    }
    public void initColor(graph g)
    {

        Iterator it = this.g.allnode.values().iterator();
        while (it.hasNext()){
            Node t = (Node) it.next();
            t.setTag(0);

        }


    }

    @Override
    public void init(graph g) {

        this.g=(DGraph) g;
    }

    @Override
    public void init(String file_name)  {

        try
        {
            FileInputStream file = new FileInputStream(file_name);
            ObjectInputStream in = new ObjectInputStream(file);
            init((graph) in.readObject());
           // g= (DGraph)in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized");
        }

        catch(IOException ex)
        {
            System.out.println(ex);
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

    }

    @Override
    public void save(String file_name) {
        //		Car car = new Car(1234, "Mazda");
        //        String filename = "myObj.txt";

        try
        {
            FileOutputStream file = new FileOutputStream(file_name);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this.g);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }

    }

    @Override
    public boolean isConnected() {
        Collection<node_data> colec = this.g.getV();
        if (colec.isEmpty())
            return true;

        for (node_data src : colec) {

            for (node_data des : colec) {

                if (this.shortestPathDist(src.getKey(), des.getKey()) == Double.MAX_VALUE)
                    return false;
                if (this.shortestPathDist(des.getKey(), src.getKey()) == Double.MAX_VALUE)
                    return false;


            }

        }

return true;
    }

    private int numberofcon(node_data vdata) {
        initColor(g);
        //int start=vdata.getKey();
        int count=0;
        Stack <node_data> a =new Stack<>();
        int ans=0;
        a.push(vdata);
        while (!a.isEmpty()){
            node_data c = a.pop();
            if (c.getTag()==0) {
                if(count!=0) {
                    c.setTag(1);

                    ans++;
                }
                count++;
                Collection<node_data> t = new ArrayList<node_data>();
                t = allWni(this.g.allnode.get(c.getKey()));

                if (t!=null) {
                    Iterator it = t.iterator();
                    while (it.hasNext()) {
                        node_data v = (node_data) it.next();
                        a.push(v);
                    }
                }
                else return -1;
            }
        }

        return  ans;

    }

    private Collection<node_data> allWni(node_data vdata){
        Collection<edge_data> Ni=this.g.getE(vdata.getKey());
        ArrayList<node_data> list=new ArrayList<node_data>();
        Iterator<edge_data> iterator=Ni.iterator();
        Iterator ni = this.g.getE(vdata.getKey()).iterator();
        if (ni!=null){
        while(ni.hasNext())
        {
            edge_data h = (edge_data) ni.next();
            list.add(this.g.getNode(h.getDest()));
        }}
        for (int i = 0; i <list.size() ; i++) {
            try {
                if(list.get(i).getTag()==1){
                    list.remove(i);
                }
            }catch (Exception E){

            }

        }

        return list;

    }


    private node_data getMinimumNode() {
        node_data n=null;
        for(node_data node: this.g.getV())
        {
            if(node.getTag()==0) {
                if(n==null)
                    n=node;
                if(node.getWeight()<n.getWeight())
                {
                    n=node;

                }
            }
        }
        return n;
    }
    private void initNodesGraph() {
        for (node_data n : this.g.getV()) {
            n.setTag(0);
            n.setInfo("");
            n.setWeight(Double.MAX_VALUE);
        }
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        this.initNodesGraph();
        if(src==dest){
            return 0;
        }
        node_data nSrc = this.g.getNode(src);
        nSrc.setWeight(0);
        while (nSrc.getTag() == 0 && nSrc.getKey() != dest) { // didn't visit this node yet
            Collection<edge_data> e = this.g.getE(nSrc.getKey());
            if(e!=null)
            {
                for(edge_data edge: e)
                {
                    node_data des=this.g.getNode(edge.getDest());
                    double sum=nSrc.getWeight()+edge.getWeight();
                    if((sum<des.getWeight())&&(des.getTag()==0))
                    {
                        des.setWeight(sum);
                        des.setInfo("" + nSrc.getKey());
                    }

                }

            }
            nSrc.setTag(1);
            nSrc = getMinimumNode();

        }
        return nSrc.getWeight();
    }


    private void initINFINTI(DGraph g) {
        Iterator  it =this.g.allnode.values().iterator();
        while (it.hasNext()){
            node_data y = (node_data) it.next();
            y.setWeight(Integer.MAX_VALUE);
        }
    }
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        List<node_data> ans= new LinkedList<node_data>();;
        try {
            List<node_data> temp = new LinkedList<node_data>();
            double k = shortestPathDist(src, dest);
            //  System.out.println(this.g.allnode.get(src).getWeight());
            node_data sr = this.g.allnode.get(src);
            node_data des = this.g.allnode.get(dest);
            temp.add(des);
            while (des.getWeight() != 0) {
                double key = Double.parseDouble(des.getInfo());
                des = this.g.allnode.get((int) key);
                temp.add(des);
            }
            for (int i = temp.size() - 1; i >= 0; i--) {
                ans.add(temp.get(i));
            }
        } catch (Exception e) {
            return null;
        }

        return ans;

    }
    public List<node_data> fake_Tsp (List<Integer> targets) {

        DGraph copy1 = new DGraph(this.g);

        List<node_data> ans = new LinkedList<node_data>();

        Collection<node_data> c = new LinkedList<>(copy1.getV());
        Iterator it = c.iterator();
        while (it.hasNext()) {
            node_data node = (node_data) it.next();
            if (!targets.contains(node.getKey())) {
                copy1.removeNode(node.getKey());
            }
        }





        Graph_Algo f = new Graph_Algo(copy1);
        if (!f.isConnected()){
            return null;
        }
      //  GUI_graph a = new GUI_graph(copy1);
//        Collection w=f.g.getV();
//        Iterator t= w.iterator();
//        while(t.hasNext()){
//            node_data e= (node_data) t.next();
//            System.out.println(e.getKey());
//            Collection <edge_data> y =this.g.getE(e.getKey());
//            Iterator iter = y.iterator();
//            while (iter.hasNext()){
//                edge_data edge = (edge_data) iter.next();
//                System.out.println(" connect to"+edge.getDest());
//            }
//        }
        //a.setVisible(true);
//        try {
//            Thread t1 = new Thread();
//            t1.sleep(1000);
//        } catch (Exception E) {
//            System.out.println(E);
//        }
//        GUI_graph b = new GUI_graph(f.g);
//        b.setVisible(true);


           return f.TSP(targets);

    }
    @Override
    public List<node_data> TSP(List<Integer> targets) {
        if (targets.size() == 1)
            return null;
        LinkedList<node_data> list = new LinkedList<node_data>();
        for (int i = 0; i < targets.size() - 1; i++) {
            List<node_data> l = shortestPath(targets.get(i), targets.get(i + 1));
            if (l == null)
                return null;
            list.addAll(l);
        }

        return removeDuplicates(list);
    }

    private static LinkedList<node_data> removeDuplicates(LinkedList<node_data> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            node_data cur = list.get(i);
            node_data next = list.get(i + 1);
            if (cur.getKey() == next.getKey()) {
                list.remove(i + 1);
            }

        }
        return list;
    }


        @Override
        public graph copy () {
            graph copy = new DGraph();

            Iterator iter = this.g.allnode.values().iterator();
            while (iter.hasNext()) {
                node_data temp = (node_data) iter.next();
                copy.addNode(temp);
                Collection<edge_data> copy_edge = new LinkedList<edge_data>();
                copy_edge = this.g.getE(temp.getKey());
                if (copy_edge != null) {
                    Iterator it = copy_edge.iterator();
                    while (it.hasNext()) {
                        edge_data temp_edge = (edge_data) it.next();
                        copy.connect(temp_edge.getSrc(), temp_edge.getDest(), temp_edge.getWeight());
                    }
                }
            }
            return copy;
        }
    }


