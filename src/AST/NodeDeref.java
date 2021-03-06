package AST;

import Visitor.IVisitor;

public class NodeDeref extends NodeExpr{


	private NodeId id;
	
	public NodeDeref(NodeId nodeId) {
		this.id = nodeId;
	}
	
	@Override
	public String toString() {
		return "Node Deref " + id.toString();	
	}
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
	
	public NodeId getId () {
		return id;
	}
	
}
