package cz.upce.ioop.lexanalyzator.collection;

import cz.upce.ioop.lexanalyzator.token.Token;

/**
 * Rozhraní jednoduchého seznamu pro tokeny. Do seznamu se pouze tokeny
 * přidávají, zpřistupňují nebo se ruší celý obsah.
 *
 * @author 
 */
public interface ITokenList extends Iterable<Token> {

    /**
     * Metoda vloží token do seznamu. Seznam nemá při vkládání omezen počet
     * prvků.
     *
     * @param token vkládaný token do seznamu
     */
    void add(Token token);

    /**
     * Metoda zruší všechny tokeny v seznamu.
     */
    void clear();

    /**
     * Metoda zpřistupní token v seznamu.
     *
     * @param position pozice prvku v rozsahu 0 až počet tokenu seznamu minus 1
     * @return token na pozici parametru
     * @throws ArrayIndexOutOfBoundsException výjimka při překročení počtu prvků
     * v seznamu
     */
    Token getToken(int position) throws ArrayIndexOutOfBoundsException;

    /**
     * Metoda zjištění kapacity seznamu.
     *
     * @return kapacita seznamu
     */
    int getCapacity();

    /**
     * Metroda zjištění počtu vložených tokenů do seznamu.
     *
     * @return počet tokenů
     */
    int getSize();

}

