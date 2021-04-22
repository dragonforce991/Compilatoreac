package Visitor;

import java.util.ArrayList;

import AST.LangType;
import AST.NodeAssign;
import AST.NodeBinOp;
import AST.NodeConvert;
import AST.NodeCost;
import AST.NodeDecSt;
import AST.NodeDecl;
import AST.NodeDeref;
import AST.NodeExpr;
import AST.NodeId;
import AST.NodePrint;
import AST.NodeProgram;
import AST.TypeDescriptor;
import symbolTable.Attributes;
import symbolTable.SymbolTable;

public class TypeCheckingVisitor implements IVisitor {

	private ArrayList<String> log;
	

	public ArrayList<String> getLog(){
		return log;
	}
	
	public TypeCheckingVisitor() {
		log = new ArrayList<String>();
	}
	
	@Override
	public void visit(NodeProgram node) {
		TypeDescriptor res = TypeDescriptor.VOID;
		
		for(NodeDecSt n : node) {
			n.accept(this);
			if(n.getResType() == TypeDescriptor.ERROR)
				res = TypeDescriptor.ERROR;
		}
		
		node.setResType(res);
		
	}

	@Override
	public void visit(NodeBinOp node) {
		
		NodeExpr left = node.getLeft();
		NodeExpr right = node.getRight();
		
		left.accept(this);
		right.accept(this);
		int comp = compatible(left.getResType(),right.getResType());
		switch(comp) {
		case 0: node.setResType(TypeDescriptor.ERROR); log.add("Errore nell'espressione : " + left.toString() + " " + right.toString() + ""); break;
		case 1: node.setResType(left.getResType()); break;
		case 2: 
		case 3: node.setLeft(convertExpr(left)); node.setRight(convertExpr(right)); node.setResType(TypeDescriptor.FLOATD); break;
		}

	}

	@Override
	public void visit(NodeCost node) {
		if(node.getType() == LangType.FLOATy)
			node.setResType(TypeDescriptor.FLOATD);
		else node.setResType(TypeDescriptor.INTD);
		
	}

	@Override
	public void visit(NodeDecl node) {
		NodeId id = node.getId();
		 
		if(SymbolTable.lookup(id.getName()) != null) {
			node.setResType(TypeDescriptor.ERROR);
			log.add("La variabile " + id.getName() + " è già definita");
			
		}
		else {
			node.setResType(TypeDescriptor.VOID);
			
			Attributes attr = new Attributes(node.getType());
			SymbolTable.enter(id.getName(),attr);
			id.setDescription(attr);
		}

	}

	@Override
	public void visit(NodeDeref node) {
		node.getId().accept(this);
		node.setResType(node.getId().getResType());

	}

	@Override
	public void visit(NodePrint node) {
		node.getId().accept(this);
		if(node.getId().getResType() != TypeDescriptor.ERROR)
			node.setResType(TypeDescriptor.VOID);
		else {
			node.setResType(TypeDescriptor.ERROR);
		}
			

	}

	@Override
	public void visit(NodeId node) {
		
		String nome = node.getName();
		
		if(SymbolTable.lookup(nome) == null ) {
			node.setResType(TypeDescriptor.ERROR);
			log.add("Errore: La variabile " + nome + " non è definita");
		}else {
			Attributes type = SymbolTable.lookup(nome);
			if(type.getType().equals(LangType.INTy))
				node.setResType(TypeDescriptor.INTD);
			else if (type.getType().equals(LangType.FLOATy))
					node.setResType(TypeDescriptor.FLOATD);
			node.setDescription(type);
		}
		 
	}

	@Override
	public void visit(NodeAssign node) {
		NodeId id = node.getNodeId();
		NodeExpr expr = node.getExpr();
		
		id.accept(this);
		expr.accept(this);
		
		int comp = compatible(id.getResType(),expr.getResType());
		switch(comp) {
		case 0: node.setResType(TypeDescriptor.ERROR); break;
		case 1: node.setResType(TypeDescriptor.VOID); break;
		case 2: node.setExpr(convertExpr(expr)); node.setResType(TypeDescriptor.VOID); break;
		case 3: node.setResType(TypeDescriptor.ERROR); log.add("Errore nell'assegnamento. tipi non compatibili " + id.getResType() + " " + expr.getResType()); break;
		}
		
	}

	@Override
	public void visit(NodeConvert node) {
		
	}

	
	private int compatible(TypeDescriptor t1, TypeDescriptor t2) {
		if(t1.equals(TypeDescriptor.ERROR) || t2.equals(TypeDescriptor.ERROR)) return 0;
		if(t1.equals(t2)) return 1;
		if(t1.equals(TypeDescriptor.FLOATD) && t2.equals(TypeDescriptor.INTD)) return 2;
		else return 3;


	}
	
	private NodeExpr convertExpr(NodeExpr node ) {
		if(node.getResType().equals(TypeDescriptor.FLOATD))
			return node;
		else {
			NodeConvert nodeConvert = new NodeConvert(node);
			nodeConvert.setResType(TypeDescriptor.FLOATD);
			return nodeConvert;
		}
		
	}
}
