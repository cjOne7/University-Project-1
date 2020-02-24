package cz.upce.ioop.lexanalyzator.statemachine;

import cz.upce.ioop.lexanalyzator.collection.ITokenList;
import cz.upce.ioop.lexanalyzator.collection.TokenList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LexikalniAnalizatorMain {

    private ITokenList tokenList = new TokenList();
    private Machine machine = new StateMachine(tokenList);

    public static void main(String[] args) {
        LexikalniAnalizatorMain analizator = new LexikalniAnalizatorMain();
        analizator.read();
    }

    public void read() {
        try (BufferedReader bufferedReader
                = new BufferedReader(new FileReader("prikazy.txt"))) {

            int value;
            while ((value = bufferedReader.read()) != -1) {
                machine.execute((char) value);
            }

        } catch (IOException ex) {
            Logger.getLogger(LexikalniAnalizatorMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < tokenList.getSize(); i++) {
            System.out.println(tokenList.getToken(i));
        }
    }

}
