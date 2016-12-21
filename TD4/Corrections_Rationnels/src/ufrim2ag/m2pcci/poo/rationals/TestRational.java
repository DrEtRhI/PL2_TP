package ufrim2ag.m2pcci.poo.rationals;

/**
 * Programme simple de test de la classe Rational
 * @author Philippe Genoud
 */
public class TestRational {

    public static void main(String[] args) {
        Rational r = new Rational(6, 4);
        System.out.println("r = " + r);
        System.out.println("\tsous la forme réelle, r = " + r.toDouble());

        Rational s = new Rational(2);
        System.out.println("s = " + s);

        r.add(s);
        System.out.println("r + s = " + r);

        Rational t = new Rational(34, 8);
        System.out.println("t = " + t);
        
        System.out.print(s + " x " + t + " = " );
        s.mult(t);
        System.out.println(s);
    }
}
