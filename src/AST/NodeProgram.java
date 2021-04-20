package AST;

import java.util.ArrayList;

public class NodeProgram extends NodeAST {
	private final ArrayList<NodeDecSt> decSts;

	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		super();
		this.decSts = decSts;
	}
	
	public NodeProgram() {
		super();
		this.decSts = new ArrayList<NodeDecSt>();
	}
	@Override
	public String toString() {
		String s="";
		for (NodeDecSt nds: decSts)
			s = s+nds.toString()+"\n";
		return s;
	}

	
}
