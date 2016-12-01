/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testensembledelettres;

import ufrim2ag.m2pcci.pl2.tp3.EnsembleDeLettres1;


/**
 *
 * @author thierrye
 */
public class TestEnsembleDeLettres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("azerty");
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("qwerty");
        EnsembleDeLettres1 e3 = new EnsembleDeLettres1("xcvb");
        
        System.out.println(e1.intersection1(e2).toString());
        System.out.println(e1.intersection1(e3).toString());
        System.out.println(e1.union(e2).union(e3).toString());
        
       
        
    }
    
}
