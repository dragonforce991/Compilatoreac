package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import token.*;

public class Scanner {
	final char EOF = (char) -1; // int 65535
	private int riga;
	private PushbackReader buffer;

	private List<Character> skipChars; // ' ', '\n', '\t', '\r', EOF
	private List<Character> letters; // 'a',...'z'
	private List<Character> numbers; // '0',...'9'
	
	private HashMap<Character, TokenType> operatorsMap;  //'+', '-', '*', '/', '=', ';'
	private HashMap<String, TokenType> keyWordsMap;  //"print", "float", "int"
	private Token currentToken;
	
	public Scanner(String fileName) throws FileNotFoundException {
		this.buffer = new PushbackReader(new FileReader(fileName));
		riga = 1;
		letters = Arrays.asList('a','b','c','d','e','f','g','j','k','h','i','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
		numbers= Arrays.asList('0','1','2','3','4','5','6','7','8','9');
		skipChars= Arrays.asList(' ', '\n', '\t', '\r', EOF);
		inizializzaMap();
	}

	private void inizializzaMap() { 
		operatorsMap = new HashMap<Character, TokenType>();
		operatorsMap.put('+', TokenType.PLUS);
		operatorsMap.put('-', TokenType.MINUS);
		operatorsMap.put('*', TokenType.TIMES);
		operatorsMap.put('/', TokenType.DIV);
		operatorsMap.put('=', TokenType.ASSIGN);
		operatorsMap.put(';', TokenType.SEMI);
		
		keyWordsMap = new HashMap<String, TokenType>();
		keyWordsMap.put("print", TokenType.PRINT);
		keyWordsMap.put("float", TokenType.TYFLOAT);
		keyWordsMap.put("int", TokenType.TYINT);
	}
	public Token peekToken() throws ScannerException { 

		if(this.currentToken == null)
			currentToken = nextToken();
		return currentToken;
		
		
	}
	private boolean isSkipChars(char nextChar) {
		for(Character c : skipChars) {
			if (c == nextChar) {
				if( c == '\n')
					this.riga+=1;
				return true;
			}
		}
		return false;
	}
	
	private boolean isNumber(char nextChar) {
		for (Character c : numbers) {
			if( c == nextChar) 
				return true;
		
		}
		return false;
	}
	private boolean isLetter(char nextChar) {
		for (Character c : letters)
			if( c == nextChar)
				return true;
		return false;
		
	}
	private boolean isOperator(char nextChar) {
		return operatorsMap.containsKey(nextChar);
	}
	public Token nextToken() throws ScannerException {
		try {
			if (currentToken != null) {
				Token tk = currentToken;
				currentToken = null;
				return tk;
			}
			// nextChar contiene il prossimo carattere dell'input.
			char nextChar = peekChar();
			
			// Avanza nel buffer leggendo i carattere in skipChars
			// incrementando riga se leggi '\n'.
			// Se raggiungi la fine del file ritorna il Token EOF
			while(isSkipChars(nextChar)) {
				if(nextChar == EOF) {
					return new Token(TokenType.EOF,this.riga);
				}
					
				nextChar = readChar();
				nextChar = peekChar();
			}
			
			// Se nextChar e' in numbers
			//                return scanNumber()
			// che legge sia un intero che un float e ritorna il Token INUM o FNUM
			// i caratteri che leggete devono essere accumulati in una stringa
			// che verra' assegnata al campo valore del Token
			if(isNumber(nextChar)) {
				return scanNumber();
			}
			
			
	
	
			// Se nextChar e' in letters
			//                return scanId()
			// che legge tutte le lettere minuscole e ritorna un Token ID o
			// il Token associato Parola Chiave (per generare i Token per le
			// parole chiave usate l'HaskMap di corrispondenza
	
			if(isLetter(nextChar)) {
				return scanId();
			}
			
			
			// Se nextChar e' in operators
			// ritorna il Token associato con l'operatore o il delimitatore
			if(isOperator(nextChar)) {
				nextChar = readChar();
				return new Token(operatorsMap.get(nextChar), this.riga);
				 
			}
			// Altrimenti il carattere NON E' UN CARATTERE LEGALE
			readChar();
			throw new ScannerException("unexpected character " + nextChar + " at row " + this.riga);
		}
		catch(IOException e) {
			throw new ScannerException("IOException",e);
		}

		

		

	}
	
	private Token scanNumber() throws ScannerException{
		try {

			String value = "";
			TokenType tokenType;
			while(isNumber(peekChar()) || peekChar() == '.') {
				value = value + readChar();
			}
			if(value.contains(".")) {
				tokenType = TokenType.FLOAT;
				if(value.substring(value.indexOf(".")+1).length() > 5)
					throw new IOException("Unexpected number at row " + riga + " near " +value+". Float number can have max 5 number after .");
				if(value.substring(value.indexOf(".")+1).length() == 0)
					throw new IOException("Unexpected number at row " + riga + " near " +value+" Float number must have number after . Do you mean " + value.substring(0,value.length()-1) +"?");
			}
			else
				tokenType = TokenType.INT;
			if(value.startsWith("0")) {
				throw new IOException("Unexpected 0 at row " + riga + " near " + value);
			}
			return new Token(tokenType,riga,value);
			
		}
		catch(IOException e) {
			throw new ScannerException("IOException",e);
		} 
		
	}
	
	private Token scanId() throws ScannerException{
		try {

			String value = "";
			while(isLetter(peekChar()))
				value = value + readChar();
			if(keyWordsMap.containsKey(value)) {
				return new Token(keyWordsMap.get(value),riga);
				
			}
				
			return new Token(TokenType.ID, riga, value);
			
		}
		catch(IOException e) {
			throw new ScannerException("IOException",e);
		} 
		
	}
	
	// private void inizializza() 

	private char readChar() throws IOException {
		return ((char) this.buffer.read());
	}

	private char peekChar() throws IOException {
		char c = (char) buffer.read();
		buffer.unread(c);
		return c;
	}
}
