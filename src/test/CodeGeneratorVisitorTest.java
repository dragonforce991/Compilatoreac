package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
		System.out.println(codeGenVisitor.getCodice());
	}
}
