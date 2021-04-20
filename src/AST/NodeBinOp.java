package AST;

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
}
