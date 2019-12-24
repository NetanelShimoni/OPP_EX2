package code.dataStructure;
import java.util.*;

public class DGraph implements graph {
	public HashMap<Integer, node_data> allnode = new HashMap<Integer, node_data>();
	public ArrayList<edge_data> edges = new ArrayList<edge_data>();
	public HashMap<Integer, HashMap<Integer, edge_data>> alledges = new HashMap<Integer, HashMap<Integer, edge_data>>();

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
		
	}
	@Override
	public void connect(int src, int dest, double w) {



			Edge_data a = new Edge_data(src, dest, w);
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

		for (Map.Entry entry: allnode.entrySet())
		{
			if(alledges.get(entry)!=null){
				if (alledges.get(entry).get(key)!=null){
					alledges.get(entry).remove(key);
				}
		}


		}
		Node y = (Node) allnode.get(key);
		allnode.put(key,null);
		alledges.remove(key);


		return y;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(alledges.get(src).get(dest)!=null){
			Edge_data a = new Edge_data();
			a= (Edge_data) alledges.get(src).get(dest);
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
		return edges.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
