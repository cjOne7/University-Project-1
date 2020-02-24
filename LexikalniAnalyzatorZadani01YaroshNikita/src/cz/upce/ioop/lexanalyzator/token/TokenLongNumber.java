package cz.upce.ioop.lexanalyzator.token;

import cz.upce.ioop.lexanalyzator.token.enums.TokenType;

/**
 *
 * @author
 */
public class TokenLongNumber extends Token {

    //TODO Doplnit atribut, tělo konstruktoru a metod
    private final long value;

    public TokenLongNumber(long value) {
        super(TokenType.LONG_NUMBER);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TokenLongNumber{" + "value=" + value + super.toString() + '}';
    }

}
