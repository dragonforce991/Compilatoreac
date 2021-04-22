package AST;

import Visitor.IVisitor;

public class NodeConvert extends NodeExpr {
	private final NodeExpr expr;


	public NodeConvert(NodeExpr expr) {
		this.expr = expr;
	}

	
	public NodeExpr getExpr() {
		return expr;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
			
		return "NodeConvert " + expr.toString();
	}
	

}
