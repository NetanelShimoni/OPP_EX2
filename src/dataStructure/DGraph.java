package dataStructure;
import utils.Point3D;
import utils.*;

import javax.swing.*;
import java.util.*;
public class DGraph extends JFrame implements graph {
	public HashMap<Integer, node_data> allnode = new HashMap<Integer, node_data>();
	public HashMap<Integer, HashMap<Integer, edge_data>> alledges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	static  int MC=0;
	public GUI_Running ch;
	private int edgesize=0;
	public void listen (GUI_Running ch){
		this.ch=ch;
	}
	public DGraph(int i) {
		for (int j = 0; j <i ; j++) {
			node_data n = new Node();
			int x= (int)(Math.random()*200)+1;
			int y= (int)(Math.random()*200)+1;
			Point3D p = new Point3D(x,y);
			n.setLocation(p);
			this.allnode.put(n.getKey(),n);
		}
	}

	public DGraph(){
		this.allnode = new HashMap<Integer, node_data>();
		this.alledges=new HashMap<Integer, HashMap<Integer, edge_data>>();
		this.MC=0;
		this.edgesize=0;
	}
	public void make_changh(){
		if (ch!=null){
			ch.graph_change();
		}
	}

    public DGraph(DGraph other) {
		this.allnode=new HashMap<Integer,node_data>();
		allnode.putAll(other.allnode);
		this.alledges=new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.alledges.putAll(other.alledges);
		//this.hashnodes=new HashMap<Integer,node_data>(other.hashnodes) ;
		//this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>(other.hashedges);
		this.edgesize=other.edgesize;
    }


    @Override
	public node_data getNode(int key) {
		if (allnode.get(key) == null) {
			return null;
		} else {
			return allnode.get(key);
		}
	}
	@Override
	public edge_data getEdge(int src, int dest) {
		if (alledges.get(src)==null){
			return null;
		}
		if (alledges.get(src).get(dest)==null){
			return null;
		}
	return alledges.get(src).get(dest);
}
	@Override
	public void addNode(node_data n) {
		if(this.allnode.size()==0){
			allnode.put(n.getKey(),n);
			MC++;
			make_changh();
			return;
		}
		boolean flag= true;
		Collection <node_data> r = new LinkedList<>();
		Iterator it = allnode.values().iterator();
		while (it.hasNext()){
			node_data e = (node_data) it.next();
			r.add(e);
		}
		for (node_data node: r) {
			if (n.getLocation().ix()==node.getLocation().ix()
					&& n.getLocation().iy()==node.getLocation().iy()
					&& n.getLocation().iz()==node.getLocation().iz()){
				flag=false;
			}
			if (flag==true){
				allnode.put(n.getKey(),n);
			}
			else{
				removeNode(n.getKey());
				System.err.println("this point "+ "(" +n.getLocation().ix() +","+n.getLocation().iy()+")"+ " is alredy exists");
				make_changh();

				return;
			}
		}

		MC++;
		make_changh();
		repaint();
	}
	@Override
	public void connect(int src, int dest, double w) {
			MC++;

			Edge a = new Edge(src, dest, w);
			if (this.alledges.get(src) == null) {
				this.alledges.put(src, new HashMap<Integer, edge_data>());
				this.alledges.get(src).put(dest, a);
				this.edgesize++;
			} else {
				this.alledges.get(src).put(dest, a);
				this.edgesize++;


			}
		make_changh();
	}

	@Override
	public Collection<node_data> getV() {
		return allnode.values();
//		Collection<node_data> a= (Collection<node_data>) allnode;
//		return a;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if (alledges.get(node_id) == null) {
			return null;
		} else {
//		Collection<edge_data> a= (Collection<edge_data>) this.alledges.get(node_id);
//		return a;
			return alledges.get(node_id).values();
		}
	}
	@Override
	public node_data 	removeNode(int key) {
		// TODO Auto-generated method stub
		node_data remove=null;
		if(this.allnode.get(key)==null) {
			System.out.println("do not exsist");
		}
		else {
			remove=((Node)this.allnode.get(key));
			this.allnode.remove(key);
			int size =this.alledges.get(key).size();
			this.edgesize=this.edgesize-size;
			this.alledges.remove(key);
			Collection<node_data> node=this.getV();
			Iterator J=node.iterator();
			while(J.hasNext()) {
				Node current=(Node) J.next();
				//HashMap<Integer,edge_data> hashcurrent=this.alledges.get(current.getKey());
				Collection <edge_data> hashcurrent = this.getE(current.getKey());
				if(hashcurrent!=null) {
				Iterator it = hashcurrent.iterator();
				if (it!=null){
					while (it.hasNext()) {
						edge_data edge = (edge_data) it.next();
						if (edge.getDest()== key) {
							this.alledges.get(edge.getSrc()).remove(key);
						this.edgesize = this.edgesize - 1;
					}}
				}}
			}
		}
		MC++;
		make_changh();
		return remove;

	}
	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data remove=new Edge();
		if(this.allnode.get(src)==null||this.allnode.get(dest)==null) {
			System.out.println("there is no posibility to remove");
		}

		else {
			remove=(Edge)this.alledges.get(src).get(dest);
			this.alledges.get(src).remove(dest);

		}
		edgesize--;
		make_changh();
		MC++;
		return remove;
	}

	@Override
	public int nodeSize() {
		return allnode.size();
	}
	@Override
	public int edgeSize() {
		int ans =0;
			Iterator iter_node =allnode.values().iterator();
			while (iter_node.hasNext()){
				Node temp = (Node) iter_node.next();
				Collection <edge_data> e = getE(temp.getKey());
				if (e!=null){
				Iterator iter_edge = e.iterator();
				while (iter_edge.hasNext()){
					iter_edge.next();
					ans++;
				}}
			}
			return ans;
	}

	@Override
	public int getMC() {
		return MC;
	}

}
