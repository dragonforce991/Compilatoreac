package Visitor;

import java.util.ArrayList;

import AST.NodeAssign;
import AST.NodeBinOp;
import AST.NodeConvert;
import AST.NodeCost;
import AST.NodeDecl;
import AST.NodeDeref;
import AST.NodeId;
import AST.NodePrint;
import AST.NodeProgram;

public interface IVisitor {
	
	public abstract void visit(NodeProgram node);
	public abstract void visit(NodeBinOp node);
	public abstract void visit(NodeCost node);
	public abstract void visit(NodeDecl node);
	public abstract void visit(NodeDeref node);
	public abstract void visit(NodePrint node);
	public abstract void visit(NodeId node);
	public abstract void visit(NodeAssign node);
	public abstract void visit(NodeConvert node);
}
