package cz.upce.ioop.lexanalyzator.token;

import cz.upce.ioop.lexanalyzator.token.enums.KeyWord;
import cz.upce.ioop.lexanalyzator.token.enums.TokenType;

/**
 *
 * @author
 */
public class TokenKey extends Token {

    //TODO Doplnit atribut, tělo konstruktoru a metod
    private final KeyWord klicoveSlovo;

    public TokenKey(KeyWord key) {
        super(TokenType.KEY);
        this.klicoveSlovo = key;
    }

    public KeyWord getKlicoveSlovo() {
        return klicoveSlovo;
    }

    @Override
    public String toString() {
        return "TokenKey{" + "key=" + klicoveSlovo + super.toString() + '}';
    }

}
