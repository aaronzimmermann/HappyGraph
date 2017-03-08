package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A graph contains a set of nodes and edges.
 * @author Aaron Zimmermann
 *
 */
public class Graph {
	
	/**
	 * The default weight of an edge.
	 */
	private static final double DEFAULT_WEIGHT = 1.0;
	
	/**
	 * All created nodes are stored here.
	 */
	protected HashSet<Node> nodes = new HashSet<Node>();
	
	/**
	 * All created edges are stored here.
	 */
	protected HashSet<Edge> edges = new HashSet<Edge>();
	
	/**
	 * Edge weights are stored here. We map the longHashCode of an edge to it's weight.
	 */
	protected HashMap<Integer, Double> weights = new HashMap<Integer, Double>();
	
	/**
	 * If true then the graph is directed. If false then the graph is undirected.
	 */
	private boolean isDirectedGraph = false;
	
	/**
	 * Keeps track of the next ID to be assigned to a node.
	 */
	private short nodeIdCounter = Short.MIN_VALUE;
	
	/**
	 * CTOR
	 * Creates a new graph.
	 */
	public Graph(boolean p_isDirectedGraph) {
		isDirectedGraph = p_isDirectedGraph;
	}
	
	/**
	 * Generates a new node id and returns the result.
	 * This method should only be used inside the CTOR of Node.
	 * @return The next node id.
	 */
	public short getNextNodeID() {
		return nodeIdCounter++;
	}
	
	/**
	 * Creates a node with the given name and adds it to the graph.
	 * The node id will be calculated and assigned to the new node.
	 * Note that you can only create 65535 nodes per graph.
	 * @param p_name An arbitrary name for the node. This is optional.
	 * @return The new node object.
	 */
	public Node createNode(String p_name) {
		Node n = new Node(p_name, this);
		addNode(n);
		return n;
	}
	
	/**
	 * Adds a node to the graph. If the node is already in the graph it will not be added again.
	 * @param p_node The node to be added.
	 */
	public void addNode(Node p_node) {
		nodes.add(p_node);
	}
	
	/**
	 * Checks if the given node is already in the graph. 
	 * Returns true if the given node is in the graph.
	 * @param p_node A given node.
	 * @return If the given node is in the graph.
	 */
	public boolean hasNode(Node p_node) {
		return nodes.contains(p_node);
	}
	
	/**
	 * Removes a node from the graph.
	 * Note that this will not remove any edges that connect this node.
	 * @param p_node The node to be removed.
	 */
	public void removeNode(Node p_node) {
		nodes.remove(p_node);
	}
	
	/***
	 * Return an iterator for the nodes.
	 * You can use this if you need to iterate through the nodes.
	 * @return The iterator for the nodes.
	 */
	public Iterator<Node> getNodeIter() {
		return nodes.iterator();
	}
	
	/**
	 * Get the number of nodes in the graph.
	 * @return The number of nodes in the graph.
	 */
	public int getNumNodes() {
		return nodes.size();
	}
	
	/**
	 * Adds an edge to the graph connecting the two input nodes and sets the default weight.
	 * Note that if the edge is already in the graph it will not be added again and the weight will be unchanged.
	 * @param p_a A node.
	 * @param p_b A node.
	 * @return The edge object.
	 */
	public Edge addEdge(Node p_a, Node p_b) {
		Edge edge = makeEdge(p_a, p_b);
		if(edges.add(edge)) {
			
			// The weight is only updated if the edge was not in the graph
			weights.put(edge.hashCode(), DEFAULT_WEIGHT);
		}
		return edge;
	}
	
	/**
	 * Checks if an edge joining the two nodes is already in the graph.
	 * @param p_a A node.
	 * @param p_b A node.
	 * @return True if it is in the graph, false otherwise.
	 */
	public boolean hasEdge(Node p_a, Node p_b) {
		Edge edge = makeEdge(p_a, p_b);
		return edges.contains(edge);
	}
	
	/**
	 * Checks if the given edge is already in the graph.
	 * @param p_a A node.
	 * @param p_b A node.
	 * @return True if it is in the graph, false otherwise.
	 */
	public boolean hasEdge(Edge p_edge) {
		return edges.contains(p_edge);
	}
	
	/**
	 * Removed an edge joining the two nodes.
	 * Note that this will not remove the edge weight.
	 * @param p_a A node.
	 * @param p_b A node.
	 */
	public void removeEdge(Node p_a, Node p_b) {
		Edge edge = makeEdge(p_a, p_b);
		edges.remove(edge);
	}
	
	/**
	 * Removed the given edge.
	 * Note that this will not remove the edge weight.
	 * @param p_node The node to be removed.
	 */
	public void removeEdge(Edge p_edge) {
		edges.remove(p_edge);
	}
	
	/***
	 * Return an iterator for the edges.
	 * You can use this if you need to iterate through the nodes.
	 * @return The iterator for the edges.
	 */
	public Iterator<Edge> getEdgeIter() {
		return edges.iterator();
	}
	
	/**
	 * Get the number of edges in the graph
	 */
	public int getNumEdges() {
		return edges.size();
	}
	
	/**
	 * Sets the weight of the edge joining the two nodes. If the edge is not in the graph, a weight for that edge will be set anyway.
	 * @param p_a A node.
	 * @param p_b A node.
	 * @param p_weight The new weight of this edge.
	 */
	public void setEdgeWeight(Node p_a, Node p_b, double p_weight) {
		
		// We need to use the makeEdge method to make a new edge
		// So we can ensure we generate an edge with consistent hashCodes
		Edge edge = makeEdge(p_a, p_b);
		int id = edge.hashCode();
		weights.put(id, p_weight);
	}
	
	/**
	 * Sets the weight of the given edge. If the edge is not in the graph, a weight for that edge will be set anyway.
	 * @param p_edge An edge.
	 * @param p_weight The new weight of this edge.
	 */
	public void setEdgeWeight(Edge p_edge, double p_weight) {
		int id = p_edge.hashCode();
		weights.put(id, p_weight);
	}
	
	/**
	 * Gets the weight of the edge connecting the two nodes. Returns null if the edge has no weight set.
	 * @param p_a A node.
	 * @param p_b A node.
	 * @return The weight.
	 */
	public Double getEdgeWeight(Node p_a, Node p_b) {
		Edge edge = makeEdge(p_a, p_b);
		int id = edge.hashCode();
		return weights.get(id);
	}	
	
	/**
	 * Gets the weight of the edge. Returns null if the edge has no weight set.
	 * @param p_edge An edge.
	 * @return The weight.
	 */
	public Double getEdgeWeight(Edge p_edge) {
		int id = p_edge.hashCode();
		return weights.get(id);
	}
	
	/**
	 * Removes the weight of the edge connecting the two given nodes.
	 * @param p_a A node.
	 * @param p_b A node.
	 */
	public void removeEdgeWeight(Node p_a, Node p_b) {
		Edge edge = makeEdge(p_a, p_b);
		weights.remove(edge);
	}
	
	/**
	 * Removes the weight of the given edge.
	 * @param p_edge An edge.
	 */
	public void removeEdgeWeight(Edge p_edge) {
		weights.remove(p_edge);
	}
	
	/**
	 * A factory method to create edge objects.
	 * If the graph is directed then we make the first input node the source node and the second input node the target node.
	 * If the graph is undirected then we make the node with the lowest id the source node, and the other node the target node.
	 * @param p_a A node.
	 * @param p_b A node.
	 * @return An edge connecting the two nodes.
	 */
	protected Edge makeEdge(Node p_a, Node p_b) {
		if(!isDirectedGraph && p_a.hashCode() > p_b.hashCode()) {
			return new Edge(p_b, p_a);
		} else {
			return new Edge(p_a, p_b);
		}
	}
}
