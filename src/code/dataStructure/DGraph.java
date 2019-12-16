package code.dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	HashMap<Integer,node_data> allnode =new HashMap<Integer, node_data>();
	HashMap<Integer,edge_data> alledge =new HashMap<Integer, edge_data>();


	@Override
	public node_data getNode(int key) {
	if(allnode.get(key)==null){
		return null;
	}else {
		return allnode.get(key);
	}
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if
		return null;
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
