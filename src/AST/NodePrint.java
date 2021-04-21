package AST;

import Visitor.IVisitor;

public class NodePrint extends NodeStm {


	private final NodeId id;

	public NodePrint(NodeId id) {
		super();
		this.id = id;
	}
	
	public NodeId getId() {
		return id;
	}

	@Override
	public String toString() {
		return "NodePrint " + id.toString();
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
}
