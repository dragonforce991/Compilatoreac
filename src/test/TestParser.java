package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import AST.NodeProgram;
import parser.Parser;
import parser.ParserException;
import scanner.Scanner;
import scanner.ScannerException;


public class TestParser {
	@Test
	public void testfileScannerCorrect1() throws ScannerException, ParserException, IOException{
		String path = "src/test/data/testParser/fileScannerCorrect1.txt";
		Parser parser = new Parser(new Scanner(path));
		ParserException exp = assertThrows(ParserException.class, () -> parser.parse());
		assertEquals(exp.getMessage(), "Expected SEMI found ASSIGN at line : 1");
	}
	@Test
	public void testfileParserCorrect2() {
		try {

			String path = "src/test/data/testParser/fileParserCorrect2.txt";
			Parser parser = new Parser(new Scanner(path));
			parser.parse();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}
	@Test
	public void testfileParserCorrect3() {
		try {

			String path = "src/test/data/testParser/fileParserCorrect3.txt";
			Parser parser = new Parser(new Scanner(path));
			parser.parse();
			
		}
		catch(Exception e) {
			System.out.println("messaggio : " + e.getMessage());
			fail(e);
		}
	}
	
	@Test
	public void testfileParserWrong1() throws IOException, ParserException, ScannerException {
			String path = "src/test/data/testParser/fileParserWrong1.txt";
			Parser parser = new Parser(new Scanner(path));
			ParserException exp = assertThrows(ParserException.class, () -> parser.parse());
			assertEquals(exp.getMessage(), "Expected ID found TYFLOAT at line : 2");
			
	}
	
	@Test
	public void testfileParserWrong2() throws IOException, ParserException, ScannerException {
			String path = "src/test/data/testParser/fileParserWrong2.txt";
			Parser parser = new Parser(new Scanner(path));
			ParserException exp = assertThrows(ParserException.class, () -> parser.parse());
			assertEquals(exp.getMessage(), "Expected ID found TYFLOAT at line : 2");
			
	}
	@Test
	public void testfileParserWrong3() throws IOException, ParserException, ScannerException {
	
			String path = "src/test/data/testParser/fileParserWrong3.txt";
			Parser parser = new Parser(new Scanner(path));
			ParserException exp = assertThrows(ParserException.class, () -> parser.parse());
			assertEquals(exp.getMessage(), "Expected ID found ASSIGN at line : 1");
			
	}
	
	@Test
	public void testFileDec() throws ParserException, ScannerException, IOException {
		String path = "src/test/data/testParser/testDec.txt";
		Parser parser = new Parser(new Scanner(path));
		parser.parse();
	}
	
	@Test
	public void testDSsDclStm() throws ParserException, ScannerException, IOException {
		String path = "src/test/data/testParser/testDSsDclStm.txt";
		Parser parser = new Parser(new Scanner(path));
		ParserException exp = assertThrows(ParserException.class,()->parser.parse());
		assertEquals(exp.getMessage(), "Expected ID found TYFLOAT at line : 5");
	}
	
	@Test
	public void testException1() throws ParserException, ScannerException, IOException {
		String path = "src/test/data/testParser/fileException1.txt";
		Parser parser = new Parser(new Scanner(path));
		ParserException exp = assertThrows(ParserException.class,()->parser.parse());
		assertEquals(exp.getMessage(), "Found PLUS at line 1" );
	}
	
	@Test
	public void testException2() throws ParserException, ScannerException, IOException {
		String path = "src/test/data/testParser/fileException2.txt";
		Parser parser = new Parser(new Scanner(path));
		ParserException exp = assertThrows(ParserException.class,()->parser.parse());
		assertEquals(exp.getMessage(), "Found TIMES at line 1" );
	}

	
}

