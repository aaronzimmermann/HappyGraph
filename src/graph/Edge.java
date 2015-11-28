package graph;

import java.util.HashMap;

/**
 * Connects two nodes together.
 * @author Aaron Zimmermann
 *
 */
public class Edge {
	
	/**
	 * First 16 high bits are all 0's, and the last 16 low bits all 1's
	 */
	private static final int MAGIC_NUMBER = 65535;
	
	/**
	 * The first node in the edge.
	 */
	public Node source;
	
	/**
	 * The second node in the edge.
	 */
	public Node target;
	
	/**
	 * CTOR
	 * @param p_source The first node.
	 * @param p_target The second node.
	 */
	public Edge(Node p_source, Node p_target) {
		
		// Save joining nodes
		source = p_source;
		target = p_target;
	}
	
	/**
	 * Returns a unique id for this edge. The id is an integer number where the first 16 bits (high bits) is the
	 * id of the source node and the last 16 bits (low bits) is the id of the target node.
	 * @return The id of the edge.
	 */
	public int hashCode() {
		
		// High bits
		int a = source.hashCode();
		a = a << 16;
		
		// Low bits
		int b = target.hashCode();
		b = b & MAGIC_NUMBER;
		
		// Return id
		return a | b;
	}
	
	/**
	 * Two edges are equal if they have the same hashCode.
	 */
	public boolean equals(Object p_obj) {
		return hashCode() == p_obj.hashCode();
	}
}
