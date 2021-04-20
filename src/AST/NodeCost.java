package AST;

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

}
