package cz.upce.ioop.lexanalyzator.statemachine;

import cz.upce.ioop.lexanalyzator.collection.ITokenList;
import cz.upce.ioop.lexanalyzator.token.enums.KeyWord;
import static cz.upce.ioop.lexanalyzator.statemachine.State.*;
import cz.upce.ioop.lexanalyzator.token.*;
import cz.upce.ioop.lexanalyzator.token.enums.OperatorEnum;
import cz.upce.ioop.lexanalyzator.token.enums.SeparatorEnum;

/**
 * // TODO Implemenentovat chování jednotlivých stavů podle popisu
 *
 *
 * @author
 */
public class StateMachine implements Machine {

    private State state;
    private final ITokenList tokenList;
// Podle potřeby využít nebo doplnit vlastní následující atributy
    private final StringBuffer identifier;
    private long integerPart;
    private long fractionPart;
    private int exponentSignum;
    private Token token;
    private long hexTolongNumber;
    private long octaToLongNumber;
    private int znamenkoExponentu = 1;
    private double fullNumber;
    private boolean isNumberAfterPoint;
    private boolean isNumberBeforePoint;

    public StateMachine(ITokenList list) {
        tokenList = list;
        state = STATE1;
        identifier = new StringBuffer();
    }

    @Override
    public void execute(char character) {

        switch (state) {

            case STATE1:
                clear();

                if (isLetter(character)) {
                    execute(STATE2, character);
                } else if (character == '0') {
                    state = STATE3;
                } else if (character == '.') {
                    state = STATE7;
                } else if ('1' <= character && character <= '9') {
                    execute(STATE6, character);
                } else if (isSeparator(character)) {
                    execute(STATE13, character);
                } else if (isOperator(character)) {
                    execute(STATE14, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE2:
                if (isDigit(character) || isLetter(character)
                        || (character == '_')) {
                    identifier.append(character);
                } else if (isEndElement(character)) {
                    KeyWord keyWord = findKey(identifier.toString());
                    if (keyWord != null) {
                        token = new TokenKey(keyWord);
                    } else {
                        token = new TokenIdentifier(identifier.toString());
                    }
                    identifier.setLength(0);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE3:
                if (character == 'x' || character == 'X') {
                    state = STATE4;
                } else if ('0' <= character && character <= '7') {
                    execute(STATE5, character);
                } else if (character == '.') {
                    isNumberBeforePoint = true;
                    state = STATE7;
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE4:
                if (isHexLetter(character) || isDigit(character)) {
                    identifier.append(character);
                } else if (isEndElement(character)) {
                    String digits = "0123456789abcdef";
                    String myHexNumber = identifier.toString();
                    myHexNumber = myHexNumber.toLowerCase();

                    for (int i = 0; i < myHexNumber.length(); i++) {
                        int hexDigit = digits.indexOf(myHexNumber.charAt(i));
                        hexTolongNumber = 16 * hexTolongNumber + hexDigit;
                    }
                    token = new TokenLongNumber(hexTolongNumber);
                    identifier.setLength(0);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }

                break;

            case STATE5:
                if (isOctalDigit(character)) {
                    octaToLongNumber = 8 * octaToLongNumber + (character - 48);
                } else if (isEndElement(character)) {
                    token = new TokenLongNumber(octaToLongNumber);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE6:
                if (isDigit(character)) {
                    isNumberBeforePoint = true;
                    integerPart = 10 * integerPart + (character - 48);
                } else if ('.' == character) {
                    state = STATE7;
                } else if (isExponentLetter(character)) {
                    state = STATE9;
                } else if (isEndElement(character)) {
                    token = new TokenLongNumber(integerPart);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE7:
                if (isDigit(character)) {
                    isNumberAfterPoint = true;
                    execute(STATE8, character);
                } else if (isExponentLetter(character)) {
                    state = STATE9;
                } else if (isEndElement(character)) {
                    if (!isNumberAfterPoint && !isNumberBeforePoint) {
                        throw new AnalyzerError("Nepovoleny znak '"
                                + character + "'");
                    }
                    String strFractionPart = "" + fractionPart;
                    fullNumber = fullDoubleNumber(integerPart, strFractionPart);
                    token = new TokenDoubleNumber(fullNumber);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE8:
                if (isDigit(character)) {
                    identifier.append(character);
                } else if (isExponentLetter(character)) {
                    state = STATE9;
                } else if (isEndElement(character)) {
                    String myFractionPart = identifier.toString();

                    fullNumber
                            = fullDoubleNumber(integerPart, myFractionPart);
                    token = new TokenDoubleNumber(fullNumber);
                    identifier.setLength(0);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE9:
                if (character == '+' || character == '-') {
                    execute(STATE11, character);
                } else if (isDigit(character)) {
                    execute(STATE12, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE10:
                if (token != null) {
                    tokenList.add(token);
                }
                if (isSeparator(character)) {
                    execute(STATE13, character);
                } else if (isOperator(character)) {
                    execute(STATE14, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE11:
                if (character == '+') {
                    znamenkoExponentu = 1;
                } else if (character == '-') {
                    znamenkoExponentu = -1;
                } else if (isDigit(character)) {
                    execute(STATE12, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE12:
                if (isDigit(character)) {
                    exponentSignum = exponentSignum * 10 + (character - 48);
                } else if (isEndElement(character)) {
                    String stringExponentSignum = identifier.toString();

                    fullNumber = fullDoubleNumber(integerPart, stringExponentSignum);

                    token = new TokenDoubleNumber(fullNumber * Math.pow(10, znamenkoExponentu * exponentSignum));
                    identifier.setLength(0);
                    execute(STATE10, character);
                } else {
                    throw new AnalyzerError("Nepovoleny znak '"
                            + character + "'");
                }
                break;

            case STATE13:
                switch (character) {
                    case '=':
                        token = new TokenSeparator(SeparatorEnum.EQUALS);
                        break;
                    case ',':
                        token = new TokenSeparator(SeparatorEnum.COMMA);
                        break;
                    case ':':
                        token = new TokenSeparator(SeparatorEnum.COLON);
                        break;
                    case ';':
                        token = new TokenSeparator(SeparatorEnum.SEMICOLON);
                        break;
                    case ' ':
                    case '\t':
                    case '\n':
                    case '\r':
                        token = new TokenSeparator(SeparatorEnum.WHITE_CHAR);
                        break;
                    default:
                        throw new AnalyzerError("Separator");
                }
                tokenList.add(token);
                state = STATE1;
                break;

            case STATE14:
                switch (character) {
                    case '+':
                        token = new TokenOperator(OperatorEnum.PLUS);
                        break;
                    case '-':
                        token = new TokenOperator(OperatorEnum.MINUS);
                        break;
                    case '*':
                        token = new TokenOperator(OperatorEnum.MULTIPLY);
                        break;
                    case '/':
                        token = new TokenOperator(OperatorEnum.DIVIDE);
                        break;
                    default:
                        throw new AnalyzerError("Operator");
                }
                tokenList.add(token);
                state = STATE1;
                break;
        }
    }

    private void clear() {
        identifier.setLength(0);
        integerPart = 0;
        exponentSignum = 0;
        octaToLongNumber = 0;
        hexTolongNumber = 0;
        fractionPart = 0;
        token = null;
    }

    @Override
    public State getState() {
        return state;
    }

    // =========================================================================
    private boolean isEndElement(char character) {
        return isSeparator(character) || isOperator(character);
    }

    private void execute(State state, char character) {
        this.state = state;
        execute(character);
    }

    private KeyWord findKey(String identifier) {
        for (KeyWord key : KeyWord.values()) {
            if (identifier.equals(key.getName())) {
                return key;
            }
        }
        return null;
    }

    private static double fullDoubleNumber(Long intPart, String fractionPart) {
        String str = intPart.toString() + '.' + fractionPart;
        return Double.parseDouble(str);
    }

    private static boolean isLetter(char character) {
        if (character >= 65 && character <= 90) {
            return true;
        }
        if (character >= 97 && character <= 122) {
            return true;
        }
        return false;
    }

    private static boolean isHexLetter(char character) {
        if ((character >= 65 && character <= 70) //A,B,C,D,E,F
                || (character >= 97 && character <= 102)) { //a,b,c,d,e,f
            return true;
        }
        return false;
    }

    private static boolean isDigit(char character) {
        switch (character) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    private static boolean isOctalDigit(char character) {
        switch (character) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
                return true;
            default:
                return false;
        }
    }

    private boolean isSeparator(char character) {
        switch (character) {
            case ' ':
            case '=':
            case ',':
            case ':':
            case ';':
            case '\t':
            case '\n':
            case '\r':
                return true;
            default:
                return false;
        }
    }

    private boolean isOperator(char character) {
        switch (character) {
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
            default:
                return false;
        }
    }

    private boolean isExponentLetter(char character) {
        if (character == 'E' || character == 'e') {
            return true;
        }
        return false;
    }
}
