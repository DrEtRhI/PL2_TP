/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrational;

/**
 *
 * @author thierrye
 */
public class TestRational {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Rational f = new Rational(6, 8);
        Rational f2 = new Rational(7, 3);
        System.out.println(f.toString());
        System.out.println(f2.toString());
        f.mult(f2);
        System.out.println(f.toString());
        f.add(f2);
        System.out.println(f.toString());
    }
    
}
