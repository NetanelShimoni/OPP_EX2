package code.dataStructure;

public class Edge_data implements edge_data {

    private double weight;
    private int src;
    private  int dest;
    private String info;
    private int tag;

    public Edge_data()
    {
        this.info="";
        this.weight=0;
        this.dest=0;
        this.src=0;
    }
    public Edge_data(int sr, int des,double w ){
        this.src=sr;
        this.dest=des;
        this.weight=w;

    }
    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
}
