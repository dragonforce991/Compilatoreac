package AST;

import java.util.ArrayList;
import java.util.Iterator;

import Visitor.IVisitor;

public class NodeProgram extends NodeAST implements Iterable<NodeDecSt>{
	private final ArrayList<NodeDecSt> decSts;

	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		super();
		this.decSts = decSts;
	}

	@Override
	public String toString() {
		String s="Node Program\n";
		for (NodeDecSt nds: decSts)
			s = s+nds.toString()+"\n";
		return s;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}

	@Override
	public Iterator<NodeDecSt> iterator() {
		return decSts.iterator();
	}
	

	
}
