package Visitor;

import java.util.HashMap;

import AST.LangOper;
import AST.NodeAssign;
import AST.NodeBinOp;
import AST.NodeConvert;
import AST.NodeCost;
import AST.NodeDecSt;
import AST.NodeDecl;
import AST.NodeDeref;
import AST.NodeId;
import AST.NodePrint;
import AST.NodeProgram;
import AST.TypeDescriptor;
import symbolTable.SymbolTable;

public class CodeGeneratorVisitor implements IVisitor {

	private String codice;
	private char [] registerName;
	private int registerIndex;
	private HashMap<LangOper,String> mapOp;
	private String Error;
	private boolean is5k = false;
	
	public CodeGeneratorVisitor() {
		this.registerName= new char[52];
		this.codice = "";
		this.Error = "";
		this.registerIndex=0;
		for(int i = 0; i<26; i++) {
			this.registerName[i] = (char) (i+65);
		}
		for(int i = 0; i<26; i++) {
			this.registerName[i+26] = (char) (i+97);
		}
		mapOp = new HashMap<LangOper,String>();
		mapOp.put(LangOper.PLUS, "+");
		mapOp.put(LangOper.MINUS, "-");
		mapOp.put(LangOper.DIV, "/");
		mapOp.put(LangOper.TIMES, "*");
		
	}
	private char newRegister() throws RegisterException{

		if(registerIndex == 52) {
			throw new RegisterException("too many variables");
		} 
		return this.registerName[registerIndex++];
	}
	@Override
	public void visit(NodeProgram node) {
		for(NodeDecSt n : node) {
			if(is5k) {
				codice +="0 k ";
				is5k = false;
			}
				
			n.accept(this);
		}
		
		if(!this.Error.equals(""))
			this.codice = "";
	}

	@Override
	public void visit(NodeBinOp node) {
		node.getLeft().accept(this);
		node.getRight().accept(this);
		this.codice += mapOp.get(node.getOp()) + " ";

	}

	@Override
	public void visit(NodeCost node) {
		this.codice += node.getValue() + " ";
	}

	@Override
	public void visit(NodeDecl node)  {
		try {
			node.getId().getDescription().setRegistro(newRegister());
		}catch(RegisterException e ) {
			this.Error += "Errore. Raggiunto il numero massimo di variabili " + node.getId().getName();
		}
		
	}

	@Override
	public void visit(NodeDeref node) {
		codice+= "l"+node.getId().getDescription().getRegistro() + " ";

	}

	@Override
	public void visit(NodePrint node) {
		codice += "l" + node.getId().getDescription().getRegistro() + " ";
		codice += "p P ";
	}

	@Override
	public void visit(NodeId node) {
		
	}

	@Override
	public void visit(NodeAssign node) {
		node.getExpr().accept(this);
		codice+="s" + node.getNodeId().getDescription().getRegistro()+ " "; 

	}

	@Override
	public void visit(NodeConvert node) {
		
		node.getExpr().accept(this);
		codice += "5 k ";
		is5k = true;
	}
	public String getCodice() {
		
		return codice;
	}
	public String getError() {
		return this.Error;
	}

}
