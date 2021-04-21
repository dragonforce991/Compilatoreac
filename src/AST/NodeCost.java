package AST;

import Visitor.IVisitor;

public class NodeCost extends NodeExpr {
	
	String value;
	LangType type;
	
	public NodeCost(String val, LangType type) {
		value = val;
		this.type = type;
	}
	
	@Override
	public String toString() {
		
		return "NodeCost " + this.type + " " + this.value;
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
	
	public String getValue() {
		return value;
	}
	public LangType getType() {
		return type;
	}

}
