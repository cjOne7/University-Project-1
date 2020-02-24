package cz.upce.ioop.lexanalyzator.statemachine;

import cz.upce.ioop.lexanalyzator.token.enums.TokenType;
import cz.upce.ioop.lexanalyzator.token.enums.SeparatorEnum;
import cz.upce.ioop.lexanalyzator.token.enums.KeyWord;
import cz.upce.ioop.lexanalyzator.collection.TokenList;
import cz.upce.ioop.lexanalyzator.token.*;
import static cz.upce.ioop.lexanalyzator.token.enums.KeyWord.*;
import static cz.upce.ioop.lexanalyzator.token.enums.SeparatorEnum.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author kasi0004
 */
public class StateMachineTest {

    @Rule
    public TestName testName = new TestName();

    static void print(String methodName) {
        System.out.println("StateMachineTest." + methodName);
    }
    static final double DELTA = 1e-9;

    Machine machine;
    TokenList tokens;

    public StateMachineTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tokens = new TokenList();
        machine = new StateMachine(tokens);
    }

    @After
    public void tearDown() {
        tokens = null;
        machine = null;
    }

    @Test
    public void test001_01_identifier() {
        print(testName.getMethodName());
        machine.execute("abcdef\n");
        assertEquals(State.STATE1, machine.getState());
    }

    @Test
    public void test001_02_identifier() {
        print(testName.getMethodName());
        machine.execute("abcdef\n");
        assertEquals(TokenType.IDENTIFIER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_03_identifier() {
        print(testName.getMethodName());
        machine.execute("abcdef\n");
        assertEquals("abcdef", ((TokenIdentifier) (tokens.getToken(0))).getNazev());
    }

    @Test
    public void test001_04_identifier() {
        print(testName.getMethodName());
        machine.execute("a123456789z\n");
        assertEquals("a123456789z", ((TokenIdentifier) (tokens.getToken(0))).getNazev());
    }

    @Test
    public void test001_05_identifier() {
        print(testName.getMethodName());
        machine.execute("abcdefghijklmnopqrstuvwxyz\n");
        assertEquals("abcdefghijklmnopqrstuvwxyz", ((TokenIdentifier) (tokens.getToken(0))).getNazev());
    }

    @Test(expected = AnalyzerError.class)
    public void test001_06_identifier() {
        print(testName.getMethodName());
        machine.execute("abc@def\n");
        fail();
    }

    @Test
    public void test001_21_keyWord() {
        print(testName.getMethodName());
        machine.execute("begin\n");
        assertEquals(BEGIN, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_22_keyWord() {
        print(testName.getMethodName());
        machine.execute("end\n");
        assertEquals(END, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_23_keyWord() {
        print(testName.getMethodName());
        machine.execute("for\n");
        assertEquals(FOR, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_24_keyWord() {
        print(testName.getMethodName());
        machine.execute("while\n");
        assertEquals(WHILE, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_25_keyWord() {
        print(testName.getMethodName());
        machine.execute("if\n");
        assertEquals(IF, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_26_keyWord() {
        print(testName.getMethodName());
        machine.execute("then\n");
        assertEquals(THEN, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_27_keyWord() {
        print(testName.getMethodName());
        machine.execute("else\n");
        assertEquals(ELSE, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_28_keyWord() {
        print(testName.getMethodName());
        machine.execute("return\n");
        assertEquals(RETURN, ((TokenKey) tokens.getToken(0)).getKlicoveSlovo());
    }

    @Test
    public void test001_29_keyWord() {
        print(testName.getMethodName());
        KeyWord[] expected = {BEGIN, FOR, WHILE, IF, THEN, ELSE, RETURN, END};
        machine.execute("begin for while if then else return end\n");
        for (int i = 0; i < 8; i++) {
            assertEquals(expected[i], ((TokenKey) tokens.getToken(2 * i)).getKlicoveSlovo());
            assertEquals(TokenType.SEPARATOR, ((TokenSeparator) tokens.getToken(2 * i + 1)).getType());
        }
    }

    @Test
    public void test002_01_octalNumber() {
        print(testName.getMethodName());
        machine.execute("01234567\n");
        assertEquals(01234567, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test002_02_octalNumber() {
        print(testName.getMethodName());
        machine.execute("00\n");
        assertEquals(0, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test002_03_octalNumber() {
        print(testName.getMethodName());
        machine.execute("000007\n");
        assertEquals(7, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test(expected = AnalyzerError.class)
    public void test002_10_octalNumber() {
        print(testName.getMethodName());
        machine.execute("012345678\n");
        fail();
    }

    @Test(expected = AnalyzerError.class)
    public void test002_11_octalNumber() {
        print(testName.getMethodName());
        machine.execute("01a2345678\n");
        fail();
    }

    @Test(expected = AnalyzerError.class)
    public void test002_12_octalNumber() {
        print(testName.getMethodName());
        machine.execute("00008\n");
        fail();
    }

    @Test
    public void test003_01_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0x1234567\n");
        assertEquals(0x1234567, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_02_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0x89\n");
        assertEquals(0x89, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_03_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xabcdef\n");
        assertEquals(0xabcdef, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_04_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xABCDEF\n");
        assertEquals(0xabcdef, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_05_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0x55555555\n");
        assertEquals(0x55555555, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_06_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xAAAAAAAA\n");
        assertEquals(Long.parseUnsignedLong("AAAAAAAA", 16),
                ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_07_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xAAAAAAAA\n");
        assertEquals(0xAAAAAAAAL, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_08_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xAAAAAAAAAAAAAAAA\n");
        assertEquals(Long.parseUnsignedLong("AAAAAAAAAAAAAAAA", 16),
                ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_09_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xFFFFFFFFFFFFFFFF\n");
        assertEquals(-1, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test
    public void test003_10_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0xFFFFFFFFFFFFFFFF\n");
        assertEquals(Long.parseUnsignedLong("FFFFFFFFFFFFFFFF", 16),
                ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test(expected = AnalyzerError.class)
    public void test003_11_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0x1ag45678\n");
        fail();
    }

    @Test(expected = AnalyzerError.class)
    public void test003_12_hexNumber() {
        print(testName.getMethodName());
        machine.execute("0x@@@@\n");
        fail();
    }

    @Test
    public void test004_01_decIntegerNumber() {
        print(testName.getMethodName());
        machine.execute("1234567890\n");
        assertEquals(1234567890, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    public void test004_02_decIntegerNumber() {
        print(testName.getMethodName());
        machine.execute("1234567890\n");
        assertEquals(1234567890, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    public void test004_03_decIntegerNumber() {
        print(testName.getMethodName());
        machine.execute("9223372036854775807\n");
        assertEquals(0x7FFFFFFFFFFFFFFFL, ((TokenLongNumber) (tokens.getToken(0))).getValue());
    }

    @Test(expected = AnalyzerError.class)
    public void test004_10_decIntegerNumber() {
        print(testName.getMethodName());
        machine.execute("01a45678\n");
        fail();
    }

    @Test
    public void test005_01_decRealNumber() {
        print(testName.getMethodName());
        machine.execute("1.2\n");
        assertEquals(1.2, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test005_02_decRealNumber() {
        print(testName.getMethodName());
        machine.execute("0.1234567890\n");
        assertEquals(0.123456789, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test005_03_decRealNumber() {
        print(testName.getMethodName());
        machine.execute("0.0\n");
        assertEquals(0.0, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test005_04_decRealNumber() {
        print(testName.getMethodName());
        machine.execute("0.\n");
        assertEquals(0.0, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test005_05_decRealNumber() {
        print(testName.getMethodName());
        machine.execute("1234567890.\n");
        assertEquals(1234567890.0, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test005_06_decRealNumber() {
        print(testName.getMethodName());
        machine.execute(".0\n");
        assertEquals(.0, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test005_07_decRealNumber() {
        print(testName.getMethodName());
        machine.execute(".0123456789\n");
        assertEquals(.0123456789, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test(expected = AnalyzerError.class)
    public void test005_14_decRealNumber() {
        print(testName.getMethodName());
        machine.execute("00.\n");
        fail();
    }

    @Test(expected = AnalyzerError.class)
    public void test005_15_decRealNumber() {
        print(testName.getMethodName());
        machine.execute(".0.\n");
    }

    @Test(expected = AnalyzerError.class)
    public void test005_16_decRealNumber() {
        print(testName.getMethodName());
        machine.execute(".0a\n");
    }

    @Test(expected = AnalyzerError.class)
    public void test005_17_decRealNumber() {
        print(testName.getMethodName());
        machine.execute(".\n");
    }

    @Test
    public void test006_01_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1E2\n");
        assertEquals(100, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_02_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1E6\n");
        assertEquals(1000000, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_03_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("123456789E6\n");
        assertEquals(123456789000000., ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_04_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1.23456789E6\n");
        assertEquals(1234567.89, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_05_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1.0E6\n");
        assertEquals(1_000_000, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_06_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1.E6\n");
        assertEquals(1.e6, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_07_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1.E-1\n");
        assertEquals(0.1, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test
    public void test006_08_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1.23456789E-6\n");
        assertEquals(0.00000123456789, ((TokenDoubleNumber) (tokens.getToken(0))).getValue(), DELTA);
    }

    @Test(expected = AnalyzerError.class)
    public void test006_10_decExpNumber() {
        print(testName.getMethodName());
        machine.execute("1.2345.6789E-6\n");
        fail();
    }

    @Test
    public void test010_01_Separator() {
        print(testName.getMethodName());
        machine.execute(" \t\n\r,:;=");
        assertEquals(8, tokens.getSize());
    }

    @Test
    public void test010_02_Separator() {
        print(testName.getMethodName());
        SeparatorEnum[] expected = {WHITE_CHAR, WHITE_CHAR, WHITE_CHAR, WHITE_CHAR,
            COMMA, COLON, SEMICOLON, EQUALS};
        machine.execute(" \t\n\r,:;=");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], ((TokenSeparator) tokens.getToken(i)).getSep());
        }

    }

    @Test
    public void test011_01_SeparatorWhite() {
        print(testName.getMethodName());
        machine.execute(" \n\t\r");
        assertEquals(4, tokens.getSize());
    }

    @Test
    public void test011_02_SeparatorWhite() {
        print(testName.getMethodName());
        machine.execute("    ");
        assertEquals(4, tokens.getSize());
    }

    @Test
    public void test011_03_SeparatorWhite() {
        print(testName.getMethodName());
        machine.execute("\n\t\r");
        for (Token token : tokens) {
            assertEquals(TokenType.SEPARATOR, token.getType());
        }
    }

    @Test
    public void test011_04_SeparatorWhite() {
        print(testName.getMethodName());
        machine.execute(" \n\t\r");
        for (Token token : tokens) {
            assertEquals(SeparatorEnum.WHITE_CHAR, ((TokenSeparator) token).getSep());
        }
    }

    @Test
    public void test011_05_SeparatorWhite() {
        print(testName.getMethodName());
        machine.execute("\n\n\n\n");
        for (int i = 0; i < 4; i++) {
            assertEquals(SeparatorEnum.WHITE_CHAR, ((TokenSeparator) tokens.getToken(i)).getSep());
        }
    }

}
