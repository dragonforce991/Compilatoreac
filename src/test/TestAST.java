package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import org.junit.Test;

import AST.NodeProgram;
import parser.Parser;
import parser.ParserException;
import scanner.Scanner;
import scanner.ScannerException;

public class TestAST {
	@Test
	public void testfileScannerCorrect1() throws ScannerException, ParserException, IOException{

			String path = "src/test/data/testAST/file1.txt";
			Parser parser = new Parser(new Scanner(path));
			NodeProgram nodeProgram = parser.parse();
			if(nodeProgram == null)
					System.out.println("null");
			String result = nodeProgram.toString();
			assertEquals(result.split("\n")[0],"stampa");
			assertEquals(result.split("\n")[1],"numberfloat FLOATy");
			assertEquals(result.split("\n")[2],"ciao INTy");
			assertEquals(result.split("\n")[3],"floata INTy");
		}	
	
}
