package AST;

public class NodeDeref extends NodeExpr{


	NodeId id;
	
	public NodeDeref(NodeId nodeId) {
		this.id = nodeId;
	}
	
	@Override
	public String toString() {
		return "Node Deref " + id.toString();	
	}
	
}
