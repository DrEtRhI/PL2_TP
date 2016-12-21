package ufrim2ag.m2pcci.pl2.tp4;

import ufrim2ag.m2pcci.poo.ensembles.EnsembleDeLettres1;

/**
 * Un petit programme pour tester l'utilisation d'ensemble de lettres sur la 
 * console.
 * @author Philippe Genoud - UGA Grenoble Alpes - Lab LIG-Steamer
 */
public class TestEnsembleDeLettres1 {
    
        public static void main(String[] args) {
        // test de creation d'un ensemble vide
        EnsembleDeLettres1 eVide = new EnsembleDeLettres1();
        System.out.println("eVide : " + eVide);  // la même chose que
        // System.out.println("eVide : " + eVide.toString());
        System.out.println("nbre de lettres " + eVide.cardinal());
        System.out.println("est vide " + eVide.estVide());
        System.out.println("contient 'd' " + eVide.contient('d'));

        // tests de création d'un ensemble au hasard
        EnsembleDeLettres1 eHasard = new EnsembleDeLettres1(14);
        System.out.println("eHasard : " + eHasard);
        System.out.println("est vide " + eHasard.estVide());
        System.out.println("nbre de lettres " + eHasard.cardinal());

        // test de creation d'ensembles à  partir de chaines
        EnsembleDeLettres1 eFixe1 = new EnsembleDeLettres1("azerty");
        System.out.println("eFixe1 : " + eFixe1);
        System.out.println("contient 'r' " + eFixe1.contient('r'));
        System.out.println("contient 'w' " + eFixe1.contient('w'));
        System.out.println("nbre de lettres " + eFixe1.cardinal());

        EnsembleDeLettres1 eFixe2 = new EnsembleDeLettres1("aqw1;ea");
        System.out.println("eFixe2 : " + eFixe2);
        System.out.println("nbre de lettres " + eFixe2.cardinal());

        // tests de l'intersection2
        System.out.println("intersection");
        System.out.println(eFixe1 + " avec " + eFixe2 + " : " + eFixe1.intersection2(eFixe2));
        System.out.println(eFixe2 + " avec " + eFixe1 + " : " + eFixe2.intersection2(eFixe1));
        System.out.println(eFixe1 + " avec " + eVide + " : " + eFixe1.intersection2(eVide));
        System.out.println(eVide + " avec " + eFixe1 + " : " + eVide.intersection2(eFixe1));

        // tests de l'union
        System.out.println("union");
        System.out.println(eFixe1 + " avec " + eFixe2 + " : " + eFixe1.union(eFixe2));
        System.out.println(eFixe2 + " avec " + eFixe1 + " : " + eFixe2.union(eFixe1));
        System.out.println(eFixe1 + " avec " + eVide + " : " + eFixe1.union(eVide));
        System.out.println(eVide + " avec " + eFixe1 + " : " + eVide.union(eFixe1));

        // test de la difference
        System.out.println("difference");
        System.out.println(eFixe1 + " avec " + eFixe2 + " : " + eFixe1.difference(eFixe2));
        System.out.println(eFixe2 + " avec " + eFixe1 + " : " + eFixe2.difference(eFixe1));
        System.out.println(eFixe1 + " avec " + eVide + " : " + eFixe1.difference(eVide));
        System.out.println(eVide + " avec " + eFixe1 + " : " + eVide.difference(eFixe1));

        // test union disjointe
        System.out.println("union disjointe");
        System.out.println(eFixe1 + " avec " + eFixe2 + " : " + eFixe1.unionDisjointe(eFixe2));
        System.out.println(eFixe2 + " avec " + eFixe1 + " : " + eFixe2.unionDisjointe(eFixe1));
        System.out.println(eFixe1 + " avec " + eVide + " : " + eFixe1.unionDisjointe(eVide));
        System.out.println(eVide + " avec " + eFixe1 + " : " + eVide.unionDisjointe(eFixe1));

    }
    
}
