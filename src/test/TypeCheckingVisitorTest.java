package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import AST.NodeProgram;
import Visitor.TypeCheckingVisitor;
import parser.Parser;
import parser.ParserException;
import scanner.Scanner;
import symbolTable.SymbolTable;

public class TypeCheckingVisitorTest {

	@Test
	public void test1() throws FileNotFoundException, ParserException {
		String path = "src/test/data/testParser/fileParserCorrect2.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		ArrayList<String> log = visitor.getLog();
		
		assertEquals(log.get(0),"Errore: La variabile stampa non è definita");
		assertEquals(log.get(1),"Errore: La variabile a non è definita");
		assertEquals(log.get(2),"Errore: La variabile b non è definita");
		assertEquals(log.get(3),"Errore: La variabile a non è definita");
		assertEquals(log.get(4),"Errore nell'espressione : Node Deref Node Id a NodeCost INTy 5");
		
	}

	@Test
	public void test2() throws FileNotFoundException, ParserException {
		String path = "src/test/data/testParser/fileParserCorrect3.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		ArrayList<String> log = visitor.getLog();
		
		assertEquals(log.get(0),"Errore: La variabile id non è definita");
		assertEquals(log.get(1),"Errore: La variabile id non è definita");
		assertEquals(log.get(2),"Errore nell'espressione : Node Deref Node Id id NodeCost FLOATy 5.0");
		assertEquals(log.get(3),"Errore: La variabile id non è definita");
		assertEquals(log.get(4),"Errore nell'espressione : Node Deref Node Id id NodeCost INTy 5");
		assertEquals(log.get(5),"Errore: La variabile id non è definita");
		assertEquals(log.get(6),"Errore: La variabile id non è definita");
		assertEquals(log.get(7),"Errore nell'espressione : Node Deref Node Id id Node Deref Node Id id");
		assertEquals(log.get(8),"Errore: La variabile id non è definita");
		assertEquals(log.get(9),"Errore nell'espressione : Node Deref Node Id id NodeCost INTy 5");
		assertEquals(log.get(10),"Errore nell'espressione : NodeBinOp Node Deref Node Id id PLUS NodeCost INTy 5 NodeBinOp NodeBinOp NodeConvert NodeCost INTy 8 TIMES NodeCost FLOATy 6.0 DIV NodeConvert NodeCost INTy 2");
		assertEquals(log.get(11),"Errore: La variabile id non è definita");
		assertEquals(log.get(12),"Errore nell'espressione : Node Deref Node Id id NodeCost INTy 5");
		assertEquals(log.get(13),"Errore nell'espressione : NodeBinOp Node Deref Node Id id TIMES NodeCost INTy 5 NodeBinOp NodeCost FLOATy 8.0 TIMES NodeConvert NodeCost INTy 6");
		assertEquals(log.get(14),"Errore nell'espressione : NodeBinOp NodeBinOp Node Deref Node Id id TIMES NodeCost INTy 5 MINUS NodeBinOp NodeCost FLOATy 8.0 TIMES NodeConvert NodeCost INTy 6 NodeCost INTy 2");
		assertEquals(log.get(15),"Errore: La variabile ok non è definita");
	}
	
	@Test
	public void test3() throws FileNotFoundException, ParserException{
		String path = "src/test/data/testParser/testDec.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		ArrayList<String> log = visitor.getLog();
		assertEquals(log.size(),0);
	}
	
	@Test
	public void test4() throws FileNotFoundException, ParserException{
		String path = "src/test/data/TypeCheckingVisitor/file1.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		ArrayList<String> log = visitor.getLog();
		assertEquals(log.size(),0);
	}
	@Test
	public void test5() throws FileNotFoundException, ParserException{
		String path = "src/test/data/TypeCheckingVisitor/file2_conversione.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		assertTrue(np.toString().contains("NodeConvert"));
		
	}
	
	@Test
	public void test6() throws FileNotFoundException, ParserException{
		String path = "src/test/data/TypeCheckingVisitor/file3_erroreconversione.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		for(String s : visitor.getLog())
			assertEquals(s,"Errore nell'assegnamento. tipi non compatibili INTD FLOATD");
		
	}
} 
