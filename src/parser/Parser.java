package parser;

import token.Token;
import token.TokenType;
import scanner.Scanner;
import scanner.ScannerException;

import java.util.ArrayList;

import AST.*;
public class Parser {
	
	private  Scanner scanner;
	
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	private Token match(TokenType type) throws ParserException {
		try {
			Token tk = scanner.peekToken();
			if(type.equals(tk.getTipo())) return scanner.nextToken();
			throw new ParserException("Expected " + type + " found " + tk.getTipo() + " at line : " + tk.getRiga());

		}catch(ScannerException e) {
			throw new ParserException("ScannerException", e);
		}
		
	}
	public NodeProgram parse() throws ParserException {
		return parsePrg();
	}
	private NodeProgram parsePrg() throws ParserException{
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case TYFLOAT:
		case TYINT:
		case ID:
		case PRINT:
		case EOF:
			ArrayList<NodeDecSt> nodeProgram = parseDSs();
			match(TokenType.EOF);
			return new NodeProgram(nodeProgram);
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
	
	private ArrayList<NodeDecSt> parseDSs() throws ParserException {
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case TYINT:
		case TYFLOAT:
			NodeDecl nt = parseDcl();
			ArrayList<NodeDecSt> nodeDecSt = parseDSs();
			nodeDecSt.add(0,nt);
			return nodeDecSt;
		case ID:
		case PRINT:
			NodeStm stm = parseStm();
			ArrayList<NodeDecSt> nodeDecSt1 = parseDSs();
			nodeDecSt1.add(0,stm);
			return nodeDecSt1;
		case EOF:
			return new ArrayList<NodeDecSt>();
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	private NodeDecl parseDcl() throws ParserException{
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case TYFLOAT:
			match(TokenType.TYFLOAT);
			Token t = match(TokenType.ID);
			match(TokenType.SEMI);
			return new NodeDecl(new NodeId(t.getVal()), LangType.FLOATy);
			
		case TYINT:
			match(TokenType.TYINT);
			Token t1 = match(TokenType.ID);
			match(TokenType.SEMI);
			return new NodeDecl(new NodeId(t1.getVal()),LangType.INTy);
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
	private NodeStm parseStm() throws ParserException {
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case ID:
			match(TokenType.ID);
			match(TokenType.ASSIGN);
			NodeExpr expr = parseExp();
			match(TokenType.SEMI);
			return new NodeAssign(new NodeId(tk.getVal()),expr);
		case PRINT:
			match(TokenType.PRINT);
			Token t = match(TokenType.ID);
			match(TokenType.SEMI);
			return new NodePrint(new NodeId(t.getVal()));
			
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
	private NodeExpr parseExp() throws ParserException {
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case INT:
		case FLOAT:
		case ID:
			NodeExpr Tr = parseTr();
			return parseExpP(Tr);
			
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	private NodeExpr parseExpP(NodeExpr left) throws ParserException{
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case PLUS:
			match(TokenType.PLUS);
			NodeExpr Tr =parseTr();
			NodeExpr res =parseExpP(new NodeBinOp(LangOper.PLUS, left, Tr));
			return res;
		case MINUS:
			match(TokenType.MINUS);
			NodeExpr Tr1 = parseTr();
			NodeExpr res1 = parseExpP(new NodeBinOp(LangOper.MINUS, left, Tr1));
			return res1;
		case SEMI:
			return left;
		
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
	private NodeExpr parseTr() throws ParserException {
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case INT:
		case FLOAT:
		case ID:
			NodeExpr valNode = parseVal();
			return parseTrP(valNode);
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
	private NodeExpr parseTrP(NodeExpr left) throws ParserException {
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case TIMES:
			match(TokenType.TIMES);
			NodeExpr val = parseVal();
			return parseTrP(new NodeBinOp(LangOper.TIMES,left,val));
			
		case DIV:
			match(TokenType.DIV);
			NodeExpr val1 =parseVal();
			return parseTrP(new NodeBinOp(LangOper.DIV,left,val1));
		
		case PLUS:
		case SEMI:
		case MINUS:
			return left;
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
	private NodeExpr parseVal() throws ParserException {
		Token tk;
		try {
			tk = scanner.peekToken();
		} catch (ScannerException e) {
			
			throw new ParserException("Scanner Exception", e);
		}
		switch(tk.getTipo()) {
		case INT:
			match(TokenType.INT);
			return new NodeCost(tk.getVal(), LangType.INTy);
		case FLOAT:
			match(TokenType.FLOAT);
			return new NodeCost(tk.getVal(),LangType.FLOATy);
		case ID:
			match(TokenType.ID);
			return new NodeDeref(new NodeId(tk.getVal()));
		default:
			break;
		}
		
		throw new ParserException("Found " + tk.getTipo() + " at line " + tk.getRiga());
	}
	
}	
