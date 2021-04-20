package AST;

public class NodeDecl extends NodeDecSt{

	private final NodeId id;
	private final LangType type;
	
	
	public NodeDecl(NodeId id, LangType type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public NodeId getId() {
		return id;
	}
	public LangType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "NodeDecl " + id + " " + type;
	}
}
