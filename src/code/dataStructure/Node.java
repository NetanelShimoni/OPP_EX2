package code.dataStructure;

import code.utils.Point3D;

import java.util.HashMap;

public class Node implements node_data {
    HashMap<Integer,edge_data> edegesmap =new HashMap<Integer, edge_data>();
    //HashMap<Integer,edge_data> edgesdest =new HashMap<Integer, edge_data>();
    public static int mark=0;
    private Point3D location;
    private double weight;
    private String info="";
    private int key,tag;

    public Node(){
    this.key=mark;
    this.weight=0;
    this.info="";
    this.tag=0;
    mark++;

    }
    public Node( Point3D p , double w, String in, int t){

    this.location=p;
    this.weight=w;
    this.tag=t;
    this.key=mark;
    this.info=in;
    mark++;
    }

    public Node(Point3D p) {
        this.setLocation(p);
        this.key=mark;
        mark++;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        return this.location;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
    this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
    this.info=s;
    }


    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag=t;
    }

    @Override
    public void setLocation(Point3D p) {
    this.location=p;
    }
}
