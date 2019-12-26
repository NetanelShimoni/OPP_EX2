package code.algorithms;

import java.util.*;

//import javax.swing.text.html.HTMLDocument.Iterator;

import code.dataStructure.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms{

    //	private ArrayList <graph> a =new ArrayList<graph>();
    //	private ArrayList <node_data> b =new ArrayList<node_data>();
    //	 HashMap<Integer, node_data> allnode = new HashMap<Integer, node_data>();
    private DGraph g = new DGraph();
    public void initColor(graph g)
    {
        for (int i= 0;i<g.nodeSize(); i++) {
            g.getNode(i).setTag(0);
        }
    }

    @Override
    public void init(graph g) {
        this.g=(DGraph) g;
    }

    @Override
    public void init(String file_name) {

        try
        {
            FileInputStream file = new FileInputStream("file_name");
            ObjectInputStream in = new ObjectInputStream(file);

            g= (DGraph)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized");
            System.out.println(g);
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
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
            System.out.println("IOException is caught");
        }

    }

    @Override
    public boolean isConnected() {
        HashMap<Integer, node_data> getallnode=new HashMap<Integer, node_data>(g.allnode);
        HashMap<Integer, HashMap<Integer, edge_data>> getalledges=new  HashMap<Integer, HashMap<Integer, edge_data>> (g.alledges);


            if (this.g.allnode.size() == 1) {
                return true;
            }
            if (this.g.alledges.size() == 0 && this.g.allnode.size() > 1) {
                return false;
            }
            if(this.g.allnode.size()>this.g.alledges.size()){
                return false;
            }
            initColor(g);
            boolean ans = true;
            Collection<node_data> temp = new ArrayList<>();
            temp = this.g.getV();
//        for (int i = 0; i <this.g.allnode.size() ; i++) {
//            temp.add(this.g.allnode.get(i));
//        }
            Iterator it = temp.iterator();
            while (it.hasNext()) {
                node_data vdata = (node_data) it.next();
                int k = numberofcon(vdata);
                if (k < g.allnode.size()) {
                    return false;
                }
            }

        return true;
    }

    private int numberofcon(node_data vdata) {
        initColor(g);
        Stack <node_data> a =new Stack<>();
        int ans=0;
        a.push(vdata);
        while (!a.isEmpty()){
            node_data c = a.pop();
            if (c.getTag()==0) {
                c.setTag(1);
                ans++;
                Collection<node_data> t = new ArrayList<node_data>();
               // Collection<edge_data> t = new ArrayList<>();
                t = allWni(this.g.allnode.get(c.getKey()));
                Iterator it = t.iterator();
                while (it.hasNext()){
                  node_data v = (node_data)it.next();
                  a.push(v);
                }
                //a.push((node_data) t);
            }
        }

        //System.out.println(ans);
        return  ans;

    }

    private Collection<node_data> allWni(node_data vdata){
        Collection<edge_data> Ni=this.g.getE(vdata.getKey());
        ArrayList<node_data> list=new ArrayList<node_data>();
        Iterator<edge_data> iterator=Ni.iterator();
        while(iterator.hasNext())
        {
            list.add(this.g.getNode(iterator.next().getDest()));
        }
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getTag()==1){
                list.remove(i);
            }
        }

//        Collection<node_data> ans =new ArrayList<>();
//        int key = vdata.getKey();
//
//        Ni=this.g.getE(this.g.alledges.get(vdata.getKey()).);
//       Ni=this.g.getE(this.g.alledges.get(vdata.getKey()).);
//        Iterator it =Ni.iterator();
//       while (it.hasNext()) {
//           edge_data e = (edge_data) it.next();
//           int u = e.getDest();
//           if (this.g.getNode(u).getTag() == 0){
//               ans.add(this.g.getNode(u));
//       }
//       }
//        int key=vdata.getKey();
//        int y1= g.alledges.get(key).size();
//        System.out.println(y1);
//        for (int i = 0; i <g.alledges.get(key).size() ; i++) {
//            int  j=g.alledges.get(key).get(i).getSrc();
//            node_data y = g.allnode.get(j);
//            if(y.getTag()==0) {
//                Ni.add(y);
//            }
//        }
        return list;

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        ////////weight=INFINITY
        if (this.g.allnode.get(src)==null || this.g.allnode.get(dest)==null){
            return -1;
        }

        initINFINTI(this.g);
        ///////tag=0
        initColor(this.g);
        double sum=0;
        double ans=0;
        node_data temp = this.g.getNode(src);
        temp.setTag(1);
        temp.setWeight(0);
        Stack <node_data> q =new Stack<node_data>();
        for (int i = this.g.allnode.size()-1; i >=0 ; i--) {
            if (this.g.allnode.get(i) != temp) {
                q.add(this.g.getNode(i));


            }
        }
        q.add(temp);
        while (!q.isEmpty()){
            node_data k =q.pop();
            Collection <edge_data> a = new LinkedList<edge_data>();
                    a=this.g.getE(k.getKey());
            if (a!=null){
            Iterator it = a.iterator();
            while(it.hasNext()){
                edge_data y = (edge_data) it.next();
                node_data r = this.g.getNode(y.getDest());
                //if(r.getTag()==0){
                    r.setTag(1);
                     sum = k.getWeight()+y.getWeight();
                     if(sum<r.getWeight()){
                         r.setWeight(sum);
                         if (r!=temp) {
                             r.setInfo("" + k.getKey());
                         }
//                         System.out.println(r.getWeight());
                         //System.out.println(r.getInfo());

                     }}
               // }

            }
        }
return this.g.allnode.get(dest).getWeight();
        }
    private void initINFINTI(DGraph g) {
        for (int i = 0; i <g.allnode.size() ; i++) {
            this.g.allnode.get(i).setWeight(Integer.MAX_VALUE);
        }
    }
    @Override
    public List<node_data> shortestPath(int src, int dest) {
    List <node_data> ans = new LinkedList<node_data>();
    double k = shortestPathDist(src,dest);
      //  System.out.println(this.g.allnode.get(src).getWeight());
        node_data sr =this.g.allnode.get(src);
        node_data des = this.g.allnode.get(dest);
        ans.add(des);
        while (des.getWeight()!=0){
            double key= Double.parseDouble(des.getInfo());
            des= this.g.allnode.get((int)key);
            ans.add(des);
        }
        return ans;
    }

    @Override
    public List<node_data> TSP(List<Integer> targets) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public graph copy() {
        graph copy =  new DGraph();
        for (int i = 0;i<this.g.nodeSize(); i++)
        {
            node_data temp =this.g.allnode.get(i);
            copy.addNode(temp);
            Collection <edge_data> copy_edge = new LinkedList<edge_data>();
            copy_edge =  this.g.getE(temp.getKey());
            if (copy_edge!=null) {
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

