package cz.upce.ioop.lexanalyzator.collection;

import cz.upce.ioop.lexanalyzator.token.Token;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO Implementujte jednoduchý seznam s tokeny pomocí pole. Do pole se pouze
 * přidávají nové položky (tokeny). Odebírají se pouze všechny metodou clear().
 * Při vkládání se pole automaticky rozšiřuje při vyčerpání kapacity a to tak,
 * že se rozšíří na dvojnásobek kapacity.
 *
 * @author kasi
 */
public class TokenList implements ITokenList {

    private Token[] list;
    private int index = -1;
    private int kapacita = 10;

    // TODO Doplňte instanční proměnné a těla metod.
    public TokenList() {
        this.list = new Token[this.kapacita];
    }

    @Override
    public void add(Token token) {
        if (token == null) {
            throw new NullPointerException("Your token is null.");
        }
        if (this.index + 1 == kapacita) {
            Token[] newTokenArray = new Token[this.kapacita*2];
            System.arraycopy(list, 0, newTokenArray, 0, kapacita);
            this.list = newTokenArray;
            kapacita*=2;
        }
        this.list[++index] = token;
    }

    @Override
    public void clear() {
        this.index = -1;
        this.kapacita = 10;
        list = new Token[kapacita];
    }
// TODO Před implementací metod iterátoru nastudujte kontrakt rozhrani Iterator z knihovny.

    @Override
    public Iterator<Token> iterator() {
        return new Iterator() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < index;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return list[i++];
            }
        };
    }

    @Override
    public Token getToken(int position) {
        if (position < 0 || position > this.index){
            throw new ArrayIndexOutOfBoundsException();
        }
        return list[position];
    }

    @Override
    public int getCapacity() {
        return kapacita;
    }

    @Override
    public int getSize() {
        return index + 1;
    }

}