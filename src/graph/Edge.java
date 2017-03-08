package graph;

/**
 * An edge connects two nodes together. A source node and a target node.
 * @author Aaron Zimmermann
 *
 */
public class Edge {
	
	/**
	 * First 16 high bits are all 0's, and the last 16 low bits all 1's
	 * This constant is used in generating the hashCode (unique ID) for an edge.
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
	 * Unique ID used to identify any given edge. Do not modify this, is set by the CTOR. 
	 */
	private int id;
	
	/**
	 * CTOR
	 * Creates a new edge connecting the two given nodes.
	 * @param p_source The first node.
	 * @param p_target The second node.
	 */
	public Edge(Node p_source, Node p_target) {
		source = p_source;
		target = p_target;
		
		// Calculate id high bits
		int a = p_source.hashCode();
		a = a << 16;
		
		// Calculate id low bits
		int b = p_target.hashCode();
		b = b & MAGIC_NUMBER;
		
		// Save the id
		id = a | b;
	}
	
	/**
	 * Returns the unique id for this edge. The id is an int type where the first 16 bits (high bits) is the
	 * id of the source node and the last 16 bits (low bits) is the id of the target node.
	 * @return The unique id of this edge.
	 */
	public int hashCode() {
		return id;
	}
	
	/**
	 * Two edges are equal if they have the same hashCode.
	 */
	public boolean equals(Object p_obj) {
		return hashCode() == p_obj.hashCode();
	}
}
