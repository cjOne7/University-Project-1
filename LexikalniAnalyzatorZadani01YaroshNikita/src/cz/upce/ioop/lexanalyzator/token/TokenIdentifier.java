package cz.upce.ioop.lexanalyzator.token;

import cz.upce.ioop.lexanalyzator.token.enums.TokenType;

/**
 *
 * @author 
 */
public class TokenIdentifier extends Token  {

   //TODO Doplnit atribut, tělo konstruktoru a metod

    private final String nazev;

    public TokenIdentifier(String name) {
        super(TokenType.IDENTIFIER);
        this.nazev = name;
    }

    public String getNazev() {
        return nazev;
    }

    @Override
    public String toString() {
        return "TokenIdentifier{" + "name=" + nazev + super.toString() + '}';
    }
    
   

}
