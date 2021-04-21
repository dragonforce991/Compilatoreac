package AST;

import Visitor.IVisitor;
import symbolTable.Attributes;

public class NodeId extends NodeAST {
	private final String name;
	private Attributes description;

	
	
	public NodeId(String name) {

		super();
		this.name = name;
	}
	
	public String toString() {
		
		return "Node Id " + name;
	}
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
	
	public Attributes getDescription() {
		return description;
	}

	public void setDescription(Attributes description) {
		this.description = description;
	}
	
	public String getName(){
		return name;
	}
}
