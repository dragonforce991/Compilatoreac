package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import AST.NodeProgram;
import parser.Parser;
import parser.ParserException;
import scanner.Scanner;
import scanner.ScannerException;

public class TestAST {
	@Test
	public void testFile1() throws ScannerException, ParserException, FileNotFoundException{

			String path = "src/test/data/testAST/file1.txt";
			Parser parser = new Parser(new Scanner(path));
			NodeProgram nodeProgram = parser.parse();
			String result = nodeProgram.toString();
			assertEquals(result.split("\n")[0],"Node Program");
			assertEquals(result.split("\n")[1],"NodePrint Node Id stampa");
			assertEquals(result.split("\n")[2],"NodeDecl Node Id numberfloat FLOATy");
			assertEquals(result.split("\n")[3],"NodeDecl Node Id ciao INTy");
			assertEquals(result.split("\n")[4],"NodeDecl Node Id floata INTy");
		}
	
	
	@Test
	public void testFileDec() throws ParserException, ScannerException, FileNotFoundException {
		String path = "src/test/data/testParser/testDec.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		String result = np.toString();
		assertEquals(result.split("\n")[0],"Node Program");
		assertEquals(result.split("\n")[1],"NodeDecl Node Id a INTy");
		assertEquals(result.split("\n")[2],"NodeDecl Node Id b FLOATy");
		assertEquals(result.split("\n")[3],"NodePrint Node Id a");
	}
	@Test
	public void testfileParserCorrect2() throws FileNotFoundException, ParserException {

		String path = "src/test/data/testParser/fileParserCorrect2.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np = parser.parse();
		String result = np.toString();
		assertEquals(result.split("\n")[0],"Node Program");
		assertEquals(result.split("\n")[1],"NodePrint Node Id stampa");
		assertEquals(result.split("\n")[2],"NodeDecl Node Id numberfloat FLOATy");
		assertEquals(result.split("\n")[3],"NodeDecl Node Id floati INTy");
		assertEquals(result.split("\n")[4],"NodeAssign Node Id a NodeBinOp NodeCost INTy 5 PLUS NodeCost INTy 3");
		assertEquals(result.split("\n")[5],"NodeAssign Node Id b NodeBinOp Node Deref Node Id a PLUS NodeCost INTy 5");
	}
	
	@Test
	public void testfileParserCorrect3() throws FileNotFoundException, ParserException{
		
		String path = "src/test/data/testParser/fileParserCorrect3.txt";
		Parser parser = new Parser(new Scanner(path));
		NodeProgram np =	parser.parse();
		String result = np.toString();
		assertEquals(result.split("\n")[0],"Node Program");
		assertEquals(result.split("\n")[1],"NodeDecl Node Id num INTy");
		assertEquals(result.split("\n")[2],"NodeAssign Node Id num NodeCost INTy 5");
		assertEquals(result.split("\n")[3],"NodeAssign Node Id num Node Deref Node Id id");
		assertEquals(result.split("\n")[4],"NodeAssign Node Id num NodeBinOp Node Deref Node Id id PLUS NodeCost FLOATy 5.0");
		assertEquals(result.split("\n")[5],"NodeAssign Node Id num NodeBinOp Node Deref Node Id id TIMES NodeCost INTy 5");
		assertEquals(result.split("\n")[6],"NodeAssign Node Id num NodeBinOp Node Deref Node Id id TIMES Node Deref Node Id id");
		assertEquals(result.split("\n")[7],"NodeAssign Node Id num NodeBinOp NodeBinOp Node Deref Node Id id PLUS NodeCost INTy 5 MINUS NodeBinOp NodeBinOp NodeCost INTy 8 TIMES NodeCost FLOATy 6.0 DIV NodeCost INTy 2");
		assertEquals(result.split("\n")[8],"NodeAssign Node Id num NodeBinOp NodeBinOp NodeBinOp Node Deref Node Id id TIMES NodeCost INTy 5 MINUS NodeBinOp NodeCost FLOATy 8.0 TIMES NodeCost INTy 6 PLUS NodeCost INTy 2");
		assertEquals(result.split("\n")[9],"NodePrint Node Id ok");
		
	}
}
