package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import AST.LangType;
import symbolTable.Attributes;
import symbolTable.SymbolTable;

public class SymbolTableTest {

	
	@Test
	public void test1() {
		SymbolTable.init();
		SymbolTable.enter("a", new Attributes(LangType.FLOATy));
		Attributes a = SymbolTable.lookup("a");
		assertEquals(a.getType(),LangType.FLOATy);
		a.setType(LangType.INTy);
		a.setRegistro('a');
		SymbolTable.enter("b", a);
		
		assertEquals(SymbolTable.lookup("b").getType(), LangType.INTy);
		assertEquals(SymbolTable.lookup("b").getRegistro(),'a');
		String [] s = SymbolTable.toStr().split("\n");
		
		assertEquals(s[0], "symbol table");
		assertEquals(s[1], "=============");
		assertEquals(s[2], "a   	INTy   	a");
		assertEquals(s[3], "b   	INTy   	a");
		
		assertEquals(SymbolTable.size(),2);
	}
}
