package AST;

import Visitor.IVisitor;

public class NodeBinOp extends NodeExpr {
	LangOper op;
	NodeExpr left;
	NodeExpr right;
	public NodeBinOp(LangOper op, NodeExpr left, NodeExpr right) {
		super();
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "NodeBinOp " + left.toString() + " "+op +" " + right.toString();
	}
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
	
	
	public LangOper getOp() {
		return op;
	}
	
	public NodeExpr getLeft() {
		return left;
	}
	public NodeExpr getRight() {
		return right;
	}
	
	
	public void setLeft(NodeExpr left) {
		this.left = left;
	}
	public void setRight(NodeExpr right) {
		this.right = right;
	}
}
