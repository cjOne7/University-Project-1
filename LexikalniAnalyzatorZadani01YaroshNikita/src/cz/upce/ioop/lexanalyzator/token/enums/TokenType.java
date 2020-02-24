package cz.upce.ioop.lexanalyzator.token.enums;


public enum TokenType {
    KEY("klíčové slovo"),
    LONG_NUMBER("celé číslo"),
    DOUBLE_NUMBER("reálné číslo"),
    IDENTIFIER("identifikátor"),
    SEPARATOR("oddělovač"),
    OPERATOR("operátor"),
    NONE("");

    private final String nazev;

    private TokenType(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return "Token{" + nazev + '}';
    }

}
