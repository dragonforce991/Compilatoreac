package AST;

public class NodeId extends NodeAST {
	private final String name;

	public String getName (){
		return name;
	}
	
	public NodeId(String name) {

		super();
		this.name = name;
	}
	
	public String toString() {
		
		return name;
	}
}
