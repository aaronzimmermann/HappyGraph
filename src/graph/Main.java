package graph;

import java.util.Iterator;

public class Main {
	
	/**
	 * Example usage
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create a new Graph
		Graph g = new Graph();
		
		// Create some new nodes
		Node n1 = g.addNode("NSW");
		Node n3 = g.addNode("VIC");
		Node n8 = g.addNode("QLD");
		Node n6 = g.addNode("WA ");
		Node n5 = g.addNode("SA ");
		Node n4 = g.addNode("TAS");
		Node n7 = g.addNode("NT ");
		Node n2 = g.addNode("ACT");		
		
		// Create some edges
		Edge e1 = g.addEdge(n1, n4);
		Edge e2 = g.addEdge(n1, n2);
		Edge e3 = g.addEdge(n3, n4);
		Edge e4 = g.addEdge(n3, n5);
		Edge e5 = g.addEdge(n6, n3);
		Edge e6 = g.addEdge(n7, n8);
		Edge e7 = g.addEdge(n8, n1);
		Edge e8 = g.addEdge(n1, n3);
		
		// Set some edge weights
		g.setEdgeWeight(e1, 2);
		g.setEdgeWeight(e2, 8);
		g.setEdgeWeight(e3, 0);
		g.setEdgeWeight(e4, 8);
		g.setEdgeWeight(e5, 6);
		g.setEdgeWeight(e6, 2);
		g.setEdgeWeight(e7, 4);
		g.setEdgeWeight(e8, 6);
		
		// Iterate through all nodes
		Iterator<Node> itern = g.getNodeIter();
		while(itern.hasNext()) {
			Node n = itern.next();
			System.out.println(n.name);
		}
		
		// Iterate through all edges
		Iterator<Edge> itere = g.getEdgeIter();
		while(itere.hasNext()) {
			Edge e = itere.next();
			System.out.println(e.source.name + " --- " + g.getEdgeWeight(e) + " --- " + e.target.name);
		}
	}
}
