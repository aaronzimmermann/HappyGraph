package graph;

/**
 * A unique vertex in the graph.
 * Note that we can only create 65535 node objects because we use a short value and a counter to assign a unique value to each node object.
 * @author Aaron Zimmermann
 *
 */
public class Node {
	
	/**
	 * Keeps track of the next ID to be assigned to a node.
	 */
	private static short ID_COUNTER = Short.MIN_VALUE;
	
	/**
	 * An arbitrary name for the node.
	 */
	public String name = "";
	
	/**
	 * Unique ID used to identify this node. Do not modify this, is set by the CTOR. 
	 */
	private short id;
	
	/**
	 * CTOR
	 * Note that calling the constructor will increment the counter used to assign a unique value to a node.
	 * We can only create 65535 node objects.
	 * @param p_name
	 */
	public Node(String p_name) {
		name = p_name;
		id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	/**
	 * We use the id of the node to identify it.
	 */
	public int hashCode() {
		return id;
	}
	
	/**
	 * Two nodes are equal if they have the same hashCode.
	 */
	public boolean equals(Object p_obj) {
		return hashCode() == p_obj.hashCode();
	}
	
	/**
	 * Resets the counter used to assign unique values to each node object.
	 * If the counter is never reset then we can only create 65535 node objects.
	 * This is useful if we want to for example, create a second graph instance and do not need to compare any of the nodes
	 * or edges from either graph instance.
	 */
	public static void resetCounter() {
		ID_COUNTER = Short.MIN_VALUE;
	}
}
