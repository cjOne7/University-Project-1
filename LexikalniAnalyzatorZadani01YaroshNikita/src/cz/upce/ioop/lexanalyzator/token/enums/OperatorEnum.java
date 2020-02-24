package cz.upce.ioop.lexanalyzator.token.enums;

/**
 *
 * @author 
 */
public enum OperatorEnum {
    PLUS("plus"),
    MINUS("minus"),
    MULTIPLY("nasobení"),
    DIVIDE("dělení"),
    NONE("není");

    private final String nazev;

    private OperatorEnum(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return "Separator{" + nazev + '}';
    }

    String getName() {
        return nazev;
    }

}
