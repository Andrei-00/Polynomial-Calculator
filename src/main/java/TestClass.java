/*import model.*;

import java.util.ArrayList;
import java.util.regex.*;

public class TestClass {

    public static void main(String[] args) {
        System.out.println("Succes!");

        Polynomial polynom1 = new Polynomial();
        Monomial mon = new Monomial(1, 0);
        polynom1.addMonomial(mon);
        mon = new Monomial(1, 2);
        polynom1.addMonomial(mon);
        System.out.println(polynom1);

        //Parser
        System.out.println("Parser");
        Parser parse;
        parse = new Parser("2x^3+4x^2+5x-42"); //1x-2x^3+4x^2+5x-42
        Polynomial polynom2 = new Polynomial();
        polynom2 = parse.parse();
        System.out.println(polynom2);

        //Addition
        System.out.println("Addition");
        Polynomial p = polynom2.add(polynom1);
        System.out.println(p);

        //Subtraction
        System.out.println("Subtraction");
        p = polynom2.sub(polynom1);
        System.out.println(p);

        //Simplification
        //polynom2.simplify();
        //System.out.println(polynom2);

        //Multiplication
        System.out.println("Multiplication");
        p = polynom2.mul(polynom1);
        ArrayList<Monomial> m = p.getPolynoms();
        for(Monomial mo : m){
            System.out.println(mo.getCoeff() + " " + mo.getPow());
        }
        System.out.println(p);

        //Derivative
        System.out.println("Derivative");
        p = p.der();
        System.out.println(p);

        //Integral
        System.out.println("Integral");
        parse.setStr("1x^3+4x^2+5");
        Polynomial polynom3 = parse.parse();
        System.out.println(polynom3);
        System.out.println(polynom3.intgr());

        //division
        System.out.println("Division");
        ArrayList<Polynomial> impartire = new ArrayList<Polynomial>();
        impartire = polynom1.div(polynom2);
        System.out.println(impartire.get(0));
        System.out.println(impartire.get(1));
    }
}
*/