package graph;

/**
 * A single node (vertex) in the graph.
 * Note that you can only create 65535 node objects per graph because we use a
 * short value type and a counter to assign a unique value to each node object.
 * @author Aaron Zimmermann
 *
 */
public class Node {
	
	/**
	 * An arbitrary name for the node if required.
	 * This is optional.
	 */
	public String name = "";
	
	/**
	 * Unique ID used to identify any given node. Do not modify this, is set by the CTOR. 
	 */
	private short id;
	
	/**
	 * CTOR
	 * This creates a new node with the given name belonging to the given graph.
	 * Note this should only be called from createNode inside of Graph.
	 * Note that calling the constructor will increment the counter used to assign a unique value to a node.
	 * We can only create 65535 node objects.
	 * @param p_name An arbitrary name for the node.
	 * @param p_graph The graph which this node belongs to..
	 */
	public Node(String p_name, Graph p_graph) {
		name = p_name;
		id = p_graph.getNextNodeID();
	}
	
	/**
	 * We use the short type id of the node to identify it.
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
}
