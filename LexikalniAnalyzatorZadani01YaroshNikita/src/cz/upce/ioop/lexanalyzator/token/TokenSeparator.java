package cz.upce.ioop.lexanalyzator.token;

import cz.upce.ioop.lexanalyzator.token.enums.SeparatorEnum;
import cz.upce.ioop.lexanalyzator.token.enums.TokenType;

/**
 *
 * @author
 */
public class TokenSeparator extends Token {

//TODO Doplnit atribut, tělo konstruktoru a metod
    private final SeparatorEnum sep;

    public TokenSeparator(SeparatorEnum separator) {
        super(TokenType.SEPARATOR);
        this.sep = separator;
    }

    public SeparatorEnum getSep() {
        return sep;
    }

    @Override
    public String toString() {
        return "TokenSeparator{" + "separator=" + sep + super.toString() + '}';
    }

}
