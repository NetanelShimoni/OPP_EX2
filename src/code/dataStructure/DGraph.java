package code.dataStructure;
import code.utils.Point3D;

import java.util.*;
public class DGraph implements graph {
	public HashMap<Integer, node_data> allnode = new HashMap<Integer, node_data>();
	public HashMap<Integer, HashMap<Integer, edge_data>> alledges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	static  int MC=0;
	private int edgesize=0;

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
		if (alledges.get(src).get(dest)==null){
			return null;
		}
	return alledges.get(src).get(dest);
}
	@Override
	public void addNode(node_data n) {
		allnode.put(n.getKey(),n);
		MC++;
	}
	@Override
	public void connect(int src, int dest, double w) {
			MC++;
			Edge a = new Edge(src, dest, w);
			if (this.alledges.get(src) == null) {
				this.alledges.put(src, new HashMap<Integer, edge_data>());
				this.alledges.get(src).put(dest, a);
			} else {
				this.alledges.get(src).put(dest, a);

			}

	}

	@Override
	public Collection<node_data> getV() {
		return allnode.values();
//		Collection<node_data> a= (Collection<node_data>) allnode;
//		return a;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if (this.alledges.get(node_id) == null) {
			return null;
		} else {
//		Collection<edge_data> a= (Collection<edge_data>) this.alledges.get(node_id);
//		return a;
			return alledges.get(node_id).values();
		}
	}

	@Override
	public node_data removeNode(int key) {
		MC++;
		Node y = (Node) allnode.get(key);
		if (y==null){
			return null;
		}
		Collection <edge_data> exit = new LinkedList<edge_data>();
		exit = getE(key);
		//Iterator it = exit.iterator();
		if (exit!=null) {
			Iterator it = alledges.get(key).values().iterator();
			while (it.hasNext()) {
				Edge u = (Edge) it.next();
				it.remove();
			}
			Iterator iter = allnode.values().iterator();
			while (iter.hasNext()) {
				Node a = (Node) iter.next();
				Collection<edge_data> join = new LinkedList<edge_data>();
				join = getE(a.getKey());
				if (join!=null){
				Iterator itr = join.iterator();
				while (itr.hasNext()) {
					Edge temp = (Edge) itr.next();
					if (temp.getDest() == key) {
						this.alledges.remove(temp);
						itr.remove();
						//this.alledges.get(temp.getSrc()).remove(temp.getDest()).;
					}
				}}


			}


		}
		allnode.remove(key);
		return y;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		MC++;
		if(alledges.get(src).get(dest)!=null){
			Edge a = new Edge();
			a= (Edge) alledges.get(src).get(dest);
			alledges.get(src).remove(dest);
			return a;
		}
		else{
			return null;
		}

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
