package testing;

import model.Monomial;
import model.Polynomial;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest{

    static Polynomial p1 = new Polynomial();
    static Polynomial p2 = new Polynomial();
    static Polynomial addResult = new Polynomial();
    static Polynomial subResult = new Polynomial();
    static Polynomial mulResult = new Polynomial();
    static Polynomial divQuotient = new Polynomial();
    static Polynomial divRemainder = new Polynomial();
    static Polynomial derResult = new Polynomial();
    static String intgrResult;

    @BeforeAll
    public static void init(){

        //cei doi polinomi pe care se efectueaza operatiile
        //3x^4+2x^3+10x+3x^2+20
        //x^6-13x^4+x
        p1.addMonomial(new Monomial(3, 4));
        p1.addMonomial(new Monomial(2, 3));
        p1.addMonomial(new Monomial(20, 0));

        p2.addMonomial(new Monomial(1, 4));
        p2.addMonomial(new Monomial(-13, 0));

        //pt. adunare 4x^4+2x^3+7
        addResult.addMonomial(new Monomial(4, 4));
        addResult.addMonomial(new Monomial(2, 3));
        addResult.addMonomial(new Monomial(7, 0));

        //pt. scadere 2x^4+2x^3+33
        subResult.addMonomial(new Monomial(2, 4));
        subResult.addMonomial(new Monomial(2, 3));
        subResult.addMonomial(new Monomial(33, 0));


        //pt. inmultire 3x^8+2x^7-19x^4-26x^3-260
        mulResult.addMonomial(new Monomial(3, 8));
        mulResult.addMonomial(new Monomial(2, 7));
        mulResult.addMonomial(new Monomial(-19, 4));
        mulResult.addMonomial(new Monomial(-26, 3));
        mulResult.addMonomial(new Monomial(-260, 0));

        //pt. impartire Cat: 3 Rest: 2x^3+59
        divQuotient.addMonomial(new Monomial(3, 0));
        divRemainder.addMonomial(new Monomial(2, 3));
        divRemainder.addMonomial(new Monomial(59, 0));

        //pt. derivare
        derResult.addMonomial(new Monomial(12, 3));
        derResult.addMonomial(new Monomial(6, 2));

        //pt. integrare 3(x^5/5)+2(x^4/4)+20x
        intgrResult = "+3(x^5/5)+2(x^4/4)+20x";
    }

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(addResult.toString(), p1.add(p2).toString());

    }

    @org.junit.jupiter.api.Test
    void sub() {
        assertEquals(subResult.toString(), p1.sub(p2).toString());
    }

    @org.junit.jupiter.api.Test
    void mul() {
        assertEquals(mulResult.toString(), p1.mul(p2).toString());
    }

    @org.junit.jupiter.api.Test
    void der() {
        assertEquals(derResult.toString(), p1.der().toString());
    }

    @org.junit.jupiter.api.Test
    void intgr() {
        assertEquals(intgrResult, p1.intgr());
    }

    @org.junit.jupiter.api.Test
    void div() {
        assertEquals(divQuotient.toString(), p1.div(p2).get(0).toString());
        assertEquals(divRemainder.toString(), p1.div(p2).get(1).toString());
    }
}