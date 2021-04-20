package token;

public class Token {

	private int riga;
	private TokenType tipo;
	private String val;
	
	public Token(TokenType tipo, int riga, String val) {
		this(tipo,riga);
		this.val = val;
	}
	
	public Token(TokenType tipo, int riga) {
		this.tipo = tipo;
		this.riga = riga;
	}
    
	public String toString() {
		return "<" + this.tipo.toString() + "," +"r:"+this.riga+ (this.val != null ? ","+this.val : "" ) +">";
	}

	public int getRiga() {
		return riga;
	}

	public TokenType getTipo() {
		return tipo;
	}

	public String getVal() {
		return val;
	}
     

}
