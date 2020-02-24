
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
public class TokenDoubleNumberTest {
   
    @Rule
    public TestName testName = new TestName();

  
    static void print(String methodName) {
        System.out.println(String.format("%03d ", counter++) + "TokenDoubleNumber." + methodName);
    }

    static final double DELTA = 1e-9;

 
    
    @Test
    public void test01_01_class() {
         print(testName.getMethodName());
         Token token = new TokenDoubleNumber(123456);
         assertEquals(TokenDoubleNumber.class, token.getClass());
    }
    
     
    @Test
    public void test01_01_value() {
         print(testName.getMethodName());
         TokenDoubleNumber token = new TokenDoubleNumber(123.456);
         assertEquals(123.456, token.getValue(),DELTA);
    }
    
    
}
