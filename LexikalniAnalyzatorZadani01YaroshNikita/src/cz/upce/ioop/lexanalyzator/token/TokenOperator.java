package cz.upce.ioop.lexanalyzator.token;

import cz.upce.ioop.lexanalyzator.token.enums.OperatorEnum;
import cz.upce.ioop.lexanalyzator.token.enums.TokenType;

/**
 *
 * @author
 */
public class TokenOperator extends Token {

    //TODO Doplnit atribut, tělo konstruktoru a metod
    private final OperatorEnum operator;

    public TokenOperator(OperatorEnum operator) {
        super(TokenType.OPERATOR);
        this.operator = operator;
    }

    public OperatorEnum getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "TokenOperator{" + "operator=" + operator + super.toString() + '}';
    }

}
