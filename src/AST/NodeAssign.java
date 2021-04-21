package AST;

import Visitor.IVisitor;

public class NodeAssign extends NodeStm {
	
	NodeId id;
	NodeExpr expr;
	public NodeAssign(NodeId nodeId, NodeExpr expr2) {
		this.id = nodeId;
		this.expr = expr2;
	}
	
	@Override
	public String toString() {
			
		return "NodeAssign " + id.toString() + " " + expr.toString();
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
	
}
