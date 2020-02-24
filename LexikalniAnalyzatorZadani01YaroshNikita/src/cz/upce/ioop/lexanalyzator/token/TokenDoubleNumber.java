package cz.upce.ioop.lexanalyzator.token;

import cz.upce.ioop.lexanalyzator.token.enums.TokenType;

/**
 *
 * @author
 */
public class TokenDoubleNumber extends Token {

//TODO Doplnit atribut, tělo konstruktoru a metod
    private final double value;

    public TokenDoubleNumber(double value) {
        super(TokenType.DOUBLE_NUMBER);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TokenDoubleNumber{" + "value=" + value + super.toString() + '}';
    }

}
