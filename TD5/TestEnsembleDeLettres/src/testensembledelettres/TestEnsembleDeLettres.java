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
        
        char a = 'a';
        char z = 'z';
        
        int iA = (int)a;
        int iZ = (int)z;
        
        System.out.println("char a = " + a + ", iA = " + iA);
        System.out.println("char a = " + z + ", iA = " + iZ);
        
        int indice = (int)Math.round((Math.random() * 25));
        System.out.println(indice);
        
        
        EnsembleDeLettres e4 = new EnsembleDeLettres(true);
        EnsembleDeLettres e5 = new EnsembleDeLettres(false);
        EnsembleDeLettres e6 = new EnsembleDeLettres("abcdy");
        EnsembleDeLettres e7 = new EnsembleDeLettres("cdxyz");
        
        
        e4.afficher();
        System.out.println(e4.cardinal());
        e5.afficher();
        System.out.println(e5.cardinal());
        e6.afficher();
        System.out.println(e6.cardinal());
        e7.afficher();
        System.out.println("l'intersection");
        e6.intersection(e7).afficher();
        System.out.println("l'intersection1");
        e6.intersection1(e7).afficher();
        System.out.println("l'intersection2");
        e6.intersection2(e7).afficher();
        System.out.println("l'union");
        e6.union(e7).afficher();
        System.out.println("la différence");
        e6.difference(e7).afficher();
        System.out.println("inclusion");
        System.out.println(e6.inclus(e7));
        System.out.println("l'union disjointe");
        e6.unionDisjointe(e7).afficher();
        System.out.println("égalité");
        System.out.println(e6.equal(e7));
        System.out.println("e7 avant ajout");
        e7.afficher();
        System.out.println("ajout de 't' à e7");
        e7.ajouterCaractere('t').afficher();
        
        
    }
    
}
