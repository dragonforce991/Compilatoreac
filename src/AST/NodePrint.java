package AST;

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
	
	
}
