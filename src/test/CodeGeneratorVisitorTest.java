package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import AST.NodeProgram;
import Visitor.CodeGeneratorVisitor;
import Visitor.RegisterException;
import Visitor.TypeCheckingVisitor;
import parser.Parser;
import parser.ParserException;
import scanner.Scanner;
import symbolTable.SymbolTable;

public class CodeGeneratorVisitorTest {

	@Test
	public void test1() throws FileNotFoundException, ParserException {
		String path = "src/test/data/testCodeGenerator/file1.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		CodeGeneratorVisitor codeGenVisitor = new CodeGeneratorVisitor();
		np.accept(codeGenVisitor);
		assertEquals(codeGenVisitor.getCodice(),"1.0 6 5 k / sB 0 k lB p P 1 6 / sA lA p P ");
	}
	
	@Test
	public void test2() throws FileNotFoundException, ParserException {
		String path = "src/test/data/testCodeGenerator/file2.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		CodeGeneratorVisitor codeGenVisitor = new CodeGeneratorVisitor();
		np.accept(codeGenVisitor);
		assertEquals(codeGenVisitor.getCodice(),"2 5 k sB 0 k lB 1.2 + 14 2 * 5 k 3.5 / + sA 0 k lA p P ");
	}
	
	@Test
	public void test3() throws FileNotFoundException, ParserException {
		String path = "src/test/data/testCodeGenerator/file3.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		CodeGeneratorVisitor codeGenVisitor = new CodeGeneratorVisitor();
		np.accept(codeGenVisitor);
		assertEquals(codeGenVisitor.getCodice(),"3 2 / 1 2 * + 3 2 / 3 * - sA lA p P ");
	}
	
	@Test
	public void test4() throws FileNotFoundException, ParserException {
		String path = "src/test/data/testCodeGenerator/file4.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		SymbolTable.init();
		TypeCheckingVisitor visitor = new TypeCheckingVisitor();
		np.accept(visitor);
		CodeGeneratorVisitor codeGenVisitor = new CodeGeneratorVisitor();
		np.accept(codeGenVisitor);
		assertEquals(codeGenVisitor.getCodice(),"");
		assertEquals(codeGenVisitor.getError(),"Errore. Raggiunto il numero massimo di variabili ff");
	}
}
