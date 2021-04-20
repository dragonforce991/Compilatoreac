package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import scanner.Scanner;
import token.Token;
import token.TokenType;
import scanner.ScannerException;
public class TestScanner {
	
	@Test
	public void testScanId() throws ScannerException, IOException {
		String path ="src/test/data/testIdKw.txt";
		Scanner scanner = new Scanner(path);
		
		Token t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.TYINT);
		assertEquals(t.getRiga(),1);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.TYFLOAT);
		assertEquals(t.getRiga(),2);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.ID);
		assertEquals(t.getRiga(),2);
		assertEquals(t.getVal(),"floata");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.PRINT);
		assertEquals(t.getRiga(),3);
		assertEquals(t.getVal(),null);

		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.ID);
		assertEquals(t.getRiga(),3);
		assertEquals(t.getVal(),"aprintf");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.ID);
		assertEquals(t.getRiga(),4);
		assertEquals(t.getVal(),"nome");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.ID);
		assertEquals(t.getRiga(),5);
		assertEquals(t.getVal(),"intnome");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.TYINT);
		assertEquals(t.getRiga(),6);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.ID);
		assertEquals(t.getRiga(),6);
		assertEquals(t.getVal(),"nome");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.EOF);
		assertEquals(t.getRiga(),6);
		assertEquals(t.getVal(),null);
		

	}
	
	@Test
	public void testNumbers() throws IOException, ScannerException {
		String path ="src/test/data/testNumbers.txt";
		Scanner scanner = new Scanner(path);
		
		Token t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.INT);
		assertEquals(t.getRiga(),1);
		assertEquals(t.getVal(),"30000");
		
		ScannerException exception = assertThrows(ScannerException.class,()->{
			scanner.nextToken();
		});
		assertEquals(exception.getCause().getMessage(),"Unexpected number at row 3 near 698. Float number must have number after . Do you mean 698?");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.FLOAT);
		assertEquals(t.getRiga(),4);
		assertEquals(t.getVal(),"13.454");
		
		exception = assertThrows(ScannerException.class,()->{
			scanner.nextToken();
		});
		assertEquals(exception.getCause().getMessage(),"Unexpected 0 at row 4 near 098.895");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.INT);
		assertEquals(t.getRiga(),5);
		assertEquals(t.getVal(),"45668");
		
		
		exception = assertThrows(ScannerException.class,()->{
			scanner.nextToken();
		});
		assertEquals(exception.getCause().getMessage(),"Unexpected number at row 6 near 98. Float number must have number after . Do you mean 98?");
		
		exception = assertThrows(ScannerException.class,()->{
			scanner.nextToken();
		});
		assertEquals(exception.getCause().getMessage(),"Unexpected number at row 8 near 89.99999999. Float number can have max 5 number after .");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.EOF);
		assertEquals(t.getRiga(),8);
		assertEquals(t.getVal(),null);

	}
	
	
	@Test
	public void testOperators() throws IOException,ScannerException {
		String path ="src/test/data/testOperators.txt";
		Scanner scanner = new Scanner(path);
		
		Token t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.PLUS);
		assertEquals(t.getRiga(),1);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.MINUS);
		assertEquals(t.getRiga(),2);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.TIMES);
		assertEquals(t.getRiga(),2);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.DIV);
		assertEquals(t.getRiga(),3);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.ASSIGN);
		assertEquals(t.getRiga(),8);
		assertEquals(t.getVal(),null);

		t = scanner.nextToken();
		assertEquals(t.getTipo(),TokenType.SEMI);
		assertEquals(t.getRiga(),10);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.EOF);
		assertEquals(t.getRiga(),10);
		assertEquals(t.getVal(),null);
	}
	
	@Test
	public void testEOF() throws IOException,ScannerException{
		String path = "src/test/data/testEOF.txt";
		Scanner scanner = new Scanner(path);
		Token t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.EOF);
		assertEquals(t.getRiga(), 3);
		assertEquals(t.getVal(),null);
	}
	
	@Test
	public void testIllegalCharacter() throws IOException,ScannerException{
		String path = "src/test/data/testCarattereIllegale.txt";
		Scanner scanner = new Scanner(path);
		
		ScannerException e = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});
		assertEquals(e.getMessage(),"unexpected character _ at row 1");
		
		e = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});
		assertEquals(e.getMessage(),"unexpected character ? at row 1");
		
		e = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});
		assertEquals(e.getMessage(),"unexpected character $ at row 2");
		
		e = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});
		assertEquals(e.getMessage(),"unexpected character % at row 2");
		
		e = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});
		assertEquals(e.getMessage(),"unexpected character & at row 2");
		
		e = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});
		assertEquals(e.getMessage(),"unexpected character \" at row 2");
		
		
		Token t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.EOF);
		assertEquals(t.getRiga(),2);
		assertEquals(t.getVal(),null);
		
		
		
	}
	@Test
	public void testGenerale() throws IOException,ScannerException{
		String path = "src/test/data/testGenerale.txt";
		Scanner scanner = new Scanner(path);
		
		Token t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.TYINT);
		assertEquals(t.getRiga(), 2);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 2);
		assertEquals(t.getVal(),"tempa");

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.SEMI);
		assertEquals(t.getRiga(), 2);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 3);
		assertEquals(t.getVal(),"tempa");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ASSIGN);
		assertEquals(t.getRiga(), 3);
		assertEquals(t.getVal(),null);
		
		ScannerException exception = assertThrows(ScannerException.class, ()->{
			scanner.nextToken();
		});

		assertEquals(exception.getCause().getMessage(), "Unexpected number at row 3 near 5. Float number must have number after . Do you mean 5?");
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.SEMI);
		assertEquals(t.getRiga(), 3);
		assertEquals(t.getVal(),null);
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.TYFLOAT);
		assertEquals(t.getRiga(), 5);
		assertEquals(t.getVal(),null);

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 5);
		assertEquals(t.getVal(),"tempb");

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.SEMI);
		assertEquals(t.getRiga(), 5);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 6);
		assertEquals(t.getVal(),"tempb");

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ASSIGN);
		assertEquals(t.getRiga(), 6);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 6);
		assertEquals(t.getVal(),"tempa");

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.PLUS);
		assertEquals(t.getRiga(), 6);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.FLOAT);
		assertEquals(t.getRiga(), 6);
		assertEquals(t.getVal(),"3.2");
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.SEMI);
		assertEquals(t.getRiga(), 6);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.PRINT);
		assertEquals(t.getRiga(), 7);
		assertEquals(t.getVal(),null);
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 7);
		assertEquals(t.getVal(),"tempb");
		

		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.SEMI);
		assertEquals(t.getRiga(), 7);
		assertEquals(t.getVal(),null);
		
		
		t = scanner.nextToken();
		assertEquals(t.getTipo(), TokenType.EOF);
		assertEquals(t.getRiga(), 7);
		assertEquals(t.getVal(),null);
	}
	
	@Test
	public void testPeekToken() throws FileNotFoundException,IOException, ScannerException {
		String path = "src/test/data/testGenerale.txt";
		Scanner scanner = new Scanner(path);
		for(int i = 0; i<10; i++) {
			Token t = scanner.peekToken();
			assertEquals(t.getTipo(), TokenType.TYINT);
			assertEquals(t.getRiga(), 2);
			assertEquals(t.getVal(),null);
		}	
		scanner.nextToken();
		for(int i = 0; i<10; i++) {

			Token t = scanner.peekToken();
			assertEquals(t.getTipo(), TokenType.ID);
			assertEquals(t.getRiga(), 2);
			assertEquals(t.getVal(),"tempa");
		
		}
		
		scanner.nextToken();
		for(int i = 0; i<10; i++) {

			Token t = scanner.peekToken();
			assertEquals(t.getTipo(), TokenType.SEMI);
			assertEquals(t.getRiga(), 2);
			assertEquals(t.getVal(),null);
		
		}
	}

}