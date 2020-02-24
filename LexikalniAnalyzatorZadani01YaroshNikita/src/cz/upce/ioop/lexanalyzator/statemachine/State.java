package cz.upce.ioop.lexanalyzator.statemachine;

/**
 * Seznam stavů lexikálního analyzátoru.
 *
 *
 * @author 
 */
public enum State {

    /**
     * Výchozí stav, ve kterém se zahajuje prvním znakem načítání elementů nebo
     * oddělovačů a operátorů. Podle prvního znaku se rozhodné, zda bude
     * následovat načítání identifikátoru nebo číselných literálů. Operátory a
     * separátory (oddělovače) se dekodují pouze podle prvního znaku. Ostatní
     * elementy se mohou skládat z více znaků.
     *
     * Elementem jsou identifikátory, klíčová slova a číselné literály ve
     * desítkové, oktalové nebo hexadecimální soustavě.
     *
     * Separátory a operátory oddělují a ukončují načítání elementů tj.
     * identifikátoru nebo číselných literálů.
     *
     *
     */
    STATE1(""),
    /**
     * Stav postupně načítá povolené alfanumerické znaky a podtržítko.
     *
     * Při příchodu jiného znaku dojde k ukončení načítání identifikátoru. Potom
     * se zjistí zda se načtený identifikátor není klíčové slovo. Podle výsledku
     * se uloží přislušný token do seznamu tokenů.
     *
     * Po uložení tokenů automat přejde okamžitě do dalšíh stavů, které
     * vyhodnotí jaký oddělovač nebo operátor přerušil načítání.
     *
     * Stav neukládá žádnyý token.
     */
    STATE2(""),
    /**
     * V tomto stavu se rozhoduje v jaké číslné soustavě se bude načítat číselný
     * literál.
     *
     * Tento stav neukládá žádný tokem.
     */
    STATE3(""),
    /**
     * Načítání literálu v hexadecimální soustavě.
     *
     * Stav při ukončení ukládá celočíselný token.
     */
    STATE4(""),
    /**
     * Načítání číselného literálu v oktalové soustavě.
     */
    STATE5(""),
    /**
     * Načítání číselného literálu v desítkové soustavě. Pokud je načítání
     * ukončeno oddělovačem nebo operátorem je převedená hodnota považována za
     * celé číslo. Pokud je načítání ukončeno tečkou je hodnota dál načítána
     * jako reálné číslo s desetinou tečkou. Je-li načítání ukončeno znaky e/E
     * bude dočten exponent a číslo bude zpracováno semilogaritmickém (vědeckém,
     * exponenciálním) tvaru.
     *
     * Stav uloží celočíselný token pokud bylo dekódováno pouze celé číslo,
     * jinak pokračuje načítání čísla s desetinou tečkou nebo v
     * semilogaritmickém tvaru.
     */
    STATE6(""),
    /**
     * Zahájení nebo pokračování načítání desítkového čísla s desetinou tečkou
     * nebo lze přejít čtení exponentu a číslo bude zpracováno semilogaritmickém
     * (vědeckém, exponenciálním) tvaru.
     *
     * Při ukončení stavu nepovoleným znakem za desetinou tečkou dojde k
     * uložení tokenu s reálnou číselnou hodnotou.
     */
    STATE7(""),
    /**
     * Dočtení čísla s desetinou tečkou a uložení tokenu s reálnou číslenou
     * hodnotou.
     */
    STATE8(""),
    /**
     * Zahájení a pokračování čtení hodnoty exponentu čísla v semilogaritmickém
     * (vědeckém, exponenciálním) tvaru. Při ukončení čtení nepovoleným znakem
     * dojde k uložení tokenu s reálnou číslenou hodnotou.
     */
    STATE9(""),
    /**
     * Mezistav, který je inicializován při ukončení čtení elementu nepovoleným
     * znakem v daném elementu. Ve stavu se zjistí, zda se jedna o legální
     * oddělovač nebo operátor a podle toho se okamžitě přejde do příslušného
     * následujího stavu..
     */
    STATE10(""),
    /**
     * Stav v kterém se vyhodnotí znaménko exponentu.
     */
    STATE11(""),
    /**
     * Zahájení a dočtení exponentu. Při ukončení čtení nepovoleným znakem dojde
     * k uložení tokenu s reálnou číslenou hodnotou.
     */
    STATE12(""),
    /**
     * Mezistav, v kterém se přebere a dekoduje oddělovač, který ukončil
     * identifikátor nebo číselný literál.
     *
     * Tento stav může být vyvolán přímo ze inicializačního stavu, když
     * oddělovač násladoval bezprostředně jiný oddělovač nebo operátor.
     *
     * Ten stav ukládá do seznamu tokenů token s dekodovaným oddělovačem.
     */
    STATE13(""),
   /**
     * Mezistav, v kterém se přebere a dekóduje operátor, který ukončil
     * identifikátor nebo číselný literál.
     *
     * Tento stav může být též vyvolán přímo ze inicializačního stavu, když
     * operátor násladoval bezprostředně jiný oddělovač nebo operátor.
     *
     * Ten stav ukládá do seznamu tokenů token s dekodovaným oddělovačem.
     */
    STATE14("");

    private final String name;

    private State(String name) {
        this.name = name;
    }
}
