
package cz.upce.ioop.lexanalyzator.token;


import static cz.upce.ioop.lexanalyzator.Counter.counter;
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
 * @author karel@simerda.cz
 */
public class TokenLongNumberTest {
   
    @Rule
    public TestName testName = new TestName();

  
    static void print(String methodName) {
        System.out.println(String.format("%03d ", counter++) + "TokenLongNumber." + methodName);
    }

    static final double DELTA = 1e-9;

 
    
    @Test
    public void test01_01_class() {
         print(testName.getMethodName());
         Token token = new TokenLongNumber(123456);
         assertEquals(TokenLongNumber.class, token.getClass());
    }
    
     
    @Test
    public void test01_01_value() {
         print(testName.getMethodName());
         TokenLongNumber token = new TokenLongNumber(123456);
         assertEquals(123456, token.getValue());
    }
    
    
}
